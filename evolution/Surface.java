/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class Surface extends JPanel {

    //PointArrayClass a;
    AutoArray a;
    
    int mySize =0;
    int iterations = 1;
    int pCol = 0;
    int pCol2 = 0;
    int pCol3 = 0;
    double pGradient = 0;
    
    public void Surface(int s) {
        mySize=s;
	
        /*a = new PointArrayClass();
        a.PointArrayClass(mySize);*/
	
	a = new AutoArray();
	
	
        a.AutoArray(mySize);
	
        a.setZero(mySize);
        a.flipZero(mySize,iterations);
                //System.out.println(a.iOne + "INIT");
    }
    
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        /*int pCol = (255-(a.getPoint(i, j)/a.maxVal)*255);
        g2d.setColor(Color.getHSBColor(pCol,pCol,pCol));//(Color.blue);*/

        Dimension size = getSize();
        Insets insets = getInsets();

        int w = size.width - insets.left - insets.right;
        int h = size.height - insets.top - insets.bottom;
        
        a.flipZero(mySize,iterations);

        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                //if(a.getPoint(i, j) != 0) {
                    
                    pGradient = 255-/**/(((double) a.getPoint(i, j)/a.maxVal)*255);
                    pCol=(int) pGradient;
                    
                    if(a.getPoint(i, j)==0) {pCol3=185; pCol2=195; pCol=215;}else if(a.getPoint(i, j)<0) {pCol3=0; pCol2=0; pCol=255;} else {pCol2=pCol; pCol3=pCol;}
                    
                    if(a.getPoint(i, j)==1) {pCol3=255; pCol2=255; pCol=180;}
                    
                    float[] floats = new float[3];
                    floats = Color.RGBtoHSB(pCol, pCol2, pCol3, floats);
                    
                    g2d.setColor(Color.getHSBColor(floats[0],floats[1],floats[2]));//(Color.blue);
                    g2d.drawLine(i,j,i,j);
                //}
            }
        }
        
        //System.out.println(a.totalVal);
	int[] aint = a.freq;
	String sint = "";
	
	for(int i = 0; i < aint.length; i++) {
	    if(i != aint.length-1) {
		sint += "[" + aint[i] + "], ";
	    } else { sint += "[" + aint[i] + "]"; }
	}
	
        //System.out.println("M:" + a.maxVal + " T:" +  a.totalVal + "   {"+ sint +"}");
        //System.out.println("C:" + a.notZero);
        
        repaint();
        
        /**/
       /* a.iOne++;
        System.out.println(a.iOne);*/
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

