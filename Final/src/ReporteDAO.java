import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {
    public List<LibroDTO> librosMasSolicitados() throws BibliotecaException {
        String query = "SELECT l.id, l.titulo, l.autor, l.genero, COUNT(p.id_libro) AS solicitudes FROM libros l INNER JOIN prestamos p ON l.id = p.id_libro GROUP BY l.id ORDER BY solicitudes DESC LIMIT 10";
        List<LibroDTO> libros = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                libros.add(new LibroDTO(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getString("genero")));
            }
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al obtener reporte de libros más solicitados: " + ex.getMessage());
        }
        return libros;
    }

    public List<UsuarioDTO> usuariosConMasPrestamos() throws BibliotecaException {
        String query = "SELECT u.id, u.nombre, u.email, COUNT(p.id_usuario) AS prestamos FROM usuarios u INNER JOIN prestamos p ON u.id = p.id_usuario GROUP BY u.id ORDER BY prestamos DESC LIMIT 10";
        List<UsuarioDTO> usuarios = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new UsuarioDTO(rs.getInt("id"), rs.getString("nombre"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al obtener reporte de usuarios con más préstamos: " + ex.getMessage());
        }
        return usuarios;
    }
}