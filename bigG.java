/*
big GUI for chatshack
*/
import javax.swing.*;

import org.omg.CORBA.portable.OutputStream;

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


public class bigG extends JFrame{

    private Font font;
    private JTextArea comment, content;
    private JButton respect, connectivity;
    private ListenForButton lfb; 
    private JPanel p, poo, pee;
    private JScrollPane scroll1, scroll2;
    public DefaultComboBoxModel usernames; 
    private JComboBox guys;
    public Socket annoy;
    public DataOutputStream toServer;
    public static void main(String[] args){
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
		// catch (UnknownHostException uhe) { System.out.println(uhe); }
        catch (IOException ioe) { System.out.println(ioe); }
        */
    }

    public bigG(){
        this.setSize(1000, 825);
        this.setLocationRelativeTo(null);        
        this.setTitle("Whacha Thinking");
        font = new Font("Times New Roman", Font.PLAIN, 20);
        bigFont = new Font("Times New Roman", Font.BOLD, 30);
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
        date
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == respect) {
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
                if (connectivity.getText().equals("Join")){
                    connectivity.setText("Leave");
                    // functionality to join
                    // username = comment.getText()
                    // date = 
                    // result = "JOIN|username|all|date"
                    // toServer.write(result.getBytes());
                }
                else {                    
                    connectivity.setText("Join");
                    // functionality to leave
                    username = comment.getText();
                    to = guys.getSelectedItem();
                    // result = "LEAV|+username+|+to+|date"
                    // toServer.write(result.getBytes());
                }
            }
        }
    }
    private static Date getCurrentUtcTime() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(new Date());
    }

    
}