
package Model;

import View.ConfrontoView;
import View.InfettiView;
import View.RimossiView;
import View.SuscettibiliView;
import java.util.ArrayList;

/**
 * Classe con il model per effettuare i calcoli
 * @author Giacomo Orsucci & Francesco Di Carlo 4IC
 */
public class Model {
    
    private int t = 0;
    
    private long parametroN;
    private long parametroS;
    private long parametroI;
   
    private long iMaggiore;
    private long rMaggiore; //non sMaggiore perchè la s + grande è sempre parametroS, il numero iniziale dei suscettibili
    
    private ArrayList<Double> listaSuscettibili = new ArrayList();
    private ArrayList<Double> listaInfetti = new ArrayList();
    private ArrayList<Double> listaRimossi = new ArrayList();
    private ArrayList<Double> listaContagiabilita = new ArrayList<>();
    
    
    private double parametroA;
    private double parametroB;
    
    private SuscettibiliView sV;
    private InfettiView iV;
    private RimossiView rV;
    private ConfrontoView cV;
    
    private String tipoGrafica;
    
    
    /**
     * Metodo per il calcolo di suscettibili, infetti e rimossi
     */
    public void calcolo(){
        
        double n = parametroN;
        double s = parametroS;
        double i = parametroI;
        double r = 0;
        
        double sPrecedente = s;
        double iPrecedente = i;
        double rPrecedente = r;
        
        int appoggioUscita = 0;
        
        listaSuscettibili = new ArrayList();
        listaInfetti = new ArrayList();
        listaRimossi = new ArrayList();
        
        
        do{
            
            //cerco il numero maggiore di infetti e rimossi
            if(i > iMaggiore){
                iMaggiore = (long) i;
            }
            
            if(r > rMaggiore){
                rMaggiore = (long) r;
            }
            
            //aggiungo i risultati alle rispettive liste
            listaSuscettibili.add(s);
            listaContagiabilita.add((parametroA/parametroB)*s);
            listaInfetti.add(i);
            listaRimossi.add(r);
            
            //applicazione delle formule
            sPrecedente = s;
            iPrecedente = i;
            rPrecedente = r;
            
            s = (sPrecedente - parametroA*sPrecedente*iPrecedente);
            i = ((iPrecedente + parametroA*sPrecedente*iPrecedente) - (parametroB*iPrecedente));
            r = (rPrecedente + parametroB*iPrecedente);
            
            /*
                appena le liste dei suscettibili e degli infetti hanno raggiunto
                i 10 elementi, comincio a controllare se ci sono 9 suscettibili e
                9 infetti consecutivi identici
            */
            if(listaSuscettibili.size()>=10 && listaInfetti.size()>=10){
                
                for(int l = listaSuscettibili.size()-1; l >= listaSuscettibili.size()-9; l--){
                    
                    int appoggioL = l-1;
                    
                    if(listaSuscettibili.get(l).longValue()-listaSuscettibili.get(appoggioL).longValue() == 0 && 
                            listaInfetti.get(l).longValue()-listaInfetti.get(appoggioL).longValue() == 0 ){
                        appoggioUscita++;
                    }  
                } 
                
                if(appoggioUscita != 9){
                        appoggioUscita = 0;
                    }
            }
            
        }while(appoggioUscita !=9); //esco quando ho trovato lo stesso numero di suscettibili ed infetti per 9 volte consecutive
    }
    
    /**
     * Metodo per la stampa nei rispettivi scrollPane dei suscettibili e della contagiabilità ai rispettivi tempi
     */
    public void stampaS(){
        
        t = 0;
        
        for(int i=0; i<listaSuscettibili.size(); i++){
            
            sV.getRisultatiArea().append("\nT" + t);
            sV.getRisultatiArea().append("\n Suscettibili: " + listaSuscettibili.get(i).longValue() + "\n");
            
            sV.getContagiabilitaArea().append("\nT" + t);
            sV.getContagiabilitaArea().append("\n Contagiabilità: " + listaContagiabilita.get(i) + "\n");
            
            sV.getSuscettibiliInizialiLabel().setText("Suscettibili iniziali: " + parametroS);
            sV.getSuscettibiliFinaliLabel().setText("Suscettibili finali: " + listaSuscettibili.get(listaSuscettibili.size()-1).longValue());
            sV.getTempoTotLabel().setText("Tempi prima che si stabilizzassero: " + t);
            
            t++;
            
        }
    }
    
    /**
     * Metodo per la stampa nei rispettivi scrollPane degli infetti e della contagiabilità ai rispettivi tempi
     */
    public void stampaI(){
        
        t=0;
        
        for(int i=0; i<listaInfetti.size(); i++){
            
            iV.getRisultatiArea().append("\nT" + t);
            iV.getRisultatiArea().append("\n Infetti: " + listaInfetti.get(i).longValue() + "\n");
            
            iV.getContagiabilitaArea().append("\nT" + t);
            iV.getContagiabilitaArea().append("\n Contagiabilità: " + listaContagiabilita.get(i) + "\n"); 
            
            iV.getSuscettibiliInizialiLabel().setText("Infetti iniziali: " + parametroI);
            iV.getSuscettibiliFinaliLabel().setText("Infetti finali: " + listaInfetti.get(listaInfetti.size()-1).longValue());
            iV.getTempoTotLabel().setText("Tempi prima che si stabilizzassero: " + t);
            
            t++;
        }
    }
    
    /**
    * Metodo per la stampa nei rispettivi scrollPane dei rimossi e della contagiabilità ai rispettivi tempi
    */
    public void stampaR(){
        
        t=0;
        
        for(int i=0; i<listaRimossi.size(); i++){
            
            rV.getRisultatiArea().append("\nT" + t);
            rV.getRisultatiArea().append("\n Rimossi: " + listaRimossi.get(i).longValue() + "\n");
            
            rV.getContagiabilitaArea().append("\nT" + t);
            rV.getContagiabilitaArea().append("\n Contagiabilità: " + listaContagiabilita.get(i) + "\n"); 
            
            rV.getSuscettibiliInizialiLabel().setText("Rimossi iniziali: " + 0);
            rV.getSuscettibiliFinaliLabel().setText("Rimossi finali: " + listaRimossi.get(listaRimossi.size()-1).longValue());
            rV.getTempoTotLabel().setText("Tempi prima che si stabilizzassero: " + t);
            
            t++;
        }
        
    }
    /**
     * Metodo per la stampa nello scrollPane della rispettiva view dei suscettibili, degli infetti,
     * dei rimossi e della contagiabilità ai rispettivi tempi
     */
    public void stampaConfronto(){
        
        t=0;
        
        for(int i=0; i<listaRimossi.size(); i++){
            
            cV.getRisultatiArea().append("\nT" + t);
            cV.getRisultatiArea().append("\n S: " + listaSuscettibili.get(i).longValue() + " " + "I: " + listaInfetti.get(i).longValue() 
                    + " " + "R: " + listaRimossi.get(i).longValue() + "\n");
            
            cV.getContagiabilitaArea().append("\nT" + t);
            cV.getContagiabilitaArea().append("\n Contagiabilità: " + listaContagiabilita.get(i) + "\n"); 
            
            cV.getSuscettibiliInizialiLabel().setText("S0: " + parametroS + "  " +  "I0: " + parametroI + "  " + "R0: " + 0);
            cV.getSuscettibiliFinaliLabel().setText("S finali: " + listaSuscettibili.get(listaSuscettibili.size()-1).longValue() 
                    + "  " + "I finali: " + listaInfetti.get(listaInfetti.size()-1).longValue() + "  " + "R finali: " + listaRimossi.get(listaRimossi.size()-1).longValue());
            cV.getTempoTotLabel().setText("Tempi prima che si stabilizzassero: " + t);
            
            t++;
        }
        
    }
    
    //GETTER E SETTER
    public void setParametroN(long parametroN) {this.parametroN = parametroN;}
    public long getParametroN() {return parametroN;}
    public void setParametroS(long parametroS) {this.parametroS = parametroS;}
    public long getParametroS() {return parametroS;}
    public void setParametroI(long parametroI) {this.parametroI = parametroI;}
    public long getParametroI() {return parametroI;}
    public void setParametroA(double parametroA) {this.parametroA = parametroA;}
    public void setParametroB(double parametroB) {this.parametroB = parametroB;}
    public long getiMaggiore() {return iMaggiore;}
    public long getrMaggiore() {return rMaggiore;}
    public int getT() {return t;}
    public void setsV(SuscettibiliView sV) {this.sV = sV;}
    public void setiV(InfettiView iV) {this.iV = iV;}
    public void setrV(RimossiView rV) {this.rV = rV;}
    public void setcV(ConfrontoView cV) {this.cV = cV;}
    public void setTipoGrafica(String tipoGrafica){this.tipoGrafica = tipoGrafica;}
    public ArrayList<Double> getListaSuscettibili() {return listaSuscettibili;}
    public ArrayList<Double> getListaInfetti() {return listaInfetti;}
    public ArrayList<Double> getListaRimossi() {return listaRimossi;}
    public ArrayList<Double> getListaContagiablita() {return listaContagiabilita;}
}
