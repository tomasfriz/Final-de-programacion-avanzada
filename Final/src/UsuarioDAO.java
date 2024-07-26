import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public void agregarUsuario(UsuarioDTO usuario) throws BibliotecaException {
        String query = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al agregar usuario: " + ex.getMessage());
        }
    }

    public void modificarUsuario(UsuarioDTO usuario) throws BibliotecaException {
        String query = "UPDATE usuarios SET nombre = ?, email = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setInt(3, usuario.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al modificar usuario: " + ex.getMessage());
        }
    }

    public void eliminarUsuario(int id) throws BibliotecaException {
        String query = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al eliminar usuario: " + ex.getMessage());
        }
    }

    public UsuarioDTO buscarUsuario(int id) throws UsuarioNoEncontradoException, BibliotecaException {
        String query = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new UsuarioDTO(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"));
                } else {
                    throw new UsuarioNoEncontradoException(id);
                }
            }
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al buscar usuario: " + ex.getMessage());
        }
    }

    public List<UsuarioDTO> listarUsuarios() throws BibliotecaException {
        String query = "SELECT * FROM usuarios";
        List<UsuarioDTO> usuarios = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new UsuarioDTO(rs.getInt("id"), rs.getString("nombre"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al listar usuarios: " + ex.getMessage());
        }
        return usuarios;
    }
}