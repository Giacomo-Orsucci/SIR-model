
package View;

import Controller.ParametriListener;
import Model.Model;
import java.awt.*;
import javax.swing.*;

/**
 * Classe per la view dei parametri
 * @author Giacomo Orsucci & Francesco Di Carlo 4IC
 */
public class ParametriView extends JFrame {
    
    private JPanel rootPanel, centerPanel;
    private JLabel param_n_label, param_i_label, param_s_label, param_a_label, param_b_label, inserimento;
    private JTextField param_n_field, param_i_field, param_s_field, param_a_field, param_b_field;
    private JButton applicaButton,incrementoAButton,decrementoAButton,incrementoBButton,decrementoBButton;
    private ParametriListener pL = new ParametriListener(this);
    private Model m;
    
    private boolean inseriti = false;
    
    /**
     * Costruttore 
     */
    public ParametriView(){
        
        super("Inserimento dei parametri al tempo t0");
        rootPanel = (JPanel) getContentPane();
        setBounds(500,100,400,500);
        setVisible(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        //istanziamento dei bottoni
        applicaButton = new JButton("Applica");
        incrementoAButton = new JButton("+");
        decrementoAButton = new JButton("-");
        incrementoBButton = new JButton("+");
        decrementoBButton = new JButton("-");
        
        //settaggi dei bottoni
        applicaButton.setPreferredSize(new Dimension(applicaButton.getWidth(), 50));
        applicaButton.addActionListener(pL);
        incrementoAButton.addActionListener(pL);
        incrementoBButton.addActionListener(pL);
        decrementoAButton.addActionListener(pL);
        decrementoBButton.addActionListener(pL);
        
        incrementoAButton.setName("IncrementaA");
        incrementoBButton.setName("IncrementaB");
        decrementoAButton.setName("DecrementaA");
        decrementoBButton.setName("DecrementaB");
        applicaButton.setName("Applica");
        
        incrementoAButton.setBounds(130,0,40,25);
        decrementoAButton.setBounds(130,35,40,25);
        incrementoBButton.setBounds(130,0,40,25);
        decrementoBButton.setBounds(130,35,40,25);
       
        
        //istanziamento dei pannelli
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5,2,30,15));
        
        //istanziamento dei label
        inserimento = new JLabel("Inserisci i parametri necessari");
        param_n_label = new JLabel("Numerosità della popolazione");
        param_i_label = new JLabel("Numero degli infetti");
        param_s_label = new JLabel("Numero dei suscettibili");
        param_a_label = new JLabel("ALPHA");
        param_b_label = new JLabel("BETA");
        
        //settaggio delle label
        inserimento.setPreferredSize(new Dimension(inserimento.getWidth(), 50));
        
        inserimento.setHorizontalAlignment(JLabel.CENTER);
        param_n_label.setHorizontalAlignment(JLabel.CENTER);
        param_i_label.setHorizontalAlignment(JLabel.CENTER);
        param_s_label.setHorizontalAlignment(JLabel.CENTER);
        param_a_label.setHorizontalAlignment(JLabel.CENTER);
        param_b_label.setHorizontalAlignment(JLabel.CENTER);
        
        //istanziamento dei field
        param_n_field = new JTextField("60000000");
        param_i_field = new JTextField("100");
        param_s_field = new JTextField("59999900");
        param_a_field = new JTextField("0.00000001");
        param_b_field = new JTextField("0.24");
        
        //settaggio dei pannelli
        centerPanel.add(param_n_label);
        centerPanel.add(param_n_field);
        centerPanel.add(param_i_label);
        centerPanel.add(param_i_field);
        centerPanel.add(param_s_label);
        centerPanel.add(param_s_field);
        centerPanel.add(param_a_label);
        centerPanel.add(param_a_field);
        centerPanel.add(param_b_label);
        centerPanel.add(param_b_field);
        
        //aggiungo i vari componenti al pannello
        rootPanel.add(inserimento, BorderLayout.NORTH);
        rootPanel.add(centerPanel, BorderLayout.CENTER);
        rootPanel.add(applicaButton, BorderLayout.SOUTH);
        param_a_label.add(incrementoAButton);
        param_a_label.add(decrementoAButton);
        param_b_label.add(incrementoBButton);
        param_b_label.add(decrementoBButton);
    }
    
    //GETTER E SETTER
    public JTextField getParam_n_field() {return param_n_field;}
    public JTextField getParam_i_field() {return param_i_field;}
    public JTextField getParam_s_field() {return param_s_field;}
    public JTextField getParam_a_field() {return param_a_field;}
    public JTextField getParam_b_field() {return param_b_field;}
    public void setm(Model m) {this.m = m;}
    public Model getm() {return m;}
    public boolean isInseriti() {return inseriti;}
    public void setInseriti(boolean inseriti) {this.inseriti = inseriti;}   
}

