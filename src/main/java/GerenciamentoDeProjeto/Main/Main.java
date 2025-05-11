package GerenciamentoDeProjeto.Main;

import GerenciamentoDeProjeto.Swing.View.TelaInicial;
import GerenciamentoDeProjeto.Swing.Controller.InicialController;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaInicial telaInicial = new TelaInicial();
            InicialController controller = new InicialController(telaInicial);
            telaInicial.setVisible(true);
        });
    }
}
