package com.iispl.dto;

import java.util.List;

public class BranchMicrResult {

	private List<String> branchList;
	private List<String> micrList;
	private long micrCount;

	private BranchMicrResult(List<String> branchList, List<String> micrList, long micrCount) {
		this.branchList = branchList;
		this.micrList = micrList;
		this.micrCount = micrCount;
	}

	public List<String> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<String> branchList) {
		this.branchList = branchList;
	}

	public List<String> getMicrList() {
		return micrList;
	}

	public void setMicrList(List<String> micrList) {
		this.micrList = micrList;
	}

	public long getMicrCount() {
		return micrCount;
	}

	public void setMicrCount(long micrCount) {
		this.micrCount = micrCount;
	}

	public static BranchMicrResult of(List<String> branchList, List<String> micrList, long micrCount) {
		
		return new BranchMicrResult(branchList, micrList, micrCount);
		
	}

}
