package com.digitalhonors.pensionerdetail.service;

import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.supercsv.io.CsvBeanReader;

import com.digitalhonors.pensionerdetail.exception.InvalidDataFormatException;
import com.digitalhonors.pensionerdetail.helper.CSVHelper;
import com.digitalhonors.pensionerdetail.model.PensionerDetail;
import com.digitalhonors.pensionerdetail.repository.PensionerRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CSVServiceTest {
	
	@InjectMocks
	CSVService csvService;
	
	@Mock
	CSVHelper mockCsvHelper;
	
	@Mock
	CsvBeanReader mockCsvBeanReader;
	
	@Mock
	PensionerRepository mockPensionerRepo;
	
	@Test
	void csvToPensionerDetails() throws FileNotFoundException, IOException, InvalidDataFormatException {
		
		PensionerDetail dummyPensioner1=new PensionerDetail();
		PensionerDetail dummyPensioner2=new PensionerDetail();
		
		when(mockCsvBeanReader.getHeader(true)).thenReturn(new String[] {"Aadhar Number","Name","DOB","PAN","Salary Earned","Allowance","Pension Type",
				"Bank Name"," Account Number","Bank Type"});
		
		when(mockCsvBeanReader.read(PensionerDetail.class))
	    .thenReturn(dummyPensioner1)
	    .thenReturn(dummyPensioner2);
	
	}
	
	@Test
	void uploadFile() {
		List<PensionerDetail> dummyPensioner=new ArrayList<>();
		when(mockPensionerRepo.saveAll(dummyPensioner)).thenReturn(dummyPensioner);
	}

	
}
