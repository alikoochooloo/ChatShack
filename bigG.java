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
import java.io.*;
import java.net.*;


public class bigG extends JFrame{

    private Font font;
    private Font bigFont;
    private JTextArea comment, content;
    private JButton respect, connectivity;
    private ListenForButton lfb; 
    private JPanel p, poo, pee;
    // private JPanel ;
    private boolean concheck = true;
    private JScrollPane scroll1, scroll2;
    private JComboBox guys;
    // public Socket client;
    public Socket annoy;
    public DataOutputStream toServer;

    // public OutputStream toClient = null;
    public static void main(String[] args){
        // this.client = client
        new bigG();
        /*
        try {
            annoy = new Socket(args[0], 1337);
            toServer = new DataOutputStream(annoy.getOutputStream());
			bigG win = new bigG();
			win.displayMessage("My name is " + args[1]);

			Thread ShackClient = new Thread(new ShackClient(annoy, win));

			ShackClient.run();
		}
		catch (UnknownHostException uhe) { System.out.println(uhe); }
        catch (IOException ioe) { System.out.println(ioe); }
        */
    }

    // public void runGui(Socket client){
    //     this.client = client;
    //     // toClient = new BufferedOutputStream(client.getOutputStream());
    //     new bigG();

    // } 


    public bigG(){
        // Toolkit tk = Toolkit.getDefaultToolkit();      
        // Dimension dim = tk.getScreenSize(); 
        this.setSize(1000, 825);
        this.setLocationRelativeTo(null);        
        this.setTitle("Whacha Thinking");
        font = new Font("Times New Roman", Font.PLAIN, 20);
        bigFont = new Font("Times New Roman", Font.BOLD, 30); 
        // JPanel p = new JPanel(new GridLayout(0,2)); 
        p = new JPanel();
        poo = new JPanel();
        pee = new JPanel();
        p.setBackground(Color.lightGray);
        poo.setBackground(Color.BLUE);
        pee.setBackground(Color.ORANGE);


        comment = new JTextArea(4,60);
        comment.setToolTipText("type your comment");
        comment.setFont(font); 
        comment.requestFocus();
        p.add(comment);

        scroll2 = new JScrollPane(comment);
        scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        p.add(scroll2);

        content = new JTextArea(25,60);
        content.setEditable(false);
        content.setToolTipText("content");
        content.setFont(font); 
        poo.add(content);

        scroll1 = new JScrollPane(content);
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        poo.add(scroll1);
        
        lfb = new ListenForButton();


        guys = new JComboBox();
        guys.addItem("all");
        pee.add(guys);

        this.respect = new JButton("press F for respect");
        respect.setToolTipText("yeet you dickwad");
        respect.addActionListener(lfb);
        respect.setFont(font);
        pee.add(respect);

        this.connectivity = new JButton();
        connectivity.setText("Join");
        connectivity.addActionListener(lfb);
        connectivity.setToolTipText("join you dickwad");
        connectivity.setFont(font);
        pee.add(connectivity);

        this.add(p,BorderLayout.CENTER); 
        this.add(pee,BorderLayout.SOUTH);
        this.add(poo,BorderLayout.NORTH);
        // this.pack();
        this.setVisible(true);
    }

    public class ListenForButton implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == respect) {
                // System.out.println(comment.getText());
                if(!comment.getText().equals("")){
                    content.append("you: " + comment.getText() + "\n");
                    comment.setText("");
                    comment.requestFocus();
                    // functionality to send text to server
                    // result = "BDMG|username|all|date\r\ncontent\r\n"
                    // toServer.write(result.getBytes());
                    
                }
            }
            else if (e.getSource() == connectivity) {
                if (concheck){
                    connectivity.setText("Leave");
                    // System.out.println("yes");
                    concheck = false;
                    // functionality to join
                    // username = comment.getText()
                    // date = 
                    // result = "JOIN|username|all|date"
                    // toServer.write(result.getBytes());
                }
                else {                    
                    // System.out.println("no");
                    concheck = true;
                    connectivity.setText("Join");
                    // functionality to leave
                    // result = "LEAV|username|all|date"
                    // toServer.write(result.getBytes());
                }
            }
        }
    }


    
}