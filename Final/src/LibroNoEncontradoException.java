public class LibroNoEncontradoException extends BibliotecaException {
    public LibroNoEncontradoException(int id) {
        super("Libro con ID " + id + " no encontrado.");
    }
}