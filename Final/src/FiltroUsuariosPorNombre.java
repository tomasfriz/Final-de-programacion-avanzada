public class FiltroUsuariosPorNombre implements EstrategiaFiltro<UsuarioDTO> {
    private String nombre;

    public FiltroUsuariosPorNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean filtrar(UsuarioDTO usuario) {
        return usuario.getNombre().equalsIgnoreCase(nombre);
    }
}