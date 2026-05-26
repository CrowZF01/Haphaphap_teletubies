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

public class itemModerasiController {
    @FXML private ImageView fotoResep;
    @FXML private Label kategoriLabel, judulLabel, waktuLabel, porsiLabel, pedasLabel, deskripsiLabel;

    private Resep resepAktif;
    private moderasiAdminController parentController;

    public void setData(Resep resep, moderasiAdminController parentController) {
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
    public void handleApprove() {
        boolean sukses = RecipeService.getInstance().updateResepStatus(resepAktif.getIdResep(), "PUBLISHED");
        if (sukses && parentController != null) {
            parentController.loadDataPendingRecipes();
        }
    }

    @FXML
    public void handleReject() {
        // Hapus permanen resep jika ditolak agar database bersih dari spam
        boolean sukses = RecipeService.getInstance().hapusResepPermanen(resepAktif.getIdResep());
        if (sukses && parentController != null) {
            parentController.loadDataPendingRecipes();
        }
    }
}
