package com.iispl.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.iispl.dao.ChequeDao;
import com.iispl.dao.ChequeDaoImpl;
import com.iispl.enums.ValidationStatus;
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
	public List<String> getChequeNumbersByPage(int pageNumber, int pageSize) {
		List<String> chequeList=chequeDao.getAllCheques().stream()
				.map(x->x.getChequeNumber())
				.skip((pageNumber-1)*pageSize)
				.limit(pageSize)
				.collect(Collectors.toList());
		return chequeList;
		

	}

	@Override
	public int getChequesCount() {
		List<Cheque> chequeList=chequeDao.getAllCheques();

		int count=(int)chequeList.stream().count();

		return count;
	}

	@Override
	public void displayMinAndMaxAmount() {
		// TODO Auto-generated method stub

	}

	@Override
	public BigDecimal getAvgAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getLookUpCheque(String chequeNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getApprovedChequeAsString() {
		List<Cheque> cheques = chequeDao.getAllCheques();
		 return cheques.stream()
		            .filter(c -> c.getValidationStatus() == ValidationStatus.APPROVED)
		            .map(Cheque::getChequeNumber)
		            .collect(Collectors.joining(","));
	}

	@Override
	public Map<String, List<Cheque>> groupByBranch() {
		 List<Cheque> cheques = chequeDao.getAllCheques();
		 return cheques.stream()
		            .collect(Collectors.groupingBy(Cheque::getBranchCode));
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
