package service;

import dao.favoritDao;
import database.favoritDB;
import model.Resep;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;

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
    public void eksporKeTxt(List<Resep> listFavorit, File file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("=====================================");
            writer.println("        DAFTAR RESEP FAVORIT         ");
            writer.println("=====================================");
            writer.println();

            for (int i = 0; i < listFavorit.size(); i++) {
                Resep resep = listFavorit.get(i);
                writer.println((i + 1) + ". " + resep.getJudul());
                writer.println("Kategori  : " + resep.getJenisMakanan());
                writer.println("Bahan     : " + resep.getBahan());
                writer.println("Cara Buat : \n" + resep.getLangkahPembuatan());
                writer.println("\n\n");
            }
            System.out.println("Ekspor berhasil ke: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat mengekspor file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
