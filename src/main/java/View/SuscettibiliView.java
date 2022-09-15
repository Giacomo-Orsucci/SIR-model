
package View;


import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * Classe per la view dei suscettibili
 * @author Giacomo Orsucci & Francesco Di Carlo 4IC
 */
public class SuscettibiliView extends JFrame{
    
    private JPanel calcoliPanel, destroPanel, riepilogoPanel, contagiabilitaPanel;
    private Grafica graficaPanel;
    private JTextArea risultatiArea = new JTextArea("Suscettibili nel tempo S(t)\n");
    private JTextArea contagiabilitaArea = new JTextArea();
    private JScrollPane risultatiScroll = new JScrollPane(risultatiArea);
    private JScrollPane contagiabilitaScroll = new JScrollPane(contagiabilitaArea);
    private JLabel suscettibiliInizialiLabel;
    private JLabel suscettibiliFinaliLabel;
    private JLabel tempoTotLabel;
    
    /**
     * Costruttore
     */
    public SuscettibiliView(){
        super("Andamento della funzione dei suscettibili");
        setBounds(600,100,600,500);
        setVisible(true);
        setLayout(new GridLayout(2,0));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        //istanziamento dei pannelli
        calcoliPanel = new JPanel();
        destroPanel = new JPanel();
        riepilogoPanel = new JPanel();
        contagiabilitaPanel = new JPanel();
        
        //settaggio dei pannelli
        calcoliPanel.setLayout(new GridLayout(1,2));
        destroPanel.setLayout(new GridLayout(2,1));
        riepilogoPanel.setLayout(null);
        contagiabilitaPanel.setLayout(new GridLayout(1,1));
        
        //istanziamento delle label
        suscettibiliInizialiLabel = new JLabel("Ciao");
        suscettibiliFinaliLabel = new JLabel("Ciao");
        tempoTotLabel = new JLabel("Ciao");
        
        //settaggio della label
        suscettibiliInizialiLabel.setBounds(10, 10, 300, 20);
        suscettibiliFinaliLabel.setBounds(10, 40, 300, 20);        
        tempoTotLabel.setBounds(10, 70, 300, 20);
        
        //settaggio degli scrollPane
        risultatiArea.setMaximumSize(new Dimension(300,500));
        risultatiScroll.setVisible(true);
        risultatiArea.setEditable(false);
        contagiabilitaArea.append("Contagiabilità nel tempo\n");
        
        contagiabilitaArea.setMaximumSize(new Dimension(200,300));
        contagiabilitaScroll.setVisible(true);
        contagiabilitaArea.setEditable(false);
      
        //aggiunta dei vari componenti ai vari pannelli
        contagiabilitaPanel.add(contagiabilitaScroll); 
        
        destroPanel.add(riepilogoPanel);
        destroPanel.add(contagiabilitaPanel);
        
        calcoliPanel.add(risultatiScroll);
        calcoliPanel.add(destroPanel);
        
        riepilogoPanel.add(suscettibiliInizialiLabel);
        riepilogoPanel.add(suscettibiliFinaliLabel);
        riepilogoPanel.add(tempoTotLabel);
    }
    
    /**
    * Metodo per settare il pannello grafico in maniera appropriata
    * @param graficaPanel, pannello della grafica 
    */
    public void setGraficaPanel(Grafica graficaPanel) {
        this.graficaPanel = graficaPanel;
        add(graficaPanel);
        add(calcoliPanel);
    }
    
    //GETTER E SETTER
    public JTextArea getRisultatiArea() {return risultatiArea;}
    public JLabel getSuscettibiliInizialiLabel() {return suscettibiliInizialiLabel; }
    public JLabel getSuscettibiliFinaliLabel() {return suscettibiliFinaliLabel;}
    public JLabel getTempoTotLabel() {return tempoTotLabel;}
    public JTextArea getContagiabilitaArea() {return contagiabilitaArea;}
}
