
package Controller;

import Model.Model;
import View.ConfrontoView;
import View.Grafica;
import View.InfettiView;
import View.InfoView;
import View.ParametriView;
import View.RimossiView;
import View.SuscettibiliView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Classe per la gestione degli ActionListener del menu
 * @author Giacomo Orsucci & Francesco Di Carlo 4IC
 */
public class MenuListener implements ActionListener{
    
    private ParametriView pV;
    private ParametriView pVPrecedente;
    
    private SuscettibiliView sV;
    private SuscettibiliView sVPrecedente;
    
    private InfettiView iV;
    private InfettiView iVPrecedente;
    
    private RimossiView rV;
    private RimossiView rVPrecedente;
    
    private ConfrontoView cV;
    private ConfrontoView cVPrecedente;
    
    private InfoView infoV;
    private InfoView infoVPrecedente;
    
    private Model m;
    
    private Grafica g;
    
    private int primaVolta;
    
    /**
     * Metodo dell'interfaccia per la gestione degli ActionListener
     * @param e, origine dell'evento 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        JButton origine = (JButton) e.getSource();
        
        /*
            per differenziare le azioni in base
            al bottone premuto
        */
        switch(origine.getText()){
            
            case "Parametri":
                pVPrecedente = pV;
                JOptionPane.showMessageDialog(null, "Inserire i parametri e premere <applica>","Parametri azzerati, completare l'inserimento", JOptionPane.WARNING_MESSAGE);
                pV = new ParametriView(); 
                if(pVPrecedente != null) //chiudo la view precedente
                    pVPrecedente.dispose();
                
                break;
                
            case "Info":
                infoVPrecedente = infoV;
                infoV = new InfoView(); 
                if(infoVPrecedente != null) //chiudo la view precedente
                    infoVPrecedente.dispose();
                
                break;
            
            case "S(t)": //suscettibili
                    if(pV != null){ //controllo se sono stati inseriri i parametri
                        if(pV.isInseriti()){
                        do{
                            sVPrecedente = sV;
                            sV = new SuscettibiliView();
                            if(sVPrecedente != null){
                                sVPrecedente.dispose(); //chiudo la view precedente
                            }   
                            /*
                                prendo ed inserisco il model di riferimento
                                nella view opportuna
                            */
                            this.m = pV.getm();
                            m.setsV(sV);
                            //creo il panello della grafica nell'apposita view
                            sV.setGraficaPanel(new Grafica("Suscettibili", m));
                            primaVolta++;
                        }while(primaVolta ==1);//obbligo a fare tutto quanto due volte perchè non sempre viene disegnato il grafico se no
                        primaVolta = 0;
                        }
                        else{
                        JOptionPane.showMessageDialog(null, "Inserire prima i parametri", "ATTENZIONE!", JOptionPane.WARNING_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Inserire prima i parametri", "ATTENZIONE!", JOptionPane.WARNING_MESSAGE);
                    }
                break;
            
            case "I(t)":
                
                if(pV != null){
                    if(pV.isInseriti()){
                        do{
                            iVPrecedente = iV;
                            iV = new InfettiView();
                            if(iVPrecedente != null){
                                iVPrecedente.dispose();
                            }   
                            this.m = pV.getm();
                            m.setiV(iV);
                            iV.setGraficaPanel(new Grafica("Infetti", m));
                            primaVolta++;
                        }while(primaVolta ==1);
                        primaVolta = 0;
                    }else{
                    JOptionPane.showMessageDialog(null, "Inserire prima i parametri", "ATTENZIONE!", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Inserire prima i parametri", "ATTENZIONE!", JOptionPane.WARNING_MESSAGE);
                    }
                
                break;
                
            case "R(t)":
                
                if(pV != null){
                    if(pV.isInseriti()){
                    do{
                        rVPrecedente = rV;
                        rV = new RimossiView();
                        if(rVPrecedente != null){
                            rVPrecedente.dispose();
                        }   
                        this.m = pV.getm();
                        m.setrV(rV);
                        rV.setGraficaPanel(new Grafica("Rimossi", m));
                        primaVolta++;
                    }while(primaVolta ==1);
                    primaVolta = 0;
                    }else{
                        JOptionPane.showMessageDialog(null, "Inserire prima i parametri", "ATTENZIONE!", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Inserire prima i parametri", "ATTENZIONE!", JOptionPane.WARNING_MESSAGE);
                }
                
                break;
            case "Confronto":
                
                if(pV != null){
                    if(pV.isInseriti()){
                    do{
                        cVPrecedente = cV;
                        cV = new ConfrontoView();
                        if(cVPrecedente != null){
                            cVPrecedente.dispose();
                        }   
                        this.m = pV.getm();
                        m.setcV(cV);
                        cV.setGraficaPanel(new Grafica("Confronto", m));
                        primaVolta++;
                    }while(primaVolta ==1);
                    primaVolta = 0;
                    }else{
                        JOptionPane.showMessageDialog(null, "Inserire prima i parametri", "ATTENZIONE!", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Inserire prima i parametri", "ATTENZIONE!", JOptionPane.WARNING_MESSAGE);
                }
                
                break;
        }
    } 
}
