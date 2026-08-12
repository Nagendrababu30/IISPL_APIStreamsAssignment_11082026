package com.iispl.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
		
      
     
    	 
	Optional<Cheque> highest = chequeDao.getAllCheques().stream().max(Comparator.comparing(Cheque:: getAmount));
	Optional<Cheque> lowest = chequeDao.getAllCheques().stream().min(Comparator.comparing(Cheque:: getAmount));
	LinkedHashMap<String, Double> map = new LinkedHashMap<>();

	highest.map(c -> {
	    map.put(c.getChequeNumber(), c.getAmount());
	    return c;
	});

	lowest.map(c -> {
	    map.put(c.getChequeNumber(), c.getAmount());
	    return c;
	});
	
	 
	 System.out.println( map.firstEntry());
	 System.out.println(map.lastEntry());
 
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
