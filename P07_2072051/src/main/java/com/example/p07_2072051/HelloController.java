package com.example.p07_2072051;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloController {
    public TextArea txtArea;
    public Button btnOpen;
    public Button btnSaveAs;
    public Button btnSave;
    private String path;

    public void initialize() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));
        path = null;
    }

    public void Open(ActionEvent actionEvent) throws IOException {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(txtArea.getScene().getWindow());
        path = file.getPath();
        HelloApplication.changeTitle(file.getName());
        txtArea.setText("");
        for (String s : Files.readAllLines(Paths.get(path))) {
            txtArea.appendText(s);
        }
    }

    public void SaveAs(ActionEvent actionEvent) throws IOException {
        FileChooser chooser = new FileChooser();
        File file = chooser.showSaveDialog(txtArea.getScene().getWindow());
        Files.write(Paths.get(file.getPath()), txtArea.getText().getBytes());
        path = file.getPath();
        HelloApplication.changeTitle(file.getName());
    }

    public void Save(ActionEvent actionEvent) throws IOException {
        if (path != null) {
            Files.write(Paths.get(path), txtArea.getText().getBytes());
        }
    }
}