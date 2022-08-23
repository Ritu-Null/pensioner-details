package com.digitalhonors.pensionerdetail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.digitalhonors.pensionerdetail.model.PensionerDetail;

import com.digitalhonors.pensionerdetail.repository.PensionerRepository;


@Service
public class PensionerService {

	@Autowired
	PensionerRepository pensionerRepository;



	public PensionerDetail saveOrUpdatePensioner(PensionerDetail pensionerDetail) {
		return pensionerRepository.save(pensionerDetail);

	}


	public PensionerDetail searchForValidAadhar(double aadharNumber) {
		return pensionerRepository.findByAadharNumber(aadharNumber);
	}

	public List<PensionerDetail> getAllPensionerDetail() {

		return pensionerRepository.findAll();
	}

}
