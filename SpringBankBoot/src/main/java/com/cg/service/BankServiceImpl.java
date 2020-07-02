package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.BankDao;
import com.cg.model.CustomerDetails;

@Transactional
@Service
@Component("bank")
public class BankServiceImpl implements BankService {

	@Autowired
	BankDao bankDao;
	public CustomerDetails register(CustomerDetails cd) {
		// TODO Auto-generated method stub
		return bankDao.register(cd);
	}
	public int login(CustomerDetails c) {
		// TODO Auto-generated method stub
		return bankDao.login(c);
	}


}
