/*
big GUI for chatshack
*/
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.*;
import java.awt.event.*; //user clicking buttons
import java.util.Calendar;
import java.util.Date;

public class bigG extends JFrame{

    private Font font;
    private Font bigFont;
    private JTextArea comment, content;
    private JButton respect, connectivity;
    private ListenForButton lfb; 
    private JPanel p;
    private JPanel poo;
    private boolean concheck = true;
    public static void main(String[] aargs){
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

    private class ListenForButton implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == respect) {
                System.out.println(connectivity.getText());
            }
            else if (e.getSource() == connectivity) {
                if (concheck){
                    connectivity.setText("Leave");
                    System.out.println("yes");
                    concheck = false;
                }
                else {                    
                    System.out.println("no");
                    concheck = true;
                    connectivity.setText("Join");
                }
            }
        }
    }


    
}