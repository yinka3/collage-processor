import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;


public class Layer implements ILayers{

  private RGBA[][] rgba;

  private ICollage layer;

  public int height;

  public int width;


  public Layer(RGBA[][] rgba, ICollage pic) {
    this.height = pic.getHeight();
    this.width = pic.getWidth();
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        rgba[i][j] = new RGBA(255, 255, 255, 255);
      }
    }
    this.rgba = rgba;
    this.layer = pic;
  }


  private boolean isOutOfBounds(int row, int col) {
    return row < 0 || col < 0 || row > rgba.length - 1 || col > rgba[0].length - 1;
  }


  public RGBA getPixelAt(int row, int col) throws IllegalArgumentException {
    if (isOutOfBounds(row, col)) {
      throw new IllegalArgumentException("invalid pixel");
    }
    return this.rgba[row][col];
  }

  public void applyFilter(RGBA[][] filtered, IFilter filter) {
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
          filtered[i][j] = filter.apply(filtered[i][j]);
        }
      }
  }

  public RGBA[][] applyTrans(RGBA[][] prev, RGBA[][] curr) {
    RGBA[][] finalImage = new RGBA[this.rgba.length][this.rgba[0].length];
    for (int i = 0; i < this.rgba.length; i++) {
      for (int j = 0; j < this.rgba[0].length; j++) {
        finalImage[i][j] = prev[i][j].transparency(curr[i][j]);
      }
    }
    return finalImage;
  }
}



/*
  public void brightenByIntensity(String name) {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int intensity = this.intensity(this.getPixelAt(x, y).getRed(),
                this.getPixelAt(x, y).getGreen(), this.getPixelAt(x, y).getBlue());
        brighten(y, x, intensity);
      }
    }
    layer.addImage(name, new Layer(height, width, rgba, layer));
  }

  public void brightenByLuma(String name) {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int luma = (int) this.luma(this.getPixelAt(x, y).getRed(),
                this.getPixelAt(x, y).getGreen(), this.getPixelAt(x, y).getBlue());
        brighten(y, x, luma);
      }
    }
    layer.addImage(name, new Layer(height, width, rgba, layer));
  }

  public void brightenByValue(String name) {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int value = this.value(this.getPixelAt(x, y).getRed(),
                this.getPixelAt(x, y).getGreen(), this.getPixelAt(x, y).getBlue());
        brighten(y, x, value);
      }
    }
    layer.addImage(name, new Layer(height, width, rgba, layer));
  }

  public void brighten(int y, int x, int amount) {
    this.getPixelAt(x, y).setBlue(this.clamp(this.getPixelAt(x, y).getBlue() + amount));
    this.getPixelAt(x, y).setGreen(this.clamp(this.getPixelAt(x, y).getGreen() + amount));
    this.getPixelAt(x, y).setGreen(this.clamp(this.getPixelAt(x, y).getGreen() + amount));
  }

  public void darkenByValue(String name) {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int value = this.value(this.getPixelAt(x, y).getRed(),
                this.getPixelAt(x, y).getGreen(), this.getPixelAt(x, y).getBlue());
        darken(y, x, value);
      }
    }
    layer.addImage(name, new Layer(height, width, rgba, layer));
  }

  private void darken(int y, int x, int amount) {
    this.getPixelAt(x, y).setBlue(this.clamp(this.getPixelAt(x, y).getBlue() - amount));
    this.getPixelAt(x, y).setGreen(this.clamp(this.getPixelAt(x, y).getGreen() - amount));
    this.getPixelAt(x, y).setGreen(this.clamp(this.getPixelAt(x, y).getGreen() - amount));
  }

  public void darkenByLuma(String name) {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int luma = (int) this.luma(this.getPixelAt(x, y).getRed(),
                this.getPixelAt(x, y).getGreen(), this.getPixelAt(x, y).getBlue());
        darken(y, x, luma);
      }
    }
    layer.addImage(name, new Layer(height, width, rgba, layer));
  }

  public void darkenByIntensity(String name) {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int intensity = this.intensity(this.getPixelAt(x, y).getRed(),
                this.getPixelAt(x, y).getGreen(), this.getPixelAt(x, y).getBlue());
        darken(y, x, intensity);
      }
    }
    layer.addImage(name, new Layer(height, width, rgba, layer));
  }*/