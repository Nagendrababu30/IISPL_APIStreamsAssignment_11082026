package com.iispl.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import com.iispl.dao.ChequeDao;
import com.iispl.dao.ChequeDaoImpl;
import com.iispl.model.Cheque;

public class AdvancedStreamServiceImpl implements AdvancedStreamService {
	
	ChequeDao chequeDao = ChequeDaoImpl.of();
	
	static AdvancedStreamService advancedStreamService = null;
	
	private AdvancedStreamServiceImpl() {
		
	}
	
	public static AdvancedStreamService of() {
		advancedStreamService = new AdvancedStreamServiceImpl();
		
		return advancedStreamService;
	}

	@Override
	public void displayUniqueBatchAndMicr() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayProcessingRecords() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayPageCheques(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getChequesCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void displayMinAndMaxAmount() {
		// TODO Auto-generated method stub

	}

	@Override
	public double getAvgAmount() {
		List<Cheque> cheques = chequeDao.getAllCheques();
		OptionalDouble avgAmount = cheques.stream().mapToDouble(Cheque::getAmount).average();
		double avg_amount = avgAmount.orElse(0.0);
		return avg_amount;
	}


	 
	@Override
	public void getLookUpCheque(String chequeNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getApprovedChequeAsString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<Cheque>> groupByBranch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayBatchRecordCount(Map<String, List<Cheque>> groupedCheques) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayBatchAmountSummary(Map<String, List<Cheque>> groupedCheques) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayBatchStatisticalSummary(Map<String, List<Cheque>> groupedCheques) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayBatchCheques(Map<String, List<Cheque>> groupedCheques) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayFinalizedCtsResult() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayStreamTrace() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Cheque> sortCheques() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayMultiLevelOrder(List<Cheque> chequeList) {
		// TODO Auto-generated method stub

	}

 
}
