package com.myServer.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myServer.model.Dakimakura;

@Repository
public interface DakimakuraService {
	public List<Dakimakura> getDakimakura() throws Exception;
	public int addDakimakura(Dakimakura daki) throws Exception;
	public int editDakimakura(Dakimakura daki) throws Exception;
	public int deleteDakimakura(int no) throws Exception;
	public int updateDakimakuraImage(int no, String filename) throws Exception;
	public int updateImage(int no, String filename) throws Exception;
    public String getImage(int no) throws Exception;
}
