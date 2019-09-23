import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

public class Ventana extends JFrame{
    public JPanel panel;
    public Ventana(){
        setSize(500,500);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(200,200));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Casino CNC");

        // PANEL 
        panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);
        
        menuPrincipal();
    }

    private void clearPanel(JPanel panel){
        panel.removeAll();
        panel.updateUI();
    }
    
    private void menuPrincipal(){

        // TITULO
        JLabel titulo = new JLabel("CASINO CNC",SwingConstants.CENTER);
        titulo.setBounds(175,10,130,30);
        titulo.setForeground(Color.RED);
        titulo.setFont(new Font(null,Font.BOLD,20));
        panel.add(titulo);

        // BOTONES  
        botonGenerala();
        botonBlackjack();
        botonSalir();

    }
    private void menuGenerala(){
        // TITULO
        tituloGenerala();

        // BOTONES  
        botonJugar();
        botonMenuPrincipal();
        botonSalir();
    }
    private void jugarGenerala(){
        tituloGenerala();
        
    }
    //ETIQUETAS
    private void tituloGenerala(){
        JLabel titulo = new JLabel("GENERALA",SwingConstants.CENTER);
        titulo.setBounds(175,10,130,30);
        titulo.setForeground(Color.RED);
        titulo.setFont(new Font(null,Font.BOLD,20));
        panel.add(titulo);
    }
    // BOTONES
    private void botonGenerala(){
        //BOTON GENERALA
        JButton buttonGenerala = new JButton();
        buttonGenerala.setBounds(175,100,130,40);
        buttonGenerala.setText("GENERALA");
        panel.add(buttonGenerala);
        
        ActionListener listenerButtonGenerala = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPanel(panel);
                menuGenerala();
            }
        };
        buttonGenerala.addActionListener(listenerButtonGenerala);
    }
    private void botonBlackjack(){
        //BOTON BLACKJACK
        JButton buttonBlackjack = new JButton();
        buttonBlackjack.setBounds(175,200,130,40);
        buttonBlackjack.setText("BLACKJACK");
        panel.add(buttonBlackjack);

        ActionListener listenerButtonBlackjack = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPanel(panel);
                
            }
        };
        buttonBlackjack.addActionListener(listenerButtonBlackjack);
    }
    private void botonSalir(){
        //BOTON SALIR
        JButton buttonExit = new JButton();
        buttonExit.setBounds(175,300,130,40);
        buttonExit.setText("SALIR");
        panel.add(buttonExit);

        ActionListener listenerButtonExit = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();//cierra el frame
            }
        };
        buttonExit.addActionListener(listenerButtonExit);
    }

    private void botonJugar(){
        JButton buttonJugar = new JButton();
        buttonJugar.setBounds(175,100,130,40);
        buttonJugar.setText("JUGAR");
        panel.add(buttonJugar);
        
        ActionListener listenerButtonJugar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPanel(panel);
                jugarGenerala();
            }
        };
        buttonJugar.addActionListener(listenerButtonJugar);
    }
    private void botonMenuPrincipal(){
        JButton buttonMenuPrincipal = new JButton();
        buttonMenuPrincipal.setBounds(175,200,130,40);
        buttonMenuPrincipal.setText("VOLVER AL MENU");
        panel.add(buttonMenuPrincipal);

        ActionListener listenerButtonMenuPrincipal = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPanel(panel);
                menuPrincipal();  
            }
        };
        buttonMenuPrincipal.addActionListener(listenerButtonMenuPrincipal);
    }
    
    
}