import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Collage implements ICollage {


  private HashMap<String, Layer> knownImages;

  private int height;

  private int width;

  public Collage(int height, int width) {
    this.height = height;
    this.width = width;
    this.knownImages = new HashMap<>();
  }

  public Collage() {
    this.knownImages = new HashMap<>();
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void addImage(String newName, Layer image) {
    if (!knownImages.containsKey(newName)) {
      knownImages.put(newName, image);
    } else {
      knownImages.replace(newName, image);
    }
  }

  public String removeExtension(String filePath) {
    File f = new File(filePath);
    String name = f.getName();
    if (name.startsWith(".")) {
      if (name.lastIndexOf('.') == name.indexOf('.')) return name;
    }
    if (!name.contains(".")) { return name; }
    return name.substring(0, name.lastIndexOf('.'));
  }

  public void load(String filepath) {
    if (filepath.endsWith("ppm")) {
      RGBA[][] newImage = readPPM(filepath);
      Layer img = new Layer(newImage, this);
      this.addImage(this.removeExtension(filepath), img);
    }
  }

  public RGBA[][] readPPM(String filename) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("File not found: " + filename);
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");

    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int alphaValue = sc.nextInt();

    RGBA[][] pixels = new RGBA[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt() * alphaValue / 255;
        int g = sc.nextInt() * alphaValue / 255;
        int b = sc.nextInt() * alphaValue / 255;
        pixels[i][j] = new RGBA(r, g, b);
      }
    }
    return pixels;
  }



  public void createProject(int height, int width) {
    this.height = height;
    this.width = width;
    this.knownImages = new HashMap<>();
  }

  public Layer getLayer(String oldName) {
    RGBA[][] clone = new RGBA[this.height][this.width];
    for (int y = 0; y < this.height; y++) {
      for (int x = 0; x < this.width; x++) {
        clone[y][x] = new RGBA(knownImages.get(oldName).getPixelAt(y, x).getRed(),
                knownImages.get(oldName).getPixelAt(y, x).getGreen(),
                knownImages.get(oldName).getPixelAt(y, x).getBlue(),
                knownImages.get(oldName).getPixelAt(y, x).getAlpha());
      }
    }
    return new Layer(clone, this);
  }

  public void addLayer(String layer) throws IllegalArgumentException, NoSuchElementException {
    if (layer == null) {
      throw new IllegalArgumentException("The given ID can not be NULL.");
    }

    this.knownImages.put(layer, new Layer(new RGBA[this.height][this.width], this));
  }


  public void addImageToLayer(String LayerName, String imgName, int yOffset, int xOffset) throws IllegalArgumentException {
    if (imgName == null) {
      throw new IllegalArgumentException("no image is here");
    }
    if (LayerName == null) {
      throw new IllegalArgumentException("no image is here");
    }
    if (imgName.endsWith(".ppm")) {
      Layer newImage = new Layer(this.readPPM(imgName), this);
      for (int i = yOffset; i < this.height; i++) {
        for (int j = xOffset; j < this.width; j++) {
          newImage.getPixelAt(i - yOffset, j - xOffset);
        }
      }
      this.knownImages.put(LayerName, newImage);
    }
  }


  public void setFilter(String currentLayer, String filterName) {
      RGBA[][] clone = new RGBA[this.height][this.width];
      for (int y = 0; y < this.height; y++) {
        for (int x = 0; x < this.width; x++) {
          this.knownImages.get(currentLayer).applyFilter(clone, this.filterChoice(filterName));
        }
      }
      this.knownImages.put(currentLayer, new Layer(clone, this));
  }

// find a way to keep track of the previous layer
  public void save(String file) {
    if (file.endsWith("ppm")) {
      try {
        FileWriter writer = new FileWriter(file);
        writer.write("P3\n");
        writer.write(height + " " + width + "\n");
        writer.write(255 + "\n");
        for (Layer ly: this.knownImages.values()) {
          for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
              int red = ly.getPixelAt(y, x).getRed();
              int green = ly.getPixelAt(y, x).getGreen();
              int blue = ly.getPixelAt(y, x).getBlue();
              int alpha = ly.getPixelAt(y, x).getAlpha();
              writer.write(red + " " + green + " " + blue + " " + alpha + "\n");
            }
          }
        }
        writer.close();
      } catch (IOException e) {
        // catch exception if thrown
      }
    }
  }


  public IFilter filterChoice(String filterOption) {
    switch (filterOption) {
      case "red-component":
        return new FilterRed();
      /*case "green-component":
        return new FilterGreen();
      case "blue-component":
        return new FilterBlue();*/
      default:
        return null;
    }
  }
}


