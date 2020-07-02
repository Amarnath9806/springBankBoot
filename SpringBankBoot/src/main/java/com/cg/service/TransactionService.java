package com.cg.service;

import com.cg.model.TransactionDetails;

public interface TransactionService {

	int deposit(int accountNo, int amt);

	int withdraw(int accountNo, int amt);

	int showBalance(int accountNo);

	TransactionDetails fundTransfer(int accountNo, TransactionDetails transaction);

	boolean insertTransaction(TransactionDetails transaction);
	
}
