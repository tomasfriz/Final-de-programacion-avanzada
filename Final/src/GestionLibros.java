import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionLibros {

    public void agregarLibro(LibroDigital libro) throws SQLException {
        String query = "INSERT INTO libros (titulo, autor, genero) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setString(3, libro.getGenero());
            pstmt.executeUpdate();
        }
    }

    public void modificarLibro(LibroDigital libro) throws SQLException {
        String query = "UPDATE libros SET titulo = ?, autor = ?, genero = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setString(3, libro.getGenero());
            pstmt.setInt(4, libro.getId());
            pstmt.executeUpdate();
        }
    }

    public void eliminarLibro(int id) throws SQLException {
        String query = "DELETE FROM libros WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public LibroDigital buscarLibro(int id) throws SQLException {
        String query = "SELECT * FROM libros WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new LibroDigital(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getString("genero"));
            }
        }
        return null;
    }

    public List<LibroDigital> listarLibros() throws SQLException {
        String query = "SELECT * FROM libros";
        List<LibroDigital> libros = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                libros.add(new LibroDigital(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getString("genero")));
            }
        }
        return libros;
    }
}