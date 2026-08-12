package com.iispl.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.iispl.enums.AccountStatus;
import com.iispl.enums.ChequeType;
import com.iispl.enums.MicrStatus;
import com.iispl.enums.ValidationStatus;

public class Cheque {

	private long chequeId;
	private String chequeNumber;
	private String accountNumber;
	private String customerName;
	private String branchCode;
	private String micrCode;
	private BigDecimal amount;
	private BigDecimal availableBalance;
	private LocalDate chequeDate;
	private AccountStatus accountStatus;
	private ChequeType chequeType;
	private MicrStatus micrStatus;
	private ValidationStatus validationStatus;
	private long batchId;

	public Cheque(long chequeId, String chequeNumber, String accountNumber, String customerName, String branchCode,
			String micrCode, BigDecimal amount, BigDecimal availableBalance, LocalDate chequeDate,
			AccountStatus accountStatus, ChequeType chequeType, MicrStatus micrStatus,
			ValidationStatus validationStatus, long batchId) {
		super();
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

	public long getChequeId() {
		return chequeId;
	}

	public void setChequeId(long chequeId) {
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
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

	public long getBatchId() {
		return batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}


}