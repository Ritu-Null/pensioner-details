package com.digitalhonors.pensionerdetail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalhonors.pensionerdetail.model.PensionerDetail;
import com.digitalhonors.pensionerdetail.service.PensionerService;

@RestController
@RequestMapping("/pensioner")
@CrossOrigin("http://localhost:4200")
public class PensionerController {
	
	private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PensionerController.class);
	
	@Autowired
	PensionerService pensionerService;
	
	@GetMapping("/home")
	public String isRunning(){
		return "Running"; 
	}
	
	
	
	@PostMapping("/insert")
	public ResponseEntity<Object> savePensionerDetails(@RequestBody PensionerDetail pensionerDetail) {
		logger.info("Saving Pensioner data..");
		PensionerDetail isSaved= pensionerService.saveOrUpdatePensioner(pensionerDetail);
		ResponseEntity<Object> entity;
		if(isSaved==null)
			entity=new ResponseEntity<Object>("No Data Available",HttpStatus.NOT_FOUND);
		else
			entity=new ResponseEntity<Object>(isSaved,HttpStatus.OK);
		
		return entity;	
					
	}
	
	
	
	@GetMapping("/isValid")
	public ResponseEntity<Object> searchAadhar(@RequestParam("aadharNumber") double aadharNumber) {
		
		
		PensionerDetail pensionerDetail= pensionerService.searchForValidAadhar(aadharNumber);
		
		ResponseEntity<Object> entity;
		if(pensionerDetail==null) {
			
			entity=new ResponseEntity<Object>("No Data Available",HttpStatus.NOT_FOUND);
		}
		else {
			logger.info(aadharNumber +" Validation complete. Entry found.");
			entity=new ResponseEntity<Object>(pensionerDetail,HttpStatus.OK);
		}
		return entity;	
			
	}
	
	@GetMapping("/pensioners")
	public ResponseEntity<Object> getAllPensioner() {
		logger.info("Fetching all pensioner details.");
		ResponseEntity<Object> entity;
		List<PensionerDetail> pensionerDetail= pensionerService.getAllPensionerDetail();
		
			if(pensionerDetail.isEmpty()) {
				logger.warn("No data available.");
				entity=new ResponseEntity<Object>("No Data Available",HttpStatus.NOT_FOUND);
			}
			else {
				logger.info("Pensioners fetched from database.");
				entity=new ResponseEntity<Object>(pensionerDetail,HttpStatus.OK);
			}
			return entity;	
			
	}
	
	
	
}
