package com.iispl.model;

import java.time.LocalDate;

import com.iispl.enums.AccountStatus;
import com.iispl.enums.ChequeType;
import com.iispl.enums.MicrStatus;
import com.iispl.enums.ValidationStatus;

public class Cheque {

	private int chequeId;
	private String chequeNumber;
	private String accountNumber;
	private String customerName;
	private String branchCode;
	private String micrCode;
	private double amount;
	private double availableBalance;
	private LocalDate chequeDate;
	private AccountStatus accountStatus;
	private ChequeType chequeType;
	private MicrStatus micrStatus;
	private ValidationStatus validationStatus;
	private int batchId;

	public Cheque(int chequeId, String chequeNumber, String accountNumber, String customerName, String branchCode,
			String micrCode, double amount, double availableBalance, LocalDate chequeDate, AccountStatus accountStatus,
			ChequeType chequeType, MicrStatus micrStatus, ValidationStatus validationStatus, int batchId) {
		this.chequeId = chequeId;
		this.chequeNumber = chequeNumber;
		this.accountNumber = accountNumber;
		this.customerName = customerName;
		this.branchCode = branchCode;
		this.micrCode = micrCode;
		this.amount = amount;
		this.availableBalance = availableBalance;
		this.chequeDate = chequeDate;
		this.accountStatus = accountStatus;
		this.chequeType = chequeType;
		this.micrStatus = micrStatus;
		this.validationStatus = validationStatus;
		this.batchId = batchId;
	}

	public int getChequeId() {
		return chequeId;
	}

	public void setChequeId(int chequeId) {
		this.chequeId = chequeId;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getMicrCode() {
		return micrCode;
	}

	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public LocalDate getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(LocalDate chequeDate) {
		this.chequeDate = chequeDate;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public ChequeType getChequeType() {
		return chequeType;
	}

	public void setChequeType(ChequeType chequeType) {
		this.chequeType = chequeType;
	}

	public MicrStatus getMicrStatus() {
		return micrStatus;
	}

	public void setMicrStatus(MicrStatus micrStatus) {
		this.micrStatus = micrStatus;
	}

	public ValidationStatus getValidationStatus() {
		return validationStatus;
	}

	public void setValidationStatus(ValidationStatus validationStatus) {
		this.validationStatus = validationStatus;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

}
