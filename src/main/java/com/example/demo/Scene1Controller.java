package com.example.demo;

import com.example.Object.Person;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Scene1Controller {

    @FXML
    private ListView<String> listViewPerson;

    @FXML
    private TextField txtFieldPerson;

    @FXML
    void pressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (!txtFieldPerson.getText().isEmpty()) {
                addAPerson(txtFieldPerson.getText());
                txtFieldPerson.setText("");
            }
        }
    }

    private void addAPerson(String name) {
        Person person = new Person(name);
        listViewPerson.getItems().add(person.getName());
    }
}
