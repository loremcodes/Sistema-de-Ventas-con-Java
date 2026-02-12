
package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InicioView extends JFrame{
    JLabel label;
    public InicioView(){
        setTitle("Sistemas de Ventas | Inicio");
        setSize(600,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        inicioPanel();
    }
    public void inicioPanel(){
        label = new JLabel();
        label.setText("Conexión realizada con éxito");
        this.add(label);
    }
}
