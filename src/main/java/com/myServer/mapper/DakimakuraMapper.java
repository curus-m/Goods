package com.myServer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myServer.model.Dakimakura;

public class DakimakuraMapper  implements RowMapper<Dakimakura>{

	@Override
	public Dakimakura mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dakimakura daki = new Dakimakura(); 
		daki.setNo(rs.getInt(1)); 
		daki.setTitle(rs.getString(2));	
		daki.setBrand(rs.getString(3));
		daki.setPrice(rs.getInt(4));	
		daki.setReleaseDate(rs.getDate(5));
		daki.setMaterial(rs.getInt(6));
		daki.setDescription(rs.getString(7));
		daki.setImage(rs.getString(8));
	    return daki;
	}

}
