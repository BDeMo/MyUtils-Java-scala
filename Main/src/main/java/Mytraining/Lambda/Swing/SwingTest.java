package Mytraining.Lambda.Swing;

import com.sun.jdi.PathSearchingVirtualMachine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLOutput;

public class SwingTest {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("My Jframe.");
        JButton jButton1 = new JButton("My Jbutton1");
        JButton jButton2 = new JButton("My Jbutton2");

        jButton1.addActionListener(e -> System.out.println("pressing button1"));
        jButton2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressing button2");
            }
        });

        jFrame.setSize(200,100);
        jButton1.setSize(100,100);
        jFrame.add(jButton1);
        jFrame.add(jButton2);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
    }

}
