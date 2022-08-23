package com.digitalhonors.pensionerdetail.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.digitalhonors.pensionerdetail.exception.InvalidDataFormatException;
import com.digitalhonors.pensionerdetail.helper.CSVHelper;
import com.digitalhonors.pensionerdetail.model.PensionerDetail;
import com.digitalhonors.pensionerdetail.repository.PensionerRepository;

@Service
public class CSVService {
	
 private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CSVService.class);
	
  @Autowired
  PensionerRepository pensionerRepository;
  
  public void save(MultipartFile file) throws FileNotFoundException, IOException, InvalidDataFormatException {
	  	
    try {
      logger.info("Parsing data from csv file.");
      List<PensionerDetail> pensioner= CSVHelper.csvToPensionerDetails(file.getInputStream());
      
      pensionerRepository.saveAll(pensioner);
    } catch (IOException e) {
      logger.error("fail to store csv data: " + e.getMessage());	 
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }
  public List<PensionerDetail> getAllTutorials() {
    return pensionerRepository.findAll();
  }
  
	
}