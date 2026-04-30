package frames;

import javax.swing.*;

public class Main {
    private JPanel MainFrame;
    private JButton button1;
    private JTextField ghjghjghjTextField;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().MainFrame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        System.out.println("Hola");
    }
}
