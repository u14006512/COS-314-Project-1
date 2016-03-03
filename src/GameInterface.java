

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Emilio Singh u14006512
 */
public class GameInterface extends javax.swing.JFrame {
        boolean hva=false;
        Game newGame=new Game();
        private JPanel[][] gameBoard=new JPanel[6][6];
     
    /**
     * Creates new form GameInterface
     */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void aRMove()
    {
            
       gameTreeNode rootNode=new gameTreeNode("Root",newGame.getState(),Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
       int dd=newGame.getD2();
       newGame.alphaBetaPruning(rootNode,dd, rootNode.a,rootNode.b, true,1,1);
        int index=-1;
        double max=-1;
        boolean found=false;
        gameTreeNode tmp=rootNode;
        
        
        for(int j=0;j<tmp.childrenStates.size() && found==false;j++)
        {
        
            if(tmp.childrenStates.get(j).v>max)
            {
                max=tmp.childrenStates.get(j).v;
                index=j;
                
            
            }

        }
                                                boolean gameWon=newGame.gameWon(newGame.getState());
                                        if (gameWon==true)
                                        {
                                            JOptionPane.showMessageDialog(rootPane, "Huzzah! Red Ai has won the game! ");
                                                        subBlue.setEnabled(false);
                                                        subRed.setEnabled(false);
                                                        aiMoveBlue.setEnabled(false);
                                                        aiMoveBlue.setEnabled(false);
                                            int choice=JOptionPane.showConfirmDialog(rootPane,"Would you like to play a new game?", "New Game", JOptionPane.OK_CANCEL_OPTION,0);
                                            if (choice==0){startNewGame();}else System.exit(0);
                                        }  else 
                                            {                                             
                                                if(index!=-1 && tmp.childrenStates!=null){
                                         tmp=tmp.childrenStates.get(index);
                                         newGame.updateBoard(tmp.move.substring(3,5), tmp.move.substring(0,2));
                                         String move= newGame.updateState(tmp.srcMove, tmp.move);
                                    
                                         int srcR,srcC,destC,destR;
                                         srcR=Integer.parseInt(move.substring(0,1));
                                         srcC=Integer.parseInt(move.substring(1,2));
                                         destR=Integer.parseInt(move.substring(2,3));
                                         destC=Integer.parseInt(move.substring(3));
                                         JPanel s=gameBoard[srcR][srcC];
                                         JPanel d=gameBoard[destR][destC];
                                         
                                         JComponent tmp1=(JComponent) s.getComponent(0);
                                           s.setPreferredSize(s.getPreferredSize());
                                           s.getComponent(0).setVisible(false);
                                        if (d.getComponentCount()>0){
                                            d.getComponent(0).setVisible(false);
                                            d.removeAll();}
                                         
                                         d.add(tmp1);
                                         d.getComponent(0).setVisible(true);
                                        // d.getComponent(0).setVisible(true);

                                            if(hva==true)
                                            {
                                                aiMoveRed.setEnabled(false);
                                                subBlue.setEnabled(true);
                                            } else {
                                                aiMoveRed.setEnabled(false);
                                                aiMoveBlue.setEnabled(true);
                                                    }
                                            }else   {
                                                    double res=newGame.materialAnalysis(tmp.containedState);
                                                    
                                                    if(res==0){JOptionPane.showMessageDialog(rootPane, "Surprisingly, the game has ended in a draw! \n Neither side wins");}
                                                    if(res==1){JOptionPane.showMessageDialog(rootPane, "Huzzah! The Blue AI has triumphed based on the material advantages on the board \n The Red AI's ability to win is no longer possible due: \n 1) to lack of sufficient pieces \n 2) lack of the right kind of pieces");}
                                                    if(res==2){JOptionPane.showMessageDialog(rootPane, "Huzzah! The Red AI has triumphed based on the material advantages on the board \n The Blue AI's ability to win is no longer possible due: \n 1) to lack of sufficient pieces \n 2) lack of the right kind of pieces");}
                                                    int choice=JOptionPane.showConfirmDialog(rootPane,"Would you like to play a new game?", "New Game", JOptionPane.OK_CANCEL_OPTION,0);
                                                     if (choice==0){startNewGame();}else System.exit(0);
                                                        subBlue.setEnabled(false);
                                                        subRed.setEnabled(false);
                                                        aiMoveBlue.setEnabled(false);
                                                        aiMoveBlue.setEnabled(false);
                                                    
                                                }
                                            }
    }
    public void aBMove()
    {
        gameTreeNode  rootNode=new gameTreeNode("Root",newGame.getState(),Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
      int dD=newGame.getD();
      newGame.alphaBetaPruning(rootNode,dD , rootNode.a, rootNode.b, true,0,0);
        
                int index=-1;
        double max=-1;
        boolean found=false;
        gameTreeNode tmp=rootNode;
        
        
        for(int j=0;j<tmp.childrenStates.size() && found==false;j++)
        {
        
            if(tmp.childrenStates.get(j).v>max)
            {
                max=tmp.childrenStates.get(j).v;
                index=j;
                
            
            }

        }
        boolean gameWon=newGame.gameWon(newGame.getState());
         if (gameWon==true)
                                        {
                                            JOptionPane.showMessageDialog(rootPane, "Huzzah! Blue Ai has won the game!");
                                            subBlue.setEnabled(false);
                                            subRed.setEnabled(false);
                                            aiMoveBlue.setEnabled(false);
                                            aiMoveRed.setEnabled(false);
                                            int choice=JOptionPane.showConfirmDialog(rootPane,"Would you like to play a new game?", "New Game", JOptionPane.OK_CANCEL_OPTION,0);
                                            if (choice==0){startNewGame();}else System.exit(0);
                                        } else 
                                            {
                                         if(index!=-1 && tmp.childrenStates!=null){
                                         tmp=tmp.childrenStates.get(index);
                                         newGame.updateBoard(tmp.move.substring(3,5), tmp.move.substring(0,2));
                                         String move= newGame.updateState(tmp.srcMove, tmp.move);
            
                                         int srcR,srcC,destC,destR;
                                         srcR=Integer.parseInt(move.substring(0,1));
                                         srcC=Integer.parseInt(move.substring(1,2));
                                         destR=Integer.parseInt(move.substring(2,3));
                                         destC=Integer.parseInt(move.substring(3));
                                         JPanel s=gameBoard[srcR][srcC];
                                         JPanel d=gameBoard[destR][destC];
                                         
                                         JComponent tmp1=(JComponent) s.getComponent(0);
                                          s.setPreferredSize(s.getPreferredSize());
                                           s.getComponent(0).setVisible(false);
                                        if (d.getComponentCount()>0){
                                            d.getComponent(0).setVisible(false);
                                            d.removeAll();}
                                         
                                         d.add(tmp1);
                                         d.getComponent(0).setVisible(true);
                                        // d.getComponent(0).setVisible(true);
 
                                            if (hva==true)
                                            {
                                                aiMoveBlue.setEnabled(false);
                                                subRed.setEnabled(true);
                                            } else{
                                                aiMoveBlue.setEnabled(false);
                                                aiMoveRed.setEnabled(true);
                                                    }
                                            }else 
                                                {
                                                    double res=newGame.materialAnalysis(tmp.containedState);
                                                    if(res==0){JOptionPane.showMessageDialog(rootPane, "Surprisingly, the game has ended in a draw! \n Neither side wins");}
                                                    if(res==1){JOptionPane.showMessageDialog(rootPane, "Huzzah! The Blue AI has triumphed based on the material advantages on the board \n The Red AI's ability to win is no longer possible due: \n 1) to lack of sufficient pieces \n 2) lack of the right kind of pieces");}
                                                    if(res==2){JOptionPane.showMessageDialog(rootPane, "Huzzah! The Red AI has triumphed based on the material advantages on the board \n The Blue AI's ability to win is no longer possible due: \n 1) to lack of sufficient pieces \n 2) lack of the right kind of pieces");}
                                                    int choice=JOptionPane.showConfirmDialog(rootPane,"Would you like to play a new game?", "New Game", JOptionPane.OK_CANCEL_OPTION,0);
                                                    if (choice==0){startNewGame();}else System.exit(0);
                                                        subBlue.setEnabled(false);
                                                        subRed.setEnabled(false);
                                                        aiMoveBlue.setEnabled(false);
                                                        aiMoveBlue.setEnabled(false);
                                                  //JOptionPane.showMessageDialog(rootPane, "Huzzah! Red Ai has won the game! \n It has won owing to material superiority");
                                                }
                                            }
    }
    private void initialiseBoard()
    {
        gameBoard=new JPanel[6][6];
        for (int i=0;i<6;i++)
        {
            for(int j=0;j<6;j++)
            {
             
                gameBoard[i][j]=new JPanel();
                gameBoard[i][j].setMaximumSize(gameBoard[i][j].getSize());
                gameBoard[i][j].setMinimumSize(gameBoard[i][j].getSize());
            }
        }  
        
                /////RED
        //row1
        gameBoard[0][0]=A1;
        gameBoard[0][1]=B1;
        gameBoard[0][2]=C1;
        gameBoard[0][3]=D1;
        gameBoard[0][4]=E1;
        gameBoard[0][5]=F1;
        //row2
        gameBoard[1][0]=A2;
        gameBoard[1][1]=B2;
        gameBoard[1][2]=C2;
        gameBoard[1][3]=D2;
        gameBoard[1][4]=E2;
        gameBoard[1][5]=F2;
        /////
        gameBoard[2][0]=A3;
        gameBoard[2][1]=B3;
        gameBoard[2][2]=C3;
        gameBoard[2][3]=D3;
        gameBoard[2][4]=E3;
        gameBoard[2][5]=F3;
        
        gameBoard[3][0]=A4;
        gameBoard[3][1]=B4;
        gameBoard[3][2]=C4;
        gameBoard[3][3]=D4;
        gameBoard[3][4]=E4;
        gameBoard[3][5]=F4;
       
        gameBoard[4][0]=A5;
        gameBoard[4][1]=B5;
        gameBoard[4][2]=C5;
        gameBoard[4][3]=D5;
        gameBoard[4][4]=E5;
        gameBoard[4][5]=F5;
        
        gameBoard[5][0]=A6;
        gameBoard[5][1]=B6;
        gameBoard[5][2]=C6;
        gameBoard[5][3]=D6;
        gameBoard[5][4]=E6;
        gameBoard[5][5]=F6;
        /////////////////////////////////////////////////////////////////////////////////////////
        
        for (int i=0;i<6;i++)
        {
            for(int j=0;j<6;j++)
            {
                gameBoard[i][j].setMaximumSize(gameBoard[i][j].getSize());
                gameBoard[i][j].setMinimumSize(gameBoard[i][j].getSize());
                gameBoard[i][j].setPreferredSize(gameBoard[i][j].getSize());
                gameBoard[i][j].setSize(gameBoard[i][j].getPreferredSize());
            }
        }
    }
    

    public void startNewGame()
    {
     try 
    {
    Runtime.getRuntime().exec("java -jar  dist/Trimok.jar");
    System.exit(0);
    } 
     catch (IOException e) 
    {
    }
    }

    public void setupGame() throws InterruptedException
    {
        /*
        This segment of code operates at the very start of the program. Once the application window has been created, and subsequently opened,
        the game will present a dialog box to the user to indicate his player mode, HvH,HvAI or AIvAI. 
        
        The player cannot advance without selecting an option.
        Once there, the game will randomly select a player to be Blue. Blue gets to have the first turn as per game convention.
        The random chance is a base 50/50 selection.
        */
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        aiMoveBlue.setEnabled(false);
        aiMoveRed.setEnabled(false);
        subBlue.setEnabled(false);
        subRed.setEnabled(false);
        
        setResizable(false);
        Object options[]={"Human vs Human","Human vs AI","AI vs AI"};
        int n=-1;
        
        
        while (n<0 || n >2){
        n=JOptionPane.showOptionDialog(rootPane, "Select your game mode", "Game Mode Selection", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        int randomValue=new Random().nextInt(4);
        if (n==0)
        {

        
        if (randomValue<=2)
            {
            JOptionPane.showMessageDialog(rootPane, "You are the Blue Player");
            JOptionPane.showMessageDialog(rootPane, "Your opponent is the Red Player");
            subBlue.setEnabled(true);

            subRed.setEnabled(true);
            subRed.setEnabled(false);
            } 
                else 
                {
            JOptionPane.showMessageDialog(rootPane, "Your opponent is the Blue Player");
            JOptionPane.showMessageDialog(rootPane, "You are the Red Player");
            subBlue.setEnabled(true);
            subRed.setEnabled(true);
            subRed.setEnabled(false);
                }
        
        }
        else if (n==1) 
            {
                hva=true;
            
            JOptionPane.showMessageDialog(rootPane, "You are the Blue Player");
            String h="";
            try{
            h=JOptionPane.showInputDialog("Please enter the depth you wish the RED AI to search at \n (number of plies)");
            }
            catch(NumberFormatException e)
            {
                newGame.setDepth2(2);
            }
            if (h.length()>0 &&Integer.parseInt(h)>0)
            {
                newGame.setDepth2(Integer.parseInt(h));
            }
            
            try{
            h=JOptionPane.showInputDialog("Please enter a selection for the evaluation function \n 1: Random \n 2: Basic \n 3: Advanced ");
            }
            catch(NumberFormatException e)
            {
                newGame.setEval2(1);
            }
            if (h.length()>0 && Integer.parseInt(h)>0)
            {
                newGame.setEval2(Integer.parseInt(h));
            }
            

            subBlue.setEnabled(true);
            subRed.setEnabled(false);
            aiMoveRed.setEnabled(false);
            redDest.setEnabled(false);
            redSrc.setEnabled(false);
            } 
              else if (n==2)
                {hva=false;

            String h="";
            try{
            h=JOptionPane.showInputDialog("Please enter the depth you wish the AI 1 to search at \n (number of plies)");
            }
            catch(NumberFormatException e)
            {
                newGame.setDepth(2);
            }
            if (Integer.parseInt(h)>0)
            {
                newGame.setDepth(Integer.parseInt(h));
            }
            h="";
            try{
            h=JOptionPane.showInputDialog("Please enter the depth you wish the AI 2 to search at \n (number of plies)");
            }
            catch(NumberFormatException e)
            {
                newGame.setDepth2(2);
            }
            if (Integer.parseInt(h)>0)
            {
                newGame.setDepth2(Integer.parseInt(h));
            }
            h="";
            try{
            h=JOptionPane.showInputDialog("Please enter a selection for the evaluation function for AI 1 \n 1: Random \n 2: Basic \n 3: Advanced ");
            }
            catch( NumberFormatException e)
            {
                newGame.setEval(1);
            }
            if (h.length()>0 && Integer.parseInt(h)>0)
                newGame.setEval(Integer.parseInt(h));
            h="";    
            try{
            h=JOptionPane.showInputDialog("Please enter a selection for the evaluation function for AI 2 \n 1: Random \n 2: Basic \n 3: Advanced ");
            }
            catch(NumberFormatException e)
            {
                newGame.setEval2(1);
            }
            if (h.length()>0 && Integer.parseInt(h)>0)
            {
                newGame.setEval2(Integer.parseInt(h));
            }
            
            int choice=1;            
            try{
            
            choice=JOptionPane.showOptionDialog(rootPane, "Would you like to enable Auto Play?", "Auto Play", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
            }
            catch(NumberFormatException e)
            {
                
            }
            JOptionPane.showMessageDialog(rootPane, "AI 1 is the Blue Player");
            JOptionPane.showMessageDialog(rootPane, "AI 2 is the Red Player");
            if (choice==0)
            {
                autoplay();
            } else{
                        
                        
                        aiMoveBlue.setEnabled(true);
                        aiMoveRed.setEnabled(true);
                        aiMoveRed.setEnabled(false);
                        blueDest.setEnabled(false);
                        blueSrc.setEnabled(false);
                        subBlue.setEnabled(false);
                        subRed.setEnabled(false);
                        redDest.setEnabled(false);
                        redSrc.setEnabled(false);       
                    } 
                  }  
                }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
        
    }
    public GameInterface() {

        initComponents();
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        E2 = new javax.swing.JPanel();
        R2D = new javax.swing.JPanel();
        F5 = new javax.swing.JPanel();
        B1B = new javax.swing.JPanel();
        D6 = new javax.swing.JPanel();
        B3D = new javax.swing.JPanel();
        B1 = new javax.swing.JPanel();
        R2A = new javax.swing.JPanel();
        D1 = new javax.swing.JPanel();
        R3B = new javax.swing.JPanel();
        C1 = new javax.swing.JPanel();
        R1A = new javax.swing.JPanel();
        A4 = new javax.swing.JPanel();
        E1 = new javax.swing.JPanel();
        R2B = new javax.swing.JPanel();
        A5 = new javax.swing.JPanel();
        B3A = new javax.swing.JPanel();
        F2 = new javax.swing.JPanel();
        R1D = new javax.swing.JPanel();
        D2 = new javax.swing.JPanel();
        R3D = new javax.swing.JPanel();
        A6 = new javax.swing.JPanel();
        B3C = new javax.swing.JPanel();
        A1 = new javax.swing.JPanel();
        R3A = new javax.swing.JPanel();
        B2 = new javax.swing.JPanel();
        R2C = new javax.swing.JPanel();
        B6 = new javax.swing.JPanel();
        B2C = new javax.swing.JPanel();
        C2 = new javax.swing.JPanel();
        R1C = new javax.swing.JPanel();
        C5 = new javax.swing.JPanel();
        B1A = new javax.swing.JPanel();
        E4 = new javax.swing.JPanel();
        D5 = new javax.swing.JPanel();
        B3B = new javax.swing.JPanel();
        E5 = new javax.swing.JPanel();
        B2B = new javax.swing.JPanel();
        B3 = new javax.swing.JPanel();
        F3 = new javax.swing.JPanel();
        D4 = new javax.swing.JPanel();
        E6 = new javax.swing.JPanel();
        B2D = new javax.swing.JPanel();
        A3 = new javax.swing.JPanel();
        A2 = new javax.swing.JPanel();
        R3C = new javax.swing.JPanel();
        E3 = new javax.swing.JPanel();
        C3 = new javax.swing.JPanel();
        C6 = new javax.swing.JPanel();
        B1C = new javax.swing.JPanel();
        F6 = new javax.swing.JPanel();
        B1D = new javax.swing.JPanel();
        C4 = new javax.swing.JPanel();
        B5 = new javax.swing.JPanel();
        B2A = new javax.swing.JPanel();
        F1 = new javax.swing.JPanel();
        R1B = new javax.swing.JPanel();
        B4 = new javax.swing.JPanel();
        D3 = new javax.swing.JPanel();
        F4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        RedPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        subRed = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        redDest = new javax.swing.JTextField();
        redSrc = new javax.swing.JTextField();
        aiMoveRed = new javax.swing.JButton();
        BluePanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        subBlue = new javax.swing.JButton();
        blueSrc = new javax.swing.JTextField();
        blueDest = new javax.swing.JTextField();
        aiMoveBlue = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setBounds(new java.awt.Rectangle(0, 0, 1000, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        E2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        E2.setMaximumSize(new java.awt.Dimension(104, 104));
        E2.setMinimumSize(new java.awt.Dimension(104, 104));

        R2D.setBackground(new java.awt.Color(255, 0, 51));
        R2D.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        R2D.setToolTipText("2 Stack");
        R2D.setMaximumSize(new java.awt.Dimension(34, 28));
        R2D.setMinimumSize(new java.awt.Dimension(34, 28));

        javax.swing.GroupLayout R2DLayout = new javax.swing.GroupLayout(R2D);
        R2D.setLayout(R2DLayout);
        R2DLayout.setHorizontalGroup(
            R2DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        R2DLayout.setVerticalGroup(
            R2DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout E2Layout = new javax.swing.GroupLayout(E2);
        E2.setLayout(E2Layout);
        E2Layout.setHorizontalGroup(
            E2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E2Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(R2D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        E2Layout.setVerticalGroup(
            E2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E2Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(R2D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        F5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        F5.setMaximumSize(new java.awt.Dimension(104, 104));
        F5.setMinimumSize(new java.awt.Dimension(104, 104));

        B1B.setBackground(new java.awt.Color(0, 0, 255));
        B1B.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B1B.setToolTipText("1 Stack");
        B1B.setMaximumSize(new java.awt.Dimension(34, 14));
        B1B.setMinimumSize(new java.awt.Dimension(34, 14));

        javax.swing.GroupLayout B1BLayout = new javax.swing.GroupLayout(B1B);
        B1B.setLayout(B1BLayout);
        B1BLayout.setHorizontalGroup(
            B1BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        B1BLayout.setVerticalGroup(
            B1BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout F5Layout = new javax.swing.GroupLayout(F5);
        F5.setLayout(F5Layout);
        F5Layout.setHorizontalGroup(
            F5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(F5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(B1B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        F5Layout.setVerticalGroup(
            F5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, F5Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(B1B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        D6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        D6.setMaximumSize(new java.awt.Dimension(104, 104));
        D6.setMinimumSize(new java.awt.Dimension(104, 104));

        B3D.setBackground(new java.awt.Color(0, 0, 255));
        B3D.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B3D.setToolTipText("3 Stack");
        B3D.setMaximumSize(new java.awt.Dimension(34, 53));
        B3D.setMinimumSize(new java.awt.Dimension(34, 53));

        javax.swing.GroupLayout B3DLayout = new javax.swing.GroupLayout(B3D);
        B3D.setLayout(B3DLayout);
        B3DLayout.setHorizontalGroup(
            B3DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        B3DLayout.setVerticalGroup(
            B3DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout D6Layout = new javax.swing.GroupLayout(D6);
        D6.setLayout(D6Layout);
        D6Layout.setHorizontalGroup(
            D6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D6Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(B3D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        D6Layout.setVerticalGroup(
            D6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D6Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(B3D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        B1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B1.setMaximumSize(new java.awt.Dimension(104, 104));
        B1.setMinimumSize(new java.awt.Dimension(104, 104));

        R2A.setBackground(new java.awt.Color(255, 0, 51));
        R2A.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        R2A.setToolTipText("2 Stack");
        R2A.setMaximumSize(new java.awt.Dimension(34, 28));
        R2A.setMinimumSize(new java.awt.Dimension(24, 28));

        javax.swing.GroupLayout R2ALayout = new javax.swing.GroupLayout(R2A);
        R2A.setLayout(R2ALayout);
        R2ALayout.setHorizontalGroup(
            R2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        R2ALayout.setVerticalGroup(
            R2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout B1Layout = new javax.swing.GroupLayout(B1);
        B1.setLayout(B1Layout);
        B1Layout.setHorizontalGroup(
            B1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(R2A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        B1Layout.setVerticalGroup(
            B1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B1Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(R2A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        D1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        D1.setMaximumSize(new java.awt.Dimension(104, 104));
        D1.setMinimumSize(new java.awt.Dimension(104, 104));

        R3B.setBackground(new java.awt.Color(255, 0, 51));
        R3B.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        R3B.setToolTipText("3 Stack");
        R3B.setMaximumSize(new java.awt.Dimension(34, 53));
        R3B.setMinimumSize(new java.awt.Dimension(34, 53));

        javax.swing.GroupLayout R3BLayout = new javax.swing.GroupLayout(R3B);
        R3B.setLayout(R3BLayout);
        R3BLayout.setHorizontalGroup(
            R3BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        R3BLayout.setVerticalGroup(
            R3BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout D1Layout = new javax.swing.GroupLayout(D1);
        D1.setLayout(D1Layout);
        D1Layout.setHorizontalGroup(
            D1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(R3B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        D1Layout.setVerticalGroup(
            D1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(R3B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        C1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        C1.setMaximumSize(new java.awt.Dimension(104, 104));
        C1.setMinimumSize(new java.awt.Dimension(104, 104));

        R1A.setBackground(new java.awt.Color(255, 0, 51));
        R1A.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        R1A.setToolTipText("1 Stack");
        R1A.setMaximumSize(new java.awt.Dimension(34, 14));
        R1A.setMinimumSize(new java.awt.Dimension(34, 14));

        javax.swing.GroupLayout R1ALayout = new javax.swing.GroupLayout(R1A);
        R1A.setLayout(R1ALayout);
        R1ALayout.setHorizontalGroup(
            R1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        R1ALayout.setVerticalGroup(
            R1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout C1Layout = new javax.swing.GroupLayout(C1);
        C1.setLayout(C1Layout);
        C1Layout.setHorizontalGroup(
            C1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(C1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(R1A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        C1Layout.setVerticalGroup(
            C1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, C1Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(R1A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        A4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A4.setMaximumSize(new java.awt.Dimension(104, 104));
        A4.setMinimumSize(new java.awt.Dimension(104, 104));

        javax.swing.GroupLayout A4Layout = new javax.swing.GroupLayout(A4);
        A4.setLayout(A4Layout);
        A4Layout.setHorizontalGroup(
            A4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        A4Layout.setVerticalGroup(
            A4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        E1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        E1.setMaximumSize(new java.awt.Dimension(104, 104));
        E1.setMinimumSize(new java.awt.Dimension(104, 104));

        R2B.setBackground(new java.awt.Color(255, 0, 51));
        R2B.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        R2B.setToolTipText("2 Stack");
        R2B.setMaximumSize(new java.awt.Dimension(34, 28));
        R2B.setMinimumSize(new java.awt.Dimension(34, 28));

        javax.swing.GroupLayout R2BLayout = new javax.swing.GroupLayout(R2B);
        R2B.setLayout(R2BLayout);
        R2BLayout.setHorizontalGroup(
            R2BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        R2BLayout.setVerticalGroup(
            R2BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout E1Layout = new javax.swing.GroupLayout(E1);
        E1.setLayout(E1Layout);
        E1Layout.setHorizontalGroup(
            E1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E1Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(R2B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        E1Layout.setVerticalGroup(
            E1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E1Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(R2B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        A5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A5.setMaximumSize(new java.awt.Dimension(104, 104));
        A5.setMinimumSize(new java.awt.Dimension(104, 104));

        B3A.setBackground(new java.awt.Color(0, 0, 255));
        B3A.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B3A.setToolTipText("3 Stack");
        B3A.setMaximumSize(new java.awt.Dimension(34, 53));
        B3A.setMinimumSize(new java.awt.Dimension(34, 53));

        javax.swing.GroupLayout B3ALayout = new javax.swing.GroupLayout(B3A);
        B3A.setLayout(B3ALayout);
        B3ALayout.setHorizontalGroup(
            B3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        B3ALayout.setVerticalGroup(
            B3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout A5Layout = new javax.swing.GroupLayout(A5);
        A5.setLayout(A5Layout);
        A5Layout.setHorizontalGroup(
            A5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A5Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(B3A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        A5Layout.setVerticalGroup(
            A5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B3A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        F2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        F2.setMaximumSize(new java.awt.Dimension(104, 104));
        F2.setMinimumSize(new java.awt.Dimension(104, 104));
        F2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                F2MouseDragged(evt);
            }
        });

        R1D.setBackground(new java.awt.Color(255, 0, 51));
        R1D.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        R1D.setToolTipText("1 Stack");
        R1D.setMaximumSize(new java.awt.Dimension(34, 14));
        R1D.setMinimumSize(new java.awt.Dimension(34, 14));

        javax.swing.GroupLayout R1DLayout = new javax.swing.GroupLayout(R1D);
        R1D.setLayout(R1DLayout);
        R1DLayout.setHorizontalGroup(
            R1DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        R1DLayout.setVerticalGroup(
            R1DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout F2Layout = new javax.swing.GroupLayout(F2);
        F2.setLayout(F2Layout);
        F2Layout.setHorizontalGroup(
            F2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(F2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(R1D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        F2Layout.setVerticalGroup(
            F2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, F2Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(R1D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        D2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        D2.setMaximumSize(new java.awt.Dimension(104, 104));
        D2.setMinimumSize(new java.awt.Dimension(104, 104));

        R3D.setBackground(new java.awt.Color(255, 0, 51));
        R3D.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        R3D.setToolTipText("3 Stack");
        R3D.setMaximumSize(new java.awt.Dimension(34, 53));
        R3D.setMinimumSize(new java.awt.Dimension(34, 53));
        R3D.setName(""); // NOI18N

        javax.swing.GroupLayout R3DLayout = new javax.swing.GroupLayout(R3D);
        R3D.setLayout(R3DLayout);
        R3DLayout.setHorizontalGroup(
            R3DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        R3DLayout.setVerticalGroup(
            R3DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout D2Layout = new javax.swing.GroupLayout(D2);
        D2.setLayout(D2Layout);
        D2Layout.setHorizontalGroup(
            D2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D2Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(R3D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        D2Layout.setVerticalGroup(
            D2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(R3D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        A6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A6.setMaximumSize(new java.awt.Dimension(104, 104));
        A6.setMinimumSize(new java.awt.Dimension(104, 104));

        B3C.setBackground(new java.awt.Color(0, 0, 255));
        B3C.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B3C.setToolTipText("3 Stack");
        B3C.setMaximumSize(new java.awt.Dimension(34, 53));
        B3C.setMinimumSize(new java.awt.Dimension(34, 53));
        B3C.setName(""); // NOI18N

        javax.swing.GroupLayout B3CLayout = new javax.swing.GroupLayout(B3C);
        B3C.setLayout(B3CLayout);
        B3CLayout.setHorizontalGroup(
            B3CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        B3CLayout.setVerticalGroup(
            B3CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout A6Layout = new javax.swing.GroupLayout(A6);
        A6.setLayout(A6Layout);
        A6Layout.setHorizontalGroup(
            A6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A6Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(B3C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        A6Layout.setVerticalGroup(
            A6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A6Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(B3C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        A1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A1.setMaximumSize(new java.awt.Dimension(104, 104));
        A1.setMinimumSize(new java.awt.Dimension(104, 104));

        R3A.setBackground(new java.awt.Color(255, 0, 51));
        R3A.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        R3A.setToolTipText("3 Stack");
        R3A.setMaximumSize(new java.awt.Dimension(34, 53));
        R3A.setMinimumSize(new java.awt.Dimension(34, 53));
        R3A.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                R3AMouseDragged(evt);
            }
        });
        R3A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                R3AMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout R3ALayout = new javax.swing.GroupLayout(R3A);
        R3A.setLayout(R3ALayout);
        R3ALayout.setHorizontalGroup(
            R3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        R3ALayout.setVerticalGroup(
            R3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout A1Layout = new javax.swing.GroupLayout(A1);
        A1.setLayout(A1Layout);
        A1Layout.setHorizontalGroup(
            A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(R3A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        A1Layout.setVerticalGroup(
            A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(R3A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        B2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B2.setMaximumSize(new java.awt.Dimension(104, 104));
        B2.setMinimumSize(new java.awt.Dimension(104, 104));

        R2C.setBackground(new java.awt.Color(255, 0, 51));
        R2C.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        R2C.setToolTipText("2 Stack");
        R2C.setMaximumSize(new java.awt.Dimension(34, 28));
        R2C.setMinimumSize(new java.awt.Dimension(34, 28));

        javax.swing.GroupLayout R2CLayout = new javax.swing.GroupLayout(R2C);
        R2C.setLayout(R2CLayout);
        R2CLayout.setHorizontalGroup(
            R2CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        R2CLayout.setVerticalGroup(
            R2CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout B2Layout = new javax.swing.GroupLayout(B2);
        B2.setLayout(B2Layout);
        B2Layout.setHorizontalGroup(
            B2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(B2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(R2C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        B2Layout.setVerticalGroup(
            B2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B2Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(R2C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        B6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B6.setMaximumSize(new java.awt.Dimension(104, 104));
        B6.setMinimumSize(new java.awt.Dimension(104, 104));

        B2C.setBackground(new java.awt.Color(0, 0, 255));
        B2C.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B2C.setToolTipText("2 Stack");
        B2C.setMaximumSize(new java.awt.Dimension(34, 28));
        B2C.setMinimumSize(new java.awt.Dimension(34, 28));

        javax.swing.GroupLayout B2CLayout = new javax.swing.GroupLayout(B2C);
        B2C.setLayout(B2CLayout);
        B2CLayout.setHorizontalGroup(
            B2CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        B2CLayout.setVerticalGroup(
            B2CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout B6Layout = new javax.swing.GroupLayout(B6);
        B6.setLayout(B6Layout);
        B6Layout.setHorizontalGroup(
            B6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B6Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(B2C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        B6Layout.setVerticalGroup(
            B6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B6Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(B2C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        C2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        C2.setMaximumSize(new java.awt.Dimension(104, 104));
        C2.setMinimumSize(new java.awt.Dimension(104, 104));

        R1C.setBackground(new java.awt.Color(255, 0, 51));
        R1C.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        R1C.setToolTipText("1 Stack");
        R1C.setMaximumSize(new java.awt.Dimension(34, 14));
        R1C.setMinimumSize(new java.awt.Dimension(34, 14));
        R1C.setName(""); // NOI18N

        javax.swing.GroupLayout R1CLayout = new javax.swing.GroupLayout(R1C);
        R1C.setLayout(R1CLayout);
        R1CLayout.setHorizontalGroup(
            R1CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        R1CLayout.setVerticalGroup(
            R1CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout C2Layout = new javax.swing.GroupLayout(C2);
        C2.setLayout(C2Layout);
        C2Layout.setHorizontalGroup(
            C2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(C2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(R1C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        C2Layout.setVerticalGroup(
            C2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, C2Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(R1C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        C5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        C5.setMaximumSize(new java.awt.Dimension(104, 104));
        C5.setMinimumSize(new java.awt.Dimension(104, 104));

        B1A.setBackground(new java.awt.Color(0, 0, 255));
        B1A.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B1A.setToolTipText("1 Stack");
        B1A.setMaximumSize(new java.awt.Dimension(34, 14));
        B1A.setMinimumSize(new java.awt.Dimension(34, 14));

        javax.swing.GroupLayout B1ALayout = new javax.swing.GroupLayout(B1A);
        B1A.setLayout(B1ALayout);
        B1ALayout.setHorizontalGroup(
            B1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        B1ALayout.setVerticalGroup(
            B1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout C5Layout = new javax.swing.GroupLayout(C5);
        C5.setLayout(C5Layout);
        C5Layout.setHorizontalGroup(
            C5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, C5Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(B1A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        C5Layout.setVerticalGroup(
            C5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, C5Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(B1A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        E4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        E4.setMaximumSize(new java.awt.Dimension(104, 104));
        E4.setMinimumSize(new java.awt.Dimension(104, 104));

        javax.swing.GroupLayout E4Layout = new javax.swing.GroupLayout(E4);
        E4.setLayout(E4Layout);
        E4Layout.setHorizontalGroup(
            E4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        E4Layout.setVerticalGroup(
            E4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        D5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        D5.setMaximumSize(new java.awt.Dimension(104, 104));
        D5.setMinimumSize(new java.awt.Dimension(104, 104));

        B3B.setBackground(new java.awt.Color(0, 0, 255));
        B3B.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B3B.setToolTipText("3 Stack");
        B3B.setMaximumSize(new java.awt.Dimension(34, 53));
        B3B.setMinimumSize(new java.awt.Dimension(34, 53));

        javax.swing.GroupLayout B3BLayout = new javax.swing.GroupLayout(B3B);
        B3B.setLayout(B3BLayout);
        B3BLayout.setHorizontalGroup(
            B3BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        B3BLayout.setVerticalGroup(
            B3BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout D5Layout = new javax.swing.GroupLayout(D5);
        D5.setLayout(D5Layout);
        D5Layout.setHorizontalGroup(
            D5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D5Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(B3B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        D5Layout.setVerticalGroup(
            D5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B3B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        E5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        E5.setMaximumSize(new java.awt.Dimension(104, 104));
        E5.setMinimumSize(new java.awt.Dimension(104, 104));

        B2B.setBackground(new java.awt.Color(0, 0, 255));
        B2B.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B2B.setToolTipText("2 Stack");
        B2B.setMaximumSize(new java.awt.Dimension(34, 28));
        B2B.setMinimumSize(new java.awt.Dimension(34, 28));

        javax.swing.GroupLayout B2BLayout = new javax.swing.GroupLayout(B2B);
        B2B.setLayout(B2BLayout);
        B2BLayout.setHorizontalGroup(
            B2BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        B2BLayout.setVerticalGroup(
            B2BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout E5Layout = new javax.swing.GroupLayout(E5);
        E5.setLayout(E5Layout);
        E5Layout.setHorizontalGroup(
            E5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E5Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(B2B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        E5Layout.setVerticalGroup(
            E5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B2B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        B3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B3.setMaximumSize(new java.awt.Dimension(104, 104));
        B3.setMinimumSize(new java.awt.Dimension(104, 104));

        javax.swing.GroupLayout B3Layout = new javax.swing.GroupLayout(B3);
        B3.setLayout(B3Layout);
        B3Layout.setHorizontalGroup(
            B3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        B3Layout.setVerticalGroup(
            B3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        F3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        F3.setMaximumSize(new java.awt.Dimension(104, 104));
        F3.setMinimumSize(new java.awt.Dimension(104, 104));

        javax.swing.GroupLayout F3Layout = new javax.swing.GroupLayout(F3);
        F3.setLayout(F3Layout);
        F3Layout.setHorizontalGroup(
            F3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        F3Layout.setVerticalGroup(
            F3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        D4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        D4.setMaximumSize(new java.awt.Dimension(104, 104));
        D4.setMinimumSize(new java.awt.Dimension(104, 104));

        javax.swing.GroupLayout D4Layout = new javax.swing.GroupLayout(D4);
        D4.setLayout(D4Layout);
        D4Layout.setHorizontalGroup(
            D4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        D4Layout.setVerticalGroup(
            D4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        E6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        E6.setMaximumSize(new java.awt.Dimension(104, 104));
        E6.setMinimumSize(new java.awt.Dimension(104, 104));

        B2D.setBackground(new java.awt.Color(0, 0, 255));
        B2D.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B2D.setToolTipText("2 Stack ");
        B2D.setMaximumSize(new java.awt.Dimension(34, 28));
        B2D.setMinimumSize(new java.awt.Dimension(34, 28));

        javax.swing.GroupLayout B2DLayout = new javax.swing.GroupLayout(B2D);
        B2D.setLayout(B2DLayout);
        B2DLayout.setHorizontalGroup(
            B2DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        B2DLayout.setVerticalGroup(
            B2DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout E6Layout = new javax.swing.GroupLayout(E6);
        E6.setLayout(E6Layout);
        E6Layout.setHorizontalGroup(
            E6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E6Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(B2D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        E6Layout.setVerticalGroup(
            E6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E6Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(B2D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        A3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A3.setMaximumSize(new java.awt.Dimension(104, 104));
        A3.setMinimumSize(new java.awt.Dimension(104, 104));

        javax.swing.GroupLayout A3Layout = new javax.swing.GroupLayout(A3);
        A3.setLayout(A3Layout);
        A3Layout.setHorizontalGroup(
            A3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        A3Layout.setVerticalGroup(
            A3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        A2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A2.setMaximumSize(new java.awt.Dimension(104, 104));
        A2.setMinimumSize(new java.awt.Dimension(104, 104));
        A2.setPreferredSize(new java.awt.Dimension(104, 104));

        R3C.setBackground(new java.awt.Color(255, 0, 51));
        R3C.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        R3C.setToolTipText("3 Stack");
        R3C.setMaximumSize(new java.awt.Dimension(34, 53));
        R3C.setMinimumSize(new java.awt.Dimension(34, 53));

        javax.swing.GroupLayout R3CLayout = new javax.swing.GroupLayout(R3C);
        R3C.setLayout(R3CLayout);
        R3CLayout.setHorizontalGroup(
            R3CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        R3CLayout.setVerticalGroup(
            R3CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout A2Layout = new javax.swing.GroupLayout(A2);
        A2.setLayout(A2Layout);
        A2Layout.setHorizontalGroup(
            A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(R3C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        A2Layout.setVerticalGroup(
            A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(R3C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        E3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        E3.setMaximumSize(new java.awt.Dimension(104, 104));
        E3.setMinimumSize(new java.awt.Dimension(104, 104));

        javax.swing.GroupLayout E3Layout = new javax.swing.GroupLayout(E3);
        E3.setLayout(E3Layout);
        E3Layout.setHorizontalGroup(
            E3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        E3Layout.setVerticalGroup(
            E3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        C3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        C3.setMaximumSize(new java.awt.Dimension(104, 104));
        C3.setMinimumSize(new java.awt.Dimension(104, 104));

        javax.swing.GroupLayout C3Layout = new javax.swing.GroupLayout(C3);
        C3.setLayout(C3Layout);
        C3Layout.setHorizontalGroup(
            C3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        C3Layout.setVerticalGroup(
            C3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        C6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        C6.setMaximumSize(new java.awt.Dimension(104, 104));
        C6.setMinimumSize(new java.awt.Dimension(104, 104));

        B1C.setBackground(new java.awt.Color(0, 0, 255));
        B1C.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B1C.setToolTipText("1 Stack");
        B1C.setMaximumSize(new java.awt.Dimension(34, 14));
        B1C.setMinimumSize(new java.awt.Dimension(34, 14));

        javax.swing.GroupLayout B1CLayout = new javax.swing.GroupLayout(B1C);
        B1C.setLayout(B1CLayout);
        B1CLayout.setHorizontalGroup(
            B1CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        B1CLayout.setVerticalGroup(
            B1CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout C6Layout = new javax.swing.GroupLayout(C6);
        C6.setLayout(C6Layout);
        C6Layout.setHorizontalGroup(
            C6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, C6Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(B1C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        C6Layout.setVerticalGroup(
            C6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, C6Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(B1C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        F6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        F6.setToolTipText("1 Stack");
        F6.setMaximumSize(new java.awt.Dimension(104, 104));
        F6.setMinimumSize(new java.awt.Dimension(104, 104));

        B1D.setBackground(new java.awt.Color(0, 0, 255));
        B1D.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B1D.setMaximumSize(new java.awt.Dimension(34, 14));
        B1D.setMinimumSize(new java.awt.Dimension(34, 14));
        B1D.setName(""); // NOI18N

        javax.swing.GroupLayout B1DLayout = new javax.swing.GroupLayout(B1D);
        B1D.setLayout(B1DLayout);
        B1DLayout.setHorizontalGroup(
            B1DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        B1DLayout.setVerticalGroup(
            B1DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout F6Layout = new javax.swing.GroupLayout(F6);
        F6.setLayout(F6Layout);
        F6Layout.setHorizontalGroup(
            F6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(F6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(B1D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        F6Layout.setVerticalGroup(
            F6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, F6Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(B1D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        C4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        C4.setMaximumSize(new java.awt.Dimension(104, 104));
        C4.setMinimumSize(new java.awt.Dimension(104, 104));

        javax.swing.GroupLayout C4Layout = new javax.swing.GroupLayout(C4);
        C4.setLayout(C4Layout);
        C4Layout.setHorizontalGroup(
            C4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        C4Layout.setVerticalGroup(
            C4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        B5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B5.setMaximumSize(new java.awt.Dimension(104, 104));
        B5.setMinimumSize(new java.awt.Dimension(104, 104));

        B2A.setBackground(new java.awt.Color(0, 0, 255));
        B2A.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B2A.setToolTipText("2 Stack");
        B2A.setMaximumSize(new java.awt.Dimension(34, 28));
        B2A.setMinimumSize(new java.awt.Dimension(34, 28));

        javax.swing.GroupLayout B2ALayout = new javax.swing.GroupLayout(B2A);
        B2A.setLayout(B2ALayout);
        B2ALayout.setHorizontalGroup(
            B2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        B2ALayout.setVerticalGroup(
            B2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout B5Layout = new javax.swing.GroupLayout(B5);
        B5.setLayout(B5Layout);
        B5Layout.setHorizontalGroup(
            B5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(B5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(B2A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        B5Layout.setVerticalGroup(
            B5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B5Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(B2A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        F1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        F1.setMaximumSize(new java.awt.Dimension(104, 104));
        F1.setMinimumSize(new java.awt.Dimension(104, 104));

        R1B.setBackground(new java.awt.Color(255, 0, 51));
        R1B.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        R1B.setToolTipText("1 Stack");
        R1B.setMaximumSize(new java.awt.Dimension(34, 14));
        R1B.setMinimumSize(new java.awt.Dimension(34, 14));

        javax.swing.GroupLayout R1BLayout = new javax.swing.GroupLayout(R1B);
        R1B.setLayout(R1BLayout);
        R1BLayout.setHorizontalGroup(
            R1BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        R1BLayout.setVerticalGroup(
            R1BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout F1Layout = new javax.swing.GroupLayout(F1);
        F1.setLayout(F1Layout);
        F1Layout.setHorizontalGroup(
            F1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, F1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(R1B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        F1Layout.setVerticalGroup(
            F1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, F1Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(R1B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        B4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        B4.setMaximumSize(new java.awt.Dimension(104, 104));
        B4.setMinimumSize(new java.awt.Dimension(104, 104));

        javax.swing.GroupLayout B4Layout = new javax.swing.GroupLayout(B4);
        B4.setLayout(B4Layout);
        B4Layout.setHorizontalGroup(
            B4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        B4Layout.setVerticalGroup(
            B4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        D3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        D3.setMaximumSize(new java.awt.Dimension(104, 104));
        D3.setMinimumSize(new java.awt.Dimension(104, 104));

        javax.swing.GroupLayout D3Layout = new javax.swing.GroupLayout(D3);
        D3.setLayout(D3Layout);
        D3Layout.setHorizontalGroup(
            D3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        D3Layout.setVerticalGroup(
            D3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        F4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        F4.setMaximumSize(new java.awt.Dimension(104, 104));
        F4.setMinimumSize(new java.awt.Dimension(104, 104));

        javax.swing.GroupLayout F4Layout = new javax.swing.GroupLayout(F4);
        F4.setLayout(F4Layout);
        F4Layout.setHorizontalGroup(
            F4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        F4Layout.setVerticalGroup(
            F4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(A6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(F6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(113, 113, 113)
                                                .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(F1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(F2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(A4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(F4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(A5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(D5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(E5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(F5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(A3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(F3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(A2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(F1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(F2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(A3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(A4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(D5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(E5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(F5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(A5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(E6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("A");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setText("B");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("C");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setText("D");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setText("E");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setText("F");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setText("1");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText("2");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setText("3");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel11.setText("4");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setText("5");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel13.setText("6");

        RedPanel.setBackground(new java.awt.Color(0, 102, 102));
        RedPanel.setForeground(new java.awt.Color(255, 255, 51));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("RED");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Source:");

        subRed.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        subRed.setText("Submit Move");
        subRed.setToolTipText("Submits your move for validation");
        subRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subRedActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Destination:");

        redDest.setText("Move");

        redSrc.setText("Move");
        redSrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redSrcActionPerformed(evt);
            }
        });

        aiMoveRed.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        aiMoveRed.setText("AI Move");
        aiMoveRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aiMoveRedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RedPanelLayout = new javax.swing.GroupLayout(RedPanel);
        RedPanel.setLayout(RedPanelLayout);
        RedPanelLayout.setHorizontalGroup(
            RedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RedPanelLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(RedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RedPanelLayout.createSequentialGroup()
                        .addGroup(RedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RedPanelLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(redSrc))
                            .addGroup(RedPanelLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(redDest, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
                        .addGap(15, 15, 15))
                    .addGroup(RedPanelLayout.createSequentialGroup()
                        .addComponent(subRed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(RedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(aiMoveRed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        RedPanelLayout.setVerticalGroup(
            RedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RedPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel7)
                .addGap(43, 43, 43)
                .addGroup(RedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(redSrc, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(RedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(redDest, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(subRed, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(aiMoveRed, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );

        BluePanel.setBackground(new java.awt.Color(0, 102, 102));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 204));
        jLabel14.setText("Blue");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Source:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Destination:");
        jLabel18.setToolTipText("");

        subBlue.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        subBlue.setText("Submit Move");
        subBlue.setToolTipText("Submits your move for validation");
        subBlue.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        subBlue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subBlueMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                subBlueMousePressed(evt);
            }
        });
        subBlue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBlueActionPerformed(evt);
            }
        });

        blueSrc.setText("Move");
        blueSrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blueSrcActionPerformed(evt);
            }
        });

        blueDest.setText("Move");

        aiMoveBlue.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        aiMoveBlue.setText("AI Move");
        aiMoveBlue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aiMoveBlueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BluePanelLayout = new javax.swing.GroupLayout(BluePanel);
        BluePanel.setLayout(BluePanelLayout);
        BluePanelLayout.setHorizontalGroup(
            BluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BluePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BluePanelLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(blueSrc))
                    .addGroup(BluePanelLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(blueDest, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
            .addGroup(BluePanelLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(BluePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(subBlue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BluePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(aiMoveBlue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        BluePanelLayout.setVerticalGroup(
            BluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BluePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(50, 50, 50)
                .addGroup(BluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(blueSrc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(blueDest, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(subBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(aiMoveBlue, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel19.setText("A");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel20.setText("B");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel21.setText("C");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel22.setText("D");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel23.setText("E");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel24.setText("F");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(BluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(3, 3, 3))
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addGap(98, 98, 98)
                        .addComponent(jLabel20)
                        .addGap(111, 111, 111)
                        .addComponent(jLabel21)
                        .addGap(100, 100, 100)
                        .addComponent(jLabel22)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel23)
                        .addGap(98, 98, 98)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel1)
                                .addGap(101, 101, 101)
                                .addComponent(jLabel2)
                                .addGap(98, 98, 98)
                                .addComponent(jLabel3)
                                .addGap(103, 103, 103)
                                .addComponent(jLabel4)
                                .addGap(100, 100, 100)
                                .addComponent(jLabel5)
                                .addGap(88, 88, 88)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(RedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel8)
                        .addGap(87, 87, 87)
                        .addComponent(jLabel9)
                        .addGap(95, 95, 95)
                        .addComponent(jLabel10)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel11)
                        .addGap(86, 86, 86)
                        .addComponent(jLabel12)
                        .addGap(88, 88, 88)
                        .addComponent(jLabel13))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(33, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(RedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:

    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
            
        initialiseBoard();
            try {
                setupGame();
            } catch (InterruptedException ex) {
                Logger.getLogger(GameInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_formWindowOpened

    private void R3AMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_R3AMouseDragged
        // TODO add your handling code here:
      //  jPanel50.setLocation(p);
      

    }//GEN-LAST:event_R3AMouseDragged

    private void R3AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_R3AMouseClicked
        // TODO add your handling code here:
        
 
    }//GEN-LAST:event_R3AMouseClicked

    private void F2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_F2MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_F2MouseDragged

    private void subBlueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBlueActionPerformed
                    
        if (newGame.checkForValidMoves(newGame.getState(),"B")==true){
        if (blueSrc.getText()==null || blueSrc.getText().equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Source Move cannot be empty");
            } else  
                if (blueDest.getText()==null || blueDest.getText().equals(""))
                {
                JOptionPane.showMessageDialog(rootPane, "Destination Move cannot be empty");
                } else if(newGame.checkFormat(blueSrc.getText())==false)
                    {
                    JOptionPane.showMessageDialog(rootPane, "Source Move Format is incorrect."+'\n'+"Format: {COL}{ROW}:{A-F}{1-6}");
                    } else if(newGame.checkFormat(blueDest.getText())==false)
                        {
                        JOptionPane.showMessageDialog(rootPane, "Destination Move Format is incorrect."+'\n'+"Format: {COL}{ROW}:{A-F}{1-6}");
                        } else if (blueDest.getText().equalsIgnoreCase(blueSrc.getText()))
                            {
                            JOptionPane.showMessageDialog(rootPane, "You cannot move to a that location: your piece is already there");
                            } else 
                                {
                                    String src=blueSrc.getText(),dest=blueDest.getText();
                                    src=src+"B";
                                    
                                    int result=-1;
                                    if ((result=newGame.validateMove(src, dest))==0)
                                    {
                    
    
                                    blueDest.setText("");
                                    blueSrc.setText("");    
                                    subBlue.setEnabled(false);
                                    if(hva==true){aiMoveRed.setEnabled(true);} else
                                    {
                                    redSrc.setEnabled(true);
                                    redDest.setEnabled(true);    
                                    subRed.setEnabled(true);
                                    }
                                    newGame.updateBoard(src, dest);
                                    
                                    String move= newGame.updateState(src, dest);
                                        
                                         int srcR,srcC,destC,destR;
                                         srcR=Integer.parseInt(move.substring(0,1));
                                         srcC=Integer.parseInt(move.substring(1,2));
                                         destR=Integer.parseInt(move.substring(2,3));
                                         destC=Integer.parseInt(move.substring(3));
                                         JPanel s=gameBoard[srcR][srcC];
                                         JPanel d=gameBoard[destR][destC];
                                         
                                         JComponent tmp=(JComponent) s.getComponent(0);
                                           s.setPreferredSize(s.getPreferredSize());
                                           d.setPreferredSize(d.getPreferredSize());
                                           s.getComponent(0).setVisible(false);
                                        if (d.getComponentCount()>0){
                                            d.getComponent(0).setVisible(false);
                                            d.removeAll();}
                                         
                                         d.add(tmp);
                                         d.getComponent(0).setVisible(true);
                                        // d.getComponent(0).setVisible(true);
                                        ///////////////////////////////////////////////////////////////////////////////////////
                                        //Check if game is over
                                        
                                        boolean gameWon=newGame.gameWon(newGame.getState());
                                        if (gameWon==true)
                                        {
                                            JOptionPane.showMessageDialog(rootPane, "Congratulations! You have won the game!");
                                            subBlue.setEnabled(false);
                                            subRed.setEnabled(false);
                                            int choice=JOptionPane.showConfirmDialog(rootPane,"Would you like to play a new game?", "New Game", JOptionPane.OK_CANCEL_OPTION,0);
                                            if (choice==0){startNewGame();}else System.exit(0);
                                        }
                                        
                                    } else 
                                        {
                                            switch (result)
                                            {
                                                case 1:JOptionPane.showMessageDialog(rootPane, "Illegal Move.");
                                                    break;
                                            }
                                        }
                                }
        } else {
            subBlue.setEnabled(false);
            subRed.setEnabled(false);
            JOptionPane.showMessageDialog(rootPane, "Sorry to say that you do not have any legal moves left. \n You lose the game!");}
            int choice=JOptionPane.showConfirmDialog(rootPane,"Would you like to play a new game?", "New Game", JOptionPane.OK_CANCEL_OPTION,0);
                                            if (choice==0){startNewGame();}else System.exit(0);
    }//GEN-LAST:event_subBlueActionPerformed

    private void redSrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redSrcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_redSrcActionPerformed

    private void blueSrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blueSrcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_blueSrcActionPerformed

    private void subBlueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subBlueMouseClicked

    }//GEN-LAST:event_subBlueMouseClicked

    private void subBlueMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subBlueMousePressed
                    // TODO add your handling code here:
            
        // TODO add your handling code here:
    }//GEN-LAST:event_subBlueMousePressed

    private void subRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subRedActionPerformed
                if (newGame.checkForValidMoves(newGame.getState(),"R")==true){
        if (redSrc.getText()==null || redSrc.getText().equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Source Move cannot be empty");
            } else  
                if (redDest.getText()==null || redDest.getText().equals(""))
                {
                JOptionPane.showMessageDialog(rootPane, "Destination Move cannot be empty");
                } else if(newGame.checkFormat(redSrc.getText())==false)
                    {
                    JOptionPane.showMessageDialog(rootPane, "Source Move Format is incorrect."+'\n'+"Format: {COL}{ROW}:{A-F}{1-6}");
                    } else if(newGame.checkFormat(redDest.getText())==false)
                        {
                        JOptionPane.showMessageDialog(rootPane, "Destination Move Format is incorrect."+'\n'+"Format: {COL}{ROW}:{A-F}{1-6}");
                        } else if (redDest.getText().equalsIgnoreCase(redSrc.getText()))
                            {
                            JOptionPane.showMessageDialog(rootPane, "You cannot move to a that location: your piece is already there");
                            } else 
                                {
                                    String src=redSrc.getText(),dest=redDest.getText();
                                    src=src+"R";
                                    
                                    int result=-1;
                                    if ((result=newGame.validateMove(src, dest))==0)
                                    {
                                   
                                    redDest.setText("");
                                    redSrc.setText("");   
                                    
                                    subRed.setEnabled(false);
                                    if(hva==true){aiMoveBlue.setEnabled(true);} else {
                                    blueSrc.setEnabled(true);
                                    blueDest.setEnabled(true);
                                    subBlue.setEnabled(true);
                                    }
                                    newGame.updateBoard(src, dest);
                                    
                                    String move= newGame.updateState(src, dest);
                                    
                                         int srcR,srcC,destC,destR;
                                         srcR=Integer.parseInt(move.substring(0,1));
                                         srcC=Integer.parseInt(move.substring(1,2));
                                         destR=Integer.parseInt(move.substring(2,3));
                                         destC=Integer.parseInt(move.substring(3));
                                         JPanel s=gameBoard[srcR][srcC];
                                         JPanel d=gameBoard[destR][destC];
                                         
                                         JComponent tmp=(JComponent) s.getComponent(0);
                                           s.setPreferredSize(s.getPreferredSize());
                                           s.getComponent(0).setVisible(false);
                                        if (d.getComponentCount()>0){
                                            d.getComponent(0).setVisible(false);
                                            d.removeAll();}
                                         
                                         d.add(tmp);
                                         d.getComponent(0).setVisible(true);
                                        // d.getComponent(0).setVisible(true);
                                        boolean gameWon=newGame.gameWon(newGame.getState());
                                        if (gameWon==true)
                                        {
                                            JOptionPane.showMessageDialog(rootPane, "Congratulations! You have won the game!");
                                            subBlue.setEnabled(false);
                                            subRed.setEnabled(false);
                                            int choice=JOptionPane.showConfirmDialog(rootPane,"Would you like to play a new game?", "New Game", JOptionPane.OK_CANCEL_OPTION,0);
                                            if (choice==0){startNewGame();}else System.exit(0);
                                        }
                                    } else 
                                        {
                                            switch (result)
                                            {
                                                case 1:JOptionPane.showMessageDialog(rootPane, "Illegal Move.");
                                                    break;
                                            }
                                        }
                                }
                }else {
                    subBlue.setEnabled(false);
                    subRed.setEnabled(false);
                    JOptionPane.showMessageDialog(rootPane, "Sorry to say that you do not have any legal moves left. \n You lose the game!");
                    
                    int choice=JOptionPane.showConfirmDialog(rootPane, "Would you like to play a new game?");
                                            if (choice==0){startNewGame();} else System.exit(0);
                } 
    }//GEN-LAST:event_subRedActionPerformed

    private void aiMoveBlueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aiMoveBlueActionPerformed
        // TODO add your handling code here:
        aBMove();
    }//GEN-LAST:event_aiMoveBlueActionPerformed

    private void aiMoveRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aiMoveRedActionPerformed
        // TODO add your handling code here:
    aRMove();
        
    }//GEN-LAST:event_aiMoveRedActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameInterface().setVisible(true);
                
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel A1;
    private javax.swing.JPanel A2;
    private javax.swing.JPanel A3;
    private javax.swing.JPanel A4;
    private javax.swing.JPanel A5;
    private javax.swing.JPanel A6;
    private javax.swing.JPanel B1;
    private javax.swing.JPanel B1A;
    private javax.swing.JPanel B1B;
    private javax.swing.JPanel B1C;
    private javax.swing.JPanel B1D;
    private javax.swing.JPanel B2;
    private javax.swing.JPanel B2A;
    private javax.swing.JPanel B2B;
    private javax.swing.JPanel B2C;
    private javax.swing.JPanel B2D;
    private javax.swing.JPanel B3;
    private javax.swing.JPanel B3A;
    private javax.swing.JPanel B3B;
    private javax.swing.JPanel B3C;
    private javax.swing.JPanel B3D;
    private javax.swing.JPanel B4;
    private javax.swing.JPanel B5;
    private javax.swing.JPanel B6;
    private javax.swing.JPanel BluePanel;
    private javax.swing.JPanel C1;
    private javax.swing.JPanel C2;
    private javax.swing.JPanel C3;
    private javax.swing.JPanel C4;
    private javax.swing.JPanel C5;
    private javax.swing.JPanel C6;
    private javax.swing.JPanel D1;
    private javax.swing.JPanel D2;
    private javax.swing.JPanel D3;
    private javax.swing.JPanel D4;
    private javax.swing.JPanel D5;
    private javax.swing.JPanel D6;
    private javax.swing.JPanel E1;
    private javax.swing.JPanel E2;
    private javax.swing.JPanel E3;
    private javax.swing.JPanel E4;
    private javax.swing.JPanel E5;
    private javax.swing.JPanel E6;
    private javax.swing.JPanel F1;
    private javax.swing.JPanel F2;
    private javax.swing.JPanel F3;
    private javax.swing.JPanel F4;
    private javax.swing.JPanel F5;
    private javax.swing.JPanel F6;
    private javax.swing.JPanel R1A;
    private javax.swing.JPanel R1B;
    private javax.swing.JPanel R1C;
    private javax.swing.JPanel R1D;
    private javax.swing.JPanel R2A;
    private javax.swing.JPanel R2B;
    private javax.swing.JPanel R2C;
    private javax.swing.JPanel R2D;
    private javax.swing.JPanel R3A;
    private javax.swing.JPanel R3B;
    private javax.swing.JPanel R3C;
    private javax.swing.JPanel R3D;
    private javax.swing.JPanel RedPanel;
    private javax.swing.JButton aiMoveBlue;
    private javax.swing.JButton aiMoveRed;
    private javax.swing.JTextField blueDest;
    private javax.swing.JTextField blueSrc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField redDest;
    private javax.swing.JTextField redSrc;
    private javax.swing.JButton subBlue;
    private javax.swing.JButton subRed;
    // End of variables declaration//GEN-END:variables

    private void autoplay() throws InterruptedException
    {
        JOptionPane.showMessageDialog(rootPane, "Well, why the AIs fight it out, how is life treating you?");
        
        while(newGame.gameWon(newGame.getState())==false)
        {
            aBMove();
            aRMove();
                
        }
    }
}
