package controller;

import javax.swing.Icon;
import javax.swing.ImageIcon;


// source: https://www.youtube.com/watch?v=cjVB8wT4kA0
class BookEntry {
	  private final String title;

	  private final String imagePath;

	  private ImageIcon image;

	  public BookEntry(String title, String imagePath) {
	    this.title = title;
	    this.imagePath = imagePath;
	  }

	  public String getTitle() {
	    return title;
	  }

	  public ImageIcon getImage() {
	    if (image == null) {
	      image = new ImageIcon(imagePath);
	    }
	    return image;
	  }

	  // Override standard toString method to give a useful result
	  public String toString() {
	    return title;
	  }
	}
