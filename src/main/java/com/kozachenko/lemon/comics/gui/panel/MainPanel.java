package com.kozachenko.lemon.comics.gui.panel;


import com.kozachenko.lemon.comics.exception.ImageNotFoundException;
import com.kozachenko.lemon.comics.gui.menu.MyMenuBar;
import com.kozachenko.lemon.comics.util.FileStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class MainPanel extends JPanel  {

    private JButton previous;
    private JButton next ;
    private ImagePanel imagePanel;
    private MyMenuBar myMenu;
    private List<String> imageList;
    private static final String defaultImage = "src/main/resources/default.jpg";
    private int index = 0;

    public MainPanel() {
        setLayout(new BorderLayout());
        init();
    }


    private void init(){
        initButtons();

        try {
            imageList = FileStorage.getImages();
        } catch (ImageNotFoundException e) {
            imageList = new LinkedList<>();
            imageList.add(defaultImage);
        }
        add(BorderLayout.NORTH, myMenu);
        add(BorderLayout.EAST, next);
        add(BorderLayout.WEST, previous);
        imagePanel = new ImagePanel();
        myMenu.setImagePanel(imagePanel);
        imagePanel.setBackground(Color.BLACK);
        add(BorderLayout.CENTER, imagePanel);
    }
    private void initButtons() {
        myMenu = new MyMenuBar();
        initPreviousButton();
        initNextButton();
    }

    private void initPreviousButton() {
        previous = new JButton("Previous");
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "";
                if(index == 0){
                    fileName = imageList.get(index);
                } else {
                    if(!(index<0)){
                        fileName = imageList.get(--index);
                    }
                }
                BufferedImage image = FileStorage.getImage(fileName);
                imagePanel.setImage(image);
                imagePanel.repaint();
            }
        });
    }

    private void initNextButton() {
        next = new JButton("next");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "";
                if(index == imageList.size()){
                    fileName = imageList.get(imageList.size()-1);
                } else {

                    fileName = imageList.get(index++);
                }
                BufferedImage image = FileStorage.getImage(fileName);
                imagePanel.setImage(image);
                imagePanel.repaint();
            }
        });
    }




}
