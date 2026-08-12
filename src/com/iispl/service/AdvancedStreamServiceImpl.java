package com.iispl.service;

import java.math.BigDecimal;

import java.util.Comparator;
import java.util.Optional;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import com.iispl.dao.ChequeDao;
import com.iispl.dao.ChequeDaoImpl;
import com.iispl.dto.BranchMicrResult;
import com.iispl.enums.ValidationStatus;
import com.iispl.model.Cheque;

public class AdvancedStreamServiceImpl implements AdvancedStreamService {
	
	ChequeDao chequeDao = ChequeDaoImpl.of();
	List<Cheque> cheques=chequeDao.getAllCheques();
	
	static AdvancedStreamService advancedStreamService = null;
	
	private AdvancedStreamServiceImpl() {
		
	}
	
	public static AdvancedStreamService of() {
		advancedStreamService = new AdvancedStreamServiceImpl();
		
		return advancedStreamService;
	}

	@Override
	public BranchMicrResult getUniqueBatchAndMicr() {
		
		
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
		
		return BranchMicrResult.of(uniqueBatchCode, uniqueMicrCode, micrCount);
				
	}

	@Override
	public List<String> getTopFiveAmountCheques() {
		List<String> chequeList= cheques.stream()
				.sorted(Comparator.comparing(Cheque::getAmount)
				.reversed()).limit(5)
				.map(x->x.getChequeNumber())
				.toList();
		return chequeList;

	}

	@Override
	public List<String> getChequeNumbersByPage(int pageNumber, int pageSize) {
		List<String> chequeList=cheques.stream()
				.map(x->x.getChequeNumber())
				.skip((pageNumber-1)*pageSize)
				.limit(pageSize)
				.collect(Collectors.toList());
		return chequeList;
		

	}

	@Override
	public long getChequesCount() {

		long count = cheques.stream().count();

		return count;
	}

	@Override
	public void displayMinAndMaxAmount() {
		
     if(chequeDao.getAllCheques().equals(null)) {
    	 System.out.println("cheque List empty ");
     }
     else {
	Optional<Cheque> highest = cheques.stream().max(Comparator.comparing(Cheque:: getAmount));
	Optional<Cheque> lowest = cheques.stream().min(Comparator.comparing(Cheque:: getAmount));
    
	System.out.println(highest.get().getChequeNumber() +" "+ highest.get().getAmount());
	System.out.println(lowest.get().getChequeNumber() +" "+ lowest.get().getAmount());

	}
	}

	@Override
	public double getAvgAmount() {
		
		OptionalDouble avgAmount = cheques.stream().mapToDouble(cheque -> cheque.getAmount().doubleValue()).average();
		double avg_amount = avgAmount.orElse(0.0);
		return avg_amount;
	}


	 
	@Override
	public  Cheque getLookUpCheque(String chequeNumber) {
		Map<String, Cheque> chequeLookup = cheques.stream()
		 .collect(Collectors.toMap(Cheque::getChequeNumber , Function.identity() ,(existing , duplicate ) -> existing));
        
		 return chequeLookup.get(chequeNumber);
	}

	@Override
	public String getApprovedChequeAsString() {
		
		 return cheques.stream()
		            .filter(c -> c.getValidationStatus() == ValidationStatus.APPROVED)
		            .map(Cheque::getChequeNumber)
		            .collect(Collectors.joining(","));
	}

	@Override
	public Map<String, List<Cheque>> groupByBranch() {
		 
		 return cheques.stream()
		            .collect(Collectors.groupingBy(Cheque::getBranchCode));
	}
	@Override
	public Map<String, Long> groupByBranchChequeCount(){
	    

	    return cheques.stream()
	            .collect(
	                Collectors.groupingBy(
	                    Cheque::getBranchCode,
	                    Collectors.counting()
	                )
	            );

		
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
	public List<String> displayMultiLevelOrder(List<Cheque> chequeList) {
		List<String> sortedList=chequeList.stream()
				.sorted(Comparator.comparing(Cheque::getBranchCode)
				.thenComparing(Comparator.comparing(Cheque::getAmount).reversed())
				.thenComparing(Cheque::getChequeNumber))
				.map(x -> x.getBranchCode() + " | "
	                    + x.getAmount() + " | "
	                    + x.getChequeNumber())
				.toList();

		return sortedList;
	}

 
}
