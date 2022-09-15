
package Test;

import View.MenuView;
import javax.swing.UIManager;

/**
 * Classe contenente il main
 * @author Giacomo Orsucci & Francesco Di Carlo 4IC
 */
public class Main {

    public static void main(String[] args) {
       
        //setto lo stile della grafica
        try {   
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) { System.err.println("Error: " + e.getMessage()); }
        
        MenuView menuView = new MenuView();
        
    }
    
}
