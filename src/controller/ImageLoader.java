package controller;

import javax.swing.*;


<<<<<<< HEAD
// source: http://www.java2s.com/Code/Java/Swing-JFC/UseJListcomponenttodisplaycustomobjectswithListCellRenderer.htm
=======
/**
 * ImageLoader is a simple helper class that populates the JList - Medical Images
 * @author Tobias Büschel
 * @link: http://www.java2s.com/Code/Java/Swing-JFC/UseJListcomponenttodisplaycustomobjectswithListCellRenderer.htm
 */
>>>>>>> 8f89938e3b67df0e005b64ff9f79cb3e5ca5137f
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

<<<<<<< HEAD
    // Override standard toString method to give a useful result
=======
    /** Override the standard "toString" method to give a useful result */
>>>>>>> 8f89938e3b67df0e005b64ff9f79cb3e5ca5137f
    public String toString() {
        return title;
    }
}
