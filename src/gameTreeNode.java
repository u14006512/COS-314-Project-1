
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
    ArrayList<gameTreeNode> childrenStates;
    gameTreeNode()
    {
        srcMove="";
        containedState=null;
        move="";
        a=0;
        b=0;
        v=0;
        childrenStates=null;
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
