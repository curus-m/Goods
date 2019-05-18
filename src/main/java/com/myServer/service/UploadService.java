package com.myServer.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface UploadService {

	public void init();
    public void store(MultipartFile file);
    public Stream<Path> loadAll();
    public Path load(String filename);
    public Resource loadAsResource(String filename);
    public void deleteAll();
}
