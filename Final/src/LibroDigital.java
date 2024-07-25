public class LibroDigital {
    private int id;
    private String titulo;
    private String autor;
    private String genero;

    public LibroDigital(int id, String titulo, String autor, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }
}