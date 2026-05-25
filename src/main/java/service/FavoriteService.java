package service;

import dao.favoritDao;
import database.favoritDB;

public class FavoriteService {

    private static FavoriteService instance;
    private final favoritDao favoritDao;

    private FavoriteService() {
        this.favoritDao = favoritDB.getInstance();
    }

    public static FavoriteService getInstance() {
        if (instance == null) {
            instance = new FavoriteService();
        }
        return instance;
    }

    public boolean cekFavorit(int idUser, int idResep) {
        return favoritDao.cekFavorit(idUser, idResep);
    }

    public void toggleFavorit(int idUser, int idResep, boolean isFavoritNow) {
        if (isFavoritNow) {
            favoritDao.hapusFavorit(idUser, idResep);
        } else {
            favoritDao.tambahKeFavorit(idUser, idResep);
        }
    }
}
