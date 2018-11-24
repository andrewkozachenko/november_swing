package com.kozachenko.lemon.comics.gui.menu;

import com.kozachenko.lemon.comics.gui.panel.ImagePanel;
import com.kozachenko.lemon.comics.util.FileStorage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class MyMenuBar extends JMenuBar {

    private JMenu fileMenu;
    private JMenuItem openFileMenuItem;
    private JMenuItem saveFileMenuItem;
    private JMenuItem exitMenuItem;

    private JMenu helpMenu;
    private JMenuItem helpMenuItem;

    private ImagePanel imagePanel;

    private String filePath;

    public MyMenuBar() {
        addElements();
    }

    public MyMenuBar(ImagePanel imagePanel) {
        this();
        this.imagePanel = imagePanel;
    }

    private void addElements(){
        init();
        add(fileMenu);
        add(helpMenu);
    }

    private void init(){
        initFileMenu();
        initHelpMenu();
    }

    private void initFileMenu(){
        fileMenu= new JMenu("File");
        addFileMenuItems();
    }

    private void addFileMenuItems() {
        initFileMenuItems();
        fileMenu.add(openFileMenuItem);
        fileMenu.add(saveFileMenuItem);
        fileMenu.add(exitMenuItem);
    }

    private void initFileMenuItems() {
        openFileMenuItem = new JMenuItem("Open file");
        openFileMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showDialog(null, "Open FIle");
                if(ret == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    filePath = file.getAbsolutePath();
                    BufferedImage image = FileStorage.getImage(file);
                    imagePanel.setImage(image);
                    imagePanel.repaint();
                    System.out.println(file.getAbsolutePath());
                }
            }
        });
        saveFileMenuItem = new JMenuItem("Save file");
        saveFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileStorage.writeImageToFile(filePath);
            }
        });
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener((event) -> System.exit(0));
    }



    private void initHelpMenu() {
        helpMenu = new JMenu("Help");
        addHelpMenuItems();

    }

    private void addHelpMenuItems() {
        initHelpMenuItems();
        helpMenu.add(helpMenuItem);
    }

    private void initHelpMenuItems() {
        helpMenuItem = new JMenuItem("About");
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    public void setImagePanel(ImagePanel imagePanel) {
        this.imagePanel = imagePanel;
    }
}
