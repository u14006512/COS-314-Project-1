
import java.util.ArrayList;
import java.util.Random;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SinghAn
 */
public class evalFunctionSet {
 public double goodEval(String[][] gameState,int team)
 {
     double R=0,B=0,centreControl=0;
     double val=0;
     for(int x=0;x<6;x++)
     {
         for(int y=0;y<6;y++)
         {
             if(gameState[x][y].contains("R"))
             {
                 R=R+1;
             } else if(gameState[x][y].contains("B"))
                {
                    B=B+1;
                } 
         }
     }
     if(team==0) 
     {
         if((B-R)>0) 
         {
             val=B-R;
         }
         for(int x=2;x<=3;x++)
         {
             for(int y=0;y<6;y++)
             {
                 if(gameState[x][y].contains("B")){centreControl=centreControl+1;}
             }
         }
     } else 
        {
            if((R-B)>0)
            {
               val=(R-B);
            }  
                     for(int x=2;x<=3;x++)
            {
             for(int y=0;y<6;y++)
             {
                 if(gameState[x][y].contains("R")){centreControl=centreControl+1;}
             }
            }
        }
        Random n=new Random();
     return val+centreControl+n.nextDouble();
 } 
 public double AdvancedEval(String[][] gameState,int team)
 {
     /*
     1)Material Advantages
        ->General difference between your forces and the enemy's
            ->compare 3 vs 3, 2 vs 2, 1 vs 1
            ->add differences
       ->specific material advantages
            ->owning pieces that the enemy cannot stop
            ->very advantageous 
       
     */
     double stratAd1=0,stratAd2=0,stratAd3=0;
     double Blue3=0,Red3=0;
     double Blue2=0,Red2=0;
     double Blue1=0,Red1=0;
     double score=0;
     double[] matAdv=new double[3];
     for(int x=0;x<6;x++)
     {
         for(int y=0;y<6;y++)
         {
             switch (gameState[x][y])
             {
                 case "3R":
                     Red3=Red3+1;
                     break;
                 case "2R":
                     Red2=Red2+1;
                     break;
                 case "1R":
                     Red1=Red1+1;
                     break;
                 case "3B":
                     Blue3=Blue3+1;
                     break;
                 case "2B":
                     Blue2=Blue2+1;
                     break;
                 case "1B":
                     Blue1=Blue1+1;
                     break;    
             }
         }
     }
     
     if(team==0)
     {
         double bA=checkPotentialAttacks(gameState,"B");
         matAdv[0]=Blue3-Red3;
         matAdv[1]=Blue2-Red2;
         matAdv[2]=Blue1-Red1;
         if (Blue3>0 && Blue3>Red1){stratAd3=Blue3-Red1;}
         if (Blue2>0 && Blue2>Red3){stratAd2=Blue2-Red3;}
         if (Blue1>0 && Blue1>Red2){stratAd1=Blue1-Red2;}
         if(bA>0)
         score=(bA*5);
                 score=score+numSafePieces(gameState, "B")+stratAd1+stratAd2+stratAd3+numIsolatedEnemyPieces(gameState, "R");

     }else 
        {
        double rA=checkPotentialAttacks(gameState, "R");
         matAdv[0]=Red3-Blue3;
         matAdv[1]=Red2-Blue2;
         matAdv[2]=Red1-Blue1;
         if (Red3>0 && Red3>Blue1){stratAd3=Red3-Blue1;}
         if (Red2>0 && Red2>Blue3){stratAd2=Red2-Blue3;}
         if (Red1>0 && Red1>Blue2){stratAd1=Red1-Blue2;}
         if (rA>0)
         score=(rA*5);
                 score=score+numSafePieces(gameState, "R")+stratAd1+stratAd2+stratAd3+numIsolatedEnemyPieces(gameState, "B");
        
        }
     for(int i=0;i<2;i++)
         {
             if (matAdv[i]<0) matAdv[i]=0; else matAdv[i]=matAdv[i]*2;
         }
     

     return score+matAdv[0]+matAdv[1]+matAdv[2];
 }
 public double numSafePieces(String[][] gameState,String side)
 {
     String attacker="";
     double safePieces=0;
     double attackers=0;
     if (side.equals("B")) attacker="R";
     if (side.equals("R")) attacker="B";
     ArrayList<moveNode> moveSpace=new ArrayList<moveNode>();

     for(int row=0;row<6;row++)
     {
         for(int col=0;col<6;col++)
         {
             if (gameState[row][col].contains(side)==true)
             {  
                  attackers=0;
                moveSpace.clear();
                 moveSpace.add(new moveNode(row+1,col));
                 moveSpace.add(new moveNode(row-1,col));
                 moveSpace.add(new moveNode(row,col+1));
                 moveSpace.add(new moveNode(row,col-1));
                 moveSpace.add(new moveNode(row-1,col-1));
                 moveSpace.add(new moveNode(row-1,col+1));
                 moveSpace.add(new moveNode(row+1,col-1));
                 moveSpace.add(new moveNode(row+1,col+1));
                 
                 for(int u=0;u<moveSpace.size();u++)
                 {
                     if (moveSpace.get(u).x<=5 && moveSpace.get(u).x>=0 )
                         if (moveSpace.get(u).y<=5 && moveSpace.get(u).y>=0 )
                         {
                             if (gameState[row][col].contains("3") && gameState[moveSpace.get(u).x][moveSpace.get(u).y].contains("1"+attacker))
                             {
                             attackers++;
                             } else
                             if (gameState[row][col].contains("2") && gameState[moveSpace.get(u).x][moveSpace.get(u).y].contains("3"+attacker))
                             {
                             attackers++;
                             }else 
                             if (gameState[row][col].contains("1") && gameState[moveSpace.get(u).x][moveSpace.get(u).y].contains("3"+attacker))
                             {
                             attackers++;
                             }
                         }
                 }

                 if (attackers==0) safePieces++;
                 
             }
         }
     }

     return safePieces;
 }
 public double numIsolatedEnemyPieces(String [][] gameState, String target)
 {

     double exchangeScore=0;
     //ARR STRUCTURE 0  1  2  3  4  5
     //              3R 2R 1R 3B 2B 1B
     int  tally[] =new int[6];
     for(int h=0;h<6;h++) tally[h]=0;
     ArrayList<moveNode> moveSpace=new ArrayList<moveNode>();

     for(int row=0;row<6;row++)
     {
         for(int col=0;col<6;col++)
         {
             if (gameState[row][col].contains(target)==true)
             {  
                for(int h=0;h<6;h++) tally[h]=0;
                moveSpace.clear();
                 moveSpace.add(new moveNode(row+1,col));
                 moveSpace.add(new moveNode(row-1,col));
                 moveSpace.add(new moveNode(row,col+1));
                 moveSpace.add(new moveNode(row,col-1));
                 moveSpace.add(new moveNode(row-1,col-1));
                 moveSpace.add(new moveNode(row-1,col+1));
                 moveSpace.add(new moveNode(row+1,col-1));
                 moveSpace.add(new moveNode(row+1,col+1));
                 
                 for(int u=0;u<moveSpace.size();u++)
                 {
                     if (moveSpace.get(u).x<=5 && moveSpace.get(u).x>=0 )
                         if (moveSpace.get(u).y<=5 && moveSpace.get(u).y>=0 )
                         {
                             switch (gameState[moveSpace.get(u).x][moveSpace.get(u).y])
                             {
                                 case "3R":tally[0]=tally[0]+1;
                                     break;
                                 case "2R":tally[1]=tally[1]+1;
                                     break;
                                 case "1R":tally[2]=tally[2]+1;
                                     break;
                                 case "3B":tally[3]=tally[3]+1;
                                     break;
                                 case "2B":tally[4]=tally[4]+1;
                                     break;
                                 case "1B":tally[5]=tally[5]+1;
                                     break;    
                             } 
                             
                         }
                     
                 }
             if(target.equals("B"))//We are BLUE; They are RED
             {
                 if (tally[2]>0 && tally[3]>0 && (tally[0]>=tally[4])){exchangeScore=exchangeScore+1;}
                 if (tally[1]>0 && tally[5]>0 && (tally[2]>=tally[3])){exchangeScore=exchangeScore+1;}
                 if (tally[0]>0 && tally[4]>0 && (tally[1]>=tally[5])){exchangeScore=exchangeScore+1;}
             }else 
                {
                 if (tally[5]>0 && tally[0]>0 && (tally[3]>=tally[1])){exchangeScore=exchangeScore+1;}
                 if (tally[4]>0 && tally[2]>0 && (tally[5]>=tally[0])){exchangeScore=exchangeScore+1;}
                 if (tally[3]>0 && tally[1]>0 && (tally[4]>=tally[2])){exchangeScore=exchangeScore+1;}   
                }
             }
         }
     }
     return exchangeScore;
 }
 public double checkPotentialAttacks(String [][] gameState, String side)
 {
     double attackCounter=0;
     String target="";
     
     if (side.equals("B")) target="R";
     if (side.equals("R")) target="B";
     
     for(int row=0;row<6;row++)
     {
         for(int col=0;col<6;col++)
         {
             if(gameState[row][col].contains(side)==true)
             {
             //Corner Cases
             //Top Left
             if(row==0 && col==0)
             {
                 if (gameState[row][col].contains("3"))
                 {
                     if(gameState[row+1][col].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col+1].contains("2"+target)){attackCounter=attackCounter+1;}
                 } else
                 if (gameState[row][col].contains("2"))
                 {
                     if(gameState[row+1][col].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col+1].contains("1"+target)){attackCounter=attackCounter+1;}
                 }else
                 if (gameState[row][col].contains("1"))
                 {
                     if(gameState[row+1][col].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col+1].contains("3"+target)){attackCounter=attackCounter+1;}
                 }
             }else    
             //Top Right
             if(row==0 && col==5)
             {
                 if (gameState[row][col].contains("3"))
                 {
                     if(gameState[row+1][col].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col-1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col-1].contains("2"+target)){attackCounter=attackCounter+1;}
                 } else
                 if (gameState[row][col].contains("2"))
                 {
                     if(gameState[row+1][col].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col-1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col-1].contains("1"+target)){attackCounter=attackCounter+1;}
                 }else
                 if (gameState[row][col].contains("1"))
                 {
                     if(gameState[row+1][col].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col-1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col-1].contains("3"+target)){attackCounter=attackCounter+1;}
                 }
             }else    
             //Bottom Left
             if(row==5 && col==0)
             {
                 if (gameState[row][col].contains("3"))
                 {
                     if(gameState[row-1][col].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col+1].contains("2"+target)){attackCounter=attackCounter+1;}
                 } else
                 if (gameState[row][col].contains("2"))
                 {
                     if(gameState[row-1][col].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col+1].contains("1"+target)){attackCounter=attackCounter+1;}
                 }else
                 if (gameState[row][col].contains("1"))
                 {
                     if(gameState[row-1][col].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col+1].contains("3"+target)){attackCounter=attackCounter+1;}
                 }
             }else
             //Bottom Right    
             if(row==5 && col==5)
             {
                 if (gameState[row][col].contains("3"))
                 {
                     if(gameState[row-1][col].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col-1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col-1].contains("2"+target)){attackCounter=attackCounter+1;}
                 } else
                 if (gameState[row][col].contains("2"))
                 {
                     if(gameState[row-1][col].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col-1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col-1].contains("1"+target)){attackCounter=attackCounter+1;}
                 }else
                 if (gameState[row][col].contains("1"))
                 {
                     if(gameState[row-1][col].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col-1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col-1].contains("3"+target)){attackCounter=attackCounter+1;}
                 }
             }else    
             //
                 
             //Edge Cases
             //Top Edge
             if (row==0 && col>0 && col<5)
             {
                 if(gameState[row][col].contains("3"))
                 {
                     if(gameState[row][col-1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col-1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col+1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col].contains("2"+target)){attackCounter=attackCounter+1;}
                 }
                 if(gameState[row][col].contains("2"))
                 {
                     if(gameState[row][col-1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col-1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col+1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col].contains("1"+target)){attackCounter=attackCounter+1;}
                 }
                 if(gameState[row][col].contains("1"))
                 {
                     if(gameState[row][col-1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col-1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col+1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col].contains("3"+target)){attackCounter=attackCounter+1;}
                 }
             }else
             //Bottom Edge
                if (row==5 && (col>0 && col<5))
                {
                    if(gameState[row][col].contains("3"))
                 {
                     if(gameState[row][col-1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col-1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col+1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col].contains("2"+target)){attackCounter=attackCounter+1;}
                 }
                 if(gameState[row][col].contains("2"))
                 {
                     if(gameState[row][col-1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col-1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col+1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col].contains("1"+target)){attackCounter=attackCounter+1;}
                 }
                 if(gameState[row][col].contains("1"))
                 {
                     if(gameState[row][col-1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col-1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col+1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col].contains("3"+target)){attackCounter=attackCounter+1;}
                 }
                }else
             //Left Edge
             if (col==0 && (row>0 && row<5))
             {
                 if(gameState[row][col].contains("3"))
                 {
                     if(gameState[row-1][col].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col+1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col+1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("2"+target)){attackCounter=attackCounter+1;}
                 }
                 if(gameState[row][col].contains("2"))
                 {
                     if(gameState[row-1][col].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col+1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col+1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("1"+target)){attackCounter=attackCounter+1;}
                 }
                 if(gameState[row][col].contains("1"))
                 {
                     if(gameState[row-1][col].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col+1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col+1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("3"+target)){attackCounter=attackCounter+1;}
                 }
             }else
             //Right Edge
             if (col==5 && (row>0 && row<5))
             {
                 if(gameState[row][col].contains("3"))
                 {
                     if(gameState[row-1][col].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col-1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col-1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col-1].contains("2"+target)){attackCounter=attackCounter+1;}
                 }
                 if(gameState[row][col].contains("2"))
                 {
                     if(gameState[row-1][col].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col-1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col-1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col-1].contains("1"+target)){attackCounter=attackCounter+1;}
                 }
                 if(gameState[row][col].contains("1"))
                 {
                     if(gameState[row-1][col].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col-1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col-1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col-1].contains("3"+target)){attackCounter=attackCounter+1;}
                 }
             }
             //General Purpose Board Move    
             else
                {
                    if(gameState[row][col].contains("3"))
                 {
                     if(gameState[row+1][col].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col-1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col-1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col+1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col-1].contains("2"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col+1].contains("2"+target)){attackCounter=attackCounter+1;}
                 }
                 if(gameState[row][col].contains("2"))
                 {
                     if(gameState[row+1][col].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col-1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col-1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col+1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col-1].contains("1"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col+1].contains("1"+target)){attackCounter=attackCounter+1;}
                 }
                 if(gameState[row][col].contains("1"))
                 {
                     if(gameState[row+1][col].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col+1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row][col-1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col-1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row-1][col+1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col-1].contains("3"+target)){attackCounter=attackCounter+1;}
                     if(gameState[row+1][col+1].contains("3"+target)){attackCounter=attackCounter+1;}
                 }
                }
             
             }
         }
     }
     return attackCounter;
 }
}
