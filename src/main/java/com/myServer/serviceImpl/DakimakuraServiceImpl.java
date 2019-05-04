package com.myServer.serviceImpl;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.myServer.mapper.DakimakuraMapper;
import com.myServer.model.Dakimakura;
import com.myServer.service.DakimakuraService;

@Repository
public class DakimakuraServiceImpl implements DakimakuraService {

	NamedParameterJdbcTemplate template;
	public DakimakuraServiceImpl(NamedParameterJdbcTemplate template) {
		 this.template = template;
	}
	@Override
	public List<Dakimakura> getDakimakura() throws Exception {
		String query = "select no, gid, title, brand, price, releaseDate from eroge";
		return template.query(query, new DakimakuraMapper());
	}

	@Override
	public int addDakimakura(Dakimakura daki) throws Exception {
		final String query =
				"insert into dakimakura (no, title, brand, price, releaseDate, material, description) "
				+ "values(nextval('goodsseq'),:title, :brand, :price,:releaseDate, :material, :description)";
				
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
        		.addValue("title", daki.getTitle())
        		.addValue("brand", daki.getBrand())
        		.addValue("price", daki.getPrice())
        		.addValue("releaseDate",daki.getReleaseDate())
        		.addValue("material",daki.getMaterial())
        		.addValue("description",daki.getDescription());
		return template.update(query,param, holder);
	}

	@Override
	public int deleteDakimakura(Dakimakura daki) throws Exception {
		final String query =
				"delete from eroge where no == :no";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
        		.addValue("no", daki.getNo());
              
		return template.update(query,param, holder);
	}

}
