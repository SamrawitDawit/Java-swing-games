import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class TicTacToe implements ActionListener{
    private Random random = new Random();
    private JFrame frame = new JFrame();
    private JPanel titlePanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JPanel footerPanel = new JPanel();
    private JTextField textField = new JTextField();
    private JButton replayButton = new JButton();
    private JButton closeButton = new JButton();
    private JButton[] buttons = new JButton[9];
    private JButton restartButton = new JButton();
    private int scoreX = 0;
    private int scoreO = 0;
    private JLabel score = new JLabel();
    boolean player1Turn;
    int count = 0;
    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setSize(800,800);
        frame.setVisible(true);
        score = new JLabel("Player X: " + scoreX + "\nPlayer O: " + scoreO);
        score.setBackground(Color.gray);
        textField.setText("Tic-Tac-Toe");
        textField.setBackground(Color.DARK_GRAY);
        textField.setForeground(Color.orange);
        textField.setFont(new Font("Georgia", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setOpaque(true);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,800, 100);
        titlePanel.add(textField);
        titlePanel.add(score, BorderLayout.EAST);
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setBounds(0,-750,800,50);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(footerPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new GridLayout(3,3));
        replayButton = new JButton();
        replayButton.setFont(new Font("Georgia", Font.BOLD, 40));
        replayButton.addActionListener(this);
        replayButton.setText("Replay");
        replayButton.setBackground(Color.cyan);
        footerPanel.add(replayButton, BorderLayout.CENTER);
        restartButton = new JButton();
        restartButton.setFont(new Font("Georgia", Font.BOLD, 40));
        restartButton.addActionListener(this);
        restartButton.setText("Restart");
        restartButton.setBackground(Color.cyan);
        footerPanel.add(restartButton, BorderLayout.WEST);
        closeButton = new JButton();
        closeButton.setFont(new Font("Comic Sans", Font.BOLD, 40));
        closeButton.addActionListener(this);
        closeButton.setText("Close");
        closeButton.setBackground(Color.cyan);
        footerPanel.add(closeButton, BorderLayout.EAST);
        for(int i=0; i<9; i++){
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFocusable(false);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
            buttons[i].setBackground(Color.lightGray);
            buttons[i].addActionListener(this);
        }
        frame.add(buttonPanel);

        firstTurn();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == replayButton){
            replay();
        }
        if (e.getSource() == restartButton){
            scoreX = 0;
            scoreO = 0;
            score.setText("Player X: " + scoreX + "  Player O: " + scoreO);
            replay();
        }
        if (e.getSource() == closeButton){
            frame.dispose();
        }
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1Turn) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(Color.RED);
                        buttons[i].setText("X");
                        player1Turn = false;
                        textField.setText("O turn");
                        count ++;
                        if (count > 4){
                            check();
                        }
                    }
                } else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(Color.BLUE);
                        buttons[i].setText("O");
                        player1Turn = true;
                        textField.setText("X turn");
                        count ++;
                        if (count > 4){
                            check();
                        }
                    }
                }
            }
        }
    }
    private void replay(){
        count = 0;
        for (int i = 0; i<9; i++){
            buttons[i].setEnabled(true);
            buttons[i].setText("");
            buttons[i].setBackground(Color.lightGray);
        }
        if (random.nextInt(2) == 0) {
            player1Turn = true;
            textField.setText("X turn");
        }else{
            player1Turn = false;
            textField.setText("O turn");
        }
    }
    private void firstTurn() {
        try {
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        if (random.nextInt(2) == 0) {
            player1Turn = true;
            textField.setText("X turn");
        }else{
            player1Turn = false;
            textField.setText("O turn");
        }
    }
    public void check(){
        if(
                (buttons[0].getText()=="X") &&
                        (buttons[1].getText()=="X") &&
                        (buttons[2].getText()=="X")
        ) {
            xWins(0,1,2);
        }
        if(
                (buttons[3].getText()=="X") &&
                        (buttons[4].getText()=="X") &&
                        (buttons[5].getText()=="X")
        ) {
            xWins(3,4,5);
        }
        if(
                (buttons[6].getText()=="X") &&
                        (buttons[7].getText()=="X") &&
                        (buttons[8].getText()=="X")
        ) {
            xWins(6,7,8);
        }
        if(
                (buttons[0].getText()=="X") &&
                        (buttons[3].getText()=="X") &&
                        (buttons[6].getText()=="X")
        ) {
            xWins(0,3,6);
        }
        if(
                (buttons[1].getText()=="X") &&
                        (buttons[4].getText()=="X") &&
                        (buttons[7].getText()=="X")
        ) {
            xWins(1,4,7);
        }
        if(
                (buttons[2].getText()=="X") &&
                        (buttons[5].getText()=="X") &&
                        (buttons[8].getText()=="X")
        ) {
            xWins(2,5,8);
        }
        if(
                (buttons[0].getText()=="X") &&
                        (buttons[4].getText()=="X") &&
                        (buttons[8].getText()=="X")
        ) {
            xWins(0,4,8);
        }
        if(
                (buttons[2].getText()=="X") &&
                        (buttons[4].getText()=="X") &&
                        (buttons[6].getText()=="X")
        ) {
            xWins(2,4,6);
        }
        //check O win conditions
        if(
                (buttons[0].getText()=="O") &&
                        (buttons[1].getText()=="O") &&
                        (buttons[2].getText()=="O")
        ) {
            oWins(0,1,2);
        }
        if(
                (buttons[3].getText()=="O") &&
                        (buttons[4].getText()=="O") &&
                        (buttons[5].getText()=="O")
        ) {
            oWins(3,4,5);
        }
        if(
                (buttons[6].getText()=="O") &&
                        (buttons[7].getText()=="O") &&
                        (buttons[8].getText()=="O")
        ) {
            oWins(6,7,8);
        }
        if(
                (buttons[0].getText()=="O") &&
                        (buttons[3].getText()=="O") &&
                        (buttons[6].getText()=="O")
        ) {
            oWins(0,3,6);
        }
        if(
                (buttons[1].getText()=="O") &&
                        (buttons[4].getText()=="O") &&
                        (buttons[7].getText()=="O")
        ) {
            oWins(1,4,7);
        }
        if(
                (buttons[2].getText()=="O") &&
                        (buttons[5].getText()=="O") &&
                        (buttons[8].getText()=="O")
        ) {
            oWins(2,5,8);
        }
        if(
                (buttons[0].getText()=="O") &&
                        (buttons[4].getText()=="O") &&
                        (buttons[8].getText()=="O")
        ) {
            oWins(0,4,8);
        }
        if(
                (buttons[2].getText()=="O") &&
                        (buttons[4].getText()=="O") &&
                        (buttons[6].getText()=="O")
        ) {
            oWins(2,4,6);
        } else if (count == 9) {
            textField.setText("Tie!");
            for(int i=0;i<9;i++) {
                buttons[i].setEnabled(false);
            }
        }
    }
    public void xWins(int a,int b,int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        scoreX ++;
        score.setText("Player X: " + scoreX + "  Player O: " + scoreO);
        for(int i=0;i<9;i++) {
            if (i != a && i != b && i != c){
                buttons[i].setEnabled(false);
            }
        }
        textField.setText("X wins");
    }
    public void oWins(int a,int b,int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        scoreO ++;
        score.setText("Player X: " + scoreX + "  Player O: " + scoreO);
        for(int i=0;i<9;i++) {
            if (i != a && i != b && i != c) {
                buttons[i].setEnabled(false);
            }
        }
        textField.setText("O wins");
    }
}
