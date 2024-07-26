public class UsuarioNoEncontradoException extends BibliotecaException {
    public UsuarioNoEncontradoException(int id) {
        super("Usuario con ID " + id + " no encontrado.");
    }
}