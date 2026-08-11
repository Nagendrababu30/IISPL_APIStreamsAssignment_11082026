package com.iispl.dao;

import java.util.List;

import com.iispl.enums.MicrStatus;
import com.iispl.enums.ValidationStatus;
import com.iispl.model.Cheque;

public class ChequeDaoImpl implements ChequeDao {
	
	private static ChequeDao chequeDao = null;
	
	private ChequeDaoImpl() {
		
	}
	
	public static ChequeDao of() {
		chequeDao = new ChequeDaoImpl();
		
		return chequeDao;
	} 

	@Override
	public List<Cheque> getAllCheques() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cheque> getChequesByBatch(int batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cheque getChequeByNumber(String chequeNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMicrStatus(String chequeNumber, MicrStatus status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateValidationStatus(String chequeNumber, ValidationStatus status) {
		// TODO Auto-generated method stub
		
	}

}
