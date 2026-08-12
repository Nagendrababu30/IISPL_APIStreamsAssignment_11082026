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
	public void displayBranchAmountSummary() {
		Map<String, Double> branchTotalAmount =
		        chequeDao.getAllCheques().stream()
		                .collect(Collectors.groupingBy(
		                        Cheque::getBranchCode,
		                        Collectors.summingDouble(
		                                cheque -> cheque.getAmount().doubleValue())
		                ));
		
		Map<String, Double> branchAverageAmount =
		        chequeDao.getAllCheques().stream()
		                .collect(Collectors.groupingBy(
		                        Cheque::getBranchCode,
		                        Collectors.averagingDouble(
		                                cheque -> cheque.getAmount().doubleValue())
		                ));
		System.out.println("===== BRANCH AMOUNT SUMMARY =====");

		for (String branch : branchTotalAmount.keySet()) {

		    double total = branchTotalAmount.get(branch);
		    double average = branchAverageAmount.get(branch);

		    System.out.printf(
		        "%s | Total: %.2f | Average: %.2f%n",
		        branch,
		        total,
		        average
		    );
		}
	}

	@Override
	public void displayBatchStatisticalSummary(Map<String, List<Cheque>> groupedCheques) {
		
		for(Map.Entry<String ,List<Cheque>> entry : groupedCheques.entrySet()) {
			
			String branchCode = entry.getKey();
			List<Cheque> cheques = entry.getValue();
			
			DoubleSummaryStatistics statistics = cheques.stream()
					.collect(Collectors.summarizingDouble(cheque -> cheque.getAmount().doubleValue()));
			
			  System.out.println(branchCode
			            + " -> Count=" + statistics.getCount()
			            + ", Sum=" + statistics.getSum()
			            + ", Avg=" + statistics.getAverage()
			            + ", Min=" + statistics.getMin()
			            + ", Max=" + statistics.getMax()
			        );
			
		}
		
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
