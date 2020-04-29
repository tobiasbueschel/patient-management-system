package pms.view;

import pms.controller.ImageLoader;

import javax.swing.*;
import java.awt.*;

/**
 * source: http://www.java2s.com/Code/Java/Swing-JFC/UseJListcomponenttodisplaycustomobjectswithListCellRenderer.htm
 */
class ImageCellRenderer extends JLabel implements ListCellRenderer<Object> {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private static final
  Color green = new Color(0, 189, 154);

  /**
   * Default constructor
   */
  public ImageCellRenderer() {
    setOpaque(true);
    setIconTextGap(12);
  }

  /**
   * @param list
   * @param value
   * @param index
   * @param isSelected
   * @param cellHasFocus
   * @return
   */
  public Component getListCellRendererComponent(JList<?> list, Object value,
                                                int index, boolean isSelected, boolean cellHasFocus) {
    ImageLoader entry = (ImageLoader) value;
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
