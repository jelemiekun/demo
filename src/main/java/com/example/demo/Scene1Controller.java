package com.example.demo;

import com.example.Object.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Scene1Controller {

    @FXML
    private Button btnClearAll;

    @FXML
    private Button btnAdd;

    @FXML
    private ListView<String> listViewPerson;

    @FXML
    private TextField txtFieldPerson;

    @FXML
    private void btnAddClick(MouseEvent event) {
        addAPerson();
    }

    @FXML
    private void pressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            addAPerson();
        }
    }

    @FXML
    private void btnClearAllClick(MouseEvent event) {
        if (listViewPerson.getItems().size() != 0) {
            for (int i = listViewPerson.getItems().size() - 1; i >= 0; i--) {
                listViewPerson.getItems().remove(i);
            }
        }
    }

    private void addAPerson() {
        if (!txtFieldPerson.getText().isEmpty()) {
            String name = txtFieldPerson.getText();
            if (listViewPerson.getItems().contains(name)) {
                duplicateName();
            } else {
                Person person = new Person(name);
                listViewPerson.getItems().add(person.getName());
                clearField();
            }
        } else {
            emptyField();
        }
    }

    private void emptyField() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Empty Field");
        alert.setContentText("Can not add. Field is empty.");
        alert.show();
    }

    private void duplicateName() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Duplicate Name");
        alert.setContentText("You can't enter a duplicate name");
        alert.show();
        clearField();
    }

    private void clearField() {
        txtFieldPerson.setText("");
    }
}
