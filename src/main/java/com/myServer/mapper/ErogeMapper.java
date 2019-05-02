package com.myServer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myServer.model.Eroge;

public class ErogeMapper implements RowMapper<Eroge> {
	@Override
	public Eroge mapRow(ResultSet rs, int arg1) throws SQLException {
		Eroge eroge = new Eroge(); 
		eroge.setNo(rs.getInt(1)); 
		eroge.setGid(rs.getString(2));
		eroge.setTitle(rs.getString(3));	
		eroge.setBrand(rs.getString(4));

		eroge.setPrice(rs.getInt(5));	
		eroge.setReleaseDate(rs.getDate(6));
	    return eroge;
	}
}
