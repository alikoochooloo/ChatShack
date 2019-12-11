/*
big GUI for chatshack
*/
import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.*;
import java.awt.event.*; //user clicking buttons
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.*;
import java.net.*;
import java.time.*;
import java.util.TimeZone;
import java.text.SimpleDateFormat;


public class bigG extends JFrame{

    private Font font;
    private JTextArea comment, content;
    private JButton respect, connectivity;
    private ListenForButton lfb; 
    private JPanel p, poo, pee;
    private JScrollPane scroll1, scroll2;
    public DefaultComboBoxModel usernames; 
    private JComboBox guys;
    public static Socket annoy;
    public static DataOutputStream toServer;
    public static String ourusername;

    public static void main(String[] args){
        // new bigG();
        
        try {
            annoy = new Socket(args[0], 1337);
            toServer = new DataOutputStream(annoy.getOutputStream());
			bigG win = new bigG();
            win.updatecontent("you","My name is " + args[1]);
            ourusername = args[1];

			Thread ShackClient = new Thread(new ShackClient(annoy, win));

			ShackClient.run();
		}
		// catch (UnknownHostException uhe) { System.out.println(uhe); }
        catch (IOException ioe) { System.out.println(ioe); }
        
    }

    public bigG(){
        this.setSize(1000, 825);
        this.setLocationRelativeTo(null);        
        this.setTitle("Whacha Thinking");
        font = new Font("Times New Roman", Font.PLAIN, 20);
        // bigFont = new Font("Times New Roman", Font.BOLD, 30);
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

        usernames = new DefaultComboBoxModel(new String[] {"all"});
        guys = new JComboBox(usernames);
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
        this.setVisible(true);
    }

    public void updatecontent(String username, String content){
        this.content.append(username + ": "+ content + "\n");
    }

    public void updateusers(String stat, String username){
        if (stat.equals("LEAVE")){
            if(usernames.getIndexOf(username) != -1 ) {
                usernames.removeElement(username);
            }
        }
        else{
            if(usernames.getIndexOf(username) == -1 ) {
                usernames.addElement(username);
            }
        }

    }

    public class ListenForButton implements ActionListener{
        String date = getCurrentUtcTime();
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == respect) {
                if(!comment.getText().equals("")){
                    String currcomment = comment.getText();
                    content.append("you: " + currcomment + "\n");
                    comment.setText("");
                    String to = guys.getSelectedItem().toString();
                    comment.requestFocus();
                    String s;
                    if (to.equals("all")){
                        s = "BDMG";
                    }
                    else{
                        s = "PVMG";
                    }
                    // functionality to send text to server
                    String result = s+"|"+ourusername+"|"+to+"|"+date+"\r\n"+currcomment+"\r\n";
                    try{
                        toServer.write(result.getBytes());
                    }catch(Exception ei){ System.out.println(ei);}
                }
            }
            else if (e.getSource() == connectivity) {
                if (connectivity.getText().equals("Join")){
                    connectivity.setText("Leave");

                    // functionality to join
                    // username = comment.getText()
                    String result = "JOIN|"+ourusername+"|all|"+date+"\r\n";
                    try{
                        toServer.write(result.getBytes());
                    }catch(Exception ei){ System.out.println(ei);}
                }
                else {                    
                    connectivity.setText("Join");
                    // functionality to leave
                    // ourusername = comment.getText();
                    //String to = guys.getSelectedItem().toString();
                    String result = "LEAV|"+ourusername+"|all|"+date+"\r\n";
                    try{
                        toServer.write(result.getBytes());
                    }catch(Exception ei){ System.out.println(ei);}
                }
            }
        }
    }
    private static String getCurrentUtcTime(){
        try{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        // SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
        }
        catch(Exception i){
            System.out.println(i);
            System.out.println("fuckyou");
            return null;
        }
    }
}