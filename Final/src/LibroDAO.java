import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    public void agregarLibro(LibroDTO libro) throws BibliotecaException {
        String query = "INSERT INTO libros (titulo, autor, genero) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setString(3, libro.getGenero());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al agregar libro: " + ex.getMessage());
        }
    }

    public void modificarLibro(LibroDTO libro) throws BibliotecaException {
        String query = "UPDATE libros SET titulo = ?, autor = ?, genero = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setString(3, libro.getGenero());
            pstmt.setInt(4, libro.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al modificar libro: " + ex.getMessage());
        }
    }

    public void eliminarLibro(int id) throws BibliotecaException {
        String query = "DELETE FROM libros WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al eliminar libro: " + ex.getMessage());
        }
    }

    public LibroDTO buscarLibro(int id) throws LibroNoEncontradoException, BibliotecaException {
        String query = "SELECT * FROM libros WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new LibroDTO(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getString("genero"));
                } else {
                    throw new LibroNoEncontradoException(id);
                }
            }
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al buscar libro: " + ex.getMessage());
        }
    }

    public List<LibroDTO> listarLibros() throws BibliotecaException {
        String query = "SELECT * FROM libros";
        List<LibroDTO> libros = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                libros.add(new LibroDTO(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getString("genero")));
            }
        } catch (SQLException ex) {
            throw new BibliotecaException("Error al listar libros: " + ex.getMessage());
        }
        return libros;
    }
}