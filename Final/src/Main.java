import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazUsuario interfaz = new InterfazUsuario();
            interfaz.setVisible(true);
        });
    }
}