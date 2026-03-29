package org.example.DAO;

import org.example.ConnectionFactory;
import org.example.Model.Lampada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LampadaDAO {

    public void insert(Lampada l) throws Exception {
        String sql = "INSERT INTO lampada (id, intensidade) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, l.getId());
            ps.setInt(2, l.getIntensidade());
            ps.executeUpdate();
        }
    }

    public void update(Lampada l) throws Exception {
        String sql = "UPDATE lampada SET intensidade = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, l.getIntensidade());
            ps.setInt(2, l.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM lampada WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Lampada getById(int id) throws Exception {
        String sql = """
        SELECT d.id, d.nome, d.status, d.tipo, l.intensidade
        FROM dispositivo d
        JOIN lampada l ON d.id = l.id
        WHERE d.id = ?
    """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Lampada(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("status"),
                        rs.getString("tipo"),
                        rs.getInt("intensidade")
                );
            }
        }

        return null;
    }
}