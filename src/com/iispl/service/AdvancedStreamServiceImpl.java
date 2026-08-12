package com.iispl.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
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
		List<Cheque> cheques=chequeDao.getAllCheques();
		
		List<String> uniqueBatchCode =cheques.stream()
				.map(cheque ->cheque.getBranchCode())
				.distinct()
				.toList();
		
		List<String> uniqueMicrCode=cheques.stream()
				.map(cheque ->cheque.getMicrCode())
				.distinct()
				.toList();
		
		long micrCount=cheques.stream()
				.map(cheque ->cheque.getMicrCode())
				.distinct().count();
				//(or 
		//long micrCount=uniqueMicrCode.size();
		
		System.out.println("======== UNIQUE CTS VALUES ========");
		System.out.println("Branches :"+uniqueBatchCode);
		System.out.println("MICR Count : "+micrCount);
		System.out.println("MICR Codes :"+uniqueMicrCode);
				
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
	
	}

	@Override
	public void displayBatchAmountSummary(Map<String, List<Cheque>> groupedCheques) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayBatchStatisticalSummary() {
		
	    List<Cheque> cheques = chequeDao.getAllCheques();
	    
	    Map<String, List<Cheque>> groupedCheques =
	            cheques.stream()
	                   .collect(Collectors.groupingBy(
	                       Cheque::getBranchCode
	                   ));

		for (Map.Entry<String, List<Cheque>> entry
	            : groupedCheques.entrySet()) {

	        String branchCode = entry.getKey();
	        List<Cheque> branchCheques = entry.getValue();

	        DoubleSummaryStatistics statistics =
	                branchCheques.stream()
	                        .collect(
	                            Collectors.summarizingDouble(
	                                cheque -> cheque.getAmount().doubleValue()
	                            )
	                        );

	        System.out.printf(
	            "%s -> Count=%d, Sum=%.2f, Avg=%.2f, Min=%.2f, Max=%.2f%n",
	            branchCode,
	            statistics.getCount(),
	            statistics.getSum(),
	            statistics.getAverage(),
	            statistics.getMin(),
	            statistics.getMax()
	        );
	    }
			
	}
	
	@Override
	public Map<String, List<String>> getBranchChequeNumbers() {

	    Map<String, List<Cheque>> groupedCheques = groupByBranch();

	    return groupedCheques.entrySet()
	            .stream().collect(Collectors.toMap(Map.Entry::getKey,
	                    entry -> entry.getValue()
	                            .stream()
	                            .collect(Collectors.mapping(
	                                    Cheque::getChequeNumber,
	                                    Collectors.toList()
	                            ))
	            ));
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
