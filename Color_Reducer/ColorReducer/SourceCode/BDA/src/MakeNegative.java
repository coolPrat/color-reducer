/**
  *
  *  This is a demo showing some of the features of the media library.
  *
  *  It uses the Media Computation library developed at Georgia 
  *  Institute of Technology (Barbara Ericson).
  *
  *
  *  Author:  Trudy Howles
  *  Date:    2014
  *
**/


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import weka.clusterers.SimpleKMeans;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class MakeNegative {

   /** 
     *  main() This program expects a digital file name as the
     *  only required command line argument.  Note that for
     *  brevity, it does not perform any error checking. 
     *
     *  @param args - an array of command line arguments
 * @throws Exception 
     **/


    public static void main (String args[] )throws Exception {

        //  With 8 bit color, 255 is the max
        final int MAX_PIXEL = 255;

        Picture pic = new Picture ("/Users/prasadchitnis/Documents/workspace/BDA/src/motorcycle.jpg");

        //  I view the picture as one stream of pixels.  You
        //  can also view as a 2-D array of rows, columns

        Pixel [] pixels = pic.getPixels();
        System.out.println ("Width " + pic.getWidth());
        System.out.println ("Height " + pic.getHeight());

        
        for (int loop = 0; loop < pixels.length; loop++) {
            Pixel p = pixels [loop];

               //  get the RGB for this pixel.  Reassign each color component
               //  to (itself - 255) (8 bit color so subtracting 255
               //  negates the color)
            System.out.println("Red:"+p.getRed()+" Green:"+p.getGreen()+" Blue"+p.getBlue());
            Instance i = new DenseInstance(3);
            i.setValue(0, p.getRed());
            i.setValue(1, p.getGreen());
            i.setValue(2, p.getBlue());
//            int value = p.getRed();
//            value = Math.abs (value - MAX_PIXEL);
//            p.setRed (value);
//
//            value = p.getBlue();
//            value = Math.abs (value - MAX_PIXEL);
//            p.setBlue (value);
//
//            value = p.getGreen();
//            value = Math.abs (value - MAX_PIXEL);
//            p.setGreen(value);

        }

	pic.show();
	
	FileInputStream inputStream = new FileInputStream("/Users/prasadchitnis/Documents/workspace/BDA/src/motorcycle.jpg");
    FileOutputStream outputStream = new FileOutputStream("/Users/prasadchitnis/Documents/workspace/BDA/src/data.arff");
     
    // reads input image from file
    BufferedImage inputImage = ImageIO.read(inputStream);
     
    // writes to the output image in specified format
    boolean result = ImageIO.write(inputImage, "ARFF", outputStream);
     
    System.out.println("result "+result);
    // needs to close the streams
    outputStream.close();
    inputStream.close();

    }
}
