package com.digitalhonors.pensionerdetail.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalhonors.pensionerdetail.model.PensionerDetail;

public interface PensionerRepository extends JpaRepository<PensionerDetail, Long> {
	

   PensionerDetail findByAadharNumber(double aadharNumber);

   
}
