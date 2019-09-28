package com.curl.hackathon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DataSetController {
	
	@Autowired
	private DataSetService dataSetService;
	
	private Logger logger = LoggerFactory.getLogger(DataSetController.class); 
	
	@CrossOrigin
	@GetMapping("/viewDataSetList")
	private Object viewFileNames() {
		return dataSetService.viewFileNames();
	}
	
	@CrossOrigin
	@GetMapping("/viewDataSet")
	private String viewData(@RequestParam(required = true) String filename) {
		return dataSetService.viewData(filename);
	}
	
	@CrossOrigin
	@PostMapping("/upload")
	private String upload(@RequestBody String file,@RequestParam(required = true) String filename) {
		return dataSetService.upload(filename,file);
	}

}
