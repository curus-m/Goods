package com.myServer.controller;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "*")
@RequestMapping("/resources")
@ComponentScan
@Controller
public class ResourceController {
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public void resources() throws Exception {


	}
}
