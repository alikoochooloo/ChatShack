/*
big GUI for chatshack
*/
import javax.swing.*;

import org.omg.CORBA.portable.OutputStream;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.*;
import java.awt.event.*; //user clicking buttons
import java.util.Calendar;
import java.util.Date;
import java.net.*;


public class bigG extends JFrame{

    private Font font;
    private Font bigFont;
    private JTextArea comment, content;
    private JButton respect, connectivity;
    private ListenForButton lfb; 
    private JPanel p;
    private JPanel poo;
    private boolean concheck = true;
    public Socket client;
    // public OutputStream toClient = null;
    public static void main(String[] args){
        // this.client = client
        new bigG();
    }

    public void runGui(Socket client){
        this.client = client;
        // toClient = new BufferedOutputStream(client.getOutputStream());
        new bigG();

    } 


    public bigG(){
        // Toolkit tk = Toolkit.getDefaultToolkit();      
        // Dimension dim = tk.getScreenSize(); 
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);        
        this.setTitle("Whacha Thinking");
        font = new Font("Times New Roman", Font.PLAIN, 20);
        bigFont = new Font("Times New Roman", Font.BOLD, 30); 
        // JPanel p = new JPanel(new GridLayout(0,2)); 
        p = new JPanel();
        poo = new JPanel();
        p.setBackground(Color.lightGray);

        comment = new JTextArea(4,50);
        // comment.setSize(200, 200);
        // comment.resize(200, 200);
        comment.setToolTipText("type your comment");
        comment.setFont(font); 
        p.add(comment);

        content = new JTextArea(25,60);
        content.setEditable(false);
        content.setToolTipText("content");
        content.setFont(font); 
        poo.add(content);

        lfb = new ListenForButton();

        this.respect = new JButton("press F to respect");
        respect.setToolTipText("yeet you dickwad");
        respect.addActionListener(lfb);
        respect.setFont(font);
        p.add(respect);

        this.connectivity = new JButton();
        connectivity.setText("Join");
        connectivity.addActionListener(lfb);
        connectivity.setToolTipText("join you dickwad");
        connectivity.setFont(font);
        p.add(connectivity);

        this.add(p,BorderLayout.SOUTH); 

        this.add(poo,BorderLayout.NORTH);
        // this.pack();
        this.setVisible(true);
    }

    public class ListenForButton implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == respect) {
                System.out.println(comment.getText());
                if(!comment.getText().equals("")){
                    content.append("you: " + comment.getText() + "\n");
                    comment.setText("");
                    // functionality to send text to client
                    // client.
                    
                }
            }
            else if (e.getSource() == connectivity) {
                if (concheck){
                    connectivity.setText("Leave");
                    System.out.println("yes");
                    concheck = false;
                    // functionality to join
                }
                else {                    
                    System.out.println("no");
                    concheck = true;
                    connectivity.setText("Join");
                    // functionality to leave
                }
            }
        }
    }


    
}