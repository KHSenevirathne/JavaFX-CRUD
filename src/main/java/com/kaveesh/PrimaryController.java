package com.kaveesh;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String studentId = id.getText();

        String query = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Student with ID " + studentId + " deleted successfully!");
                alert.showAndWait();
                clearFields(); // Clear fields after deletion
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("No student found with ID: " + studentId);
                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getStudentBtn(ActionEvent event) {
        String studentId = id.getText();

        String query = "SELECT * FROM students WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String studentName = resultSet.getString("name");
                int studentAge = resultSet.getInt("age");
                String studentAddress = resultSet.getString("address");

                name.setText(studentName);
                age.setText(String.valueOf(studentAge));
                address.setText(studentAddress);
                System.out.println("Student data retrieved successfully!");

            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("No student found with ID: " + studentId);
                alert.showAndWait();
                clearFields();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

            clearFields();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateStudentBtn(ActionEvent event) {
        String studentId = id.getText();
        String studentName = name.getText();
        String studentAge = age.getText();
        String studentAddress = address.getText();

        String query = "UPDATE students SET name = ?, age = ?, address = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentName);
            preparedStatement.setInt(2, Integer.parseInt(studentAge));
            preparedStatement.setString(3, studentAddress);
            preparedStatement.setString(4, studentId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Student with ID " + studentId + " updated successfully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("No student found with ID: " + studentId);
                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        id.clear();
        name.clear();
        age.clear();
        address.clear();
    }
}
