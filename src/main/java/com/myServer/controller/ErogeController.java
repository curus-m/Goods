package com.myServer.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.myServer.model.Eroge;
import com.myServer.model.Result;
import com.myServer.service.ErogeService;
import com.myServer.service.UploadService;
import com.myServer.util.Consts;


@CrossOrigin(origins = Consts.originPath)
@RequestMapping("/eroge")
@ComponentScan
@Controller
public class ErogeController {
	
	@Resource
	ErogeService erogeService;
	
	@Resource
    UploadService uploadService;

	Logger logger;
	ObjectMapper mapper;
	
    @Autowired
    public ErogeController(UploadService uploadService) {
        this.uploadService  = uploadService;
        logger = LoggerFactory.getLogger(this.getClass());
        mapper = new ObjectMapper();
    }

	@RequestMapping(value = "/", method=RequestMethod.GET)
	public @ResponseBody List<Eroge> erogeList() throws Exception {
		logger.debug(">>>>>>> Get Eroge");
		return erogeService.getEroge();
	}
	@RequestMapping(value = "/", method=RequestMethod.POST)
	public @ResponseBody Result addEroge(@RequestParam("goods") String erogeString, @RequestParam("file") MultipartFile file) throws Exception {
		Result result = new Result();
		Eroge eroge = mapper.readValue(erogeString, Eroge.class);
		String filename;
		try {
		    if (!file.isEmpty()) {
		    	filename = uploadService.storeEroge(file);
		    } else {
		    	filename = "noimage.jpg";
		    }
	        eroge.setImage(filename);
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
