/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution;

import java.util.Random;
import java.security.SecureRandom;

/**
 *
 * @author Kraaken
 */
public class PointArrayClass {
     
    public int[][] pointsArray;
    
    public int maxVal = 0;
    public int totalVal = 0;
    public int notZero = 0;
    
    Random r = new Random();
    XSRandom rXOR = new XSRandom();
    SecureRandom rSec = new SecureRandom();
    

    int randStr = 2;
    int fuelLimit = -1;
    int rndRotationSize = 128;
    int doubleBackSize = 64;
    
    
    
    /****************************************************************************/
    int rndRotation = rndRotationSize;
    int doubleBack = doubleBackSize;
    int dir = 1;
    /****************************************************************************/
    
    public void PointArrayClass(int mySize) {
        pointsArray = new int[mySize][mySize];
    }
    
    public void setZero(int mySize) {
        for(int i=0; i < mySize; i++){
            for(int j=0; j < mySize; j++){
                pointsArray[i][j] = 0;
            }
        }
    }
    
    public int getPoint(int i, int j) {
        return pointsArray[i][j];
    }
    
    public void flipZero(int mySize, int iterations) {
        
        for (int t=0; t< iterations;t++){
            
            
            //TIMEBOX
            maxVal=-1;
            totalVal=0;
            notZero=0;
            
            if(rndRotation == 0 && rndRotationSize > 0) {randStr+=dir; doubleBack--; rndRotation=rndRotationSize;} else if (rndRotationSize > 0){rndRotation--;}
	    if(doubleBack == 0) {dir*=-1; doubleBack=doubleBackSize;}
            if(fuelLimit>0){fuelLimit--;}
            //TIMEBOX END
            
            
            for(int i=0; i < mySize; i++){
                for(int j=0; j < mySize; j++){

		    /*porus(i, j, mySize); //1,!0!0!0!0;--1,;
                    condense(i, j, mySize); //>0,====;++4,--1;
                    expire3(i, j, mySize); //0,>>>>;++4,--1;
                    
                    expireHigh(i, j, mySize); //>0,0000;--1,;
                    spread4(i, j, mySize); //>3,0'0'0'0;--1,++1;/**/
		   /**********************************************/
		    //
		    FuelExplode(i, j, mySize);
		    porus(i, j, mySize); //1,!0!0!0!0;--1,;
		    
                    condense(i, j, mySize); //>0,====;++4,--1;
                    expire3(i, j, mySize); //0,>>>>;++4,--1;
                    
		    
                    
                    //instability(i, j, mySize);//1,<=<=<=<=;--1,;
		    
                    
		    
		    nova(i, j, mySize);
		    //AUTOGROW(i, j, mySize);
		    function1(i, j, mySize);
		    
		    instability(i, j, mySize);//1,<=<=<=<=;--1,;
		    //bleed(i, j, mySize);
		    expireHigh(i, j, mySize); //>0,0000;--1,;
                    /**************************************************************/
		    
		    seedMe(i, j); //Motherfukn'Magic;
		    cutBoarders(i, j, mySize); //US Boarder
		    
                    
                    
                    /**************************************************************/
                    
                    //MAXVAL CALCULATION
                    if(i > 1 && i < mySize -2 && j > 1 && j < mySize -2) {
                        totalVal+=pointsArray[i][j];
                        if(pointsArray[i][j]>maxVal){
                             maxVal=pointsArray[i][j];
                        }
                        
                        if(pointsArray[i][j] != 0){
                            notZero++;
                        }
                    }// END MAXVAL CALCULATION
                }//END J LOOP
            }//END I LOOP
        }//END T LOOP
    } // END flipZero

    
    /************************************************************/
    /************************************************************/
    /************************************************************/
    /************************************************************/
    /************************************************************/

    private void function1 (int i, int j, int mySize){
	if(i > 0) {if(i < mySize-1) {if(j > 0) {if(j < mySize-1) { /*Proceed*/
	    if((pointsArray[i][j] > 3 ) || ( pointsArray[i][j]== 1 )){   
		if((pointsArray[i+1][j] > 0 && pointsArray[i+1][j]!= 1 && pointsArray[i-1][j]> 0 && pointsArray[i-1][j]!= 1 && pointsArray[i][j+1]> 0 && pointsArray[i][j+1]!= 1 && pointsArray[i][j-1]> 0 && pointsArray[i][j-1]!= 1 )){   
		    if((pointsArray[i+1][j] > 0 && pointsArray[i+1][j]!= 1 && pointsArray[i-1][j]> 0 && pointsArray[i-1][j]!= 1 && pointsArray[i][j+1]> 0 && pointsArray[i][j+1]!= 1 && pointsArray[i][j-1]> 0 && pointsArray[i][j-1]!= 1 )) { 
			pointsArray[i][j] += 4; pointsArray[i+1][j] -= 1; 
			pointsArray[i-1][j] -= 1; pointsArray[i][j+1] -= 1; 
			pointsArray[i][j-1] -= 1;  
		    } /**/
		}}/*EndOfFunction/**/    /**/ 
	}}}}
    }
    
    
    private void FuelExplode(int i, int j, int mySize){
	if(i > 0) {if(i < mySize-1) {if(j > 0) {if(j < mySize-1) { /*Proceed*/
	    if((pointsArray[i][j] == 1 )){   
		if((pointsArray[i+1][j] > 1 ) || ( pointsArray[i-1][j]> 1 ) || ( pointsArray[i][j+1]> 1 ) || ( pointsArray[i][j-1]> 1 )){   
		    if((pointsArray[i+1][j] > 1 )) { pointsArray[i][j] += 0; pointsArray[i+1][j] -= 1;  } 
		    if(( pointsArray[i-1][j]> 1 )) { pointsArray[i][j] += 0; pointsArray[i-1][j] -= 1;  } 
		    if(( pointsArray[i][j+1]> 1 )) { pointsArray[i][j] += 0; pointsArray[i][j+1] -= 1;  } 
		    if(( pointsArray[i][j-1]== 1 )) { pointsArray[i][j] += 0; pointsArray[i][j-1] -= 1;  } /**/
		}}/*EndOfFunction/**/    /**/ 
	}}}}
    }
    
    private void AUTOGROW(int i, int j, int mySize){

	
if(i > 0) {if(i < mySize-1) {if(j > 0) {if(j < mySize-1) { /*Proceed*/
    if((pointsArray[i][j] > 1 ) || ( pointsArray[i][j]== 0 )){   
	if((pointsArray[i+1][j] == 1 && pointsArray[i-1][j]== 1 ) || ( pointsArray[i][j+1]== 1 && pointsArray[i][j-1]== 1 )){   
	    if((pointsArray[i+1][j]== 1 )) { pointsArray[i][j] += 1; pointsArray[i+1][j] -= 1;  } 
	    if((pointsArray[i-1][j]== 1 )) { pointsArray[i][j] += 1; pointsArray[i-1][j] -= 1;  } 
	    if((pointsArray[i][j+1]== 1 )) { pointsArray[i][j] += 1; pointsArray[i][j+1] -= 1;  } 
	    if((pointsArray[i][j-1]== 1 )) { pointsArray[i][j] += 1; pointsArray[i][j-1] -= 1;  } /**/
	}}/*EndOfFunction/**/    /**/ 
}}}}

/*if(i > 0) {if(i < mySize-1) {if(j > 0) {if(j < mySize-1) { 
    if((pointsArray[i][j] > 1 ) || ( pointsArray[i][j]== 0 )){   
	if((pointsArray[i+1][j] == 1 && pointsArray[i-1][j]== 1 ) || ( pointsArray[i][j+1]== 1 && pointsArray[i][j-1]== 1 )){   
	    if((pointsArray[i+1][j] == 1 && pointsArray[i-1][j]== 1 )) { pointsArray[i][j] += 1; pointsArray[i+1][j] -= 1; pointsArray[i-1][j] -= 1;  } 
	    if(( pointsArray[i-1][j]== 1 && pointsArray[i][j-1]== 1 )) { pointsArray[i][j] += 1; pointsArray[i][j+1] -= 1; pointsArray[i][j-1] -= 1;  } 
	    //It didnt work. ^ that's an i, not j. The +2 is more complex...
	}}/*EndOfFunction/**/    /* 
}}}}*/

	
	
	/*
	if((pointsArray[i][j] == 1 )){   
	    if((pointsArray[i+1][j] == 1 && pointsArray[i-1][j]== 1 ) || ( pointsArray[i][j+1]== 1 && pointsArray[i][j-1]== 1 )){   
		if((pointsArray[i+1][j] == 1 && pointsArray[i-1][j]== 1 )) {pointsArray[i][j]+=2; pointsArray[i+1][j]--; pointsArray[i-1][j]--;} 
		if((pointsArray[i][j+1] == 1 && pointsArray[i][j-1]== 1 )) {pointsArray[i][j]+=2; pointsArray[i][j+1]--; pointsArray[i][j-1]--;}
	    }}/*EndOfFunction/**/   

    }
    
    private void bleed(int i, int j, int mySize){
	if((pointsArray[i][j] > 3 )){   
	    if((pointsArray[i+1][j] == 0 ) || ( pointsArray[i-1][j]== 0 ) || ( pointsArray[i][j+1]== 0 ) || ( pointsArray[i][j-1]== 0 )){   
		if((pointsArray[i+1][j] == 0 )) {pointsArray[i][j]--; pointsArray[i+1][j]++;} 
		if((pointsArray[i-1][j] == 0 )) {pointsArray[i][j]--; pointsArray[i-1][j]++;} 
		if((pointsArray[i][j+1] == 0 )) {pointsArray[i][j]--; pointsArray[i][j+1]++;} 
		if((pointsArray[i][j-1] == 0 )) {pointsArray[i][j]--; pointsArray[i][j-1]++;} /**/
	    }}/*EndOfFunction/**/   
    }
    
    private void nova(int i, int j, int mySize){
	if((pointsArray[i][j] > 1 )){   
	    if((pointsArray[i+1][j] == 1 && pointsArray[i-1][j]== 1 && pointsArray[i][j+1]== 1 && pointsArray[i][j-1]== 1 )){   
		if((pointsArray[i+1][j] == 1 && pointsArray[i-1][j]== 1 && pointsArray[i][j+1]== 1 && pointsArray[i][j-1]== 1 )) {
		    pointsArray[i][j]+=4; 
		    pointsArray[i-1][j]--;    
		    pointsArray[i+1][j]--; 
		    pointsArray[i][j-1]--; 
		    pointsArray[i][j+1]--;} /**/
	    }}/*EndOfFunction/**/   
    
    }
    
    /*private void economy(int i, int j, int mySize){ //>0,>0>0'>0>0;1,?;
        if (pointsArray[i][j] > 0) {
                    
            if(i > 0) {
                if(pointsArray[i-1][j] > 0) {
                    if(i < mySize-1) {
                        if(pointsArray[i+1][j] > 0) {
                            
                            pointsArray[i][j]=pointsArray[i-1][j]++;
                            pointsArray[i-1][j]=pointsArray[i+1][j];
                            pointsArray[i+1][j]=pointsArray[i][j];
                            
                            pointsArray[i][j]=1;
                        }
                    }
                }
            }

            if(j > 0) {
                if(pointsArray[i][j-1] > 0) {
                    if(j < mySize-1) {
                        if(pointsArray[i][j+1] > 0) {
                            
                            pointsArray[i][j]=pointsArray[i][j-1]++;
                            pointsArray[i][j-1]=pointsArray[i][j+1];
                            pointsArray[i][j+1]=pointsArray[i][j];
                            
                            pointsArray[i][j]=1;
                        }
                    }
                }
            }
            
            
        }
    }    */
    
    private void deflation(int i, int j, int mySize){

    }   
    
    private void deflation2(int i, int j, int mySize){ //>3,00'00;--2,++1;
        if (pointsArray[i][j] > 3) {
                    
            if(i > 0) {
                if(pointsArray[i-1][j] == 0) {
                    if(i < mySize-1) {
                        if(pointsArray[i+1][j] == 0) {
                            pointsArray[i][j]--;
                            pointsArray[i][j]--;
                            pointsArray[i-1][j]++;
                            pointsArray[i+1][j]++;
                        }
                    }
                }
            }

            if(j > 0) {
                if(pointsArray[i][j-1] == 0) {
                    if(j < mySize-1) {
                        if(pointsArray[i][j+1] == 0) {
                            pointsArray[i][j]--;
                            pointsArray[i][j]--;
                            pointsArray[i][j-1]++;
                            pointsArray[i][j+1]++;
                        }
                    }
                }
            }
            
            
            
            
        }
    }   
    
    private void deflation3(int i, int j, int mySize){
        if (pointsArray[i][j] > 7) {
                    
            if(i > 0) {
                if(pointsArray[i-1][j] == 0) {
                    if(i < mySize-1) {
                        if(pointsArray[i+1][j] == 0) {
                            pointsArray[i][j]--;
                            pointsArray[i][j]--;
                            pointsArray[i-1][j]++;
                            pointsArray[i+1][j]++;
                            pointsArray[i][j]--;
                            pointsArray[i][j]--;
                            pointsArray[i-1][j]++;
                            pointsArray[i+1][j]++;
                        }
                    }
                }
            }

            if(j > 0) {
                if(pointsArray[i][j-1] == 0) {
                    if(j < mySize-1) {
                        if(pointsArray[i][j+1] == 0) {
                            pointsArray[i][j]--;
                            pointsArray[i][j]--;
                            pointsArray[i][j-1]++;
                            pointsArray[i][j+1]++;
                            pointsArray[i][j]--;
                            pointsArray[i][j]--;
                            pointsArray[i][j-1]++;
                            pointsArray[i][j+1]++;
                        }
                    }
                }
            }
            
            
            
            
        }
    }   
            
    private void expire(int i, int j, int mySize){
        if (pointsArray[i][j] == 1) {
            if(i > 0) {
                if(i < mySize-1) {
                    if(j > 0) {
                        if(j < mySize-1) {
                    
                            if(pointsArray[i-1][j] == 0 && pointsArray[i+1][j] == 0 && pointsArray[i][j-1] == 0 && pointsArray[i][j+1] == 0) {
                                pointsArray[i][j]--;
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void expireHigh(int i, int j, int mySize){
        if (pointsArray[i][j] > 0) {
            if(i > 0) {
                if(i < mySize-1) {
                    if(j > 0) {
                        if(j < mySize-1) {
                    
                            if(pointsArray[i-1][j] == 0 && pointsArray[i+1][j] == 0 && pointsArray[i][j-1] == 0 && pointsArray[i][j+1] == 0) {
                                pointsArray[i][j]--;
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void instability(int i, int j, int mySize){//1,<=<=<=<=;--1,;
        if (pointsArray[i][j] == 1) {
            if(i > 0) {
                if(i < mySize-1) {
                    if(j > 0) {
                        if(j < mySize-1) {
                    
                            if(pointsArray[i-1][j] <= 1 && pointsArray[i+1][j] <= 1 && pointsArray[i][j-1] <= 1 && pointsArray[i][j+1] <= 1) {
                                pointsArray[i][j]--;
                            }
                        }
                    }
                }
            }
        }
    }
    
     private void expire2(int i, int j, int mySize){
        if (pointsArray[i][j] == 0) {
            if(i > 0) {
                if(i < mySize-1) {
                    if(j > 0) {
                        if(j < mySize-1) {
                    
                            if(pointsArray[i-1][j] == 1 && pointsArray[i+1][j] == 1 && pointsArray[i][j-1] == 1 && pointsArray[i][j+1] == 1) {
                                pointsArray[i-1][j] --;
                                pointsArray[i+1][j] --;
                                pointsArray[i][j-1] --;
                                pointsArray[i][j+1] --;
                            }
                        }
                    }
                }
            }
        }
    }
     
     private void expire3(int i, int j, int mySize){
        if (pointsArray[i][j] == 0) {
            if(i > 0) {
                if(i < mySize-1) {
                    if(j > 0) {
                        if(j < mySize-1) {
                    
                            if(pointsArray[i-1][j] > 0 && pointsArray[i+1][j] > 0 && pointsArray[i][j-1] > 0 && pointsArray[i][j+1] > 0) {
                                pointsArray[i-1][j] --;
                                pointsArray[i+1][j] --;
                                pointsArray[i][j-1] --;
                                pointsArray[i][j+1] --;
                                pointsArray[i][j]+=4;
                            }
                        }
                    }
                }
            }
        }
    }
     
     //porus(i, j, mySize);//1,!0!0!0!0;0,;
    private void porus(int i, int j, int mySize){
        if (pointsArray[i][j] == 1) {
            if(i > 0) {
                if(i < mySize-1) {
                    if(j > 0) {
                        if(j < mySize-1) {
                            if(pointsArray[i-1][j] != 0 && pointsArray[i+1][j] != 0 && pointsArray[i][j-1] != 0 && pointsArray[i][j+1] != 0) {
                                pointsArray[i][j]--;
                            }
                        }
                    }
                }
            }
        }
    }
     
    private void AUTOporus(int i, int j, int mySize){
	if((pointsArray[i][j] == 1 )){   
	    if((pointsArray[i+1][j] != 0 && pointsArray[i-1][j]!= 0 && pointsArray[i][j+1]!= 0 && pointsArray[i][j-1]!= 0 )){   
		if((pointsArray[i+1][j] != 0 && pointsArray[i-1][j]!= 0 && pointsArray[i][j+1]!= 0 && pointsArray[i][j-1]!= 0 )) {pointsArray[i][j]--;} /**/
	    }}/*EndOfFunction/**/   
 
    }
    
    private void osmosis(int i, int j, int mySize){
        if (pointsArray[i][j] > 3) {
            
            pointsArray[i][j]=0;
                    
            if(i > 0) {
                if(pointsArray[i][j] > pointsArray[i-1][j]) {
            if(i < mySize-1) {
                if(pointsArray[i][j] > pointsArray[i+1][j]) {
                    pointsArray[i-1][j]++;
                    pointsArray[i+1][j]++;
                }
            }
        }}

            if(j > 0) {
                if(pointsArray[i][j] > pointsArray[i][j-1]) {
            if(j < mySize-1) {
                if(pointsArray[i][j] > pointsArray[i][j+1]) {
                    pointsArray[i][j-1]++;
                    pointsArray[i][j+1]++;
                }
            }
        }}
            
            
        }
    }
    
    private void condense(int i, int j, int mySize){

        if (pointsArray[i][j] > 0) {

            if(i > 0) {
                if(pointsArray[i][j] == pointsArray[i-1][j]) {
                    if(i < mySize-1) {
                        if(pointsArray[i][j] == pointsArray[i+1][j]) {
                            if(j > 0) {
                                if(pointsArray[i][j] == pointsArray[i][j-1]) {
                                    if(j < mySize-1) {
                                        if(pointsArray[i][j] == pointsArray[i][j+1]) {
                    
                                            pointsArray[i][j]++;
                                            pointsArray[i][j]++;
                                            pointsArray[i][j]++;
                                            pointsArray[i][j]++;/**/
                                            
                                            pointsArray[i-1][j]--;
                                            pointsArray[i+1][j]--;
                                            pointsArray[i][j-1]--;
                                            pointsArray[i][j+1]--;
                                               
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
     private void AUTOcondense(int i, int j, int mySize){
	if((pointsArray[i][j] > 0 )){   
	    if((pointsArray[i+1][j] == pointsArray[i][j] && pointsArray[i-1][j]== pointsArray[i][j] && pointsArray[i][j+1]== pointsArray[i][j] && pointsArray[i][j-1]== pointsArray[i][j] )){   
		if((pointsArray[i+1][j] == pointsArray[i][j] && pointsArray[i-1][j]== pointsArray[i][j] && pointsArray[i][j+1]== pointsArray[i][j] && pointsArray[i][j-1]== pointsArray[i][j] )) {
		    pointsArray[i][j]+=4; 
		    pointsArray[i-1][j]--;    
		    pointsArray[i+1][j]--; 
		    pointsArray[i][j-1]--; 
		    pointsArray[i][j+1]--;
		}
	    }}/*EndOfFunction/**/   
     }
     
    
    private void fuelEat(int i, int j, int mySize){//>0,1111;++4,--1; 

        if (pointsArray[i][j] > 0) {

            if(i > 0) {
                if(pointsArray[i-1][j] == 1) {
                    if(i < mySize-1) {
                        if(pointsArray[i+1][j] == 1) {
                            if(j > 0) {
                                if(pointsArray[i][j-1] == 1) {
                                    if(j < mySize-1) {
                                        if(pointsArray[i][j+1]  == 1) {
                    
                                            pointsArray[i][j]++;
                                            pointsArray[i][j]++;
                                            pointsArray[i][j]++;
                                            pointsArray[i][j]++;/**/
                                            
                                            pointsArray[i-1][j]--;
                                            pointsArray[i+1][j]--;
                                            pointsArray[i][j-1]--;
                                            pointsArray[i][j+1]--;
                                               
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void fuelEat2(int i, int j, int mySize){//>0,1111;++4,--1; 

        if (pointsArray[i][j] > 0) {

            if(i > 0) {
                if(pointsArray[i-1][j] == 1) {
                    pointsArray[i][j]++;
                    pointsArray[i-1][j]--;
                }
            }
                    if(i < mySize-1) {
                        if(pointsArray[i+1][j] == 1) {
                            pointsArray[i][j]++;
                            pointsArray[i+1][j]--;
                        }
                    }
                            if(j > 0) {
                                if(pointsArray[i][j-1] == 1) {
                                    pointsArray[i][j]++;
                                    pointsArray[i][j-1]--;
                                }
                            }
                                    if(j < mySize-1) {
                                        if(pointsArray[i][j+1]  == 1) {
                                            pointsArray[i][j]++;
                                            pointsArray[i][j+1]--;
                                        }
                                    }

        }
    } 
    
    private void fuelEat3(int i, int j, int mySize){// >0, 00|00; ++2, --1;

        if (pointsArray[i][j] > 0) {
            
            if(i > 0 && i < mySize-1) {

                if(pointsArray[i-1][j] == 1 && pointsArray[i+1][j] == 1) {
                    pointsArray[i][j]++;
                    pointsArray[i+1][j]--;
                    pointsArray[i][j]++;
                    pointsArray[i-1][j]--;
                }
            }
            
            if(j > 0 && j < mySize-1) {
                if(pointsArray[i][j-1] == 1 && pointsArray[i][j+1] == 1) {
                    pointsArray[i][j]++;
                    pointsArray[i][j+1]--;
                    pointsArray[i][j]++;
                    pointsArray[i][j-1]--;
                }
            }
        }
    } 
    
    private void fuelCheat(int i, int j, int mySize){// >0, 00|00; ++2, --1;

        if (pointsArray[i][j] > 0) {
            
            if(i > 0 && i < mySize-1 && j > 0 && j < mySize-1) {
                if((pointsArray[i-1][j] == 1 && pointsArray[i+1][j] == 1) || (pointsArray[i][j-1] == 1 && pointsArray[i][j+1] == 1)) {


                    if(pointsArray[i-1][j] == 1 && pointsArray[i+1][j] == 1) {
                        pointsArray[i][j]++;
                        pointsArray[i+1][j]--;
                        pointsArray[i][j]++;
                        pointsArray[i-1][j]--;
                    }
                    if(pointsArray[i][j-1] == 1 && pointsArray[i][j+1] == 1) {
                        pointsArray[i][j]++;
                        pointsArray[i][j+1]--;
                        pointsArray[i][j]++;
                        pointsArray[i][j-1]--;
                    }
                }
            }
        }
    } 
    
     private void fuelCheat2(int i, int j, int mySize){// >0, 00|00; ++2, --1;

        if (pointsArray[i][j] > 0) {
            
            if(i > 0 && i < mySize-1 && j > 0 && j < mySize-1) {
                if((pointsArray[i-1][j] == 1 && pointsArray[i+1][j] == 1) || (pointsArray[i][j-1] == 1 && pointsArray[i][j+1] == 1)) {


                    if(pointsArray[i-1][j] == 1 && pointsArray[i+1][j] == 1) {
                        pointsArray[i][j]++;
                        pointsArray[i+1][j]--;
                        pointsArray[i-1][j]--;
                    }
                    if(pointsArray[i][j-1] == 1 && pointsArray[i][j+1] == 1) {
                        pointsArray[i][j]++;
                        pointsArray[i][j+1]--;
                        pointsArray[i][j-1]--;
                    }
                }
            }
        }
    } 
    
    private void fuelEat4(int i, int j, int mySize){//>0,11'11;++2,--1; 

        if (pointsArray[i][j] > 0) {

            if(i > 0) {
                if(pointsArray[i-1][j] == 1) {
                    
            if(i < mySize-1) {
                if(pointsArray[i+1][j] == 1) {
                    pointsArray[i][j]++;
                    pointsArray[i+1][j]--;
                    pointsArray[i][j]++;
                    pointsArray[i-1][j]--;
                }
            }
                }
            }
            
            
            if(j > 0) {
                if(pointsArray[i][j-1] == 1) {
                    
            if(j < mySize-1) {
                if(pointsArray[i][j+1]  == 1) {
                    pointsArray[i][j]++;
                    pointsArray[i][j+1]--;
                    pointsArray[i][j]++;
                    pointsArray[i][j-1]--;
                }
            }
                }
            }
            
            
        }
    } 
            
    private void ascend(int i, int j, int mySize){//>0,<!0<!0<!0<!0;++4,--1;

        if (pointsArray[i][j] > 0) {

            if(i > 0) {
                if(pointsArray[i-1][j] < pointsArray[i][j] && pointsArray[i-1][j] != 0) {
                    if(i < mySize-1) {
                        if(pointsArray[i+1][j] < pointsArray[i][j] && pointsArray[i+1][j] != 0) {
                            if(j > 0) {
                                if(pointsArray[i][j-1] < pointsArray[i][j] && pointsArray[i][j-1] != 0) {
                                    if(j < mySize-1) {
                                        if(pointsArray[i][j+1] < pointsArray[i][j] && pointsArray[i][j+1] != 0) {
                    
                                            pointsArray[i][j]++;
                                            pointsArray[i][j]++;
                                            pointsArray[i][j]++;
                                            pointsArray[i][j]++;/**/
                                            
                                            pointsArray[i-1][j]--;
                                            pointsArray[i+1][j]--;
                                            pointsArray[i][j-1]--;
                                            pointsArray[i][j+1]--;
                                               
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void ascend2(int i, int j, int mySize){//>0,<!0<!0<!0<!0;++4,--1;

        if (pointsArray[i][j] > 0) {

            if(i > 0) {
                if(pointsArray[i-1][j] != 0) {
                    pointsArray[i][j]++;
                    pointsArray[i-1][j]--;
                }
            }
            
            if(i < mySize-1) {
                if(pointsArray[i+1][j] != 0) {
                    pointsArray[i][j]++;
                    pointsArray[i+1][j]--;
                }
            }
            
            if(j > 0) {
                if(pointsArray[i][j-1] != 0) {
                    pointsArray[i][j]++;
                    pointsArray[i][j-1]--;
                }
            }
            
            if(j < mySize-1) {
                if(pointsArray[i][j+1] != 0) {
                    pointsArray[i][j]++;
                    pointsArray[i][j+1]--;
                }
            }

        }
    }
    
    private void ascend2v2(int i, int j, int mySize){//>0,<!0<!0<!0<!0;++4,--1;

        if (pointsArray[i][j] > 0) {

            if(i > 0) {
                if(pointsArray[i-1][j] != 0) {
                    pointsArray[i-1][j]--;
                }
            }
            
            if(i < mySize-1) {
                if(pointsArray[i+1][j] != 0) {
                    pointsArray[i+1][j]--;
                }
            }
            
            if(j > 0) {
                if(pointsArray[i][j-1] != 0) {
                    pointsArray[i][j-1]--;
                }
            }
            
            if(j < mySize-1) {
                if(pointsArray[i][j+1] != 0) {
                    pointsArray[i][j+1]--;
                }
            }

        }
    }
          
    //>0,!=0'!=0'!=0'!=0;++1;--1;
    private void condense2(int i, int j, int mySize){

        if (pointsArray[i][j] > 0) {

            if(i > 0) {
                if(pointsArray[i-1][j]!=0) {
                    pointsArray[i][j]++;
                    pointsArray[i-1][j]--;
                }
            }
            
            if(i < mySize-1) {
                if(pointsArray[i+1][j]!=0) {
                    pointsArray[i][j]++;
                    pointsArray[i+1][j]--;
                }
            }
                    
            if(j > 0) {
                if(pointsArray[i][j-1]!=0) {
                    pointsArray[i][j]++;
                    pointsArray[i][j-1]--;
                }
            }
                            
            if(j < mySize-1) {
                if( pointsArray[i][j+1]!=0) {
                    pointsArray[i][j]++;
                    pointsArray[i][j+1]--;
                }
            }

        }
    }
    
    private void grow(int i, int j, int mySize){

        if (pointsArray[i][j] > 0) {
            if(i > 0) {
                if(pointsArray[i][j] >= pointsArray[i-1][j] && pointsArray[i-1][j] != 0) {
                    pointsArray[i-1][j]--;
                    pointsArray[i][j]++;
                }
            }

            if(i < mySize-1) {
                if(pointsArray[i][j] >= pointsArray[i+1][j] && pointsArray[i+1][j] != 0) {
                    pointsArray[i+1][j]--;
                    pointsArray[i][j]++;
                }
            }

            if(j > 0) {
                if(pointsArray[i][j] >= pointsArray[i][j-1] && pointsArray[i][j-1] != 0) {
                    pointsArray[i][j-1]--;
                    pointsArray[i][j]++;
                }
            }

            if(j < mySize-1) {
                if(pointsArray[i][j] >= pointsArray[i][j+1] && pointsArray[i][j+1] != 0) {
                    pointsArray[i][j+1]--;
                    pointsArray[i][j]++;
                }
            }
        }
    }
    
    private void vaxx(int i, int j, int mySize){

        if (pointsArray[i][j] == 0) {
            
            if(i > 0) {
                if(pointsArray[i][j] < pointsArray[i-1][j]) {
                    pointsArray[i-1][j]--;
                    pointsArray[i][j]++;
                }
            }

            if(i < mySize-1) {
                if(pointsArray[i][j] < pointsArray[i+1][j]) {
                    pointsArray[i+1][j]--;
                    pointsArray[i][j]++;
                }
            }

            if(j > 0) {
                if(pointsArray[i][j] < pointsArray[i][j-1]) {
                    pointsArray[i][j-1]--;
                    pointsArray[i][j]++;
                }
            }

            if(j < mySize-1) {
                if(pointsArray[i][j] < pointsArray[i][j+1]) {
                    pointsArray[i][j+1]--;
                    pointsArray[i][j]++;
                }
            }
      
        }
    }

    private void spread2(int i, int j, int mySize){
        if (pointsArray[i][j] > 3) {
            /*if(i > 0) {
                if(pointsArray[i][j] > pointsArray[i-1][j]) {
            if(i < mySize-1) {
                if(pointsArray[i][j] > pointsArray[i+1][j]) {
            if(j > 0) {
                if(pointsArray[i][j] > pointsArray[i][j-1]) {
            if(j < mySize-1) {
                if(pointsArray[i][j] > pointsArray[i][j+1]) {
 
                    pointsArray[i][j+1]++;
                    pointsArray[i][j]--;
                    pointsArray[i][j-1]++;
                    pointsArray[i][j]--;
                    pointsArray[i+1][j]++;
                    pointsArray[i][j]--;
                    pointsArray[i-1][j]++;
                    pointsArray[i][j]--;
                    
                }
            }
                }
            }
                }
            }
                }
            }/**/
            
            pointsArray[i][j]-=4;
            
            if(i > 0) {
                if(pointsArray[i][j] > pointsArray[i-1][j]) {
                    pointsArray[i-1][j]++;
                }
            }
                    
            if(i < mySize-1) {
                if(pointsArray[i][j] > pointsArray[i+1][j]) {
                    pointsArray[i+1][j]++;
                }
            }
                    
            if(j > 0) {
                if(pointsArray[i][j] > pointsArray[i][j-1]) {
                    pointsArray[i][j-1]++;
                }
            }
                    
            if(j < mySize-1) {
                if(pointsArray[i][j] > pointsArray[i][j+1]) {
                    pointsArray[i][j+1]++;
                }
            }
/**/
            
        }
    }
    
    private void spread1(int i, int j, int mySize){
        if (pointsArray[i][j] > 0) {
            if(i > 0) {
                if(pointsArray[i][j] > pointsArray[i-1][j] && pointsArray[i-1][j] != 0) {
            if(i < mySize-1) {
                if(pointsArray[i][j] > pointsArray[i+1][j] && pointsArray[i+1][j] != 0) {
            if(j > 0) {
                if(pointsArray[i][j] > pointsArray[i][j-1] && pointsArray[i][j-1] != 0) {
            if(j < mySize-1) {
                if(pointsArray[i][j] > pointsArray[i][j+1] && pointsArray[i][j+1] != 0) {
 
                    pointsArray[i][j+1]++;
                    pointsArray[i][j]--;
                    pointsArray[i][j-1]++;
                    pointsArray[i][j]--;
                    pointsArray[i+1][j]++;
                    pointsArray[i][j]--;
                    pointsArray[i-1][j]++;
                    pointsArray[i][j]--;
                    
                }
            }
                }
            }
                }
            }
                }
            }
            
            
        }
    }
    
    private void blackHole(int i, int j, int mySize){
        if(pointsArray[i][j] < 0) {pointsArray[i][j]++;}
    }
    
    private void spread3(int i, int j, int mySize){
        if (pointsArray[i][j] > 0) {
            if(i > 0) {
                if(pointsArray[i][j] < pointsArray[i-1][j]) {
            if(i < mySize-1) {
                if(pointsArray[i][j] < pointsArray[i+1][j]) {
            if(j > 0) {
                if(pointsArray[i][j] < pointsArray[i][j-1]) {
            if(j < mySize-1) {
                if(pointsArray[i][j] < pointsArray[i][j+1]) {
 
                    pointsArray[i][j+1]--;
                    pointsArray[i][j-1]--;
                    pointsArray[i+1][j]--;
                    pointsArray[i-1][j]--;
                    
                    pointsArray[i][j]++;
                    pointsArray[i][j]++;
                    pointsArray[i][j]++;
                    pointsArray[i][j]++;
                    
                }
            }
                }
            }
                }
            }
                }
            }
            
            //spread3(i, j, mySize); //>0,>>>>;+1,-1;
            
        }
    }
    
    //spread4(i, j, mySize); //>0,0'0'0'0;--1,++1;
    private void spread4(int i, int j, int mySize){
        if (pointsArray[i][j] > 3) {
            if(i > 0) {
                if(pointsArray[i-1][j] == 0) {
                    pointsArray[i-1][j]++;
                    pointsArray[i][j]--;
                }
            }
                    
            if(i < mySize-1) {
                if(pointsArray[i+1][j] == 0) {
                    pointsArray[i+1][j]++;
                    pointsArray[i][j]--;
                }
            }
                    
            if(j > 0) {
                if(pointsArray[i][j-1] == 0) {
                    pointsArray[i][j-1]++;
                    pointsArray[i][j]--;
                }
            }
                    
            if(j < mySize-1) {
                if(pointsArray[i][j+1] == 0) {
                    pointsArray[i][j+1]++;
                    pointsArray[i][j]--;
                }
            }
                    

            
        }
    }
    
    private void seedMe(int i, int j) {
        //int randm=r.nextInt(randStr);
        //int randm=rXOR.nextInt(randStr);
        int randm=rSec.nextInt(randStr);
        
        if(randm == 0){
            /*if(pointsArray[i][j] > 0){
                pointsArray[i][j]--;
            } else /**/if(pointsArray[i][j] == 0 && fuelLimit != 0){
                pointsArray[i][j] = 1;
            }
        }
    
    }
    
    private void cutBoarders(int i, int j, int mySize) {
        if(i <= 1) {pointsArray[i][j] = 0;}
        if(i >= mySize-2) {pointsArray[i][j] = 0;}
        if(j <= 1) {pointsArray[i][j] = 0;}
        if(j >= mySize-2) {pointsArray[i][j] = 0;}
    }
    
}

















                    /*if(i > 0 && i < mySize-1 && j > 0 && j < mySize-1){
                        if(pointsArray[i-1][j] == 0 && pointsArray[i+1][j] == 0 && pointsArray[i][j-1] == 0 && pointsArray[i][j+1] == 0) {seedMe(i, j, false);}
                    }*/