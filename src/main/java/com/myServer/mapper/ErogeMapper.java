package com.myServer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myServer.model.Eroge;

public class ErogeMapper implements RowMapper<Eroge> {
	@Override
	public Eroge mapRow(ResultSet rs, int arg1) throws SQLException {
		Eroge eroge = new Eroge();
		eroge.setId(rs.getString(1));
		eroge.setTitle(rs.getString(2));	
		eroge.setBrand(rs.getString(3));
//		eroge.setReleaseDate(rs.getDate(3));	
		eroge.setPrice(rs.getInt(4));	
		
	    return eroge;
	}
}
