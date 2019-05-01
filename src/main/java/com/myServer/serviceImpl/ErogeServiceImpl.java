package com.myServer.serviceImpl;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.myServer.mapper.ErogeMapper;
import com.myServer.model.Eroge;
import com.myServer.service.ErogeService;

@Repository
public class ErogeServiceImpl implements ErogeService {
	
	NamedParameterJdbcTemplate template;  
	public ErogeServiceImpl (NamedParameterJdbcTemplate template) {  
        this.template = template;
	}
	@Override
	public List<Eroge> getEroge() throws Exception {
		String query = "select id, title, brand, price from eroge";
		
		return template.query(query, new ErogeMapper());
	}

}
