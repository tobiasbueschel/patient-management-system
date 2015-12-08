package controller;

import javax.swing.*;


// source: http://www.java2s.com/Code/Java/Swing-JFC/UseJListcomponenttodisplaycustomobjectswithListCellRenderer.htm
public class ImageLoader {
    private final String title;

    private final ImageIcon image;

    public ImageLoader(String title, ImageIcon img) {
        this.title = title;
        this.image = img;
    }

    public String getTitle() {
        return title;
    }

    public ImageIcon getImage() {
        return image;
    }

    // Override standard toString method to give a useful result
    public String toString() {
        return title;
    }
}
