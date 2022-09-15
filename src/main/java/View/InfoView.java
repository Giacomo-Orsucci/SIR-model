package View;

import java.awt.Font;
import javax.swing.*;

/**
 * Classe per le informazioni sul programma
 * @author Giacomo Orsucci 4IC.
 */
public class InfoView extends JFrame {

    private JPanel rootPanel = new JPanel();
    private final JTextArea testo = new JTextArea();
    
    /**
     * Costruttore
     */
    public InfoView() {

        //setto le impostazioni generali del frame
        super("Informazioni sul programma");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(600, 100, 620, 400);
        rootPanel = (JPanel) getContentPane();
        setResizable(false);
        
        testo.setFont(new Font("Courier New", Font.PLAIN, 13));

        //scrivo nella TextArea
        testo.append("PROGRAMMA CHE IMPLEMENTA IL MODELLO SIR PER L'ANDAMENTO DELLE EPIDEMIE"
                + "\n\nSCOPO DEL PROGRAMMA:\n"
                + "\nRappresentare l'andamento dei suscettibili, degli infetti e dei "
                + "\nrimossi di un'epidemia nel tempo, sia graficamente che analiticamente. "
                + "\nE' possibile sia consultare le funzioni dell'epidemia singolarmente "
                + "\nin maniera più dettagliata, che insieme nell'apposita finestra grafica. "
                + "\nE' inoltre possibile affiancare i grafici singoli, ma non aprire più"
                + "\ngrafici identici. In tal caso il grafico precedente, identico, si "
                + "\nchiuderà. Inoltre tutte le volte che viene aperta la finestra grafica per"
                + "\nl'inserimento dei parametri, essi verranno azzerati e le funzioni non "
                + "\nsaranno consultabili fino a che non sarà completato il loro inserimento."
                + "\nBisogna inoltre segnalare che i tastini per l'aumento ed il decremento di "
                + "\nalpha e beta permettono di muoversi solo all'interno dell'ultima cifra"
                + "\ninserita, da 0 a 9. Nel caso in cui non venga chiusa la finestra "
                + "\nper l'inserimento dei parametri e vengano fatti un inserimento giusto"
                + "\ned uno sbagliato, sarà possibile aprire le finestre dei grafici,"
                + "\nche però mostreranno i dati dell'inserimento corretto."
                +"\n\nIn ogni caso peculiarità, eccezioni e casi particolari sono sempre"
                +"\ngestiti ed indicati mediante appositi messaggi.");
        
        testo.setEditable(false);

        rootPanel.add(testo);

        setVisible(true);
    }
}
