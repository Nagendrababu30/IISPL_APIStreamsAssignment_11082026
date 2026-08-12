package com.iispl.dao;

import java.util.List;

import com.iispl.enums.MicrStatus;
import com.iispl.enums.ValidationStatus;
import com.iispl.model.Cheque;

public interface ChequeDao {

	public List<Cheque> getAllCheques();
	
	public List<Cheque> getChequesByBatch(long batchId);
	
	public Cheque getChequeByNumber(String chequeNumber);
	
	public void updateMicrStatus(String chequeNumber, MicrStatus status);
	
	public void updateValidationStatus(String chequeNumber, ValidationStatus status);
	
}
