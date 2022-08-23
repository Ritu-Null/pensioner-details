package com.digitalhonors.pensionerdetail.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class BankDetail {

	@Column(name="bank_name",length=24,nullable = false)
	private String bankName;
	
	@Column(name="account_number",length=16,nullable = false)
	private double accountNumber;
	
	@Column(name="bank_type",length=15,nullable = false)
	private String bankType;
}
