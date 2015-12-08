package view;

import java.awt.*;

/**
 *  <p>Patient Registry Management System</p>
 *  <p>As part of the GC01 coursework assignment</p>
 *
 *  @author Tobias Büschel
 *  version 0.7
 */
public class PRMS {


    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    System.out.println("working");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
