package com.cg.dao;

import org.springframework.web.bind.annotation.RequestBody;

import com.cg.model.CustomerDetails;

public interface BankDao {
	public CustomerDetails register(@RequestBody CustomerDetails cd);
	public int login(CustomerDetails c);
}
