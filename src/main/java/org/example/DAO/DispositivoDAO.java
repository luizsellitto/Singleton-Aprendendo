package org.example.DAO;

import org.example.Model.Dispositivo;
import org.example.ConnectionFactory;
import org.example.Model.Lampada;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DispositivoDAO {

    public void insert(Dispositivo d) throws Exception {
        String sql = "INSERT INTO dispositivo (nome, tipo, status) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, d.getNome());
            ps.setString(2, d.getTipo());
            ps.setInt(3, d.getStatus());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();//A ResultSet object maintains a
                                                 // cursor pointing to its current row of data
            if (rs.next()) { //se existe uma linha, pega o valor dela (a linha vem do GeneratedKeys)
                d.setId(rs.getInt(1));
            }
        }
    }

    public void update(Dispositivo d) throws Exception {
        String sql = "UPDATE dispositivo SET nome = ?, tipo = ?, status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getNome());
            ps.setString(2, d.getTipo());
            ps.setInt(3, d.getStatus());
            ps.setInt(4, d.getId());
            ps.executeUpdate();
        }
    }

    public void delete(Dispositivo d) throws Exception {
        String sql = "DELETE FROM dispositivo WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, d.getId());
            ps.executeUpdate();
        }
    }

    public List<Dispositivo> getAll() throws Exception {
        List<Dispositivo> lista = new ArrayList<>();

        String sql = "SELECT * FROM dispositivo";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");
                int status = rs.getInt("status");

                Lampada lampada = new LampadaDAO().getById(id);

                if (lampada != null) {
                    lista.add(lampada);
                } else {
                    lista.add(new Dispositivo(id, nome, status, tipo));
                }
            }
        }

        return lista;
    }

    public Dispositivo getById(int id) throws Exception {

        String sql = "SELECT * FROM dispositivo WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Dispositivo(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getInt("status"),
                            rs.getString("tipo")
                    );
                }
            }
        }

        return null;
    }
}