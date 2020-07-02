package com.cg.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cg.model.CustomerDetails;
import com.cg.model.TransactionDetails;

@Repository
@Component("tDao")
public class TransactionDaoImpl implements TransactionDao {
	@PersistenceContext
	EntityManager entityManager;
	public int deposit(int accountNo, int amt) {
		// TODO Auto-generated method stub
		int amount = 0;
		CustomerDetails cd = entityManager.find(CustomerDetails.class, accountNo);
		amount = cd.getBalance()+amt;
		cd.setBalance(amount);
		Query query = entityManager.createQuery("update CustomerDetails set balance = :balanceAmt where accountNo= :accountNumber");
		query.setParameter("balanceAmt", amount);
		query.setParameter("accountNumber", accountNo);
		query.executeUpdate();
		return amount;
	}
	
	public int withdraw(int accountNo, int amt) {
		// TODO Auto-generated method stub
		int amount = 0;
		CustomerDetails cd = entityManager.find(CustomerDetails.class, accountNo);
		if(cd.getBalance()>amt) {
			amount = cd.getBalance()-amt;
			cd.setBalance(amount);
			Query query = entityManager.createQuery("update CustomerDetails set balance = :balanceAmt where accountNo= :accountNumber");
			query.setParameter("balanceAmt", amount);
			query.setParameter("accountNumber", accountNo);
			query.executeUpdate();
		}
		return amount;
	}
	

	public int showBalance(int accountNo) {
		// TODO Auto-generated method stub
		int amount = 0;
		CustomerDetails cd = entityManager.find(CustomerDetails.class, accountNo);
		amount = cd.getBalance();
		return amount;
	}

	public TransactionDetails fundTransfer(int accountNo, TransactionDetails transaction) {
		// TODO Auto-generated method stub
		int amount = 0;
		TransactionDetails td = null;
		CustomerDetails from = entityManager.find(CustomerDetails.class, accountNo);
		CustomerDetails to = entityManager.find(CustomerDetails.class, transaction.getToAcc());
		if(from.getBalance()>transaction.getAmt()) {
			amount = from.getBalance()-transaction.getAmt();
			from.setBalance(amount);
			int toAmt = to.getBalance()+transaction.getAmt();
			to.setBalance(toAmt);
			Query query = entityManager.createQuery("update CustomerDetails set balance = :balanceAmt where accountNo= :accountNumber");
			query.setParameter("balanceAmt", amount);
			query.setParameter("accountNumber", accountNo);
			query.executeUpdate();
			query = entityManager.createQuery("update CustomerDetails set balance = :balanceAmt where accountNo= :accountNumber");
			query.setParameter("balanceAmt", toAmt);
			query.setParameter("accountNumber", to.getAccountNo());
			query.executeUpdate();
			td = new TransactionDetails();
			td.setFromAcc(accountNo);
			td.setToAcc(transaction.getToAcc());
			td.setAmt(transaction.getAmt());
		}
		return td;
	}

	public boolean insertTransaction(TransactionDetails transaction) {
		// TODO Auto-generated method stub
		boolean isInserted = false;
		if(transaction.getAmt() != 0) {
			entityManager.persist(transaction);
			isInserted = true;
		}
		return isInserted;
	}
}
