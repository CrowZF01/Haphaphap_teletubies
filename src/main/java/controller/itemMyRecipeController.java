package controller;

import service.RecipeService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Resep;
import util.imageUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class itemMyRecipeController {
    @FXML private ImageView fotoResep;
    @FXML private Label kategoriLabel, judulLabel, waktuLabel, porsiLabel, pedasLabel, deskripsiLabel;
    @FXML private Label statusLabel;
    @FXML private javafx.scene.control.Button btnPublish;
    @FXML private javafx.scene.control.Button btnEdit;

    private Resep resepAktif;
    private myRecipesController parentController;

    public void setData(Resep resep, myRecipesController parentController) {
        this.resepAktif = resep;
        this.parentController = parentController;

        judulLabel.setText(resep.getJudul());
        kategoriLabel.setText(resep.getJenisMakanan() != null ? resep.getJenisMakanan().toUpperCase() : "UMUM");
        waktuLabel.setText("⏱ " + resep.getEstimasiWaktu() + " Menit");
        porsiLabel.setText("🍽 " + resep.getPorsiSajian() + " Porsi");
        pedasLabel.setText("🌶 Level " + resep.getTingkatKepedasan());

        String deskripsi = resep.getLangkahPembuatan();
        if (deskripsi != null && deskripsi.length() > 100) {
            deskripsi = deskripsi.substring(0, 100) + "...";
        }
        deskripsiLabel.setText(deskripsi);

        fotoResep.setImage(imageUtil.getImage(resep.getFoto()));

        // Set status text and Publish button visibility
        String status = resep.getStatus();
        if ("DRAFT".equalsIgnoreCase(status)) {
            statusLabel.setText("Draft (Private)");
            btnPublish.setVisible(true);
            btnPublish.setManaged(true);
        } else if ("PENDING".equalsIgnoreCase(status)) {
            statusLabel.setText("Menunggu Persetujuan Admin");
            btnPublish.setVisible(false);
            btnPublish.setManaged(false);
        } else if ("PUBLISHED".equalsIgnoreCase(status)) {
            statusLabel.setText("Published (Public)");
            btnPublish.setVisible(false);
            btnPublish.setManaged(false);
        } else {
            statusLabel.setText(status);
            btnPublish.setVisible(false);
            btnPublish.setManaged(false);
        }
    }

    @FXML
    public void handleLihatResep(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/felix_71241153/app/copy_Teletubies_haphaphap/detail.fxml"));
            Parent root = loader.load();
            detailController controller = loader.getController();
            controller.setResepData(resepAktif);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleHapusResep() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Hapus Resep");
        alert.setHeaderText(null);
        alert.setContentText("Apakah Anda yakin ingin menghapus resep '" + resepAktif.getJudul() + "' secara permanen?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean sukses = RecipeService.getInstance().hapusResepPermanen(resepAktif.getIdResep());
            if (sukses) {
                Alert suksesAlert = new Alert(Alert.AlertType.INFORMATION);
                suksesAlert.setTitle("Berhasil");
                suksesAlert.setHeaderText(null);
                suksesAlert.setContentText("Resep Anda berhasil dihapus.");
                suksesAlert.showAndWait();
                
                if (parentController != null) {
                    parentController.loadDataMyRecipes();
                }
            } else {
                Alert gagalAlert = new Alert(Alert.AlertType.ERROR);
                gagalAlert.setTitle("Gagal");
                gagalAlert.setHeaderText(null);
                gagalAlert.setContentText("Gagal menghapus resep.");
                gagalAlert.showAndWait();
            }
        }
    }

    @FXML
    public void handlePublish() {
        boolean sukses = RecipeService.getInstance().updateResepStatus(resepAktif.getIdResep(), "PENDING");
        if (sukses && parentController != null) {
            parentController.loadDataMyRecipes();
        }
    }

    @FXML
    public void handleEdit(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/felix_71241153/app/copy_Teletubies_haphaphap/add.fxml"));
            Parent root = loader.load();
            
            addResepController controller = loader.getController();
            controller.setResepUntukDiedit(resepAktif);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}