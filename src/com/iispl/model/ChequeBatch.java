package com.iispl.model;

import java.time.LocalDate;
import java.util.List;

public class ChequeBatch {

	private int batchId;
	private String batchNumber;
	private String branchCode;
	private LocalDate batchDate;
	private String batchStatus;
	private List<Cheque> cheques;

	public ChequeBatch(int batchId, String batchNumber, String branchCode, LocalDate batchDate, String batchStatus,
			List<Cheque> cheques) {
		super();
		this.batchId = batchId;
		this.batchNumber = batchNumber;
		this.branchCode = branchCode;
		this.batchDate = batchDate;
		this.batchStatus = batchStatus;
		this.cheques = cheques;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public LocalDate getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(LocalDate batchDate) {
		this.batchDate = batchDate;
	}

	public String getBatchStatus() {
		return batchStatus;
	}

	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}

	public List<Cheque> getCheques() {
		return cheques;
	}

	public void setCheques(List<Cheque> cheques) {
		this.cheques = cheques;
	}

}
