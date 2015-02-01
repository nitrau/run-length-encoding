/* PixImage.java */

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

  /**
   *  Define any variables associated with a PixImage object here.  These
   *  variables MUST be private.
   */
  private short[][][] pixarray;


  /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   */
  public PixImage(int width, int height) {
    pixarray = new short[width][height][3];
    // Your solution here.
  }

  /**
   * copies pixels from PixImage INPUT into self. 
   * Dimesions of current pixarray and input's pixarray must be the same.
   */

  public void copy(PixImage input) {
    for (int i = 0; i < input.getWidth(); i++) {
      for (int j = 0; j < input.getHeight(); j++){
        for (int n = 0; n < 3; n ++) {
          this.pixarray[i][j][n] = input.pixarray[i][j][n];
        }
      }
    }
  }

  public void changePixel(PixImage input, int x, int y) {
    if ((x == 0 && y == 0) || (x == 0 && y == input.getHeight() - 1) || (y == 0 && x == input.getWidth() - 1) || (x == input.getWidth() - 1 && y == input.getHeight() - 1)) {
      cornerPixel(input, x, y);
    } else if (x == 0 || y == 0 || x == input.getWidth() - 1 || y == input.getHeight() - 1) {
      sidePixel(input, x, y);
    } else {
      centerPixel(input, x, y);
    }
  }

  public void cornerPixel(PixImage input, int x, int y) {
    if (x == 0 && y == 0) {
      short red = (short)((input.getRed(x, y) + input.getRed(x + 1, y) + input.getRed(x + 1, y + 1) + input.getRed(x, y + 1)) / 4);
      short green = (short)((input.getGreen(x, y) + input.getGreen(x + 1, y) + input.getGreen(x + 1, y + 1) + input.getGreen(x, y + 1)) / 4);
      short blue = (short)((input.getBlue(x, y) + input.getBlue(x + 1, y) + input.getBlue(x + 1, y + 1) + input.getBlue(x, y + 1)) / 4);
      this.setPixel(x, y, red, green, blue);
    } else if (x == 0 && y == input.getHeight() - 1) {
      short red = (short)((input.getRed(x, y) + input.getRed(x + 1, y - 1) + input.getRed(x + 1, y) + input.getRed(x, y - 1)) / 4);
      short green = (short)((input.getGreen(x, y) + input.getGreen(x + 1, y - 1) + input.getGreen(x + 1, y) + input.getGreen(x, y - 1)) / 4);
      short blue = (short)((input.getBlue(x, y) + input.getBlue(x + 1, y - 1) + input.getBlue(x + 1, y) + input.getBlue(x, y - 1)) / 4);
      this.setPixel(x, y, red, green, blue);
    } else if (y == 0 && x == input.getWidth() - 1) {
      short red = (short)((input.getRed(x, y) + input.getRed(x - 1, y) + input.getRed(x, y + 1) + input.getRed(x - 1, y + 1)) / 4);
      short green = (short)((input.getGreen(x, y) + input.getGreen(x - 1, y) + input.getGreen(x, y + 1) + input.getGreen(x - 1, y + 1)) / 4);
      short blue = (short)((input.getBlue(x, y) + input.getBlue(x - 1, y) + input.getBlue(x, y + 1) + input.getBlue(x - 1, y + 1)) / 4);
      this.setPixel(x, y, red, green, blue);
    } else {
      short red = (short)((input.getRed(x, y) + input.getRed(x - 1, y - 1) + input.getRed(x - 1, y) + input.getRed(x, y - 1)) / 4);
      short green = (short)((input.getGreen(x, y) + input.getGreen(x - 1, y - 1) + input.getGreen(x - 1, y) + input.getGreen(x, y - 1)) / 4);
      short blue = (short)((input.getBlue(x, y) + input.getBlue(x - 1, y - 1) + input.getBlue(x - 1, y) + input.getBlue(x, y - 1)) / 4);
      this.setPixel(x, y, red, green, blue);
    }
  }

  public void centerPixel(PixImage input, int x, int y) {
    int[] xnext = {x - 1, x, x + 1};
    int[] ynext = {y - 1, y, y + 1};
    short red = 0;
    short blue = 0;
    short green = 0;
    for (int xi: xnext) {
      for (int yi: ynext) {
        red = (short)(red + input.getRed(xi, yi));
        green = (short)(green + input.getGreen(xi, yi));
        blue = (short)(blue + input.getBlue(xi, yi));
      }
    }
    this.setPixel(x, y, (short)(red / 9), (short)(green / 9), (short)(blue / 9));
  }

  public void sidePixel(PixImage input, int x, int y) {
    if (y == 0) {
      short red = (short)((input.getRed(x-1, y) + input.getRed(x, y) + input.getRed(x+1, y) + input.getRed(x-1, y + 1) + input.getRed(x, y+1) + input.getRed(x+1, y+1))/6);
      short green = (short)((input.getGreen(x-1, y) + input.getGreen(x, y) + input.getGreen(x+1, y) + input.getGreen(x-1, y + 1) + input.getGreen(x, y+1) + input.getGreen(x+1, y+1))/6);
      short blue = (short)((input.getBlue(x-1, y) + input.getBlue(x, y) + input.getBlue(x+1, y) + input.getBlue(x-1, y + 1) + input.getBlue(x, y+1) + input.getBlue(x+1, y+1))/6);
      this.setPixel(x, y, red, green, blue);
    } else if (y == input.getHeight()-1) {
      short red = (short)((input.getRed(x-1, y) + input.getRed(x, y) + input.getRed(x+1, y) + input.getRed(x-1, y-1) + input.getRed(x,y-1) + input.getRed(x+1, y-1))/6);
      short green = (short)((input.getGreen(x-1, y) + input.getGreen(x, y) + input.getGreen(x+1, y) + input.getGreen(x-1, y-1) + input.getGreen(x,y-1) + input.getGreen(x+1, y-1))/6);
      short blue = (short)((input.getBlue(x-1, y) + input.getBlue(x, y) + input.getBlue(x+1, y) + input.getBlue(x-1, y-1) + input.getBlue(x,y-1) + input.getBlue(x+1, y-1))/6);
      this.setPixel(x, y, red, green, blue);
    } else if (x == 0) {
      short red = (short)((input.getRed(x,y-1) + input.getRed(x,y) + input.getRed(x,y+1) + input.getRed(x+1,y-1) + input.getRed(x+1,y) + input.getRed(x+1,y+1))/6);
      short green = (short)((input.getGreen(x,y-1) + input.getGreen(x,y) + input.getGreen(x,y+1) + input.getGreen(x+1,y-1) + input.getGreen(x+1,y) + input.getGreen(x+1,y+1))/6);
      short blue = (short)((input.getBlue(x,y-1) + input.getBlue(x,y) + input.getBlue(x,y+1) + input.getBlue(x+1,y-1) + input.getBlue(x+1,y) + input.getBlue(x+1,y+1))/6);
      this.setPixel(x, y, red, green, blue);
    } else {
      short red = (short)((input.getRed(x,y-1) + input.getRed(x,y) + input.getRed(x,y+1) + input.getRed(x-1,y-1) + input.getRed(x-1,y) + input.getRed(x-1,y+1))/6);
      short green = (short)((input.getGreen(x,y-1) + input.getGreen(x,y) + input.getGreen(x,y+1) + input.getGreen(x-1,y-1) + input.getGreen(x-1,y) + input.getGreen(x-1,y+1))/6);
      short blue = (short)((input.getBlue(x,y-1) + input.getBlue(x,y) + input.getBlue(x,y+1) + input.getBlue(x-1,y-1) + input.getBlue(x-1,y) + input.getBlue(x-1,y+1))/6);
      this.setPixel(x, y, red, green, blue);
    }
  }

  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   */

  public int getWidth() {
    return pixarray.length;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    return pixarray[0].length;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   */
  public short getRed(int x, int y) {
    return pixarray[x][y][0];
  }

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   */
  public short getGreen(int x, int y) {
    return pixarray[x][y][1];
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   */
  public short getBlue(int x, int y) {
    return pixarray[x][y][2];
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    pixarray[x][y][0] = red;
    pixarray[x][y][1] = green;
    pixarray[x][y][2] = blue;
  }

  /**
   * toString() returns a String representation of this PixImage.
   *
   * This method isn't required, but it should be very useful to you when
   * you're debugging your code.  It's up to you how you represent a PixImage
   * as a String.
   *
   * @return a String representation of this PixImage.
   */
  public String toString() {
    // Replace the following line with your solution.
    return "";
  }

  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   */
  public PixImage boxBlur(int numIterations) {
    if (numIterations <= 0) {
      return this;
    } else {
      int n = 0;
      PixImage current, newimg;
      current = this;
      while (n < numIterations) {
        newimg = new PixImage(current.getWidth(), current.getHeight());
        newimg.copy(current);
        for (int i = 0; i < newimg.getWidth(); i++) {
          for (int j = 0; j < newimg.getHeight(); j++) {
            newimg.changePixel(current, i, j);
          }
        }
        n++;
        current = newimg;
      }
    return current;
      }
    }

  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
   * correct images and pass the autograder.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */
  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }

  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */
  public PixImage sobelEdges() {
    short intens;
    PixImage current = new PixImage(this.getWidth(), this.getHeight());
    current.copy(this);
    for (int i = 0; i < current.getWidth(); i++) {
      for (int j = 0; j < current.getHeight(); j++) {
        intens = mag2gray(checkEdge(this, i, j));
        current.setPixel(i, j, intens, intens, intens);
      }
    }
    return current;
    // Don't forget to use the method mag2gray() above to convert energies to
    // pixel intensities.
  }

  public long energyCalc(int gxred, int gyred, int gxgreen, int gygreen, int gxblue, int gyblue) {
    return (long)(gxred * gxred + gyred * gyred + gxgreen * gxgreen + gygreen * gygreen + gxblue * gxblue + gyblue * gyblue);
  }

  public long checkEdge(PixImage input, int x, int y) {
    if ((x == 0 && y == 0) || (x == 0 && y == input.getHeight() - 1) || (y == 0 && x == input.getWidth() - 1) || (x == input.getWidth() - 1 && y == input.getHeight() - 1)) {
      return energyCorner(input, x, y);
    } else if (x == 0 || y == 0 || x == input.getWidth() - 1 || y == input.getHeight() - 1) {
      return energySide(input, x, y);
    } else {
      return energyCenter(input, x, y);
    }
  }

  public long energyCorner(PixImage input, int x, int y) {
    int redx, redy, greenx, greeny, bluex, bluey;
    if (x == 0 && y == 0) {
      redx = input.getRed(x, y) + 2 * input.getRed(x, y) + input.getRed(x, y + 1) - input.getRed(x + 1, y) - 2 * input.getRed(x + 1, y) - input.getRed(x + 1, y + 1);
      redy = input.getRed(x, y) + 2 * input.getRed(x, y) + input.getRed(x + 1, y) - input.getRed(x, y + 1) - 2 * input.getRed(x, y + 1) - input.getRed(x + 1, y + 1);
      greenx = input.getGreen(x, y) + 2 * input.getGreen(x, y) + input.getGreen(x, y + 1) - input.getGreen(x + 1, y) - 2 * input.getGreen(x + 1, y) - input.getGreen(x + 1, y + 1);
      greeny = input.getGreen(x, y) + 2 * input.getGreen(x, y) + input.getGreen(x + 1, y) - input.getGreen(x, y + 1) - 2 * input.getGreen(x, y + 1) - input.getGreen(x + 1, y + 1);
      bluex = input.getBlue(x, y) + 2 * input.getBlue(x, y) + input.getBlue(x, y + 1) - input.getBlue(x + 1, y) - 2 * input.getBlue(x + 1, y) - input.getBlue(x + 1, y + 1);
      bluey = input.getBlue(x, y) + 2 * input.getBlue(x, y) + input.getBlue(x + 1, y) - input.getBlue(x, y + 1) - 2 * input.getBlue(x, y + 1) - input.getBlue(x + 1, y + 1);
      return energyCalc(redx, redy, greenx, greeny, bluex, bluey);
    } else if (x == 0 & y == input.getHeight() - 1) {
      redx = input.getRed(x, y - 1) + 2 * input.getRed(x, y) + input.getRed(x, y) - input.getRed(x + 1, y - 1) - 2 * input.getRed(x + 1, y) - input.getRed(x + 1, y);
      redy = input.getRed(x, y - 1) + 2 * input.getRed(x, y - 1) + input.getRed(x + 1, y - 1) - input.getRed(x, y) - 2 * input.getRed(x, y) - input.getRed(x + 1, y);
      greenx = input.getGreen(x, y - 1) + 2 * input.getGreen(x, y) + input.getGreen(x, y) - input.getGreen(x + 1, y - 1) - 2 * input.getGreen(x + 1, y) - input.getGreen(x + 1, y);
      greeny = input.getGreen(x, y - 1) + 2 * input.getGreen(x, y - 1) + input.getGreen(x + 1, y - 1) - input.getGreen(x, y) - 2 * input.getGreen(x, y) - input.getGreen(x + 1, y);
      bluex = input.getBlue(x, y - 1) + 2 * input.getBlue(x, y) + input.getBlue(x, y) - input.getBlue(x + 1, y - 1) - 2 * input.getBlue(x + 1, y) - input.getBlue(x + 1, y);
      bluey = input.getBlue(x, y - 1) + 2 * input.getBlue(x, y - 1) + input.getBlue(x + 1, y - 1) - input.getBlue(x, y) - 2 * input.getBlue(x, y) - input.getBlue(x + 1, y);
      return energyCalc(redx, redy, greenx, greeny, bluex, bluey);
    } else if (y == 0 & x == input.getWidth() - 1) {
      redx = input.getRed(x - 1, y) + 2 * input.getRed(x - 1, y) + input.getRed(x - 1, y + 1) - input.getRed(x, y) - 2 * input.getRed(x, y) - input.getRed(x, y + 1);
      redy = input.getRed(x - 1, y) + 2 * input.getRed(x, y) + input.getRed(x, y) - input.getRed(x - 1, y + 1) - 2 * input.getRed(x, y + 1) - input.getRed(x, y + 1);
      greenx = input.getGreen(x - 1, y) + 2 * input.getGreen(x - 1, y) + input.getGreen(x - 1, y + 1) - input.getGreen(x, y) - 2 * input.getGreen(x, y) - input.getGreen(x, y + 1);
      greeny = input.getGreen(x - 1, y) + 2 * input.getGreen(x, y) + input.getGreen(x, y) - input.getGreen(x - 1, y + 1) - 2 * input.getGreen(x, y + 1) - input.getGreen(x, y + 1);
      bluex = input.getBlue(x - 1, y) + 2 * input.getBlue(x - 1, y) + input.getBlue(x - 1, y + 1) - input.getBlue(x, y) - 2 * input.getBlue(x, y) - input.getBlue(x, y + 1);
      bluey = input.getBlue(x - 1, y) + 2 * input.getBlue(x, y) + input.getBlue(x, y) - input.getBlue(x - 1, y + 1) - 2 * input.getBlue(x, y + 1) - input.getBlue(x, y + 1);
      return energyCalc(redx, redy, greenx, greeny, bluex, bluey);
    } else {
      redx = input.getRed(x - 1, y - 1) + 2 * input.getRed(x - 1, y) + input.getRed(x - 1, y) - input.getRed(x, y - 1) - 2 * input.getRed(x, y) - input.getRed(x, y);
      redy = input.getRed(x - 1, y - 1) + 2 * input.getRed(x, y - 1) + input.getRed(x, y - 1) - input.getRed(x - 1, y) - 2 * input.getRed(x, y) - input.getRed(x, y);
      greenx = input.getGreen(x - 1, y - 1) + 2 * input.getGreen(x - 1, y) + input.getGreen(x - 1, y) - input.getGreen(x, y - 1) - 2 * input.getGreen(x, y) - input.getGreen(x, y);
      greeny = input.getGreen(x - 1, y - 1) + 2 * input.getGreen(x, y - 1) + input.getGreen(x, y - 1) - input.getGreen(x - 1, y) - 2 * input.getGreen(x, y) - input.getGreen(x, y);
      bluex = input.getBlue(x - 1, y - 1) + 2 * input.getBlue(x - 1, y) + input.getBlue(x - 1, y) - input.getBlue(x, y - 1) - 2 * input.getBlue(x, y) - input.getBlue(x, y);
      bluey = input.getBlue(x - 1, y - 1) + 2 * input.getBlue(x, y - 1) + input.getBlue(x, y - 1) - input.getBlue(x - 1, y) - 2 * input.getBlue(x, y) - input.getBlue(x, y);
      return energyCalc(redx, redy, greenx, greeny, bluex, bluey);
    }
  }

  public long energySide(PixImage input, int x, int y) {
    int redx, redy, greenx, greeny, bluex, bluey;
    if (x == 0) {
      redx = input.getRed(x, y - 1) + 2 * input.getRed(x, y) + input.getRed(x, y + 1) - input.getRed(x + 1, y - 1) - 2 * input.getRed(x + 1, y) - input.getRed(x + 1, y + 1);
      redy = input.getRed(x, y - 1) - input.getRed(x, y + 1) + 2 * input.getRed(x, y - 1) - 2 * input.getRed(x, y + 1) + input.getRed(x + 1, y - 1) - input.getRed(x + 1, y + 1);
      greenx = input.getGreen(x, y - 1) + 2 * input.getGreen(x, y) + input.getGreen(x, y + 1) - input.getGreen(x + 1, y - 1) - 2 * input.getGreen(x + 1, y) - input.getGreen(x + 1, y + 1);
      greeny = input.getGreen(x, y - 1) - input.getGreen(x, y + 1) + 2 * input.getGreen(x, y - 1) - 2 * input.getGreen(x, y + 1) + input.getGreen(x + 1, y - 1) - input.getGreen(x + 1, y + 1);
      bluex = input.getBlue(x, y - 1) + 2 * input.getBlue(x, y) + input.getBlue(x, y + 1) - input.getBlue(x + 1, y - 1) - 2 * input.getBlue(x + 1, y) - input.getBlue(x + 1, y + 1);
      bluey = input.getBlue(x, y - 1) - input.getBlue(x, y + 1) + 2 * input.getBlue(x, y - 1) - 2 * input.getBlue(x, y + 1) + input.getBlue(x + 1, y - 1) - input.getBlue(x + 1, y + 1);
      return energyCalc(redx, redy, greenx, greeny, bluex, bluey);
    } else if (x == input.getWidth() - 1) {
      redx = input.getRed(x - 1, y - 1) + 2 * input.getRed(x - 1, y) + input.getRed(x - 1, y + 1) - input.getRed(x, y - 1) - 2 * input.getRed(x, y) - input.getRed(x, y + 1);
      redy = input.getRed(x - 1, y - 1) + 2 * input.getRed(x, y - 1) + input.getRed(x, y - 1) - input.getRed(x - 1, y + 1) - 2 * input.getRed(x, y + 1) - input.getRed(x, y + 1);
      greenx = input.getGreen(x - 1, y - 1) + 2 * input.getGreen(x - 1, y) + input.getGreen(x - 1, y + 1) - input.getGreen(x, y - 1) - 2 * input.getGreen(x, y) - input.getGreen(x, y + 1);
      greeny = input.getGreen(x - 1, y - 1) + 2 * input.getGreen(x, y - 1) + input.getGreen(x, y - 1) - input.getGreen(x - 1, y + 1) - 2 * input.getGreen(x, y + 1) - input.getGreen(x, y + 1);
      bluex = input.getBlue(x - 1, y - 1) + 2 * input.getBlue(x - 1, y) + input.getBlue(x - 1, y + 1) - input.getBlue(x, y - 1) - 2 * input.getBlue(x, y) - input.getBlue(x, y + 1);
      bluey = input.getBlue(x - 1, y - 1) + 2 * input.getBlue(x, y - 1) + input.getBlue(x, y - 1) - input.getBlue(x - 1, y + 1) - 2 * input.getBlue(x, y + 1) - input.getBlue(x, y + 1);
      return energyCalc(redx, redy, greenx, greeny, bluex, bluey);
    } else if (y == 0) {
      redx = input.getRed(x - 1, y) + 2 * input.getRed(x - 1, y) + input.getRed(x - 1, y + 1) - input.getRed(x + 1, y) - 2 * input.getRed(x + 1, y) - input.getRed(x + 1, y + 1);
      redy = input.getRed(x - 1, y) + 2 * input.getRed(x, y) + input.getRed(x + 1, y) - input.getRed(x - 1, y + 1) - 2 * input.getRed(x, y + 1) - input.getRed(x + 1, y + 1);
      greenx = input.getGreen(x - 1, y) + 2 * input.getGreen(x - 1, y) + input.getGreen(x - 1, y + 1) - input.getGreen(x + 1, y) - 2 * input.getGreen(x + 1, y) - input.getGreen(x + 1, y + 1);
      greeny = input.getGreen(x - 1, y) + 2 * input.getGreen(x, y) + input.getGreen(x + 1, y) - input.getGreen(x - 1, y + 1) - 2 * input.getGreen(x, y + 1) - input.getGreen(x + 1, y + 1);
      bluex = input.getBlue(x - 1, y) + 2 * input.getBlue(x - 1, y) + input.getBlue(x - 1, y + 1) - input.getBlue(x + 1, y) - 2 * input.getBlue(x + 1, y) - input.getBlue(x + 1, y + 1);
      bluey = input.getBlue(x - 1, y) + 2 * input.getBlue(x, y) + input.getBlue(x + 1, y) - input.getBlue(x - 1, y + 1) - 2 * input.getBlue(x, y + 1) - input.getBlue(x + 1, y + 1);
      return energyCalc(redx, redy, greenx, greeny, bluex, bluey);
    } else {
      redx = input.getRed(x - 1, y - 1) + 2 * input.getRed(x - 1, y) + input.getRed(x - 1, y) - input.getRed(x + 1, y - 1) - 2 * input.getRed(x + 1, y) - input.getRed(x + 1, y);
      redy = input.getRed(x - 1, y - 1) + 2 * input.getRed(x, y - 1) + input.getRed(x + 1, y - 1) - input.getRed(x - 1, y) - 2 * input.getRed(x, y) - input.getRed(x + 1, y);
      greenx = input.getGreen(x - 1, y - 1) + 2 * input.getGreen(x - 1, y) + input.getGreen(x - 1, y) - input.getGreen(x + 1, y - 1) - 2 * input.getGreen(x + 1, y) - input.getGreen(x + 1, y);
      greeny = input.getGreen(x - 1, y - 1) + 2 * input.getGreen(x, y - 1) + input.getGreen(x + 1, y - 1) - input.getGreen(x - 1, y) - 2 * input.getGreen(x, y) - input.getGreen(x + 1, y);
      bluex = input.getBlue(x - 1, y - 1) + 2 * input.getBlue(x - 1, y) + input.getBlue(x - 1, y) - input.getBlue(x + 1, y - 1) - 2 * input.getBlue(x + 1, y) - input.getBlue(x + 1, y);
      bluey = input.getBlue(x - 1, y - 1) + 2 * input.getBlue(x, y - 1) + input.getBlue(x + 1, y - 1) - input.getBlue(x - 1, y) - 2 * input.getBlue(x, y) - input.getBlue(x + 1, y);
      return energyCalc(redx, redy, greenx, greeny, bluex, bluey);
    }
  }

  public long energyCenter(PixImage input, int x, int y) {
    int redx, redy, greenx, greeny, bluex, bluey;
    redx = input.getRed(x - 1, y - 1) + 2 * input.getRed(x - 1, y) + input.getRed(x - 1, y + 1) -
      input.getRed(x + 1, y - 1) - 2 * input.getRed(x + 1, y) - input.getRed(x + 1, y + 1);
    redy = input.getRed(x - 1, y - 1) + 2 * input.getRed(x, y - 1) + input.getRed(x + 1, y - 1) -
      input.getRed(x - 1, y + 1) - 2 * input.getRed(x, y + 1) - input.getRed(x + 1, y + 1);
    greenx = input.getGreen(x - 1, y - 1) + 2 * input.getGreen(x - 1, y) + input.getGreen(x - 1, y + 1) -
      input.getGreen(x + 1, y - 1) - 2 * input.getGreen(x + 1, y) - input.getGreen(x + 1, y + 1);
    greeny = input.getGreen(x - 1, y - 1) + 2 * input.getGreen(x, y - 1) + input.getGreen(x + 1, y - 1) -
      input.getGreen(x - 1, y + 1) - 2 * input.getGreen(x, y + 1) - input.getGreen(x + 1, y + 1);
    bluex = input.getBlue(x - 1, y - 1) + 2 * input.getBlue(x - 1, y) + input.getBlue(x - 1, y + 1) -
      input.getBlue(x + 1, y - 1) - 2 * input.getBlue(x + 1, y) - input.getBlue(x + 1, y + 1);
    bluey = input.getBlue(x - 1, y - 1) + 2 * input.getBlue(x, y - 1) + input.getBlue(x + 1, y - 1) -
      input.getBlue(x - 1, y + 1) - 2 * input.getBlue(x, y + 1) - input.getBlue(x + 1, y + 1); 
    return energyCalc(redx, redy, greenx, greeny, bluex, bluey);
  }


  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * main() runs a series of tests to ensure that the convolutions (box blur
   * and Sobel) are correct.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");
    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());


    PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image2);
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 2x3 image.");
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());
  }
}
