package controller;

import database.resepDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Resep;
import util.sessionManager;
import java.util.List;

public class favoritController {

    @FXML private VBox favoritContainer;

    @FXML
    public void initialize() {
        loadDataFavorit();
    }

    public void loadDataFavorit() {
        favoritContainer.getChildren().clear();

        if (!sessionManager.isLogin()) return;

        resepDB db = new resepDB();
        int idUser = sessionManager.getUser().getId();
        List<Resep> listFavorit = db.getFavoritByUser(idUser);

        if (listFavorit.isEmpty()) {
            Label kosong = new Label("Belum ada resep yang difavoritkan.");
            kosong.setStyle("-fx-text-fill: #888888; -fx-font-size: 15px;");
            favoritContainer.getChildren().add(kosong);
            return;
        }

        for (Resep resep : listFavorit) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/felix_71241153/app/copy_Teletubies_haphaphap/itemFavorit.fxml"));
                HBox card = loader.load();
                itemFavoritController controller = loader.getController();

                // Kirim data resep dan controller ini
                controller.setData(resep, this);
                favoritContainer.getChildren().add(card);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}