package database;

import dao.favoritDao;
import util.databaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class favoritDB implements favoritDao {

    private static favoritDB instance;

    private favoritDB() {}

    public static favoritDB getInstance() {
        if (instance == null) {
            instance = new favoritDB();
        }
        return instance;
    }

    @Override
    public boolean cekFavorit(int idUser, int idResep) {
        String sql = "SELECT * FROM favorit_user WHERE id_user = ? AND id_resep = ?";
        try (Connection conn = databaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            stmt.setInt(2, idResep);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tambahKeFavorit(int idUser, int idResep) {
        String sql = "INSERT INTO favorit_user (id_user, id_resep) VALUES (?, ?)";
        try (Connection conn = databaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            stmt.setInt(2, idResep);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean hapusFavorit(int idUser, int idResep) {
        String sql = "DELETE FROM favorit_user WHERE id_user = ? AND id_resep = ?";
        try (Connection conn = databaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            stmt.setInt(2, idResep);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
