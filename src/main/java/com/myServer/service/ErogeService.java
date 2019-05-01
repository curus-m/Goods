package com.myServer.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myServer.model.Eroge;

@Repository
public interface ErogeService {

	public List<Eroge> getEroge() throws Exception;
}
