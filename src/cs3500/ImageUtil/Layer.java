package cs3500.ImageUtil;

import cs3500.Filter.IFilter;


public class Layer {

  private RGBA[][] rgba2;

  public int height;

  public int width;

  public double alpha;

  public IFilter filter;

  public String name;


  public Layer(String name, int height, int width) {
    this.name = name;
    this.height = height;
    this.width = width;
    this.rgba2 = new RGBA[this.height][this.width];
    this.alpha = 0;
    for (int y = 0; y < this.height; y++) {
      for (int x = 0; x < this.width; x++) {
        this.rgba2[y][x] = new RGBA(255, 255, 255, 0);
      }
    }
  }


  public String getName() {
    return this.name;
  }

  public double getAlpha() {
    return alpha;
  }

  public void setPixels(RGBA[][] pixels) {
    if (pixels.length != this.height || pixels[0].length != this.width) {
      throw new IllegalArgumentException("invalid pixel set in layer");
    }

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.rgba2[i][j] = pixels[i][j].copy();
      }
    }
  }

  public void updateFilter(IFilter filter) {
    this.filter = filter;
  }

  public RGBA[][] applyFilter(RGBA[][] filtered) {
      for (int j = 0; j < this.height; j++) {
        for (int i = 0; i < this.width; i++) {
          filtered[j][i] = this.filter.apply(filtered[j][i]);
        }
      }
    return filtered;
  }

 public void applyAlpha(double alpha) {
    for (int j = 0; j < this.height; j++) {
      for (int i = 0; i < this.width; i++) {
        this.rgba2[j][i].setAlpha(alpha);
      }
    }
    this.alpha = alpha;
  }

  public RGBA[][] getNewRgba2() {
    RGBA[][] temp = new RGBA[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
          temp[i][j] = this.rgba2[i][j].copy();
        }
      }
    return temp;
  }

  public RGBA[][] visualize() {
    return this.applyFilter(this.getNewRgba2());
  }

  public void addImageToLayer(PPMUtil imgName, int yOffset, int xOffset) {
    if (imgName == null) {
      throw new IllegalArgumentException("no image is here");
    }

    if (yOffset < 0 || yOffset >= this.height || xOffset < 0 || xOffset >= this.width) {
      throw new IllegalArgumentException("Invalid man");
    }

    for (int y = yOffset; y < Math.min(imgName.height + yOffset, this.height); y++) {
      for (int x = xOffset; x < Math.min(imgName.width + xOffset, this.width) ; x++) {
        RGBA clone = imgName.getPixelAt(y - yOffset, x - xOffset).copy();
        this.rgba2[y][x] = clone;
      }
    }
  }
}
