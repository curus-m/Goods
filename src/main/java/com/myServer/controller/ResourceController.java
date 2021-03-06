package com.myServer.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myServer.model.Eroge;
import com.myServer.model.Result;
import com.myServer.service.DakimakuraService;
import com.myServer.service.ErogeService;
import com.myServer.service.UploadService;
import com.myServer.util.Consts;


@CrossOrigin(origins = Consts.originPath)
@RequestMapping("/resources")
@ComponentScan
@Controller
public class ResourceController {
	
	@Resource
    UploadService uploadService;

	@Resource
	ErogeService erogeService;
	@Resource
	DakimakuraService dakimakuraService;
	
	Logger logger;
	
	public ResourceController() {
        logger = LoggerFactory.getLogger(this.getClass());
	}
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public void resources() throws Exception {


	}
	@RequestMapping(value = "/eroge", method=RequestMethod.PUT)
	public @ResponseBody Result setErogeResource(@RequestParam("no") int no, @RequestParam("file") MultipartFile file) throws Exception {
		Result result = new Result();
		String filename = "";
		try {
		    if (!file.isEmpty()) { // update
		    	filename = erogeService.getImage(no); // erase old file
		    	uploadService.deleteFile(filename);
		    	filename = uploadService.storeEroge(file);
		    } else {
		    	filename = "noimage.jpg";
		    	erogeService.updateImage(no, filename);
		    }
	        erogeService.updateImage(no, filename);
			result.setResult(1);
			result.setErrorMessage("OK");
		}
		catch (Exception e) {
			result.setResult(0);
			result.setErrorMessage(e.getMessage());
		}
		return result;
	}
	@RequestMapping(value = "/dakimakura", method=RequestMethod.PUT)
	public @ResponseBody Result setDakimakuraResource(@RequestParam("no") int no, @RequestParam("file") MultipartFile file) throws Exception {
		Result result = new Result();
		String filename = "";
		try {
		    if (!file.isEmpty()) { // update
		    	filename = dakimakuraService.getImage(no); // erase old file
		    	uploadService.deleteFile(filename);
		    	filename = uploadService.storeDakimakura(file);
		    } else {
		    	filename = "noimage.jpg";
		    	dakimakuraService.updateImage(no, filename);
		    }
	        dakimakuraService.updateImage(no, filename);
			result.setResult(1);
			result.setErrorMessage("OK");
		}
		catch (Exception e) {
			result.setResult(0);
			result.setErrorMessage(e.getMessage());
		}
		return result;
	}
}
