package it.avbo.game;

import javax.swing.*;

public class Window extends JFrame {

    public Window() {
        setTitle("The Hunter's Bounty");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1920, 1080));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
