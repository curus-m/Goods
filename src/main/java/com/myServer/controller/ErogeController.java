package com.myServer.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "*")
@RequestMapping("/eroge")
@ComponentScan
@Controller
public class ErogeController {
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public @ResponseBody String erogeList() {
			// get all data
			// List<Eroge>
		return "hello!";
	}
}
