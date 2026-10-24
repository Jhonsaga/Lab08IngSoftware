package dao;

import config.Conexion;
import modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // PASO 1: Consultar todos los clientes
    public List<Cliente> getClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM TCliente";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getInt("id"),
                    rs.getString("dni"),
                    rs.getString("apellidos"),
                    rs.getString("nombres"),
                    rs.getString("email"),
                    rs.getString("celular"),
                    rs.getString("direccion")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // PASO 2: Insertar registro
    public String insertarCliente(Cliente c) {
        String sql = "INSERT INTO TCliente (dni, apellidos, nombres, email, celular, direccion) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setString(1, c.getDni());
            ps.setString(2, c.getApellidos());
            ps.setString(3, c.getNombres());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getCelular());
            ps.setString(6, c.getDireccion());
            
            int filas = ps.executeUpdate();
            return filas > 0 ? "¡Cliente registrado con éxito!" : "ERROR: No se pudo registrar.";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    // PASO 3: Buscar cliente único por ID
    public Cliente consultarCliente(int id) {
        Cliente c = null;
        String sql = "SELECT * FROM TCliente WHERE id = ?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new Cliente(
                        rs.getInt("id"),
                        rs.getString("dni"),
                        rs.getString("apellidos"),
                        rs.getString("nombres"),
                        rs.getString("email"),
                        rs.getString("celular"),
                        rs.getString("direccion")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    // PASO 4: Modificar datos de cliente
    public String modificarCliente(Cliente c) {
        String sql = "UPDATE TCliente SET dni=?, apellidos=?, nombres=?, email=?, celular=?, direccion=? WHERE id=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setString(1, c.getDni());
            ps.setString(2, c.getApellidos());
            ps.setString(3, c.getNombres());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getCelular());
            ps.setString(6, c.getDireccion());
            ps.setInt(7, c.getId());
            
            int filas = ps.executeUpdate();
            return filas > 0 ? "¡Cliente modificado de manera correcta!" : "ERROR: No se pudo modificar.";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    // PASO 5: Eliminar registro por ID
    public String eliminarCliente(int id) {
        String sql = "DELETE FROM TCliente WHERE id = ?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0 ? "¡El cliente fue eliminado con éxito!" : "ERROR: No se pudo eliminar.";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }
}