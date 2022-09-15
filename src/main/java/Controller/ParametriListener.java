
package Controller;

import Model.Model;
import View.ParametriView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Classe per la gestione degli ActionListener dei parametri
 * @author Giacomo Orsucci & Francesco Di Carlo 4IC
 */
public class ParametriListener implements ActionListener{
    
    private ParametriView pV;
    private Model m;
    
   
    /**
     * Costruttore parametrizzato
     * @param pV, View di riferimento 
     */
    public ParametriListener(ParametriView pV){
        
       this.pV = pV;
        
    }

    /**
     * Metodo dell'interfaccia ActionListener per la gestione degli eventi
     * @param e, origine dell'evento 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        JButton origine = (JButton) e.getSource();
        
        String appoggioN;
        String appoggioI;
        String appoggioS;
        String appoggioA;
        String appoggioB;
        String appoggioAIncrementoString;
        String appoggioBIncrementoString;
        
        
        long parametroN = 0;
        long parametroI = 0;
        long parametroS = 0;
        
        double parametroA = 0;
        double parametroB = 0;
        
        int appoggio;
        
        double incremento;
        double appoggioAIncrementoDouble = 0;
        
        float r_0 = 0;
        
        /*
            per controllare che l'inserimento dei parametri
            sia stato effettuato correttamente
        */
        boolean eccezione = false;
        
        switch(origine.getName()){ //differenzio le azioni in base al bottone
            
            case "IncrementaA": //incrementa di 1 l'ultima cifra di alpha
                
                appoggioA = pV.getParam_a_field().getText();
                appoggioAIncrementoString = appoggioA.substring(appoggioA.length()-1);
                appoggio = Integer.parseInt(appoggioAIncrementoString);

                if(appoggio <9 ){   
                    appoggio +=1;
                    appoggioAIncrementoString = appoggioA.substring(0, appoggioA.length()-1) + appoggio;
                    pV.getParam_a_field().setText(appoggioAIncrementoString);
                }
                else{
                    JOptionPane.showMessageDialog(null, "I bottoni permettono di incrementare o decrementare di 1 l'ultima cifra decimale, ma solo quando aggiornandola non si passa di unità", 
                            "Informazioni sull'incremento ed il decremento", JOptionPane.WARNING_MESSAGE);
                }
                eccezione = true;
                break;
                
            case "DecrementaA": //decrementa di 1 l'ultima cifra di alpha
                
                appoggioA = pV.getParam_a_field().getText();
                appoggioAIncrementoString = appoggioA.substring(appoggioA.length()-1);
                appoggio = Integer.parseInt(appoggioAIncrementoString);

                if(appoggio >1 && appoggio <=9){   
                    appoggio -=1;
                    appoggioAIncrementoString = appoggioA.substring(0, appoggioA.length()-1) + appoggio;
                    pV.getParam_a_field().setText(appoggioAIncrementoString);
                }
                else{
                    JOptionPane.showMessageDialog(null, "I bottoni permettono di incrementare o decrementare di 1 l'ultima cifra decimale, ma solo quando aggiornandola non si passa di unità", 
                            "Informazioni sull'incremento ed il decremento", JOptionPane.WARNING_MESSAGE);
                }
                eccezione = true;
                break;
                
            case "IncrementaB": //incrementa di 1 l'ultima cifra di beta
                
                appoggioB = pV.getParam_b_field().getText();
                appoggioBIncrementoString = appoggioB.substring(appoggioB.length()-1);
                appoggio = Integer.parseInt(appoggioBIncrementoString);

                if(appoggio <9 ){   
                    appoggio +=1;
                    appoggioBIncrementoString = appoggioB.substring(0, appoggioB.length()-1) + appoggio;
                    pV.getParam_b_field().setText(appoggioBIncrementoString);
                }
                else{
                    JOptionPane.showMessageDialog(null, "I bottoni permettono di incrementare o decrementare di 1 l'ultima cifra decimale, ma solo quando aggiornandola non si passa di unità", 
                            "Informazioni sull'incremento ed il decremento", JOptionPane.WARNING_MESSAGE);
                }
                eccezione = true;
                break;
                
            case "DecrementaB": //decrementa di 1 l'ultima cifra di beta
                
                appoggioB = pV.getParam_b_field().getText();
                appoggioBIncrementoString = appoggioB.substring(appoggioB.length()-1);
                appoggio = Integer.parseInt(appoggioBIncrementoString);

                if(appoggio >1 && appoggio <=9){   
                    appoggio -=1;
                    appoggioBIncrementoString = appoggioB.substring(0, appoggioB.length()-1) + appoggio;
                    pV.getParam_b_field().setText(appoggioBIncrementoString);
                }
                else{
                    JOptionPane.showMessageDialog(null, "I bottoni permettono di incrementare o decrementare di 1 l'ultima cifra decimale, ma solo quando aggiornandola non si passa di unità", 
                            "Informazioni sull'incremento ed il decremento", JOptionPane.WARNING_MESSAGE);
                }
                eccezione = true;
                break;
                
            case "Applica":
            //prendo la popolazione
            try{
                appoggioN = pV.getParam_n_field().getText(); //prendo dalla text field
                parametroN = Long.parseLong(appoggioN); //provo a convertire
            }catch(NumberFormatException nFE){
                JOptionPane.showMessageDialog(null, "La numerosità della popolazione è stata inserita scorrettamente", "Inserire un numero intero", JOptionPane.WARNING_MESSAGE);
                eccezione = true;
            }

            //prendo gli infetti
            try{
                appoggioI = pV.getParam_i_field().getText();
                parametroI = Long.parseLong(appoggioI);
            }catch(NumberFormatException nFE){
                JOptionPane.showMessageDialog(null, "Il numero degli infetti è stato inserito scorrettamente", "Inserire un numero intero", JOptionPane.WARNING_MESSAGE);
                eccezione = true;
            }

            //prendo i suscettibili
            try{
                appoggioS = pV.getParam_s_field().getText();
                parametroS = Long.parseLong(appoggioS);
            }catch(NumberFormatException nFE){
                JOptionPane.showMessageDialog(null, "Il numero dei suscettibili è stato inserito scorrettamente", "Inserire un numero intero", JOptionPane.WARNING_MESSAGE);
                eccezione = true;
            }

            //prendo alpha
            try{
                appoggioA = pV.getParam_a_field().getText();
                parametroA = Double.parseDouble(appoggioA);
            }catch(NumberFormatException nFE){
                JOptionPane.showMessageDialog(null, "Il valore di alpha è stato inserito scorrettamente", "Inserire un numero double", JOptionPane.WARNING_MESSAGE);
                eccezione = true;
            }

            //prendo beta
            try{
                appoggioB = pV.getParam_b_field().getText();
                parametroB = Double.parseDouble(appoggioB);
            }catch(NumberFormatException nFE){
                JOptionPane.showMessageDialog(null, "Il valore di beta è stato inserito scorrettamente", "Inserire un numero double", JOptionPane.WARNING_MESSAGE);
                eccezione = true;
            } 

            /*
                controllo che la popolazione coincida con la somma
                tra suscettibili ed infetti. Escludo i rimossi perchè
                è imposto che all'inizio siano nulli.
            */
            if(parametroN != parametroI + parametroS){

                JOptionPane.showMessageDialog(null, "La numerosità della popolazione deve essere pari alla somma del numero dei suscettibili e degli infetti", "Inserire correttamente i parametri", JOptionPane.WARNING_MESSAGE);
                eccezione = true;  
            }

            //controllo che la popolazione sia un numero positivo
            if(parametroN <= 0){

                JOptionPane.showMessageDialog(null, "La numerosità della popolazione deve essere un numero maggiore di 0", "Inserire correttamente il parametro", JOptionPane.WARNING_MESSAGE);
                eccezione = true;  
            }
            //controllo che gli infetti siano un numero positivo
            if(parametroI < 0){

                JOptionPane.showMessageDialog(null, "il numero degli infetti deve essere un numero positivo", "Inserire correttamente il parametro", JOptionPane.WARNING_MESSAGE);
                eccezione = true;  
            }
            //controllo che i suscettibili siano un numero positivo
            if(parametroS <= 0){

                JOptionPane.showMessageDialog(null, "Il numero dei suscettibili deve essere un numero maggiore di 0", "Inserire correttamente il parametro", JOptionPane.WARNING_MESSAGE);
                eccezione = true;  
            }

            /*
                controllo che il parametro iniziale di contagiabilità
                sia compreso tra i valori voluti
            */
            r_0 = (float) (parametroA/parametroB)*parametroS;

            if(r_0 <=0.8 || r_0 >=5){

                JOptionPane.showMessageDialog(null, "R0 [(ALPHA/BETA)*numero dei suscettibili]: " + r_0 +". Tale valore deve invece avere un valore compreso tra 0.8 e 5", "Inserire correttamente i parametri", JOptionPane.WARNING_MESSAGE);
                eccezione = true;
            }
            break;
        }
        
        
        //se non si sono verificate eccezioni
        if(eccezione == false){
            //stampo la contagiabilità, per info, all'utente
            JOptionPane.showMessageDialog(null, "R0 [(ALPHA/BETA)*numero dei suscettibili]: " + r_0, "Informazione su R0", JOptionPane.INFORMATION_MESSAGE);
            pV.setInseriti(true);//indico che sono stati inseriti i parametri
            m = new Model(); //creo il model, identico per tutte le funzioni
            //setto i parametri necessari per i calcoli al model
            m.setParametroN(parametroN);
            m.setParametroI(parametroI);
            m.setParametroS(parametroS);
            m.setParametroA(parametroA);
            m.setParametroB(parametroB);
            /*
                passo il model alla view dei parametri per poi passarlo
                al listener del menu e poter effettuare i calcoli e disegnare 
                i grafici adeguati
            */
            pV.setm(m);
        }
    }   
}
