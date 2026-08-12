package com.iispl.service;

import java.util.List;
import java.util.Map;

import com.iispl.dto.BranchMicrResult;
import com.iispl.model.Cheque;

public interface AdvancedStreamService {

	public BranchMicrResult getUniqueBranchAndMicr();
	
	public List<String> getTopFiveAmountCheques();
	
	public List<String> getChequeNumbersByPage(int pageNumber, int pageSize);
	
	public long getChequesCount();
	
	public Map<String, Double> getMinAndMaxAmount();
	
	public  double getAvgAmount();
	
	public  Cheque getLookUpCheque(String chequeNumber);
	
	public String getApprovedChequeAsString();
	
	public Map<String, List<Cheque>> groupByBranch();
	
	public Map<String, Long> groupByBranchChequeCount();
	
	public void displayBranchAmountSummary();
	
	public void displayBranchStatisticalSummary();
	
	public Map<String, List<String>> getBranchChequeNumbers();
	
	public void displayFinalizedCtsResult();
	
	public void displayStreamTrace();

	public List<String> sortCheques();
	
	public void displayMultiLevelOrder();
}
