package controller;

import javax.swing.*;


/**
 * ImageLoader is a simple helper class that populates the JList - Medical Images
 *
 * @author Tobias Büschel
 * @link: http://www.java2s.com/Code/Java/Swing-JFC/UseJListcomponenttodisplaycustomobjectswithListCellRenderer.htm
 */
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

  /**
   * Override the standard "toString" method to give a useful result
   */
  public String toString() {
    return title;
  }
}
