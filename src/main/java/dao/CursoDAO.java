package dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Curso;

public class CursoDAO {

    public List<Curso> getCursos() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM TCurso";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Curso c = new Curso();
                c.setId(rs.getInt("id"));
                c.setCodigo(rs.getString("codigo"));
                c.setNombre(rs.getString("nombre"));
                c.setCreditos(rs.getInt("creditos"));
                c.setDocente(rs.getString("docente"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar cursos: " + e.getMessage());
        }
        return lista;
    }

    public String insertarCurso(Curso c) {
        String sql = "INSERT INTO TCurso (codigo, nombre, creditos, docente) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getCodigo());
            ps.setString(2, c.getNombre());
            ps.setInt(3, c.getCreditos());
            ps.setString(4, c.getDocente());
            ps.executeUpdate();
            return "Curso registrado correctamente.";
        } catch (SQLException e) {
            return "Error al registrar curso: " + e.getMessage();
        }
    }

    public Curso consultarCurso(int id) {
        Curso c = null;
        String sql = "SELECT * FROM TCurso WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new Curso(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getInt("creditos"),
                        rs.getString("docente")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar curso: " + e.getMessage());
        }
        return c;
    }

    public String modificarCurso(Curso c) {
        String sql = "UPDATE TCurso SET codigo=?, nombre=?, creditos=?, docente=? WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getCodigo());
            ps.setString(2, c.getNombre());
            ps.setInt(3, c.getCreditos());
            ps.setString(4, c.getDocente());
            ps.setInt(5, c.getId());
            ps.executeUpdate();
            return "Curso actualizado correctamente.";
        } catch (SQLException e) {
            return "Error al actualizar curso: " + e.getMessage();
        }
    }

    public String eliminarCurso(int id) {
        String sql = "DELETE FROM TCurso WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return "Curso eliminado correctamente.";
        } catch (SQLException e) {
            return "Error al eliminar curso: " + e.getMessage();
        }
    }
}