package com.digitalhonors.pensionerdetail.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name="pensioner_details")

public class PensionerDetail extends BankDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	


	public PensionerDetail( double aadharNumber, String name, Date dob, String pan, double salaryEarned,
			double allowance, String pensionType,String bankName, double accountNumber, String bankType) {
		//super();
		super(bankName, accountNumber, bankType);
		this.aadharNumber = aadharNumber;
		this.name = name;
		this.dob = dob;
		this.pan = pan;
		this.salaryEarned = salaryEarned;
		this.allowance = allowance;
		this.pensionType = pensionType;
		
	}
 

	public PensionerDetail(String name, Date dob, Double salaryEarned, Double allowance,
			String pensionType,String bankName, double accountNumber, String bankType) {
		super(bankName, accountNumber, bankType);
		this.name = name;
		this.dob = dob;
		this.salaryEarned = salaryEarned;
		this.allowance = allowance;
		this.pensionType = pensionType;
	}




//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "pensioner_id", unique = true, nullable = false)
//	private int pensionerId;
	
	@Id
	@Column(name="aadhar_number",unique = true, nullable = false)	
	private double aadharNumber;
	
	@Column(name="name",length=50,nullable = false)	
	private String name;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="date_of_birth",length=24,nullable = false)	
	private Date dob;

	@Column(name="PAN",length=10,unique = true,nullable = false)	
	private String pan;
	 
	@Column(name="salary_earned",nullable = false)
	private Double salaryEarned;
	
	@Column(name="allowance",nullable = false)	
	private Double allowance;
	
	@Column(name="pension_type",length=10,nullable = false)	
	private String pensionType;
}
