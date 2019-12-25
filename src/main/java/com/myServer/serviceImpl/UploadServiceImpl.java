package com.myServer.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.myServer.service.UploadService;
import com.myServer.util.AwsService;
import com.myServer.util.Consts;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketConfiguration;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


@Repository
public class UploadServiceImpl implements UploadService {

    
    private String localResourcePath = Consts.localResourcePath;
    private String awsResourcePath = Consts.awsResourcePath;
    String filePattern = "yyyyMMddHHmmss";
    Logger logger;
    SimpleDateFormat simpleDateFormat;
    boolean isLocal;
    @Value("${aws.accessKeyId}")
    private String awsAccessKey;
    @Value("${aws.secretKey}")
    private String awsSecretKey;
    
    public UploadServiceImpl () {
    	this.logger = LoggerFactory.getLogger(this.getClass());
    	simpleDateFormat = new SimpleDateFormat(filePattern);
    	this.isLocal = Consts.appPath.contains("WebProject\\Goods");
    }
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String storeEroge(MultipartFile file) {
		Path resourcePath = null;
		if(this.isLocal) {
			resourcePath = Paths.get(this.localResourcePath+Consts.Resource_Eroge);
		}
		else {
			resourcePath = Paths.get(this.awsResourcePath+Consts.Resource_Eroge);
		}
    	   	
    	return storeGoods(file, resourcePath);
	}

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path load(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource loadAsResource(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String storeDakimakura(MultipartFile file) {
		Path resourcePath = null;
		if(this.isLocal) {
			resourcePath = Paths.get(this.localResourcePath+Consts.Resource_Dakimakura);
		}
		else {
			resourcePath = Paths.get(this.awsResourcePath+Consts.Resource_Dakimakura);
		} 	
    	return storeGoods(file, resourcePath);		
	}

	@Override
	public String storeTapestry(MultipartFile file) {
		Path resourcePath = null;
		if(this.isLocal) {
			resourcePath = Paths.get(this.localResourcePath+Consts.Resource_Tapestry);
		}
		else {
			resourcePath = Paths.get(this.awsResourcePath+Consts.Resource_Tapestry);
		}   	
    	return storeGoods(file, resourcePath);			
	}
	public String storeGoods(MultipartFile file, Path resourcePath) {
		String filename = simpleDateFormat.format(new Date());
    	String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
    	if (filename.contains("..")) {
    		throw new RuntimeException("Cannot store file with relative path outside current directory " + filename);
    	}    	
    	String storedFile = filename.concat(".").concat(ext);
		// define target type
		if(isLocal) {
			saveFileToLocal(resourcePath.resolve(storedFile), file);
		} else {
			saveFileToAws(resourcePath.resolve(storedFile), file);
		}
		
    	return storedFile;
	}
	public void saveFileToLocal(Path path, MultipartFile file) {
		// save to local 
    	try (InputStream inputStream = file.getInputStream()) {
    		Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {	
			e.printStackTrace();
			this.logger.error("local file save error!" + e);
		}
    	
		
	}
	@Override
	public void saveFileToAws(Path path, MultipartFile file) {
		// save to aws s3
		String key = "resources/dakimakura/"+path.getFileName();
		Region region = Region.AP_NORTHEAST_1;
		S3Client s3 = S3Client.builder().region(region).build();

		String bucketName = "goods-resources";

		ByteBuffer fileBytes;
		try {
			fileBytes = ByteBuffer.wrap(file.getBytes());
			s3.putObject(PutObjectRequest.builder().bucket(bucketName).key(key)
                    .build(),
             RequestBody.fromByteBuffer(fileBytes));
		
			this.logger.trace("save to : " +bucketName + ", " + key + " via aws s3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.logger.error("aws error: " + e);
		}
		
		s3.close();
		
	}
	public void deleteFile(String filename) {
		String filePath = null;
		if(isLocal) {
			filePath = localResourcePath.concat(filename);	
		}
		
		File file = new File(filePath);
		if (file.canRead())
		{ file.delete(); }
	}
}
