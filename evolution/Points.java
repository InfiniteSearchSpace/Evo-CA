/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kraaken
 */


/* To Do:
 * ******************
 * Save each numerical array at t to *.csv
 * 
 * 
 * 
 */

public class Points extends JFrame {

    public Points() {

        initUI();
    }

    private void initUI() {
        
        /******************************************/
        int SIZE = 128;
        /******************************************/
        
        
        setTitle("Points");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StringToFunction stf = new StringToFunction();

	
	/*
	
	
			    for (int i = 0; i < 50; i++) {
				stf.randFunction(false);
				stf.randFunction(false);
				stf.randFunction(true);
				stf.randFunction(false);
				stf.randFunction(true);
			    }
	

			    
			    
			    
			    
	/**/
	
        Surface s = new Surface();
	
	
	
        s.Surface(SIZE);
        add(s);
        setSize(SIZE, SIZE);
        
        setLocation(72,8);
        

	
	
        //setLocation(8,8);
        //setLocation(16+SIZE,8);
        //setLocation(8,16+SIZE);
        //setLocation(16+SIZE,16+SIZE);
        
        
        setUndecorated(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Points ps = new Points();
                ps.setVisible(true);
            }
        });
    }
}
