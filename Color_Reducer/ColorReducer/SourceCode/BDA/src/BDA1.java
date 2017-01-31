import java.awt.Color;

public class BDA1 {

	   /** 
	     *  main() This program expects a digital file name as the
	     *  only required command line argument.  Note that for
	     *  brevity, it does not perform any error checking. 
	     *
	     *  @param args - an array of command line arguments
	     **/


	    public static void main (String args[] ) {

	        //  With 8 bit color, 255 is the max
	        final int MAX_PIXEL = 255;

	        Picture pic = new Picture (args[0]);

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


	            int value = p.getRed();
	            value = Math.abs (value - MAX_PIXEL);
	            p.setRed (value);

	            value = p.getBlue();
	            value = Math.abs (value - MAX_PIXEL);
	            p.setBlue (value);

	            value = p.getGreen();
	            value = Math.abs (value - MAX_PIXEL);
	            p.setGreen(value);

	        }

		pic.show();

	    }}
