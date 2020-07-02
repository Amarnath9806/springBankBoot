package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cg.model.CustomerDetails;
import com.cg.model.TransactionDetails;
import com.cg.service.BankService;
import com.cg.service.TransactionService;
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders="*")
@RestController
public class HomeController {
	int accountNo;
	@Autowired
	BankService bank;
	@Autowired
	TransactionService tService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start() {

		return "Hello";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public int register(@RequestBody CustomerDetails customer) {
		bank.register(customer);
		//if (customer != null)
			return customer.getAccountNo();
		//else
			//return 0;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public int login(@RequestBody CustomerDetails c) {
		accountNo = bank.login(c);
		if (accountNo != 0)
			return accountNo;
		else
			return 0;
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.PUT)
	public int deposit(@RequestBody int amt) {
		int amount = 0;
		if (accountNo != 0) {
			amount = tService.deposit(accountNo, amt);
		}
		return amount;
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.PUT)
	public int withdraw(@RequestBody int amt) {
		int amount = 0;
		if (accountNo != 0) {
			amount = tService.withdraw(accountNo, amt);
		}

		return amount;

	}

	@RequestMapping(value = "/showbalance", method = RequestMethod.GET)
	public int showBalance() {
		int amount = 0;
		if (accountNo != 0) {
			amount = tService.showBalance(accountNo);
		}
		return amount;

	}

	@RequestMapping(value = "/fundtransfer", method = RequestMethod.PUT)
	public int fundTransfer(@RequestBody TransactionDetails transaction) {
		int amount = 0;
		if (accountNo != 0) {
			transaction = tService.fundTransfer(accountNo, transaction);
			tService.insertTransaction(transaction);
		}

		return transaction.getTransactionId();
	}

}
