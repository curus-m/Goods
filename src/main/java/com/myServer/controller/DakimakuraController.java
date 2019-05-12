package com.myServer.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myServer.model.Dakimakura;
import com.myServer.model.Result;
import com.myServer.service.DakimakuraService;


@CrossOrigin(origins = "*")
@RequestMapping("/dakimakura")
@ComponentScan
@Controller
public class DakimakuraController {
	
	@Resource
	DakimakuraService dakimakuraService;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public @ResponseBody List<Dakimakura> dakimakuraList() throws Exception {
		List<Dakimakura> list = dakimakuraService.getDakimakura(); 
		return list;
	}
	@RequestMapping(value = "/", method=RequestMethod.POST)
	public @ResponseBody Result addDakimakura(@RequestBody Dakimakura daki) throws Exception {
		Result result = new Result();
		try {
			result.setResult(dakimakuraService.addDakimakura(daki));
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
