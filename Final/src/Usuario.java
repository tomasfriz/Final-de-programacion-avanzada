public class Usuario extends Persona {
    private String email;

    public Usuario(int id, String nombre, String email) {
        super(id, nombre);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getDetalles() {
        return "ID: " + getId() + ", Nombre: " + getNombre() + ", Email: " + email;
    }
}