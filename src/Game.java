
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

/**
 *
 * @author Emilio Singh u14006512
 */
public class Game 
{
    public String[][] getState()
    {
        return gameState;
    }

    public int evalFunction(gameTreeNode node)
    {
        Random n=new Random();
        return n.nextInt(10)+1;
    }
    public boolean checkTerminal(gameTreeNode node)
    {
        
        if (checkForValidMoves(node.containedState,"A")==false || checkForValidMoves(node.containedState,"R")==false || gameWon(node.containedState)==true)
                
        return true;
        else return false;
    }    
    public ArrayList<String> generateChildren(String[][] state,String side)
    {
        ArrayList<String> pieces=new ArrayList<>();
        ////////////////////////////////////////////////////////////////////////////////////////
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<6;j++)
            {
                if (state[i][j].contains(side))
                {
                    pieces.add(0,state[i][j]+";"+Integer.toString(j)+Integer.toString(i));
                }
            }
        }
        //////////////////////////////////////////////////////////////////////////////////////////
        ArrayList<String> destMoves=new ArrayList<String>();
        for (int k=0;k<pieces.size();k++)
        {
            String temp=pieces.get(k).substring(pieces.get(k).indexOf(";")+1)+side;
            int cT=Integer.parseInt(temp.substring(1,2));
            String srcMove=reverseCol(Integer.parseInt(temp.substring(0,1)))+Integer.toString(cT+1)+side;
            ArrayList<moveNode>tmp=getMoveSet(srcMove);
  
            
            
            for(int m=0;m<tmp.size();m++)
            {
                String moveTmp=reverseCol(tmp.get(m).y)+Integer.toString(tmp.get(m).x+1);
                if (validateMove(srcMove, moveTmp)==0)
                {
                    destMoves.add(moveTmp+";"+srcMove);
                    
                }
            }
          
        }
        /////////////////////////////////////////////////////////////////////////////////////////////
        /*
        To do, implement the process that converts the moves in destMoves into new gameTreeNodes
       
        */
        return destMoves;
    } 
    
    public double alphaBetaPruning(gameTreeNode node,int depth, double alpha, double beta, boolean max,int team)
    {
        String maxSide="",minSide="";
        if (team==0) 
            {
                maxSide="B";
                minSide="R";
            } else 
            {
                maxSide="R";
                minSide="B";
            }
        
        if(depth==0 || (checkTerminal(node))==true) return evalFunction(node);
        if(max)
        {
            /*
            childList=generateChildren(node.state, "B");
            node.childList=childList;
            
            for each node in childList
            */
            node.v=Double.NEGATIVE_INFINITY;
  
            ArrayList<String> listMoves=generateChildren(node.containedState,maxSide);
            
            for(int u=0;u<listMoves.size();u++)
            {gameTreeNode tmp=new gameTreeNode(listMoves.get(u),node.containedState,alpha,beta);
            if (node.childrenStates.size()==0 || node.childrenStates.contains(tmp)==false)
            node.childrenStates.add(tmp);
            node.childrenStates.get(u).setSrcMove(listMoves.get(u).substring(listMoves.get(u).indexOf(";")+1));
            node.v=Double.max(node.v, alphaBetaPruning(node.childrenStates.get(u), depth-1, node.a, node.b, false,team));
            node.a=Double.max(node.a, node.v);
            
            if (node.b<=node.a)
                {
                    for(int i=u;i<node.childrenStates.size();i++)
                    {
                    node.childrenStates.remove(i);
                    }
                break;    
                }
            }
        return node.v;
        
        }else 
            {
                        /*
            childList=generateChildren(node.state, "R");
            node.childList=childList;
            
            for each node in childList
            */
             node.v=Double.POSITIVE_INFINITY;
            
           ArrayList<String> listMoves=generateChildren(node.containedState,minSide);
            
            for(int u=0;u<listMoves.size();u++)
            {
            node.childrenStates.add(new gameTreeNode(listMoves.get(u),node.containedState,alpha,beta));
            
            node.v=Double.min(node.v, alphaBetaPruning(node.childrenStates.get(u), depth-1, node.a, node.b, true,team));
            node.b=Double.min(node.b, node.v);
            
            if (node.b<=node.a)
                {
                    for(int i=u;i<node.childrenStates.size();i++)
                    {
                    node.childrenStates.remove(i);
                    }
                break;    
                }
            }
        return node.v;
        }     
    }
    private  String[][] gameState=new String[6][6];
    public Game()
    {
       
        for (int i=0;i<6;i++)
        {
            for(int j=0;j<6;j++)
            {
                gameState[i][j]="-";

            }
        }
        
        initialiseMatrix();

    }
    public boolean determineCaptureValidity(String src,String dest)
    {
        boolean status=false;
        int srcCol,srcRow,destCol,destRow;
        ///Col
        srcCol=translateCol(src.substring(0,1));
        destCol=translateCol(dest.substring(0,1));
        
        ///Row
        srcRow=Integer.parseInt(src.substring(1,2))-1;
        destRow=Integer.parseInt(dest.substring(1))-1;
        
        String srcPiece,destPiece;
        
        srcPiece=getCell(srcRow, srcCol);
        destPiece=getCell(destRow, destCol);
        
        switch(srcPiece.charAt(0))
        {
            case '3': 
            {
                if (destPiece.charAt(0)=='2')
                {
                    status=true;
                } else status=false;
            }
                break;
            case '2':
            {
                if (destPiece.charAt(0)=='1')
                {
                    status=true;
                } else status=false;
            }
                break;
            case '1':
            {
                if (destPiece.charAt(0)=='3')
                {
                    status=true;
                } else status=false;
            }
                break;
        }
        
        
        return status;
    }
    public boolean gameWon(String[][] state)
    {
        int redCounter=0,blueCounter=0;
        for(int i=0;i<6;i++)
        {
            for (int j=0;j<6;j++)
            {
                if (state[i][j].contains("B"))
                {
                    blueCounter++;
                } else if (state[i][j].contains("R"))
                    {
                        redCounter++;
                    }
            }
        }
        if (redCounter>0 && blueCounter>0)
        {
            return false;
        } else if (redCounter==0 || blueCounter==0)
            {
            return true;        
            }
        return false;
    }
    public void initialiseMatrix()
    {
        /////RED
        //row1
        gameState[0][0]="3R";
        gameState[0][1]="2R";
        gameState[0][2]="1R";
        gameState[0][3]="3R";
        gameState[0][4]="2R";
        gameState[0][5]="1R";
        //row2
        gameState[1][0]="3R";
        gameState[1][1]="2R";
        gameState[1][2]="1R";
        gameState[1][3]="3R";
        gameState[1][4]="2R";
        gameState[1][5]="1R";
        /////
        
        ////BLUE
        //row1
        gameState[4][0]="3B";
        gameState[4][1]="2B";
        gameState[4][2]="1B";
        gameState[4][3]="3B";
        gameState[4][4]="2B";
        gameState[4][5]="1B";
        //row2
        
        gameState[5][0]="3B";
        gameState[5][1]="2B";
        gameState[5][2]="1B";
        gameState[5][3]="3B";
        gameState[5][4]="2B";
        gameState[5][5]="1B";
        ////
    }
    public String getCell(int a,int b){return gameState[a][b];}
    public void setCell(String state,int a,int b)
    {
        gameState[a][b]=state;
    }
    public boolean checkForValidMoves(String[][] state,String side)
    {
        int redMoves=0,blueMoves=0;
        if (side.equals("R"))
        {
            for (int i=0;i<6;i++)
            {
                for(int j=0;j<6;j++)
                {
                    if(state[i][j].contains("R")==true)
                    {
                        String src=reverseCol(j)+(i+1)+"R";
                        redMoves=redMoves+getMoveSet(src).size();
                    }
                }
            }
        if (redMoves==0) return false; else return true;    
        }else if (side.equals("B"))
            {
            for (int i=0;i<6;i++)
            {
                for(int j=0;j<6;j++)
                {
                    if(state[i][j].contains("B")==true)
                    {
                        String src=reverseCol(j)+(i+1)+"B";
                        blueMoves=blueMoves+getMoveSet(src).size();
                    }
                }
            }
        
            if (blueMoves==0) return false; else return true;
    }
        
    return true;
    
}    
    public String reverseCol(int a)
    {
        String num="-1";

        switch (a)
        {
            case 0: num="A";
                break;
            case 1: num="B";
                break;
            case 2:  num="C";
                break;
            case 3:  num="D";
                break;    
            case 4:  num="E";
                break;         
            case 5: num="F";
                break;
        }
        
        return num;
    }
    public int translateCol(String a)
    {
        int num=-1;
        a=a.toUpperCase();
        switch (a.charAt(0))
        {
            case 'A': num=0;
                break;
            case 'B': num=1;
                break;
            case 'C':  num=2;
                break;
            case 'D':  num=3;
                break;    
            case 'E':  num=4;
                break;         
            case 'F': num=5;
                break;
        }
        
        return num;
    }    
    public ArrayList<moveNode> getMoveSet(String src)
    {
           int row=-1,col=-1;
           row=Integer.parseInt(src.substring(1,2))-1;
           col=translateCol(src.substring(0,1));
           ArrayList<moveNode> moveCoordinates=new ArrayList<>();
           
           moveCoordinates.clear();

           /////////////////
           ///x-1;y-1
           moveNode tmp1=new moveNode(row,col);
           tmp1.x=tmp1.x-1;
           tmp1.y=tmp1.y-1;
           moveCoordinates.add(tmp1);
           ////////////////
           
           /////////////////
           ///x;y-1
           moveNode tmp2=new moveNode(row,col);
           tmp2.y=tmp2.y-1;
           moveCoordinates.add(tmp2);
           ////////////////
           
           /////////////////
           ///x+1;y-1
           moveNode tmp3=new moveNode(row,col);
           tmp3.x=tmp3.x+1;
           tmp3.y=tmp3.y-1;
           moveCoordinates.add(tmp3);
           

           ////////////////
           
           /////////////////
           ///x-1;
           moveNode tmp4=new moveNode(row,col);
           tmp4.x=tmp4.x-1;
           moveCoordinates.add(tmp4);

           ////////////////
           
           /////////////////
           ///x+1;
           moveNode tmp5=new moveNode(row,col);
           tmp5.x=tmp5.x+1;
           moveCoordinates.add(tmp5);

           ////////////////
           
           /////////////////
           ///x-1;y+1
           moveNode tmp6=new moveNode(row,col);
           tmp6.x=tmp6.x-1;
           tmp6.y=tmp6.y+1;
           moveCoordinates.add(tmp6);

           ////////////////
           
           /////////////////
           ///y+1
           moveNode tmp7=new moveNode(row,col);
           tmp7.y=tmp7.y+1;
           moveCoordinates.add(tmp7);
           ////////////////
           
           /////////////////
           ///x+1;y+1
           moveNode tmp8=new moveNode(row,col);
           tmp8.x=tmp8.x+1;
           tmp8.y=tmp8.y+1;
           moveCoordinates.add(tmp8);

           ////////////////
           
           //Return Move
           if (src.endsWith("B") && row==0)
           {
           //x-1;y
           moveNode tmp9=new moveNode(5,col);
           tmp9.y=tmp9.y-1;
           
           moveCoordinates.add(tmp9);
         
           //x+1;y
           moveNode tmp10=new moveNode(5,col);
           tmp10.y=tmp10.y+1;
           
           moveCoordinates.add(tmp10);
           
           //x;y    
           moveNode tmp11=new moveNode(5,col);
           
           moveCoordinates.add(tmp11);
           
          
           
           } else 
               if (src.endsWith("R") && row==5)
                {
                //x-1;y
           moveNode tmp11=new moveNode(0,col);         
           tmp11.y=tmp11.y-1;
           
           moveCoordinates.add(tmp11);
           //x+1;y
           moveNode tmp12=new moveNode(0,col);
           tmp12.y=tmp12.y+1;
           
           moveCoordinates.add(tmp12);
           
            //x;y    
           moveNode tmp13=new moveNode(0,col);
           moveCoordinates.add(tmp13);
                }
           
            ArrayList<moveNode> finalMoveSet=new ArrayList<>();
           for(int i=0;i<moveCoordinates.size();i++)
           {/*
            (Math.abs((moveCoordinates.get(i).y-row)) >1 
               || Math.abs((moveCoordinates.get(i).x)-col) >1
               ||(Math.abs((moveCoordinates.get(i).y-row)) >1 &&Math.abs((moveCoordinates.get(i).x)-col) >1)
               ||moveCoordinates.get(i).x>5 
               || moveCoordinates.get(i).x<0 
               || moveCoordinates.get(i).y>5 
               || moveCoordinates.get(i).y<0 
               ||(getCell(moveCoordinates.get(i).x,moveCoordinates.get(i).y).toUpperCase().contains(src.substring(src.length()-1))))   
               */
              if((moveCoordinates.get(i).x<=5 && moveCoordinates.get(i).x>=0 ) && (moveCoordinates.get(i).y<=5 && moveCoordinates.get(i).y>=0 ) &&
                      !(getCell(moveCoordinates.get(i).x,moveCoordinates.get(i).y).contains(src.substring(src.length()-1))))
               {
                    finalMoveSet.add(moveCoordinates.get(i));
               } 
           } 
           
           


        return finalMoveSet;
    }
    public void updateBoard(String src,String dest)
    {
        boolean status=false;
        int srcCol,srcRow,destCol,destRow;
        ///Col
        srcCol=translateCol(src.substring(0,1));
        destCol=translateCol(dest.substring(0,1));
        
        ///Row
        srcRow=Integer.parseInt(src.substring(1,2))-1;
        destRow=Integer.parseInt(dest.substring(1))-1;
        
        String srcPiece,destPiece;
        
        srcPiece=getCell(srcRow, srcCol);
        destPiece=getCell(destRow, destCol);
        setCell("-",srcRow,srcCol);
        setCell(srcPiece, destRow, destCol);
    }
    public int validateMove(String src,String dest)
    {
        boolean found=false; 
        int destRow=-1,destCol=-1;
        int status=-1;
        ///Blue Move
        if (src.endsWith("B")==true)
        {
            
           String move=getCell(Integer.parseInt(src.substring(1,2))-1,translateCol(src.substring(0,1)));
           String dMove=getCell(Integer.parseInt(dest.substring(1))-1,translateCol(dest.substring(0,1)));
           if (move.equals("-")|| !move.endsWith("B") || dMove.endsWith("B"))
           {
               return 1;
           }
           
           ArrayList<moveNode> moveList=getMoveSet(src);
           
           destRow=Integer.parseInt(dest.substring(1))-1;
           destCol=translateCol(dest.substring(0,1));
           
           int srcRow,srcCol;
           srcRow=Integer.parseInt(src.substring(1,2))-1;
           srcCol=translateCol(src.substring(0,1));
           
           if(move.endsWith("R") && srcRow==5 && (destRow!=4 || destRow!=0)) return 1;
           if(move.endsWith("B") && srcRow==0 && (destRow!=5 || destRow!=1)) return 1;
           if(Math.abs(destRow-srcRow)!=1 && Math.abs(destCol-srcCol)!=1) return 1;
           if (moveList.size()==0) return 1;
           for(int i=0;i<moveList.size() && found==false;i++)
           {
           if (moveList.get(i).y==destCol && moveList.get(i).x==destRow )
                {
                    found=true;
                    status=0;
                }    
           }
           
           
        } else
        ///Red Move    
        if (src.endsWith("R")==true)
        {
            String dMove=getCell(Integer.parseInt(dest.substring(1))-1,translateCol(dest.substring(0,1)));
           String move=getCell(Integer.parseInt(src.substring(1,2))-1,translateCol(src.substring(0,1)));
           if (move.equals("-")|| !move.endsWith("R")|| dMove.endsWith("R"))
           {
               return 1;
           }
           
           ArrayList<moveNode> moveList=getMoveSet(src);
           
           destRow=Integer.parseInt(dest.substring(1))-1;
           destCol=translateCol(dest.substring(0,1));
           
           int srcRow,srcCol;
           srcRow=Integer.parseInt(src.substring(1,2))-1;
           srcCol=translateCol(src.substring(0,1));
           
           if(move.endsWith("R") && srcRow==5 && (destRow!=4 || destRow!=0)) return 1;
           if(move.endsWith("B") && srcRow==0 && (destRow!=5 || destRow!=1)) return 1;
           if(Math.abs(destRow-srcRow)!=1 && Math.abs(destCol-srcCol)!=1) return 1;
           if (moveList.size()==0) return 1;           
           for(int i=0;i<moveList.size() && found==false;i++)
           {
           if (moveList.get(i).y==destCol && moveList.get(i).x==destRow )
                {
                    found=true;
                    status=0;
                }    
           }
        }

        if (found==true)
        {
            if (getCell(destRow,destCol).equals("-"))
            {
                return 0;
            } else if (!getCell(destRow,destCol).equals("-")) //Check for capture statues
                {
                    if (src.endsWith("B") && getCell(destRow,destCol).endsWith("R"))
                    {
                     if (determineCaptureValidity(src, dest)==true)
                     {
                         status=0;
                     }   else return 1;
                     
                    }else
                    
                    if (src.endsWith("R") && getCell(destRow,destCol).endsWith("B"))
                    {
                      if (determineCaptureValidity(src, dest)==true)
                     {
                         status=0;
                     }  else return 1;
                    }
                }
        }
        
        return status;
    }
public boolean checkFormat(String a)
{
    a=a.toUpperCase();
    ///COL CHECK
    int val=0;
    
    switch(a.charAt(0))
    {
        case 'A':val=1;
            break;
        case 'B':val=1;
            break;
        case 'C':val=1;
            break;
        case 'D':val=1;
            break;
        case 'E':val=1;
            break;
        case 'F':val=1;
            break;
    };
    if (val==1)
    {
        //Row Check
        int b=Character.getNumericValue(a.charAt(1));
                if (b<=6 && b>=1){val=2;}
    }
    if (val==2) return true; else return false;
}    
public String updateState(String src,String dest)
{
    String a,b;
    a=src;
    b=dest;
    String val="";
    int srcR,srcC,destC,destR;
    
    if (dest.length()==6)
    {
    srcR=Integer.parseInt(b.substring(4,5))-1;
    srcC=translateCol(b.substring(3,4));
    
    destR=Integer.parseInt(b.substring(1,2))-1;
    destC=translateCol(b.substring(0,1));
    } else {

    srcR=Integer.parseInt(a.substring(1,2))-1;
    srcC=translateCol(a.substring(0,1));
    
    destR=Integer.parseInt(b.substring(1))-1;
    destC=translateCol(b.substring(0,1));
    

    }
    val=Integer.toString(srcR)+Integer.toString(srcC)+Integer.toString(destR)+Integer.toString(destC);
    return val;
}
public void moveFunction(JComponent a,JComponent b)
{
    
    int x,y;
    
    x=a.getX();
    y=a.getY();
    b.getParent().setPreferredSize(b.getParent().getPreferredSize());
    a.getParent().setPreferredSize(a.getParent().getPreferredSize());
    
    a.setVisible(false);
    JComponent tmp=(JComponent) b.getParent().getComponent(0);
    b.getParent().remove(0);
    a.getParent().add(tmp);
    
}    
}
