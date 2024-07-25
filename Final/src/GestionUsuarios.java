import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionUsuarios {

    public void agregarUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getEmail());
            pstmt.executeUpdate();
        }
    }

    public void modificarUsuario(Usuario usuario) throws SQLException {
        String query = "UPDATE usuarios SET nombre = ?, email = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setInt(3, usuario.getId());
            pstmt.executeUpdate();
        }
    }

    public void eliminarUsuario(int id) throws SQLException {
        String query = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public Usuario buscarUsuario(int id) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"));
            }
        }
        return null;
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        String query = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email")));
            }
        }
        return usuarios;
    }
}