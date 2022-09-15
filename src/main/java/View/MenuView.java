
package View;

import Controller.MenuListener;
import Model.Model;
import java.awt.Color;
import javax.swing.*;


/**
 * Classe per la view del menu
 * @author Giacomo Orsucci & Francesco Di Carlo 4IC
 */
public class MenuView extends JFrame {
    
    private JPanel rootPanel;
    private JButton paramButton, suscettibiliButton, infettiButton, rimessiButton,infoButton,confrontoButton;
    private MenuListener menuListener = new MenuListener();
    private Model m;
    
    /**
     * Costruttore 
     */
    public MenuView(){
        
        super("Programma sul modello SIR");
        rootPanel = (JPanel) getContentPane();
        setBounds(50,100,400,400);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        //istanziamento bottoni
        paramButton = new JButton("Parametri");
        suscettibiliButton = new JButton("S(t)");
        infettiButton = new JButton("I(t)");
        rimessiButton = new JButton("R(t)");
        infoButton = new JButton("Info");
        confrontoButton = new JButton("Confronto");
        
        //settaggio dei bottoni
        paramButton.setBounds(getWidth()-130,20,100,50);
        suscettibiliButton.setBounds(75,120,100,50);
        infettiButton.setBounds(225,120,100,50);
        rimessiButton.setBounds(75,270,100,50);
        infoButton.setBounds(20,20,100,50);
        confrontoButton.setBounds(225,270,100,50);
        
        paramButton.addActionListener(menuListener);
        suscettibiliButton.addActionListener(menuListener);
        infettiButton.addActionListener(menuListener);
        rimessiButton.addActionListener(menuListener);
        confrontoButton.addActionListener(menuListener);
        infoButton.addActionListener(menuListener);
        
        
        //aggiungo i vari componenti al pannello
        rootPanel.add(paramButton);
        rootPanel.add(suscettibiliButton);
        rootPanel.add(infettiButton);
        rootPanel.add(rimessiButton);
        rootPanel.add(infoButton);
        rootPanel.add(confrontoButton);
        rootPanel.setBackground(Color.YELLOW);
    }
    
    //GETTER E SETTER
    public void setm(Model m) {this.m = m;}   
}
