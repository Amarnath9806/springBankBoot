package com.cg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Transaction")
public class TransactionDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
	private int transactionId;
	private int fromAcc;
	private int toAcc;
	private int amt;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getFromAcc() {
		return fromAcc;
	}
	public void setFromAcc(int fromAcc) {
		this.fromAcc = fromAcc;
	}
	public int getToAcc() {
		return toAcc;
	}
	public void setToAcc(int toAcc) {
		this.toAcc = toAcc;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	public TransactionDetails() {
		
	}
	
}
