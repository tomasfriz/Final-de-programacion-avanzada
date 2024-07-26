import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class InterfazUsuario extends JFrame {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private LibroDAO libroDAO = new LibroDAO();
    private PrestamoDAO prestamoDAO = new PrestamoDAO();

    public InterfazUsuario() {
        setTitle("Sistema de Gestión de Biblioteca Digital");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuUsuarios = new JMenu("Usuarios");
        JMenuItem itemAgregarUsuario = new JMenuItem("Agregar Usuario");
        JMenuItem itemModificarUsuario = new JMenuItem("Modificar Usuario");
        JMenuItem itemEliminarUsuario = new JMenuItem("Eliminar Usuario");
        JMenuItem itemBuscarUsuario = new JMenuItem("Buscar Usuario");
        JMenuItem itemListarUsuarios = new JMenuItem("Listar Usuarios");
        menuUsuarios.add(itemAgregarUsuario);
        menuUsuarios.add(itemModificarUsuario);
        menuUsuarios.add(itemEliminarUsuario);
        menuUsuarios.add(itemBuscarUsuario);
        menuUsuarios.add(itemListarUsuarios);
        menuBar.add(menuUsuarios);

        JMenu menuLibros = new JMenu("Libros");
        JMenuItem itemAgregarLibro = new JMenuItem("Agregar Libro");
        JMenuItem itemModificarLibro = new JMenuItem("Modificar Libro");
        JMenuItem itemEliminarLibro = new JMenuItem("Eliminar Libro");
        JMenuItem itemBuscarLibro = new JMenuItem("Buscar Libro");
        JMenuItem itemListarLibros = new JMenuItem("Listar Libros");
        menuLibros.add(itemAgregarLibro);
        menuLibros.add(itemModificarLibro);
        menuLibros.add(itemEliminarLibro);
        menuLibros.add(itemBuscarLibro);
        menuLibros.add(itemListarLibros);
        menuBar.add(menuLibros);

        JMenu menuPrestamos = new JMenu("Préstamos");
        JMenuItem itemAgregarPrestamo = new JMenuItem("Agregar Préstamo");
        JMenuItem itemModificarPrestamo = new JMenuItem("Modificar Préstamo");
        JMenuItem itemEliminarPrestamo = new JMenuItem("Eliminar Préstamo");
        JMenuItem itemBuscarPrestamo = new JMenuItem("Buscar Préstamo");
        JMenuItem itemListarPrestamos = new JMenuItem("Listar Préstamos");
        menuPrestamos.add(itemAgregarPrestamo);
        menuPrestamos.add(itemModificarPrestamo);
        menuPrestamos.add(itemEliminarPrestamo);
        menuPrestamos.add(itemBuscarPrestamo);
        menuPrestamos.add(itemListarPrestamos);
        menuBar.add(menuPrestamos);

        setJMenuBar(menuBar);

        // Usuarios
        itemAgregarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Nombre:");
                String email = JOptionPane.showInputDialog("Email:");
                try {
                    usuarioDAO.agregarUsuario(new UsuarioDTO(0, nombre, email));
                    JOptionPane.showMessageDialog(null, "Usuario agregado con éxito");
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar usuario: " + ex.getMessage());
                }
            }
        });

        itemModificarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID del usuario a modificar:"));
                String nombre = JOptionPane.showInputDialog("Nuevo nombre:");
                String email = JOptionPane.showInputDialog("Nuevo email:");
                try {
                    usuarioDAO.modificarUsuario(new UsuarioDTO(id, nombre, email));
                    JOptionPane.showMessageDialog(null, "Usuario modificado con éxito");
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar usuario: " + ex.getMessage());
                }
            }
        });

        itemEliminarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID del usuario a eliminar:"));
                try {
                    usuarioDAO.eliminarUsuario(id);
                    JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito");
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + ex.getMessage());
                }
            }
        });

        itemBuscarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID del usuario a buscar:"));
                try {
                    UsuarioDTO usuario = usuarioDAO.buscarUsuario(id);
                    JOptionPane.showMessageDialog(null, "ID: " + usuario.getId() + "\nNombre: " + usuario.getNombre() + "\nEmail: " + usuario.getEmail());
                } catch (UsuarioNoEncontradoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar usuario: " + ex.getMessage());
                }
            }
        });

        itemListarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<UsuarioDTO> usuarios = usuarioDAO.listarUsuarios();
                    StringBuilder sb = new StringBuilder();
                    for (UsuarioDTO usuario : usuarios) {
                        sb.append("ID: ").append(usuario.getId()).append(", Nombre: ").append(usuario.getNombre()).append(", Email: ").append(usuario.getEmail()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al listar usuarios: " + ex.getMessage());
                }
            }
        });

        // Libros
        itemAgregarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog("Título:");
                String autor = JOptionPane.showInputDialog("Autor:");
                String genero = JOptionPane.showInputDialog("Género:");
                try {
                    libroDAO.agregarLibro(new LibroDTO(0, titulo, autor, genero));
                    JOptionPane.showMessageDialog(null, "Libro agregado con éxito");
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar libro: " + ex.getMessage());
                }
            }
        });

        itemModificarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID del libro a modificar:"));
                String titulo = JOptionPane.showInputDialog("Nuevo título:");
                String autor = JOptionPane.showInputDialog("Nuevo autor:");
                String genero = JOptionPane.showInputDialog("Nuevo género:");
                try {
                    libroDAO.modificarLibro(new LibroDTO(id, titulo, autor, genero));
                    JOptionPane.showMessageDialog(null, "Libro modificado con éxito");
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar libro: " + ex.getMessage());
                }
            }
        });

        itemEliminarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID del libro a eliminar:"));
                try {
                    libroDAO.eliminarLibro(id);
                    JOptionPane.showMessageDialog(null, "Libro eliminado con éxito");
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar libro: " + ex.getMessage());
                }
            }
        });

        itemBuscarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID del libro a buscar:"));
                try {
                    LibroDTO libro = libroDAO.buscarLibro(id);
                    JOptionPane.showMessageDialog(null, "ID: " + libro.getId() + "\nTítulo: " + libro.getTitulo() + "\nAutor: " + libro.getAutor() + "\nGénero: " + libro.getGenero());
                } catch (LibroNoEncontradoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar libro: " + ex.getMessage());
                }
            }
        });

        itemListarLibros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<LibroDTO> libros = libroDAO.listarLibros();
                    StringBuilder sb = new StringBuilder();
                    for (LibroDTO libro : libros) {
                        sb.append("ID: ").append(libro.getId()).append(", Título: ").append(libro.getTitulo()).append(", Autor: ").append(libro.getAutor()).append(", Género: ").append(libro.getGenero()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al listar libros: " + ex.getMessage());
                }
            }
        });

        // Préstamos
        itemAgregarPrestamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idUsuario = Integer.parseInt(JOptionPane.showInputDialog("ID del usuario:"));
                int idLibro = Integer.parseInt(JOptionPane.showInputDialog("ID del libro:"));
                Date fechaPrestamo = new Date();
                Date fechaDevolucion = new Date(fechaPrestamo.getTime() + (7 * 24 * 60 * 60 * 1000)); // 7 días después
                try {
                    prestamoDAO.agregarPrestamo(new PrestamoDTO(0, idUsuario, idLibro, fechaPrestamo, fechaDevolucion));
                    JOptionPane.showMessageDialog(null, "Préstamo agregado con éxito");
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar préstamo: " + ex.getMessage());
                }
            }
        });

        itemModificarPrestamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID del préstamo a modificar:"));
                int idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Nuevo ID del usuario:"));
                int idLibro = Integer.parseInt(JOptionPane.showInputDialog("Nuevo ID del libro:"));
                Date fechaPrestamo = new Date();
                Date fechaDevolucion = new Date(fechaPrestamo.getTime() + (7 * 24 * 60 * 60 * 1000)); // 7 días después
                try {
                    prestamoDAO.modificarPrestamo(new PrestamoDTO(id, idUsuario, idLibro, fechaPrestamo, fechaDevolucion));
                    JOptionPane.showMessageDialog(null, "Préstamo modificado con éxito");
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar préstamo: " + ex.getMessage());
                }
            }
        });

        itemEliminarPrestamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID del préstamo a eliminar:"));
                try {
                    prestamoDAO.eliminarPrestamo(id);
                    JOptionPane.showMessageDialog(null, "Préstamo eliminado con éxito");
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar préstamo: " + ex.getMessage());
                }
            }
        });

        itemBuscarPrestamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID del préstamo a buscar:"));
                try {
                    PrestamoDTO prestamo = prestamoDAO.buscarPrestamo(id);
                    JOptionPane.showMessageDialog(null, "ID: " + prestamo.getId() + "\nID Usuario: " + prestamo.getIdUsuario() + "\nID Libro: " + prestamo.getIdLibro() + "\nFecha Préstamo: " + prestamo.getFechaPrestamo() + "\nFecha Devolución: " + prestamo.getFechaDevolucion());
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar préstamo: " + ex.getMessage());
                }
            }
        });

        itemListarPrestamos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<PrestamoDTO> prestamos = prestamoDAO.listarPrestamos();
                    StringBuilder sb = new StringBuilder();
                    for (PrestamoDTO prestamo : prestamos) {
                        sb.append("ID: ").append(prestamo.getId()).append(", ID Usuario: ").append(prestamo.getIdUsuario()).append(", ID Libro: ").append(prestamo.getIdLibro()).append(", Fecha Préstamo: ").append(prestamo.getFechaPrestamo()).append(", Fecha Devolución: ").append(prestamo.getFechaDevolucion()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                } catch (BibliotecaException ex) {
                    JOptionPane.showMessageDialog(null, "Error al listar préstamos: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }
}