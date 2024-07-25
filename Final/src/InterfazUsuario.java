import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InterfazUsuario extends JFrame {
    private GestionUsuarios gestionUsuarios;
    private GestionLibros gestionLibros;
    private GestionPrestamos gestionPrestamos;

    public InterfazUsuario() {
        // Inicializar las clases de gestión
        gestionUsuarios = new GestionUsuarios();
        gestionLibros = new GestionLibros();
        gestionPrestamos = new GestionPrestamos();

        // Configuración básica del JFrame
        setTitle("Sistema de Gestión de Biblioteca Digital");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el menú
        JMenuBar menuBar = new JMenuBar();

        // Menú de gestión de usuarios
        JMenu menuUsuarios = new JMenu("Gestión de Usuarios");
        JMenuItem menuItemAgregarUsuario = new JMenuItem("Agregar Usuario");
        JMenuItem menuItemModificarUsuario = new JMenuItem("Modificar Usuario");
        JMenuItem menuItemEliminarUsuario = new JMenuItem("Eliminar Usuario");

        menuUsuarios.add(menuItemAgregarUsuario);
        menuUsuarios.add(menuItemModificarUsuario);
        menuUsuarios.add(menuItemEliminarUsuario);
        menuBar.add(menuUsuarios);

        // Menú de gestión de libros
        JMenu menuLibros = new JMenu("Gestión de Libros");
        JMenuItem menuItemAgregarLibro = new JMenuItem("Agregar Libro");
        JMenuItem menuItemModificarLibro = new JMenuItem("Modificar Libro");
        JMenuItem menuItemEliminarLibro = new JMenuItem("Eliminar Libro");
        JMenuItem menuItemBuscarLibro = new JMenuItem("Buscar Libro");

        menuLibros.add(menuItemAgregarLibro);
        menuLibros.add(menuItemModificarLibro);
        menuLibros.add(menuItemEliminarLibro);
        menuLibros.add(menuItemBuscarLibro);
        menuBar.add(menuLibros);

        // Menú de préstamos
        JMenu menuPrestamos = new JMenu("Préstamos");
        JMenuItem menuItemRegistrarPrestamo = new JMenuItem("Registrar Préstamo");
        JMenuItem menuItemDevolverLibro = new JMenuItem("Devolver Libro");

        menuPrestamos.add(menuItemRegistrarPrestamo);
        menuPrestamos.add(menuItemDevolverLibro);
        menuBar.add(menuPrestamos);

        // Menú de reportes
        JMenu menuReportes = new JMenu("Reportes");
        JMenuItem menuItemGenerarReporte = new JMenuItem("Generar Reporte");

        menuReportes.add(menuItemGenerarReporte);
        menuBar.add(menuReportes);

        setJMenuBar(menuBar);

        // Configurar listeners para los ítems del menú
        configurarListeners(menuItemAgregarUsuario, menuItemModificarUsuario, menuItemEliminarUsuario,
                menuItemAgregarLibro, menuItemModificarLibro, menuItemEliminarLibro, menuItemBuscarLibro,
                menuItemRegistrarPrestamo, menuItemDevolverLibro, menuItemGenerarReporte);
    }

    private void configurarListeners(JMenuItem... menuItems) {
        for (JMenuItem item : menuItems) {
            item.addActionListener(new MenuActionListener());
        }
    }

    // Clase interna para manejar las acciones del menú
    private class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();
            try {
                switch (comando) {
                    case "Agregar Usuario":
                        mostrarDialogoAgregarUsuario();
                        break;
                    case "Modificar Usuario":
                        mostrarDialogoModificarUsuario();
                        break;
                    case "Eliminar Usuario":
                        mostrarDialogoEliminarUsuario();
                        break;
                    case "Agregar Libro":
                        mostrarDialogoAgregarLibro();
                        break;
                    case "Modificar Libro":
                        mostrarDialogoModificarLibro();
                        break;
                    case "Eliminar Libro":
                        mostrarDialogoEliminarLibro();
                        break;
                    case "Buscar Libro":
                        mostrarDialogoBuscarLibro();
                        break;
                    case "Registrar Préstamo":
                        mostrarDialogoRegistrarPrestamo();
                        break;
                    case "Devolver Libro":
                        mostrarDialogoDevolverLibro();
                        break;
                    case "Generar Reporte":
                        mostrarDialogoGenerarReporte();
                        break;
                    default:
                        break;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(InterfazUsuario.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarDialogoAgregarUsuario() throws SQLException {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del Usuario:");
        String email = JOptionPane.showInputDialog(this, "Email del Usuario:");
        gestionUsuarios.agregarUsuario(new Usuario(0, nombre, email));
        JOptionPane.showMessageDialog(this, "Usuario agregado exitosamente.");
    }

    private void mostrarDialogoModificarUsuario() throws SQLException {
        int id = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del Usuario a Modificar:"));
        String nombre = JOptionPane.showInputDialog(this, "Nuevo Nombre del Usuario:");
        String email = JOptionPane.showInputDialog(this, "Nuevo Email del Usuario:");
        gestionUsuarios.modificarUsuario(new Usuario(id, nombre, email));
        JOptionPane.showMessageDialog(this, "Usuario modificado exitosamente.");
    }

    private void mostrarDialogoEliminarUsuario() throws SQLException {
        int id = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del Usuario a Eliminar:"));
        gestionUsuarios.eliminarUsuario(id);
        JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente.");
    }

    private void mostrarDialogoAgregarLibro() throws SQLException {
        String titulo = JOptionPane.showInputDialog(this, "Título del Libro:");
        String autor = JOptionPane.showInputDialog(this, "Autor del Libro:");
        String genero = JOptionPane.showInputDialog(this, "Género del Libro:");
        gestionLibros.agregarLibro(new LibroDigital(0, titulo, autor, genero));
        JOptionPane.showMessageDialog(this, "Libro agregado exitosamente.");
    }

    private void mostrarDialogoModificarLibro() throws SQLException {
        int id = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del Libro a Modificar:"));
        String titulo = JOptionPane.showInputDialog(this, "Nuevo Título del Libro:");
        String autor = JOptionPane.showInputDialog(this, "Nuevo Autor del Libro:");
        String genero = JOptionPane.showInputDialog(this, "Nuevo Género del Libro:");
        gestionLibros.modificarLibro(new LibroDigital(id, titulo, autor, genero));
        JOptionPane.showMessageDialog(this, "Libro modificado exitosamente.");
    }

    private void mostrarDialogoEliminarLibro() throws SQLException {
        int id = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del Libro a Eliminar:"));
        gestionLibros.eliminarLibro(id);
        JOptionPane.showMessageDialog(this, "Libro eliminado exitosamente.");
    }

    private void mostrarDialogoBuscarLibro() throws SQLException {
        int id = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del Libro a Buscar:"));
        LibroDigital libro = gestionLibros.buscarLibro(id);
        if (libro != null) {
            JOptionPane.showMessageDialog(this, "Libro Encontrado: \nTítulo: " + libro.getTitulo() + "\nAutor: " + libro.getAutor() + "\nGénero: " + libro.getGenero());
        } else {
            JOptionPane.showMessageDialog(this, "Libro no encontrado.");
        }
    }

    private void mostrarDialogoRegistrarPrestamo() throws SQLException {
        int idUsuario = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del Usuario:"));
        int idLibro = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del Libro:"));
        gestionPrestamos.registrarPrestamo(new Prestamo(0, idUsuario, idLibro, new java.util.Date(), null));
        JOptionPane.showMessageDialog(this, "Préstamo registrado exitosamente.");
    }

    private void mostrarDialogoDevolverLibro() throws SQLException {
        int idPrestamo = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del Préstamo:"));
        gestionPrestamos.devolverLibro(idPrestamo, new java.util.Date());
        JOptionPane.showMessageDialog(this, "Libro devuelto exitosamente.");
    }

    private void mostrarDialogoGenerarReporte() throws SQLException {
        // Aquí puedes implementar la lógica para generar reportes
        JOptionPane.showMessageDialog(this, "Generando reporte...");
    }
}