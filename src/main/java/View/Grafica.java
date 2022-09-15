
package View;

import Model.Model;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Classe per il disegno dei grafici
 * @author Giacomo Orsucci & Francesco Di Carlo 4IC
 */
public class Grafica extends JPanel {
    
    private final static int nValoriAscissa = 10;
    private final static int nValoriOrdinata = 10;
    
    private long giorni, valoriScalaY;
    
    private String tipoGrafica;
    
    private Model m;
    
    private double uX;//rapporto di conversione delle x
    private double uY;//rapporto di conversione delle y
    private double uYScala;//rapporto di conversione per la scala y
    private double uXScala;//rapporto di conversione per la scala x
    private double xPrecedente;
    private double yPrecedente;
    
    private ArrayList<Double> listaSuscettibili = new ArrayList();
    private ArrayList<Double> listaInfetti = new ArrayList();
    private ArrayList<Double> listaRimossi = new ArrayList();
    
    
    /**
     * Costruttore parametrizzato
     * @param tipoGrafica, stringa indicante il tipo di grafica
     * @param m, il model 
     */
    public Grafica(String tipoGrafica, Model m){
        
        this.tipoGrafica = tipoGrafica;
        this.m = m;
        
        //prendo le liste con il risultato
        listaSuscettibili = m.getListaSuscettibili();
        listaInfetti = m.getListaInfetti();
        listaRimossi = m.getListaRimossi();
        
        setVisible(true);
        m.calcolo();
        
        //differenzio le stampe in base al tipo di grafica
        switch(tipoGrafica){ 
            
            case "Suscettibili":
                m.stampaS();
                break;
                
            case "Infetti":
                m.stampaI();
                break;
            
            case "Rimossi":
                m.stampaR();
                break;
                
            case "Confronto":
                m.stampaConfronto();
                break;
        }
        
        setLayout(null);
    }
    
    /**
     * Metodo per disegnare il grafico
     * @param g, oggetto della grafica 
     */
    
    @Override
    public void paintComponent(Graphics g){
        
       Graphics2D g2 = (Graphics2D) g; //casting
        
        uX = (double) (getWidth()-50)/(m.getT());
        uXScala = (double) (getWidth()-60)/(m.getT());
        
        giorni = m.getT()/nValoriAscissa;   //ogni quanto stampare i giorni della scala x 
        g2.drawLine(50,getHeight()-10,getWidth(),getHeight()-10);//asse x
        
        //rimpicciolisco il font
        Font fontG2Precedente = g2.getFont();
                    
        Font fontG2Nuovo = fontG2Precedente.deriveFont(fontG2Precedente.getSize() * 0.65f);
                
        g2.setFont(fontG2Nuovo);
        
        //stampo i valori sull'asse x
        for(int i=m.getT(); i>=0; i--){

            if(i%giorni==0 || i==0){

                g2.drawString(String.valueOf(i), convertiXScala(m.getT()-i), getHeight()-1);
            }

        }
        
        g2.setFont(fontG2Precedente);
        
        /*
            La scala della x è univoca perchè si avanza di un giorno tutte le volte,
            ma quella delle y no. Quindi dobbiamo differenziare e definirla in base al tipo
            di grafica
        */
        switch(tipoGrafica){ 
            
            case "Suscettibili":
                uY = (double) (getHeight()-14)/(m.getParametroS());
                uYScala = (double) (getHeight()-14)/(m.getParametroS());
                valoriScalaY = (long) (m.getParametroS()/(nValoriOrdinata));
                break;
                
            case "Infetti":
                uY = (double) (getHeight()-14)/(m.getiMaggiore());
                uYScala = (double) (getHeight()-14)/(m.getiMaggiore());
                valoriScalaY = (long) (m.getiMaggiore()/(nValoriOrdinata));
                break;
                
            case "Rimossi":
                uY = (double) (getHeight()-14)/(m.getrMaggiore());
                uYScala = (double) (getHeight()-14)/(m.getrMaggiore());
                valoriScalaY = (long) (m.getrMaggiore()/(nValoriOrdinata));
                break;
            
            case "Confronto":
                uY = (double) (getHeight()-14)/(m.getParametroN());
                uYScala = (double) (getHeight()-14)/(m.getParametroN());
                valoriScalaY = (long) (m.getParametroN()/(nValoriOrdinata));
                break;
        }
        
        g2.drawLine(50,getHeight(),50,0); //asse y

        fontG2Precedente = g2.getFont();

        fontG2Nuovo = fontG2Precedente.deriveFont(fontG2Precedente.getSize() * 0.65f);

        g2.setFont(fontG2Nuovo);

        for(int i=nValoriOrdinata; i>=0; i--){  

            g2.drawString(String.valueOf(i*valoriScalaY),0, getHeight()-convertiYScala(i*valoriScalaY));

        }

        g2.setFont(fontG2Precedente);
                
        
        //differenzio in base alla grafica per stampare i risultati corrispondenti con i colori desiderati
        switch(tipoGrafica){ 
            
            case "Suscettibili":
                
                for(int i=1; i<listaSuscettibili.size()-1; i++){
                    
                    g2.setColor(Color.GREEN);
                    g2.setStroke(new BasicStroke(3));
                    g2.drawLine(convertiX(i-1),convertiYS(listaSuscettibili.get(listaSuscettibili.size()-1-i).longValue()),
                            convertiX(i),convertiYS(listaSuscettibili.get(listaSuscettibili.size()-2-i).longValue()));
                }
                
                setVisible(true);
                break;
                
            case "Infetti" :
                
                for(int i=1; i<listaInfetti.size()-1; i++){
                    
                    g2.setColor(Color.RED);
                    g2.setStroke(new BasicStroke(3));
                    g2.drawLine(convertiX(i-1),convertiYI(listaInfetti.get(listaInfetti.size()-1-i).longValue()),
                            convertiX(i),convertiYI(listaInfetti.get(listaInfetti.size()-2-i).longValue()));
                }
                
                setVisible(true);
                break;
                
            case "Rimossi":
                
                for(int i=1; i<listaRimossi.size()-1; i++){
                    
                    g2.setColor(Color.BLUE);
                    g2.setStroke(new BasicStroke(3));
                    g2.drawLine(convertiX(i-1),convertiYR(listaRimossi.get(listaRimossi.size()-1-i).longValue()),
                            convertiX(i),convertiYR(listaRimossi.get(listaRimossi.size()-2-i).longValue()));
                }
                setVisible(true);
                break;
            
            case "Confronto":
                for(int i=1; i<listaSuscettibili.size()-1; i++){
                    
                    g2.setColor(Color.GREEN);
                    g2.setStroke(new BasicStroke(3));
                    g2.drawLine(convertiX(i-1),convertiYConfronto(listaSuscettibili.get(listaSuscettibili.size()-1-i).longValue()),
                            convertiX(i),convertiYConfronto(listaSuscettibili.get(listaSuscettibili.size()-2-i).longValue()));
                }
                
                for(int i=1; i<listaInfetti.size()-1; i++){
                    
                    g2.setColor(Color.RED);
                    g2.setStroke(new BasicStroke(3));
                    g2.drawLine(convertiX(i-1),convertiYConfronto(listaInfetti.get(listaInfetti.size()-1-i).longValue()),
                            convertiX(i),convertiYConfronto(listaInfetti.get(listaInfetti.size()-2-i).longValue()));
                }
                for(int i=1; i<listaRimossi.size()-1; i++){
                    
                    g2.setColor(Color.BLUE);
                    g2.setStroke(new BasicStroke(3));
                    g2.drawLine(convertiX(i-1),convertiYConfronto(listaRimossi.get(listaRimossi.size()-1-i).longValue()),
                            convertiX(i),convertiYConfronto(listaRimossi.get(listaRimossi.size()-2-i).longValue()));
                }
                setVisible(true);
                break;
        }  
    }
    
    /**
     * Metodo per convertire la x in pixel
     * @param x, x calcolata
     * @return x in pixel
     */
    public int convertiX(int x){
        
        int xPixel;
        
        xPixel = (int) ((m.getT() - x)*uX)+45; 
       
        return xPixel; 
    }
    
    /**
     * Metodo per convertire la y dei suscettibili in pixel
     * @param y, y dei suscettibili calcolata
     * @return y dei sucsettibili in pixel
     */
    public int convertiYS(long y){
        
        int yPixel;
        
        yPixel = (int) (Math.abs(y - m.getParametroS())*uY)+2;
        
        return yPixel;
    }
    
    /**
     * Metodo per convertire la y degli infetti in pixel
     * @param y, y degli infetti calcolata
     * @return y degli infetti in pixel
     */
    public int convertiYI(long y){
        
        int yPixel;
        
        yPixel = (int) (Math.abs(y - m.getiMaggiore())*uY)+2;
        
        return yPixel;
    }
    
    /**
     * Metodo per convertire la y dei rimossi in pixel
     * @param y, y dei rimossi calcolata
     * @return y dei rimossi in pixel
     */
    public int convertiYR(long y){
        
        int yPixel;
        
        yPixel = (int) (Math.abs(y - m.getrMaggiore())*uY)+2;
        
        return yPixel;
    }
    
    /**
     * Metodo per convertire la x dei valori dell'asse x in pixel
     * @param x, tempo da convertire in pixel
     * @return x in pixel dei valori dell'asse x 
     */
    public int convertiXScala(int x){
        
     int xPixel;
        
        xPixel = (int) ((m.getT() - x)*uXScala) +50; 
       
        return xPixel;    
    }
    
     /**
     * Metodo per convertire la y dei valori dell'asse y in pixel
     * @param y, valore da stampare sull'asse y da convertire
     * @return y in pixel dei valori dell'asse y
     */
    public int convertiYScala(long y){
        
        int yPixel;
        
        yPixel = (int) (y*uYScala)+8;
        
        return yPixel;
    }   
    
    /**
     * Metodo per convertire la y del confronto in pixel secondo la sua scala
     * @param y, valore da convertire
     * @return y in pixel dei grafici di confronto
     */
    public int convertiYConfronto(long y){
        
        int yPixel;
        
        yPixel = (int) (Math.abs(y - m.getParametroN())*uY)+2;
        
        return yPixel;
        
    }
}
