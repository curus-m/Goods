package com.myServer.controller;

import java.util.List;

import javax.annotation.Resource;

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
import com.myServer.util.Logger;

@CrossOrigin(origins = "*")
@RequestMapping("/eroge")
@ComponentScan
@Controller
public class ErogeController {
	
	@Resource
	ErogeService erogeService;
	
	@Resource
    UploadService uploadService;

	Logger logger;
    @Autowired
    public ErogeController(UploadService uploadService) {
        this.uploadService  = uploadService;
        logger = new Logger(ErogeController.class);
    }

	@RequestMapping(value = "/", method=RequestMethod.GET)
	public @ResponseBody List<Eroge> erogeList() throws Exception {

		return erogeService.getEroge();
	}
	@RequestMapping(value = "/", method=RequestMethod.POST)
	public @ResponseBody Result addEroge(@RequestParam("eroge") String erogeString, @RequestParam("file") MultipartFile file) throws Exception {
		Result result = new Result();
		ObjectMapper mapper = new ObjectMapper();
		Eroge eroge = mapper.readValue(erogeString, Eroge.class);
		String filename;
		try {
			//  empty check /  file store
		    if (!file.isEmpty()) {
		    	// get filename
		    	filename = uploadService.storeEroge(file);
		    } else {
		    	filename = "noimage.jpg";
		    }
	        eroge.setImage(filename);
			// save to eroge table
			logger.Log("Title: "+eroge.getTitle());
			logger.Log("Image file: "+eroge.getImage());
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
