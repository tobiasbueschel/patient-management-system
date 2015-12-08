package controller;

import java.io.*;

/**
 *  <p>The CopyFile class is a helper class that enables the saving of images within this workspace.</p>
 *  <p>In other words, when a someone uses the program and imports a photo it will be uploaded to the repository of the program</p>
 *
 *  @link credit goes to http://stackoverflow.com/questions/10353022/writing-file-object-to-another-location for the implementation
 */
public class CopyFile {

    public CopyFile(String srFile, String dtFile) {

        try {
            File f1 = new File(srFile); // file to be copied
            File f2 = new File(dtFile); // future file

            InputStream in = new FileInputStream(f1);
            OutputStream out = new FileOutputStream(f2);

            /** byte array */
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();

//            System.out.println("The Image was copied into the images directory");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage() + " in the specified directory.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
