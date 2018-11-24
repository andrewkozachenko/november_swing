package com.kozachenko.lemon.comics.gui.frame;

import com.kozachenko.lemon.comics.gui.panel.MainPanel;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    private MainPanel mainPanel;

    public MyFrame() throws HeadlessException {
        init();
    }

    private void init() {
        this.setTitle("My comics");
        Dimension frameDimension = new Dimension(600, 600);
        mainPanel = new MainPanel();
        getContentPane().add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(frameDimension);
        //pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }



}
