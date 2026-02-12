
package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class VentaView extends JFrame {
    private JPanel panel;
    private JLabel lbl;
    private JTextField txt;
    
    public VentaView(){
        configurarFrame();
        initComponentes();
        eventos();
    }
    public void configurarFrame(){
        setTitle("Sistema de ventas | Registrar venta");
        setSize(800,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setLayout(new BorderLayout());
    }
    private void initComponentes(){
        
    }
    private void eventos(){
        
    }
}
