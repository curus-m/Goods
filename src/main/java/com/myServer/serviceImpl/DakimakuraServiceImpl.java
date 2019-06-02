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
import com.myServer.model.Eroge;
import com.myServer.service.DakimakuraService;

@Repository
public class DakimakuraServiceImpl implements DakimakuraService {

	NamedParameterJdbcTemplate template;
	public DakimakuraServiceImpl(NamedParameterJdbcTemplate template) {
		 this.template = template;
	}
	
	public List<Dakimakura> getDakimakura() throws Exception {
		String query = "select no, title, brand, price, releaseDate, material, description, image from dakimakura";
		return template.query(query, new DakimakuraMapper());
	}

	@Override
	public int addDakimakura(Dakimakura daki) throws Exception {
		final String query =
				"insert into dakimakura (no, title, brand, price, releaseDate, material, image, description) "
				+ "values(nextval('goodsseq'),:title, :brand, :price,:releaseDate, :material, :image, :description)";
				
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
        		.addValue("title", daki.getTitle())
        		.addValue("brand", daki.getBrand())
        		.addValue("price", daki.getPrice())
        		.addValue("releaseDate",daki.getReleaseDate())
        		.addValue("material",daki.getMaterial())
        		.addValue("image",daki.getImage())
        		.addValue("description",daki.getDescription());
		return template.update(query,param, holder);
	}

	@Override
	public int deleteDakimakura(int no) throws Exception {
		final String query =
				"delete from dakimakura where no = :no";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
        		.addValue("no", no);
              
		return template.update(query,param, holder);
	}

	@Override
	public int editDakimakura(Dakimakura daki) throws Exception {
		final String query =
				"update dakimakura set title = :title, brand = :brand, price = :price, material = :material"
				+ " releaseDate = :releaseDate where no = :no";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
        		.addValue("title", daki.getTitle())
        		.addValue("brand", daki.getBrand())
        		.addValue("price", daki.getPrice())
        		.addValue("material", daki.getMaterial())
        		.addValue("releaseDate",daki.getReleaseDate())
        		.addValue("no",daki.getNo());
              
		return template.update(query,param, holder);
	}

	@Override
	public int updateDakimakuraImage(String no, String image) throws Exception {
		final String query =
				"update dakimakura set image = :image where no = :no";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
        		.addValue("image",image)
        		.addValue("no",no);
              
		return template.update(query,param, holder);
	}
	@Override
	public int updateImage(String no, String image) throws Exception {
		final String query =
				"update dakimakura set image = :image where no = :no";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
        		.addValue("image",image)
        		.addValue("no",no);
              
		return template.update(query,param, holder);
		
	}
	@Override
	public String getImage(String no) throws Exception {
		final String query =
				"select image from dakimakura where no = :no";
        SqlParameterSource param = new MapSqlParameterSource()
        		.addValue("no",no);
		return template.queryForObject(query, param, String.class);
	}

}
