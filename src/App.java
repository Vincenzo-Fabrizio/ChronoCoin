import javax.swing.SwingUtilities;
import ui.MenuGUI;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuGUI().setVisible(true);
        });
    }

};
