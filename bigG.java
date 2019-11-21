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
    private JTextArea tf1; 
    private JTextField tf2;
    private JButton respect;
    public static void main(String[] aargs){
        new bigG();
    }

    public bigG(){
        Toolkit tk = Toolkit.getDefaultToolkit();      
        Dimension dim = tk.getScreenSize(); 
        this.setSize(1200, 1000);
        this.setLocationRelativeTo(null);        
        this.setTitle("Whacha Thinking");
        font = new Font("Times New Roman", Font.PLAIN, 20);
        bigFont = new Font("Times New Roman", Font.BOLD, 30); 
        // JPanel p = new JPanel(new GridLayout(0,2)); 
        JPanel p = new JPanel();
        JPanel poo = new JPanel();
        p.setBackground(Color.lightGray);

        tf1 = new JTextArea(20,20);
        // tf1.setSize(200, 200);
        // tf1.resize(200, 200);
        tf1.setToolTipText("put in the weight");
        tf1.setFont(font); 
        p.add(tf1);

        tf2 = new JTextField("",20);
        tf2.setToolTipText("put in the weight");
        tf2.setFont(font); 
        poo.add(tf2);

        this.respect = new JButton("press F to respect");
        respect.setToolTipText("Deletes the pickup of the specified ID. Cannot be undone, but can be re-added");
        // respect.addActionListener(lfb);
        respect.setFont(font);
        p.add(respect);

        this.add(p); 

        this.add(poo);
        // this.pack();
        this.setVisible(true);
    }


    
}