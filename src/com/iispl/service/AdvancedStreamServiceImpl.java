package com.iispl.service;

import java.math.BigDecimal;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.OptionalDouble;
import java.util.stream.Collector;
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
	public Map  getMinAndMaxAmount() {

	Optional<Cheque> highest = chequeDao.getAllCheques().stream().max(Comparator.comparing(Cheque:: getAmount));
	Optional<Cheque> lowest = chequeDao.getAllCheques().stream().min(Comparator.comparing(Cheque:: getAmount));
	LinkedHashMap<String, Double> map = new LinkedHashMap<>();
	
    map.put(highest.get().getChequeNumber(), highest.get().getAmount().doubleValue() );
    map.put(lowest.get().getChequeNumber(), lowest.get().getAmount().doubleValue() );
	
	
     return map ;
 
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
		
		List<Cheque> cheques = chequeDao.getAllCheques();
		
		List<Cheque> finalizedCheques = cheques.stream()
				.collect(Collectors.collectingAndThen(
						Collectors.toList(),
						Collections::unmodifiableList
						));
		System.out.println("===== FINALIZED CTS RESULT =====");
	    System.out.println("Records Collected : " + finalizedCheques.size());

	    try {
	        finalizedCheques.add(finalizedCheques.get(0));
	    } catch (UnsupportedOperationException e) {
	        System.out.println(
	            "Modification Test : UnsupportedOperationException"
	        );
	    }

	    System.out.println("Result : Collection remains unchanged");

	}

	@Override
	public void displayStreamTrace() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> sortCheques() {
		
		List<String> sortedList=cheques.stream()
				.sorted(Comparator.comparing(Cheque::getBranchCode)
				.thenComparing(Comparator.comparing(Cheque::getAmount).reversed())
				.thenComparing(Cheque::getChequeNumber))
				.map(x -> x.getBranchCode() + " | "
	                    + x.getAmount() + " | "
	                    + x.getChequeNumber())
				.toList();
				
		return sortedList;
	}

	@Override
	public void displayMultiLevelOrder() {
		List<String> sortedList = sortCheques();
		
		sortedList.forEach(System.out::println);

		
	}

 
}
