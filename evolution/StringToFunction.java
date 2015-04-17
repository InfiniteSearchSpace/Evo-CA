/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Kraaken
 */
public class StringToFunction {
    
    public void randFunction(boolean b3){ 

        Random r = new Random();

	String[] actions = new String[4];
	
            int[] plan = {r.nextInt(2)+1, (r.nextInt(1/*2*/)+1)*4, 4, 0, r.nextInt(3)};

	    if(b3) { 
		actions[0] = "=!><op";
		actions[1] = "I0123";
		actions[2] = "&|c";
		actions[3] = " +-";
	    } else {
		actions[0] = "=!><op";
		actions[1] = "I01";
		actions[2] = "&|c";
		actions[3] = " +-";
	    }
	    
	    
            String ss;
            int rr;
            int cCount;
            String[][] f = {{"", ""}, {"", "", "", "", "", "", "", ""}, {"", "", "", "", "", "", "", ""}, {"", "", "", "", ""}};
        
        
            
            for(int i = 0; i < plan[0]; i++) { // Initial Condition
                rr = r.nextInt(actions[0].length());
                ss = actions[0].substring(rr, rr+1);

                rr = r.nextInt(actions[1].length()-1)+1;
                ss += actions[1].substring(rr, rr+1);

                if(i < plan[0]-1) {
                    rr = r.nextInt(actions[2].length()-1);
                    ss += actions[2].substring(rr, rr+1);
                } else { ss += "a"; }

                f[0][i]=ss;
            } // Initial Condition
                
            
            int r1 = r.nextInt(actions[0].length());
            int r2 = r.nextInt(actions[1].length());
            int r3 = 0;
            
            for(int i = 0; i < plan[1]; i++) { 
                r3 = r.nextInt(actions[2].length()-1);
                
                if(i < plan[1]-1) {
                    ss = actions[0].substring(r1, r1+1);
                    ss += actions[1].substring(r2, r2+1);
                    ss += actions[2].substring(r3, r3+1);
                } else {
                    ss = actions[0].substring(r1, r1+1);
                    ss += actions[1].substring(r2, r2+1);
                    ss += "b"; 
                }
            
                f[1][i]=ss;
            } // First Check
            
            
            cCount = 0;
            boolean cTime = false;
            r1 = r.nextInt(actions[0].length());
            r2 = r.nextInt(actions[1].length());
            r3 = 0;
            
            for(int i = 0; i < plan[2]; i++) { // Second Check, Proc Condition
                
                ss = actions[0].substring(r1, r1+1);
                ss += actions[1].substring(r2, r2+1);

                if(i < plan[2]-1) {
                    
                    if (plan[2]==4 && plan[4] == 1){ //cTime on all i
                        cTime=true;
                    }
                        
                    if (plan[2]==4 && plan[4] == 2){ //cTime on odd i
                        if(i % 2 == 1) { cTime=true; }
                    }
                    
                    
                    if (plan[2]==8 && plan[4] == 1){ //cTime on odd i
                        if(i % 2 == 1) { cTime=true; }
                    }
                    
                    if (plan[2]==8 && plan[4] == 2){ //cTime on every second odd i
                         if(i % 4 == 3) { cTime=true; }
                    }
                    
                    
                    if(!cTime) {
                        rr = r.nextInt(actions[2].length()-1);
                        ss += actions[2].substring(rr, rr+1);
                    } else {
                        rr = 2;//r.nextInt(actions[2].length());
                        ss += actions[2].substring(rr, rr+1);
                        if("c".equals(actions[2].substring(rr, rr+1))) {cCount++;}
                        cTime = false;
                    }
                } else { ss += "d"; }

                f[2][i]=ss;
            } // // Second Check, Proc Condition
            
            
            if(cCount == 0) {
                plan[3] = 5;
            } else if(cCount == 1) {
                plan[3] = 3;
            } else if(cCount == 3) {
                plan[3] = 2;
            } else {System.out.println("Well, fuck.");}
            
            r1 = r.nextInt(actions[3].length());
            r2 = r.nextInt(actions[1].length()-1)+1;
            
            for(int i = 0; i < plan[3]; i++) { // ACTION!
                if(i == 1) {
                    r1 = r.nextInt(actions[3].length());
                    r2 = r.nextInt(actions[1].length()-1)+1;
                }
                ss = actions[3].substring(r1, r1+1);
                ss += actions[1].substring(r2, r2+1);
                ss += "."; //rand 250
                
                f[3][i]=ss;
            } // ACTION!

            
            /*System.out.println(f[0][0].toString() + " " + f[0][1].toString());
            System.out.println(f[1][0].toString() + " " + f[1][1].toString() + " " + f[1][2].toString() + " " + f[1][3].toString() + " " + f[1][4].toString() + " " + f[1][5].toString() + " " + f[1][6].toString() + " " + f[1][7].toString());
            System.out.println(f[2][0].toString() + " " + f[2][1].toString() + " " + f[2][2].toString() + " " + f[2][3].toString() + " " + f[2][4].toString() + " " + f[2][5].toString() + " " + f[2][6].toString() + " " + f[2][7].toString());
            System.out.println(f[3][0].toString() + " " + f[3][1].toString() + " " + f[3][2].toString() + " " + f[3][3].toString() + " " + f[3][4].toString());
            System.out.println("  ");*/
	    
           /* System.out.println("  ");
            System.out.println("{{\""+ f[0][0].toString() +"\", \""+ f[0][1].toString() +"\"},{\"" + f[1][0].toString() + "\", \"" + f[1][1].toString() + "\", \"" + f[1][2].toString() + "\", \"" + f[1][3].toString() + "\", \"" + f[1][4].toString() + "\", \"" + f[1][5].toString() + "\", \"" + f[1][6].toString() + "\", \"" + f[1][7].toString()+"\"},{\"" +f[2][0].toString() + "\", \"" + f[2][1].toString() + "\", \"" + f[2][2].toString() + "\", \"" + f[2][3].toString() + "\", \"" + f[2][4].toString() + "\", \"" + f[2][5].toString() + "\", \"" + f[2][6].toString() + "\", \"" + f[2][7].toString()+"\"},{\"" +f[3][0].toString() + "\", \"" + f[3][1].toString() + "\", \"" + f[3][2].toString() + "\", \"" + f[3][3].toString() + "\", \"" + f[3][4].toString()+"\"}};" );
            System.out.println("  ");*/
	    
	    /*String[][] g = {
			   {"=1a"},                            
                           {">0&",">0&",">0&",">0b"},
                           {">0c",">0c",">0c",">0d"},
			   {"+1.","-1."}
		       }; /**/
	    
	    //StringToFunction(g);
            StringToFunction(f);
	    
    }
    
    
    public void StringToFunction(String[][] f){
  
	//id the number of unfilled {""}
	//f = s without {""}
	/*String ss = "";
	for (int i = 0; i < 4; i++) {
	    for (int j = 0; j < f[i].length; j++) {
		ss += f[i][j] + " ~ ";
	    }
	}
	System.out.println(ss + "  /  " + f[0].length + " " + f[1].length + " " + f[2].length + " " + f[3].length); /**/
	
	
	String[][] s = new String[4][];
	int nulls[] = {0,0,0,0};
	
	
	for (int i = 0; i < 4; i++) {
	    for (int j = 0; j < f[i].length; j++) {
		if("".equals(f[i][j])) {nulls[i]+=1;}
	    }
	}
	
	for (int i = 0; i < 4; i++) {
	    for (int j = 0; j < f[i].length-(nulls[i]-1); j++) {
		s[i] = new String[j];
	    }
	}
	
	for (int i = 0; i < 4; i++) {
	    for (int j = 0; j < s[i].length; j++) {
		s[i][j] = f[i][j];
	    }
	}/**/
	
	//String ss = "";
	/*ss = "";
	for (int i = 0; i < 4; i++) {
	    for (int j = 0; j < f[i].length; j++) {
		ss += f[i][j] + " ~ ";
	    }
	}
	System.out.println(ss + "  /  " + f[0].length + " " + f[1].length + " " + f[2].length + " " + f[3].length); /**/
	
	
	/*ss = "";
	for (int i = 0; i < 4; i++) {
	    for (int j = 0; j < s[i].length; j++) {
		ss += s[i][j] + " ~ ";
	    }
	}
	System.out.println(ss + "  /  " + s[0].length + " " + s[1].length + " " + s[2].length + " " + s[3].length); /**/
	
	
	makeFunction3(s);

    }
    
    
    /************************************************/
    
    private void makeFunction3(String[][] f) {
	//System.out.println(f[0].length + " " + f[1].length + " " + f[2].length + " " + f[3].length); /**/
	String[] result = {"", "", "", ""};
	String[] l = {"pointsArray[i][j]", "pointsArray[i+1][j]", "pointsArray[i-1][j]", "pointsArray[i][j+1]", "pointsArray[i][j-1]"};
	String A=""; //Action
	String V=""; //Value
	String O=""; //Operator
	String S=""; 
	
	String fString = "";
	
        String endValueChange = "";
        int resultCount = 0;
        int shiftVal = 0;
        int doShift = 0;
	int e = 0;
	
	for (int i=0; i < 3; i++) {
	    
	    if(i==3){result[i] = "if((" + l[0] + " "; e++;} else if(i==0){result[i] = "if((" + l[0] + " ";} else {e=1; result[i] = "if((" + l[e] + " ";}
	    
	    for (int j=0; j < f[i].length; j++) {
		
		if(f[i].length / 4 == 2) {
		    if(i==1) {
			//if(j==0) {e++;}
			if(j==1) {e++;}
			//if(j==2) {e++;}
			if(j==3) {e++;}
			//if(j==4) {e++;}
			if(j==5) {e++;}
			//if(j==6) {e++;}
		    }

		    if(i==2) {
			//if(j==0) {e++;}
			if(j==1) {e++;}
			//if(j==2) {e++;}
			if(j==3) {e++;}
			//if(j==4) {e++;}
			if(j==5) {e++;}
			//if(j==6) {e++;}
		    }
		}
		
		if(f[i].length / 4 == 1) {
		    if(i==1) {
			if(j==0) {e++;}
			if(j==1) {e++;}
			if(j==2) {e++;}
			if(j==3) {e++;}
			if(j==4) {e++;}
			if(j==5) {e++;}
			if(j==6) {e++;}
		    }

		    if(i==2) {
			if(j==0) {e++;}
			if(j==1) {e++;}
			if(j==2) {e++;}
			if(j==3) {e++;}
			if(j==4) {e++;}
			if(j==5) {e++;}
			if(j==6) {e++;}
		    }
		}
		
		
		S = f[i][j];//.toString();
		A = S.substring(0, 1);
		V = S.substring(1, 2);
		O = S.substring(2, 3);
		
		//System.out.println("["+S+"]" + " "+i+" "+j+" "+A+V+O);
		
		if("!".equals(A)) {A = "!=";}
		if("=".equals(A)) {A = "==";}
		if("p".equals(A)) {A = ">=";}
		if("o".equals(A)) {A = "<=";}
		
		if("I".equals(V)) {V = l[0];}
		
		if(i == 0 && "&".equals(O)) {O = "&& " + l[0];} else if("&".equals(O)) {O = "&& " + l[e];}
		if("|".equals(O)) {O = ") || ( " + l[e];}
		if("a".equals(O)) {O = ")){";}
		if("b".equals(O)) {O = ")){";}
		if("e".equals(O)) {O = "@@@ /*)){*/ ";}
		if("f".equals(O)) {O = ")/*@@@*/";}
		//if("c".equals(O)) {O = ")){";}
		if(".".equals(O)) {O = "/*WAIT*/";}
		if(";".equals(O)) {O = "/*==END==*/";}

		/*if("c".equals(O)) {O = ")) {/*C*//*"+/*)) { "+l[0]+" " + f[3][0].substring(0, 1)+"= " +f[3][0].substring(1, 2)+/* 
					  "; "+l[1]+" " + f[3][1].substring(0, 1)+"= " +f[3][1].substring(1, 2)+ 
					  "; "+l[2]+" " + f[3][2].substring(0, 1)+"= " +f[3][2].substring(1, 2)+ 
					  //"; "+l[3]+" " + f[3][3].substring(0, 1)+"= " +f[3][3].substring(1, 2)+ 
					  //"; "+l[4]+" " + f[3][4].substring(0, 1)+"= " +f[3][4].substring(1, 2)+ */
					  //";} if((" + l[e];}
		
		/*if("d".equals(O)) {O = ")) {/*D*//*"+/*;}/*)) { "+l[0]+" " + f[3][0].substring(0, 1)+"= " +f[3][0].substring(1, 2)+ 
					  //"; "+l[1]+" " + f[3][1].substring(0, 1)+"= " +f[3][1].substring(1, 2)+ 
					  //"; "+l[2]+" " + f[3][2].substring(0, 1)+"= " +f[3][2].substring(1, 2)+ 
					  /*"; "+l[3]+" " + f[3][3].substring(0, 1)+"= " +f[3][3].substring(1, 2)+ 
					  "; "+l[4]+" " + f[3][4].substring(0, 1)+"= " +f[3][4].substring(1, 2)+ */
					  /*";} /**//*}}/*EndOfFunction/**//*";}/**/
		
		// ABOVE WORKS / BELOW EXPEREMENTAL
		
                endValueChange = "";
                for (int k = 0; k < f[3].length; k++) {
                    if(k == 0) {endValueChange += l[0]+" " + f[3][k].substring(0, 1) + "= "+ f[3][k].substring(1, 2)+"; ";} else {
                    endValueChange += l[k+doShift]+" " + f[3][k].substring(0, 1) + "= "+ f[3][k].substring(1, 2)+"; ";
                    }
                }
                
                
                //System.out.println("I,J,e,eVC  " + i + " " + j + " " + e + " " + endValueChange);
                
                
		if("c".equals(O)) {
                    //O = ")) { "+endValueChange+" } if(( " + l[j+2];
		    O = ")) { "+endValueChange+" } if(( " + l[j+2];
                    if(shiftVal == 0) { shiftVal = (j+1); } 
                    doShift+=shiftVal;
                    //System.out.println("C: I,J,e,eVC" + i + " " + j + " " + e + " " + endValueChange);
                }
		
		if("d".equals(O)) {
                    O = ")) { "+endValueChange+" } /**/}}/*EndOfFunction/**/";
                    if(shiftVal == 0) { shiftVal = (j+1); } 
                    doShift+=shiftVal;
                    //System.out.println("C: I,J,e,eVC" + i + " " + j + " " + e + " " + endValueChange);
                }
		
                //System.out.println(f[3].length + " " + (j+1));
                
		//if("d".equals(O)) {O = ")) { XXX } /**/}}/*EndOfFunction/**/"; System.out.println("D: " + i + " " + j + " " + e + " " + endValueChange);}/**/
		
                /*endValueChange = l[0]+" " + f[2][j].substring(0, 1) + "= "+ f[2][j].substring(1, 2);
                System.out.println(i + " " + j + " " + e + " " + endValueChange);
		//System.out.println(A + " " + V + " " + O);/**/
		
		
		
		result[i] += A + " " + V + " " + O;
		
	    }
	}
	

	   
	   fString+="{";
           for (int ii = 0; ii < f.length; ii++) {
	       fString+="{";
	       for (int jj = 0; jj < f[ii].length; jj++) {
		   if (jj != f[ii].length-1) {
			fString+="\""+f[ii][jj]+"\", ";
		    } else { fString+="\""+f[ii][jj]+"\"";}
	       }
		if (ii != f.length-1) {
		    fString+="}, ";
		} else { fString+="}";}
	   }
           fString+="}";
	   
           String functStr = "if(i > 0) {if(i < mySize-1) {if(j > 0) {if(j < mySize-1) { /*Proceed*/" + result[0].toString() + "   " + result[1].toString() + "   " + result[2].toString() + "   " + result[3].toString() + " /**/ }}}} /* " + fString + " */";
           
           /*StringSelection selection = new StringSelection(functStr);
           Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
           clipboard.setContents(selection, selection);*/
           
           /*fString*/
           
           File fy = new File("C:/func.txt");
            if(fy.exists() == false) {
            PrintWriter writer;
                    try {
                        writer = new PrintWriter("C:/func.txt", "UTF-8");


                        writer.println("");

                        writer.close();
                    } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                        Logger.getLogger(AutoArray.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            
            
           int lineCount = 0;
	String[] strAr;
	String line = null;
	int ii=0;
	
	BufferedReader reader;
	
	try {
	    reader = new BufferedReader(new FileReader("C:/func.txt"));
	    
	    try {
		
		while ((line = reader.readLine()) != null) { lineCount++; }
		
		strAr = new String[lineCount+1];
		
		
		while ((line = reader.readLine()) != null) {
		    ii++;
		    strAr[ii] = line;
		}
		
	    } catch (IOException ex) {
		Logger.getLogger(AutoArray.class.getName()).log(Level.SEVERE, null, ex);
	    }
	} catch (FileNotFoundException ex) {
	    Logger.getLogger(AutoArray.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	strAr = new String[lineCount];
	
	try {
	    reader = new BufferedReader(new FileReader("C:/func.txt"));
	    
	    try {
		
		while ((line = reader.readLine()) != null) {
		   strAr[ii] = line;
		    ii++;
		}
		
	    } catch (IOException ex) {
		Logger.getLogger(AutoArray.class.getName()).log(Level.SEVERE, null, ex);
	    }
	} catch (FileNotFoundException ex) {
	    Logger.getLogger(AutoArray.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	
	PrintWriter writer;
	try {
	    writer = new PrintWriter("C:/func.txt", "UTF-8");
	    
	    for (int jj = 0; jj < strAr.length; jj++){
		writer.println(strAr[jj]);
	    }

	    writer.println(fString);
	    
	    writer.close();
	} catch (FileNotFoundException | UnsupportedEncodingException ex) {
	    Logger.getLogger(AutoArray.class.getName()).log(Level.SEVERE, null, ex);
	}
	
           
            
           
           
           
	System.out.println(functStr);
	/*System.out.println("");
	System.out.println(fString);
	System.out.println("");
	System.out.println(result[2].toString());
	System.out.println("");*/
	
    }
}
