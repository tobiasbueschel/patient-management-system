package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import controller.BookEntry;


// source: http://www.java2s.com/Code/Java/Swing-JFC/UseJListcomponenttodisplaycustomobjectswithListCellRenderer.htm
class BookCellRenderer extends JLabel implements ListCellRenderer {
	  private static final 
	  Color green = new Color(0, 189, 154);


	  public BookCellRenderer() {
	    setOpaque(true);
	    setIconTextGap(12);
	  }

	  public Component getListCellRendererComponent(JList list, Object value,
	      int index, boolean isSelected, boolean cellHasFocus) {
	    BookEntry entry = (BookEntry) value;
	    setText(entry.getTitle());
	    setIcon(entry.getImage());
	    
	    if (isSelected) {
	      setBackground(green);
	      setForeground(Color.white);
	    } else {
	      setBackground(Color.white);
	      setForeground(Color.black);
	    }
	    return this;
	  }
	}