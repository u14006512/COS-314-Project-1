
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Emilio Singh u14006512
 */
public class Game 
{

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
           moveNode tmp9=new moveNode(row,col);
           tmp9.x=tmp9.x-1;
           tmp9.y=5;
           
           moveCoordinates.add(tmp9);
         
           //x+1;y
           moveNode tmp10=new moveNode(row,col);
           tmp10.x=tmp10.x+1;
           tmp10.y=5;
           
           moveCoordinates.add(tmp10);
           
           //x;y    
           moveNode tmp11=new moveNode(row,col);
           tmp11.y=5;
           
           moveCoordinates.add(tmp11);
           
          
           
           } else 
               if (src.endsWith("R") && row==5)
                {
                //x-1;y
           moveNode tmp11=new moveNode(row,col);         
           tmp11.x=tmp11.x-1;
           tmp11.y=0;
           
           moveCoordinates.add(tmp11);
           //x+1;y
           moveNode tmp12=new moveNode(row,col);
           tmp12.x=tmp12.x+1;
           tmp12.y=0;
           
           moveCoordinates.add(tmp12);
           
            //x;y    
           moveNode tmp13=new moveNode(row,col);
            tmp13.y=0;
                }
           
            ArrayList<moveNode> finalMoveSet=new ArrayList<>();
           for(int i=0;i<moveCoordinates.size();i++)
           {
               
               if(moveCoordinates.get(i).x>5 || moveCoordinates.get(i).x<0 || moveCoordinates.get(i).y>5 || moveCoordinates.get(i).y<0 || src.substring(2).equals(getCell(moveCoordinates.get(i).x,moveCoordinates.get(i).y)))
               {
                   moveCoordinates.remove(i);
                   i=0;           
               } else
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
           if (move.equals("-")|| !move.endsWith("B"))
           {
               return 1;
           }
           
           ArrayList<moveNode> moveList=getMoveSet(src);
        
           destRow=Integer.parseInt(dest.substring(1))-1;
           destCol=translateCol(dest.substring(0,1));
           
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
           String move=getCell(Integer.parseInt(src.substring(1,2))-1,translateCol(src.substring(0,1)));
           if (move.equals("-")|| !move.endsWith("R"))
           {
               return 1;
           }
           
           ArrayList<moveNode> moveList=getMoveSet(src);
           
           destRow=Integer.parseInt(dest.substring(1))-1;
           destCol=translateCol(dest.substring(0,1));
           
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
    int srcR,srcC,destC,destR;
    srcR=Integer.parseInt(a.substring(1,2))-1;
    srcC=translateCol(src.substring(0,1));
    
    destR=Integer.parseInt(b.substring(1))-1;
    destC=translateCol(b.substring(0,1));
    
    String val=Integer.toString(srcR)+Integer.toString(srcC)+Integer.toString(destR)+Integer.toString(destC);
    
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
