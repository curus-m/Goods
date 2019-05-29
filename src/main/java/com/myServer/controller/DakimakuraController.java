package com.myServer.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myServer.model.Dakimakura;
import com.myServer.model.Result;
import com.myServer.service.DakimakuraService;
import com.myServer.service.UploadService;


@CrossOrigin(origins = "*")
@RequestMapping("/dakimakura")
@ComponentScan
@Controller
public class DakimakuraController {
	
	@Resource
	DakimakuraService dakimakuraService;

	@Resource
    UploadService uploadService;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public @ResponseBody List<Dakimakura> dakimakuraList() throws Exception {
		List<Dakimakura> list = dakimakuraService.getDakimakura(); 
		return list;
	}
	@RequestMapping(value = "/", method=RequestMethod.POST)
	public @ResponseBody Result addDakimakura(@RequestParam("goods") String dakimakuraString, @RequestParam("file") MultipartFile file) throws Exception {
		Result result = new Result();
		ObjectMapper mapper = new ObjectMapper();
		Dakimakura dakimakura = mapper.readValue(dakimakuraString, Dakimakura.class);
		String filename;
		try {
		    if (!file.isEmpty()) {
		    	filename = uploadService.storeDakimakura(file);
		    } else {
		    	filename = "noimage.jpg";
		    }
		    dakimakura.setImage(filename);
			result.setResult(dakimakuraService.addDakimakura(dakimakura));
			result.setErrorMessage("OK");
		}
		catch (Exception e) {
			result.setResult(0);
			result.setErrorMessage(e.getMessage());
		}
		return result;
	}
	@RequestMapping(value = "/", method=RequestMethod.PUT)
	public @ResponseBody Result editDakimakura(@RequestBody Dakimakura dakimakura) throws Exception {
		Result result = new Result();
		try {
			result.setResult(dakimakuraService.editDakimakura(dakimakura));
			result.setErrorMessage("OK");
		}
		catch (Exception e) {
			result.setResult(0);
			result.setErrorMessage(e.getMessage());
		}
		return result;
	}
	@DeleteMapping("/{no}")
	public @ResponseBody Result deleteDakimakura(@PathVariable int no) throws Exception {
		Result result = new Result();
		try {
			result.setResult(dakimakuraService.deleteDakimakura(no));
			result.setErrorMessage("OK");
		}
		catch (Exception e) {
			result.setResult(0);
			result.setErrorMessage(e.getMessage());
		}
		return result;
	}
	
}
