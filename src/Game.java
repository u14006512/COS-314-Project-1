
import javax.swing.JComponent;



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
    }
    
    public void initialiseMatrix()
    {
        /////RED
        //row1
        gameState[0][0]="3A1R";
        gameState[0][1]="2B1R";
        gameState[0][2]="1C1R";
        gameState[0][3]="3D1R";
        gameState[0][4]="2E1R";
        gameState[0][5]="1F1R";
        //row2
        gameState[0][0]="3A2R";
        gameState[0][1]="2B2R";
        gameState[0][2]="1C2R";
        gameState[0][3]="3D2R";
        gameState[0][4]="2E2R";
        gameState[0][5]="1F2R";
        /////
        
        ////BLUE
        //row1
        gameState[0][0]="3A5B";
        gameState[0][1]="2B5B";
        gameState[0][2]="1C5B";
        gameState[0][3]="3D5B";
        gameState[0][4]="2E5B";
        gameState[0][5]="1F5B";
        //row2
        
        gameState[0][0]="3A6B";
        gameState[0][1]="2B6B";
        gameState[0][2]="1C6B";
        gameState[0][3]="3D6B";
        gameState[0][4]="2E6B";
        gameState[0][5]="1F6B";
        ////
    }
    public String getCell(int a,int b){return gameState[a][b];}
    public void setCell(String state,int a,int b)
    {
        gameState[a][b]=state;
    }
        
    public boolean validateMove(String src,String dest)
    {

        return false;
    }
    
public void saf(JComponent a,JComponent b)
{
    /*
    int x,y;
    
    x=a.getX();
    y=a.getY();
    b.getParent().setPreferredSize(b.getParent().getPreferredSize());
    a.getParent().setPreferredSize(a.getParent().getPreferredSize());
    
    a.setVisible(false);
    JComponent tmp=(JComponent) b.getParent().getComponent(0);
    b.getParent().remove(0);
    a.getParent().add(tmp);
    */
}    
}
