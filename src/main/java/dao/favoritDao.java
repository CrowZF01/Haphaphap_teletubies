package dao;

public interface favoritDao {
    boolean cekFavorit(int idUser, int idResep);
    boolean tambahKeFavorit(int idUser, int idResep);
    boolean hapusFavorit(int idUser, int idResep);
}
