import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionPrestamos {

    public void registrarPrestamo(Prestamo prestamo) throws SQLException {
        String query = "INSERT INTO prestamos (id_usuario, id_libro, fecha_prestamo, fecha_devolucion) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, prestamo.getIdUsuario());
            pstmt.setInt(2, prestamo.getIdLibro());
            pstmt.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            pstmt.setDate(4, prestamo.getFechaDevolucion() != null ? new java.sql.Date(prestamo.getFechaDevolucion().getTime()) : null);
            pstmt.executeUpdate();
        }
    }

    public void devolverLibro(int idPrestamo, Date fechaDevolucion) throws SQLException {
        String query = "UPDATE prestamos SET fecha_devolucion = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDate(1, new java.sql.Date(fechaDevolucion.getTime()));
            pstmt.setInt(2, idPrestamo);
            pstmt.executeUpdate();
        }
    }

    public List<Prestamo> listarPrestamos() throws SQLException {
        String query = "SELECT * FROM prestamos";
        List<Prestamo> prestamos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                prestamos.add(new Prestamo(
                        rs.getInt("id"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_libro"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion")
                ));
            }
        }
        return prestamos;
    }
}