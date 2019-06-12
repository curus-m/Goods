package com.myServer.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myServer.model.Eroge;

@Repository
public interface ErogeService {

	public List<Eroge> getEroge() throws Exception;
	public int addEroge(Eroge eroge) throws Exception;
	public int editEroge(Eroge eroge) throws Exception;
	public int deleteEroge(int no) throws Exception;
    public int updateImage(int no, String filename) throws Exception;
    public String getImage(int no) throws Exception;
}
