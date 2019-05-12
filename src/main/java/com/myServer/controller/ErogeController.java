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
import org.springframework.web.bind.annotation.ResponseBody;

import com.myServer.model.Eroge;
import com.myServer.model.Result;
import com.myServer.service.ErogeService;

@CrossOrigin(origins = "*")
@RequestMapping("/eroge")
@ComponentScan
@Controller
public class ErogeController {
	
	@Resource
	ErogeService erogeService;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public @ResponseBody List<Eroge> erogeList() throws Exception {

		return erogeService.getEroge();
	}
	@RequestMapping(value = "/", method=RequestMethod.POST)
	public @ResponseBody Result addEroge(@RequestBody Eroge eroge) throws Exception {
		Result result = new Result();
		try {
			result.setResult(erogeService.addEroge(eroge));
			result.setErrorMessage("OK");
		}
		catch (Exception e) {
			result.setResult(0);
			result.setErrorMessage(e.getMessage());
		}
		return result;
	}
	
	@DeleteMapping("/{no}")
	public @ResponseBody Result deleteEroge(@PathVariable int no) throws Exception {
		Result result = new Result();
		try {
			result.setResult(erogeService.deleteEroge(no));
			result.setErrorMessage("OK");
		}
		catch (Exception e) {
			result.setResult(0);
			result.setErrorMessage(e.getMessage());
		}
		return result;
	}
	@RequestMapping(value = "/", method=RequestMethod.PUT)
	public @ResponseBody Result editEroge(@RequestBody Eroge eroge) throws Exception {
		Result result = new Result();
		try {
			result.setResult(erogeService.editEroge(eroge));
			result.setErrorMessage("OK");
		}
		catch (Exception e) {
			result.setResult(0);
			result.setErrorMessage(e.getMessage());
		}
		return result;
	}
}
