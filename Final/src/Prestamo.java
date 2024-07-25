import java.util.Date;

public class Prestamo {
    private int id;
    private int idUsuario;
    private int idLibro;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public Prestamo(int id, int idUsuario, int idLibro, Date fechaPrestamo, Date fechaDevolucion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
}