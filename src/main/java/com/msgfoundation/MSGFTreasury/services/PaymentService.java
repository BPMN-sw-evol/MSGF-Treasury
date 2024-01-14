package com.msgfoundation.MSGFTreasury.services;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class PaymentService {

    public void updatePayment(Long codRequest) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/credit_request", "postgres", "admin");

        String updateQuery = "UPDATE credit_request SET payment = true WHERE cod_request = ?";

        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setLong(1, codRequest);
            updateStatement.executeUpdate();
            System.out.println("CONTROLLER: The payment was made");
        }

    }
}
