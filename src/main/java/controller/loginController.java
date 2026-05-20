package controller;

import database.userDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label; // <-- Pastikan ini Label milik javafx.scene.control
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import util.sessionManager;

public class loginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    public void handleLogin() {
        if (statusLabel != null) {
            statusLabel.setText("");
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            tampilkanError("Username dan Password tidak boleh kosong!");
            return;
        }

        userDB db = new userDB();
        User user = db.validasiLogin(username, password);

        if (user != null) {
            sessionManager.setUser(user);
            System.out.println("Login berhasil: " + user.getUsername());
            try {
                Stage stage = (Stage) usernameField.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/felix_71241153/app/copy_Teletubies_haphaphap/home.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                tampilkanError("Sistem Error: Gagal memuat halaman Home!");
                e.printStackTrace();
            }

        } else {
            tampilkanError("Username atau Password salah!");
        }
    }

    @FXML
    public void pindahDaftar(){
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/felix_71241153/app/copy_Teletubies_haphaphap/daftar.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println("Gagal memuat halaman Daftar");
            e.printStackTrace();
        }
    }

    private void tampilkanError(String pesan) {
        if (statusLabel != null) {
            statusLabel.setText(pesan);
            statusLabel.setStyle("-fx-text-fill: #E74C3C; -fx-font-size: 13px;");
        } else {
            System.out.println("ERROR LOGIN: " + pesan);
        }
    }
}