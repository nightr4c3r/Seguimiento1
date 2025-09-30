package com.example.fx9;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.Optional;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TableView<Person> peopleTable;
    @FXML
    private TableColumn<Person, String> nameColumn;
    @FXML
    private TableColumn<Person, Integer> ageColumn;
    @FXML
    private TableColumn<Person, String> sexColumn;
    @FXML
    private TableColumn<Person, String> cityColumn;

    private final ObservableList<Person> people = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        peopleTable.setItems(people);


        welcomeText.setText("People");
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Tarea de Seguimiento Programacion 1.");
    }

    @FXML
    protected void onAddPerson() {
        Dialog<Person> dialog = new Dialog<>();
        dialog.setTitle("Add Person");
        dialog.setHeaderText("Enter person details");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField ageField = new TextField();
        ageField.setPromptText("Age");

        ChoiceBox<String> sexChoice = new ChoiceBox<>(FXCollections.observableArrayList("Male", "Female", "Prefer not to say"));
        sexChoice.getSelectionModel().selectFirst();

        TextField cityField = new TextField();
        cityField.setPromptText("City");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.addRow(0, new Label("Name:"), nameField);
        grid.addRow(1, new Label("Age:"), ageField);
        grid.addRow(2, new Label("Sex:"), sexChoice);
        grid.addRow(3, new Label("City:"), cityField);
        GridPane.setHgrow(nameField, Priority.ALWAYS);
        GridPane.setHgrow(ageField, Priority.ALWAYS);
        GridPane.setHgrow(cityField, Priority.ALWAYS);

        // Enable/disable OK button based on validation
        Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setDisable(true);

        Runnable updateOk = () -> okButton.setDisable(!isValid(nameField.getText(), ageField.getText(), cityField.getText()));
        nameField.textProperty().addListener((obs, old, val) -> updateOk.run());
        ageField.textProperty().addListener((obs, old, val) -> updateOk.run());
        cityField.textProperty().addListener((obs, old, val) -> updateOk.run());

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                String name = nameField.getText().trim();
                String ageText = ageField.getText().trim();
                String sex = sexChoice.getValue();
                String city = cityField.getText().trim();
                if (isValid(name, ageText, city)) {
                    int age = Integer.parseInt(ageText);
                    return new Person(name, age, sex, city);
                }
            }
            return null;
        });

        Optional<Person> result = dialog.showAndWait();
        result.ifPresent(people::add);
    }

    private boolean isValid(String name, String ageText, String city) {
        if (name == null || name.isBlank()) return false;
        if (city == null || city.isBlank()) return false;
        try {
            int age = Integer.parseInt(ageText);
            return age >= 0 && age <= 110;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
