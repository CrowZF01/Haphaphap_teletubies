package controller;

import service.RecipeService;
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

        int idUser = sessionManager.getUser().getId();
        List<Resep> listFavorit = RecipeService.getInstance().getFavoritByUser(idUser);

        if (listFavorit.isEmpty()) {
            Label kosong = new Label("Belum ada resep yang difavoritkan.");
            kosong.setStyle("-fx-text-fill: #888888; -fx-font-size: 15px;");
            favoritContainer.getChildren().add(kosong);
            return;
        }

        for (Resep resep : listFavorit) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/felix_71241153/app/copy_Teletubies_haphaphap/itemRow.fxml"));
                HBox card = loader.load();
                itemRowController controller = loader.getController();

                // Kirim data resep, mode "FAVORIT", dan controller ini
                controller.setData(resep, "FAVORIT", this);
                favoritContainer.getChildren().add(card);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}