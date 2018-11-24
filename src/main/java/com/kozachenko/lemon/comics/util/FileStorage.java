package com.kozachenko.lemon.comics.util;

import com.kozachenko.lemon.comics.exception.ImageNotFoundException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileStorage {

    private static File file;
    private static String fileName = "src/main/resources/image_list.txt";

    public static   List<String> getImages() throws ImageNotFoundException {
        List<String> images = new LinkedList<>();
        initFile();

        if (file.exists()){
            readFile(images);
        } else {
            createFile();
            throw new ImageNotFoundException();
        }
        return images;
    }

    public static void writeImageToFile(String imagePath){
        if(file != null){
            if (file.exists()){
                writeToFile(imagePath);
            } else {
                createFile();
            }
        } else {
            createFile();
        }
    }

    public static BufferedImage getImage(String path){
        if(path.isEmpty()){
            path = "src/main/resources/default.jpg";
        }
        File file = new File(path);
        return getImage(file);
    }

    public static BufferedImage getImage(File file) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return image;
    }

    private static void createFile() {
        try {
            file = new File(fileName);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initFile(){
        file = new File(fileName);
    }

    private static void writeToFile(String imagePath) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
            writer.write(imagePath);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private static void readFile(List<String> images) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String startReadLine = "";
            while ((startReadLine = reader.readLine())!=null){
                if (!startReadLine.isEmpty()){
                    images.add(startReadLine);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
