package com.digitalhonors.pensionerdetail.service;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.digitalhonors.pensionerdetail.model.PensionerDetail;
import com.digitalhonors.pensionerdetail.repository.PensionerRepository;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PensionerServiceTest {
	
	@InjectMocks
	 PensionerService pensionerService;
	
	@Mock
	 PensionerRepository mockpensionerRepository;
	
		
	@Test
	void searchForValidAadhar() throws Exception {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	
		PensionerDetail dummyPension=new PensionerDetail(555555555d,"Ritu Rajwar",formatter.parse("1996-01-24"),"ASDPG1234W",500000000d,5000000d,"SELF","HDFC BANK",050505042222d,"PUBLIC");
		
		when(mockpensionerRepository.findByAadharNumber(555555555d)).thenReturn(dummyPension);
		
		when(mockpensionerRepository.findByAadharNumber(anyDouble())).thenReturn(dummyPension);
		
		
	}
	
	@Test
	void saveOrUpdatePensioner() throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		PensionerDetail dummyPensioner=new PensionerDetail(555555555d,"Ritu Rajwar",formatter.parse("1996-01-24"),"ASDPG1234W",500000000d,5000000d,"SELF","HDFC BANK",050505042222d,"PUBLIC");
		when(mockpensionerRepository.save(dummyPensioner)).thenReturn(dummyPensioner);
	}
	
	@Test
	void getAllPensionerDetail() {
		List<PensionerDetail> dummyPensioner=new ArrayList<>();
		when(mockpensionerRepository.findAll()).thenReturn(dummyPensioner);
	}
	
	
	
	
	
	
	


	

}
