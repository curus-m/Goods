package com.myServer.serviceImpl;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
		String query = "select no, gid, title, brand, price, releaseDate from eroge";
		
		return template.query(query, new ErogeMapper());
	}
	@Override
	public int addEroge(Eroge eroge) throws Exception {
		final String query =
				"insert into eroge (no, gid, title, brand, price, releaseDate) "
				+ "values(nextval('goodsseq'), :gid, :title, :brand, :price,:releaseDate)";
				
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
        		.addValue("gid", eroge.getGid())
        		.addValue("title", eroge.getTitle())
        		.addValue("brand", eroge.getBrand())
        		.addValue("price", eroge.getPrice())
        		.addValue("releaseDate",eroge.getReleaseDate());
       
		return template.update(query,param, holder);
	}
	@Override
	public int deleteEroge(int no) throws Exception {
		final String query =
				"delete from eroge where no = :no";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
        		.addValue("no", no);
              
		return template.update(query,param, holder);
	}
	@Override
	public int editEroge(Eroge eroge) throws Exception {
		final String query =
				"update eroge set gid = :gid, title = :title, brand = :brand, price = :price,"
				+ " releaseDate = :releaseDate where no = :no";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
        		.addValue("gid", eroge.getGid())
        		.addValue("title", eroge.getTitle())
        		.addValue("brand", eroge.getBrand())
        		.addValue("price", eroge.getPrice())
        		.addValue("releaseDate",eroge.getReleaseDate())
        		.addValue("no",eroge.getNo());
              
		return template.update(query,param, holder);
	}

}
