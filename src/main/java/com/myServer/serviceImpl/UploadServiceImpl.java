package com.myServer.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.myServer.service.UploadService;
import com.myServer.util.Consts;
import com.myServer.util.Logger;


@Repository
public class UploadServiceImpl implements UploadService {

    
    private String erogeResouceLocation = Consts.erogeResourcePath;
    private String dakimakuraResouceLocation = Consts.dakimakuraResourcePath;
    String filePattern = "yyyyMMddHHmmss";
    Logger logger;
    SimpleDateFormat simpleDateFormat;
    public UploadServiceImpl () {
    	this.logger = new Logger(UploadServiceImpl.class);
    	simpleDateFormat = new SimpleDateFormat(filePattern);
    }
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String storeEroge(MultipartFile file) {
    	Path resourcePath = Paths.get(erogeResouceLocation);   	
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
		Path resourcePath = Paths.get(dakimakuraResouceLocation);   	
    	return storeGoods(file, resourcePath);		
	}

	@Override
	public String storeTapestry(MultipartFile file) {
		Path resourcePath = Paths.get(dakimakuraResouceLocation);   	
    	return storeGoods(file, resourcePath);			
	}
	public String storeGoods(MultipartFile file, Path resourcePath) {
		String filename = simpleDateFormat.format(new Date());
    	String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
    	if (filename.contains("..")) {
    		throw new RuntimeException("Cannot store file with relative path outside current directory " + filename);
    	}    	
    	String storedFile = filename.concat(".").concat(ext);
    	try (InputStream inputStream = file.getInputStream()) {
    		Files.copy(inputStream, resourcePath.resolve(storedFile), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {	
			e.printStackTrace();
		}
    	return storedFile;
	}

}
