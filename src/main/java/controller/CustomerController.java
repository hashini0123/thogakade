package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CustomerInfoDTO;

import java.sql.*;

public class CustomerController {
    public ObservableList<CustomerInfoDTO> getAllCustomers(){

        ObservableList<CustomerInfoDTO> customerInfoDTOS = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerInfoDTOS.add(new CustomerInfoDTO(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getDate("DOB"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerInfoDTOS;
    }
}
