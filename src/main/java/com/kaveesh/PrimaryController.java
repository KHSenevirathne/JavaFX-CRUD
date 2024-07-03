package com.kaveesh;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrimaryController {

    @FXML
    private TextField address;

    @FXML
    private TextField age;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    void deleteStudentBtn(ActionEvent event) {
        // Delete student logic here
    }

    @FXML
    void getStudentBtn(ActionEvent event) {
        // Get student logic here
    }

    @FXML
    void saveStudentBtn(ActionEvent event) {
        String studentId = id.getText();
        String studentName = name.getText();
        String studentAge = age.getText();
        String studentAddress = address.getText();

        String query = "INSERT INTO students (id, name, age, address) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, studentName);
            preparedStatement.setInt(3, Integer.parseInt(studentAge));
            preparedStatement.setString(4, studentAddress);

            preparedStatement.executeUpdate();
            System.out.println("Student data saved successfully!");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Student data saved successfully!");
            alert.showAndWait();

            id.clear(); name.clear(); age.clear(); address.clear();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateStudentBtn(ActionEvent event) {
        // Update student logic here
    }
}
