package com.cg.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cg.model.CustomerDetails;

@Repository
@Component("bankDao")
public class BankDaoImpl implements BankDao {

	@PersistenceContext
	EntityManager entityManager;

	//public CustomerDetails register(CustomerDetails cd) {

		//entityManager.persist(cd);
		//return cd;
	//}

	public CustomerDetails register(CustomerDetails cd) {
		entityManager.persist(cd);
		Query query = (Query) entityManager.createQuery("select accountNo from CustomerDetails where pancardNo = :pan");
		query.setParameter("pan", cd.getPancardNo());
		List results = query.getResultList();
		cd.setAccountNo(Integer.parseInt(results.get(0).toString()));
		return cd;
	}

	public int login(CustomerDetails c) {
		// TODO Auto-generated method stub
		int accountNo = 0;

		CustomerDetails cd = entityManager.find(CustomerDetails.class, c.getAccountNo());
		if (cd.getPassword().equals(c.getPassword())) {
			accountNo = c.getAccountNo();
		}
		return accountNo;
	}

}
