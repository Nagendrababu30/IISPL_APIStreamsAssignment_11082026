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
            	            rs.getLong("cheque_id"),
            	            rs.getString("cheque_number"),
            	            rs.getString("account_number"),
            	            rs.getString("customer_name"),
            	            rs.getString("branch_code"),
            	            rs.getString("micr_code"),
            	            rs.getBigDecimal("amount"),
            	            rs.getBigDecimal("available_balance"),
            	            rs.getDate("cheque_date").toLocalDate(),
            	            AccountStatus.valueOf(rs.getString("account_status")),
            	            ChequeType.valueOf(rs.getString("cheque_type")),
            	            MicrStatus.valueOf(rs.getString("micr_status")),
            	            ValidationStatus.valueOf(rs.getString("validation_status")),
            	            rs.getLong("batch_id")
            	        )
            	    );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return chequeList;
	}

	@Override
	public List<Cheque> getChequesByBatch(long batchId) {
		List<Cheque> chequeList = new ArrayList<>();

        String sql = "SELECT * FROM CTS_CHEQUE WHERE batch_id = ?";

        try (
                Connection connection = ConnectionPool.getDataSource().getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setLong(1, batchId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                	chequeList.add(
                	        new Cheque(
                	            rs.getLong("cheque_id"),
                	            rs.getString("cheque_number"),
                	            rs.getString("account_number"),
                	            rs.getString("customer_name"),
                	            rs.getString("branch_code"),
                	            rs.getString("micr_code"),
                	            rs.getBigDecimal("amount"),
                	            rs.getBigDecimal("available_balance"),
                	            rs.getDate("cheque_date").toLocalDate(),
                	            AccountStatus.valueOf(rs.getString("account_status")),
                	            ChequeType.valueOf(rs.getString("cheque_type")),
                	            MicrStatus.valueOf(rs.getString("micr_status")),
                	            ValidationStatus.valueOf(rs.getString("validation_status")),
                	            rs.getLong("batch_id")
                	        )
                	    );
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return chequeList;
	}

	@Override
	public Cheque getChequeByNumber(String chequeNumber) {
		Cheque cheque = null;
		
		String sql = "SELECT * FROM CTS_CHEQUE WHERE cheque_number = ?";

        try (
                Connection connection = ConnectionPool.getDataSource().getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, chequeNumber);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    cheque =  new Cheque(
            	            rs.getLong("cheque_id"),
            	            rs.getString("cheque_number"),
            	            rs.getString("account_number"),
            	            rs.getString("customer_name"),
            	            rs.getString("branch_code"),
            	            rs.getString("micr_code"),
            	            rs.getBigDecimal("amount"),
            	            rs.getBigDecimal("available_balance"),
            	            rs.getDate("cheque_date").toLocalDate(),
            	            AccountStatus.valueOf(rs.getString("account_status")),
            	            ChequeType.valueOf(rs.getString("cheque_type")),
            	            MicrStatus.valueOf(rs.getString("micr_status")),
            	            ValidationStatus.valueOf(rs.getString("validation_status")),
            	            rs.getLong("batch_id"));
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return cheque;
	}

	@Override
	public void updateMicrStatus(String chequeNumber, MicrStatus status) {
		String sql = "UPDATE CTS_CHEQUE SET micr_status = ? WHERE cheque_number = ?";

        try (
                Connection connection = ConnectionPool.getDataSource().getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, status.name());
            ps.setString(2, chequeNumber);

            ps.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
		
	}

	@Override
	public void updateValidationStatus(String chequeNumber, ValidationStatus status) {
		
		String sql = "UPDATE CTS_CHEQUE SET validation_status = ? WHERE cheque_number = ?";

        try (
                Connection connection = ConnectionPool.getDataSource().getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, status.name());
            ps.setString(2, chequeNumber);

            ps.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        
		
	}

}
