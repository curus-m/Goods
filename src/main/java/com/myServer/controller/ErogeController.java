package com.myServer.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myServer.model.Eroge;
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
}
