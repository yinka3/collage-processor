import java.io.FileWriter;
import java.io.IOException;


public class Layer {

  String layerName;
  private final RGBA[][] rgba;
  private final int width;
  private final int height;
  private Collage layer;

  public Layer(String layerName, int height, int width, RGBA[][] rgba) {
    this.layerName = layerName;
    this.rgba = rgba;
    this.width = width;
    this.height = height;
  }

  public Layer(int height, int width, RGBA[][] rgba, Collage pic) {
    this.width = width;
    this.height = height;
    this.rgba = rgba;
    this.layer = pic;
  }

  public Layer(int height, int width, RGBA[][] rgba) {
    this.width = width;
    this.height = height;
    this.rgba = rgba;
  }

  public Layer(Collage pic) {
    this.width = 0;
    this.height = 0;
    this.rgba = new RGBA[][]{};
    this.layer = pic;
  }

  public String getName() { return this.layerName; }

  // getter for height
  public int getHeight() {
    return this.height;
  }

  // getter for width
  public int getWidth() {
    return this.width;
  }

  private boolean isOutOfBounds(int row, int col) {
    return row < 0 || col < 0 || row > height - 1 || col > width - 1;
  }

  public RGBA getPixelAt(int row, int col) throws IllegalArgumentException {
    if (isOutOfBounds(row, col)) {
      throw new IllegalArgumentException("invalid pixel");
    }
    return this.rgba[row][col];
  }

  public void save(String file) {
    if (file.endsWith("ppm")) {
      try {
        FileWriter writer = new FileWriter(file);
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            sb.append(this.getPixelAt(y, x).getRed()).append("\n");
            sb.append(this.getPixelAt(y, x).getGreen()).append("\n");
            sb.append(this.getPixelAt(y, x).getBlue()).append("\n");
          }
        }
        writer.write("P3\n");
        writer.write(height + " " + width + "\n");
        writer.write(255 + "\n");
        writer.write(sb.toString());
        writer.close();
      } catch (IOException e) {
        // catch exception if thrown
      }
    }
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