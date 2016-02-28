
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SinghAn
 */
public  class gameTreeNode {
    String[][] containedState;
    String move,srcMove;
    double a,b,v;
    ArrayList<gameTreeNode> childrenStates=new ArrayList<>();
    gameTreeNode()
    {
        srcMove="";
        for(int u=0;u<6;u++)
            for(int k=0;k<6;k++)
            {
                containedState[u][k]="";
            }
        move="";
        a=0;
        b=0;
        v=0;
        childrenStates=new ArrayList<>();
    }
    public void setSrcMove(String a)
    {
        srcMove=a;
    }
    gameTreeNode(String moveIn, String[][] newState, double alpha, double beta)
    {
        move=moveIn;
        containedState=newState;
        a=alpha;
        b=beta;
        
    }
}
