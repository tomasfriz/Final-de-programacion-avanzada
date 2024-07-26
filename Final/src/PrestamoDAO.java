import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrestamoDAO {
    public void agregarPrestamo(PrestamoDTO prestamo) throws BibliotecaException {
        String query = "INSERT INTO prestamos (id_usuario, id_libro, fecha_prestamo, fecha_devolucion) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, prestamo.getIdUsuario());
            pstmt.setInt(2, prestamo.getIdLibro());
            pstmt.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            pstmt.setDate(4, new java.sql.Date(prestamo.getFechaDevolucion().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al agregar préstamo: " + ex.getMessage());
        }
    }

    public void modificarPrestamo(PrestamoDTO prestamo) throws BibliotecaException {
        String query = "UPDATE prestamos SET id_usuario = ?, id_libro = ?, fecha_prestamo = ?, fecha_devolucion = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, prestamo.getIdUsuario());
            pstmt.setInt(2, prestamo.getIdLibro());
            pstmt.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            pstmt.setDate(4, new java.sql.Date(prestamo.getFechaDevolucion().getTime()));
            pstmt.setInt(5, prestamo.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al modificar préstamo: " + ex.getMessage());
        }
    }

    public void eliminarPrestamo(int id) throws BibliotecaException {
        String query = "DELETE FROM prestamos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al eliminar préstamo: " + ex.getMessage());
        }
    }

    public PrestamoDTO buscarPrestamo(int id) throws BibliotecaException {
        String query = "SELECT * FROM prestamos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new PrestamoDTO(rs.getInt("id"), rs.getInt("id_usuario"), rs.getInt("id_libro"),
                            rs.getDate("fecha_prestamo"), rs.getDate("fecha_devolucion"));
                } else {
                    throw new BibliotecaException("Préstamo con ID " + id + " no encontrado.");
                }
            }
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al buscar préstamo: " + ex.getMessage());
        }
    }

    public List<PrestamoDTO> listarPrestamos() throws BibliotecaException {
        String query = "SELECT * FROM prestamos";
        List<PrestamoDTO> prestamos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                prestamos.add(new PrestamoDTO(rs.getInt("id"), rs.getInt("id_usuario"), rs.getInt("id_libro"),
                        rs.getDate("fecha_prestamo"), rs.getDate("fecha_devolucion")));
            }
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al listar préstamos: " + ex.getMessage());
        }
        return prestamos;
    }
}