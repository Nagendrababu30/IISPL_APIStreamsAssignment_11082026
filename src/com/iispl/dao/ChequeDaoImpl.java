package com.iispl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iispl.connectionpool.ConnectionPool;
import com.iispl.enums.AccountStatus;
import com.iispl.enums.ChequeType;
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
		List<Cheque> chequeList = new ArrayList<>();

        String sql = "SELECT * FROM cheque";

        try (
                Connection connection = ConnectionPool.getDataSource().getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {


            	    chequeList.add(
            	        new Cheque(
            	            rs.getInt("cheque_id"),
            	            rs.getString("cheque_number"),
            	            rs.getString("account_number"),
            	            rs.getString("customer_name"),
            	            rs.getString("branch_code"),
            	            rs.getString("micr_code"),
            	            rs.getDouble("amount"),
            	            rs.getDouble("available_balance"),
            	            rs.getDate("cheque_date").toLocalDate(),
            	            AccountStatus.valueOf(rs.getString("account_status")),
            	            ChequeType.valueOf(rs.getString("cheque_type")),
            	            MicrStatus.valueOf(rs.getString("micr_status")),
            	            ValidationStatus.valueOf(rs.getString("validation_status")),
            	            rs.getInt("batch_id")
            	        )
            	    );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return chequeList;
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
