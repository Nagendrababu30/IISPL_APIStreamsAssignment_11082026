package com.iispl.main;

import com.iispl.service.AdvancedStreamServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.iispl.dto.BranchMicrResult;
import com.iispl.model.Cheque;
import com.iispl.service.AdvancedStreamService;

public class CTSResearchApplication {
	
	static AdvancedStreamService advancedStreamService = AdvancedStreamServiceImpl.of();

	 public static void main(String[] args) {

	        Scanner scanner = new Scanner(System.in);

	        while (true) {

	            displayMenu();

	            System.out.print("Enter your choice : ");
	            int choice = scanner.nextInt();

	            switch (choice) {

	            case 1:
	                displayUniqueCTSValues();
	                break;

	            case 2:
	                displayTopFiveProcessingRecords();
	                break;

	            case 3:
	                displayChequePagination(scanner);
	                break;

	            case 4:
	                displayRecordCount();
	                break;

	            case 5:
	                displayAmountExtremes();
	                break;

	            case 6:
	                displayAverageChequeAmount();
	                break;

	            case 7:
	                displayChequeLookup(scanner);
	                break;

	            case 8:
	                displayCTSReferenceString();
	                break;

	            case 9:
	                displayCountPerBranch();
	                break;

	            case 10:
	                displayBranchAmountSummary();
	                break;

	            case 11:
	                displayBranchStatistics();
	                break;

	            case 12:
	                displayBranchChequeNumbers();
	                break;

	            case 13:
	                displayFinalizedCollection();
	                break;

	            case 14:
	                displayPipelineDiagnostics();
	                break;

	            case 15:
	                displayMultiLevelOrder();
	                break;

	            case 16:
	                System.out.println("Exiting application...");
	                scanner.close();
	                return;

	            default:
	                System.out.println("Invalid choice. Please try again.");
	            }

	            System.out.println();
	        }
	    }


	    // ================= MENU =================

	    static void displayMenu() {

	        System.out.println();
	        System.out.println("========== ADVANCED CTS STREAM REPORTS ==========");
	        System.out.println("1. Unique Branch/MICR Values");
	        System.out.println("2. Top 5 Processing Queue");
	        System.out.println("3. Paginated Cheques");
	        System.out.println("4. Record Count");
	        System.out.println("5. Highest/Lowest Cheque");
	        System.out.println("6. Average Cheque Amount");
	        System.out.println("7. Cheque Lookup Map");
	        System.out.println("8. CTS Reference String");
	        System.out.println("9. Count Per Branch");
	        System.out.println("10. Total/Average Per Branch");
	        System.out.println("11. Branch Statistics");
	        System.out.println("12. Branch -> Cheque Numbers");
	        System.out.println("13. Finalized Collection");
	        System.out.println("14. Pipeline Diagnostics");
	        System.out.println("15. Multi-Level Comparator");
	        System.out.println("16. Exit");
	        System.out.println("=================================================");
	    }


	    // ================= 7.1 =================

	    static void displayUniqueCTSValues() {

	        BranchMicrResult result =
	                advancedStreamService.getUniqueBranchAndMicr();

	        System.out.println();
	        System.out.println("===== UNIQUE CTS VALUES =====");

	        System.out.println("Branches : "
	                + result.getBranchList());

	        System.out.println("MICR Count : "
	                + result.getMicrCount());

	        System.out.println("MICR Codes : "
	                + result.getMicrList());
	    }


	    // ================= 7.2 =================

	    static void displayTopFiveProcessingRecords() {

	        List<String> chequeNumbers =
	                advancedStreamService.getTopFiveAmountCheques();

	        System.out.println();
	        System.out.println("===== TOP 5 CTS PROCESSING QUEUE =====");

	        int position = 1;

	        for (String chequeNumber : chequeNumbers) {

	            Cheque cheque =
	                    advancedStreamService.getLookUpCheque(chequeNumber);

	            System.out.printf(
	                    "%d. %s | %s | %.2f%n",
	                    position++,
	                    cheque.getChequeNumber(),
	                    cheque.getBranchCode(),
	                    cheque.getAmount().doubleValue()
	            );
	        }
	    }


	    // ================= 7.3 =================

	    static void displayChequePagination(Scanner scanner) {

	        System.out.print("Enter Page Number : ");
	        int pageNumber = scanner.nextInt();

	        System.out.print("Enter Page Size : ");
	        int pageSize = scanner.nextInt();

	        List<String> chequeNumbers =
	                advancedStreamService.getChequeNumbersByPage(
	                        pageNumber,
	                        pageSize
	                );

	        System.out.println();
	        System.out.println("Page Number : " + pageNumber);
	        System.out.println("Page Size : " + pageSize);

	        System.out.println(
	                "===== CHEQUE PAGE " + pageNumber + " ====="
	        );

	        chequeNumbers.forEach(System.out::println);
	    }


	    // ================= 7.4 =================

	    static void displayRecordCount() {

	        long count =
	                advancedStreamService.getChequesCount();

	        System.out.println();
	        System.out.println("===== CTS RECORD COUNT =====");
	        System.out.println("Total Cheque Records : " + count);
	    }


	    // ================= 7.5 =================

	    static void displayAmountExtremes() {

	        Map<String, Double> result =
	                advancedStreamService.getMinAndMaxAmount();

	        System.out.println();
	        System.out.println("===== AMOUNT EXTREMES =====");

	        if (result.isEmpty()) {
	            System.out.println("No cheque records available.");
	            return;
	        }

	        boolean first = true;

	        for (Map.Entry<String, Double> entry : result.entrySet()) {

	            if (first) {
	                System.out.printf(
	                        "Highest : %s | %.2f%n",
	                        entry.getKey(),
	                        entry.getValue()
	                );
	                first = false;
	            } else {
	                System.out.printf(
	                        "Lowest : %s | %.2f%n",
	                        entry.getKey(),
	                        entry.getValue()
	                );
	            }
	        }
	    }


	    // ================= 7.6 =================

	    static void displayAverageChequeAmount() {

	        double average =
	                advancedStreamService.getAvgAmount();

	        System.out.println();
	        System.out.println("===== AVERAGE CHEQUE AMOUNT =====");

	        System.out.printf(
	                "Average Amount : %.2f%n",
	                average
	        );
	    }


	    // ================= 7.7 =================

	    static void displayChequeLookup(Scanner scanner) {

	        System.out.print("Enter Cheque Number : ");
	        String chequeNumber = scanner.next();

	        Cheque cheque =
	                advancedStreamService.getLookUpCheque(chequeNumber);

	        System.out.println();
	        System.out.println("===== CHEQUE LOOKUP =====");

	        if (cheque == null) {
	            System.out.println( "Cheque not found : " + chequeNumber);
	            return;
	        }

	        System.out.println("Key      : "
	                + cheque.getChequeNumber());

	        System.out.println("Customer : "
	                + cheque.getCustomerName());

	        System.out.printf(
	                "Amount   : %.2f%n",
	                cheque.getAmount().doubleValue()
	        );

	        System.out.println("Branch   : "
	                + cheque.getBranchCode());
	    }


	    // ================= 7.8 =================

	    static void displayCTSReferenceString() {

	        String result =
	                advancedStreamService.getApprovedChequeAsString();

	        System.out.println();
	        System.out.println("===== APPROVED CTS REFERENCES =====");
	        System.out.println(result);
	    }


	    // ================= 7.9 =================

	    static void displayCountPerBranch() {

	        Map<String, Long> result =
	                advancedStreamService.groupByBranchChequeCount();

	        System.out.println();
	        System.out.println("===== CHEQUE COUNT BY BRANCH =====");

	        result.forEach((branch, count) ->
	                System.out.println(
	                        branch + " -> " + count
	                )
	        );
	    }


	    // ================= 7.10 =================

	    static void displayBranchAmountSummary() {

	        System.out.println();
	        System.out.println("===== BRANCH AMOUNT SUMMARY =====");

	        advancedStreamService.displayBranchAmountSummary();
	    }


	    // ================= 7.11 =================

	    static void displayBranchStatistics() {

	        System.out.println();
	        System.out.println("===== BRANCH STATISTICS =====");

	        advancedStreamService.displayBranchStatisticalSummary();
	    }


	    // ================= 7.12 =================

	    static void displayBranchChequeNumbers() {

	        Map<String, List<String>> result =
	                advancedStreamService.getBranchChequeNumbers();

	        System.out.println();
	        System.out.println("===== BRANCH -> CHEQUE NUMBERS =====");

	        result.forEach((branch, chequeNumbers) ->
	                System.out.println(
	                        branch + " -> " + chequeNumbers
	                )
	        );
	    }


	    // ================= 7.13 =================

	    static void displayFinalizedCollection() {

	        advancedStreamService.displayFinalizedCtsResult();
	    }


	    // ================= 7.14 =================

	    static void displayPipelineDiagnostics() {

	        advancedStreamService.displayStreamTrace();
	    }


	    // ================= 7.15 =================

	    static void displayMultiLevelOrder() {

	        List<String> result =
	                advancedStreamService.sortCheques();

	        System.out.println();
	        System.out.println("===== MULTI-LEVEL ORDER =====");

	        result.forEach(System.out::println);
	    }
	}


