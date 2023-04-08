package cs3500.controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import cs3500.filter.BrightenByIntensity;
import cs3500.filter.BrightenByLuma;
import cs3500.filter.BrightenByValue;
import cs3500.filter.DarkenByIntensity;
import cs3500.filter.DarkenByLuma;
import cs3500.filter.DarkenByValue;
import cs3500.filter.FilterBlue;
import cs3500.filter.FilterGreen;
import cs3500.filter.FilterRed;
import cs3500.filter.IBlending;
import cs3500.filter.IFilter;
import cs3500.imageutil.RGBA;
import cs3500.model.ICollage;
import cs3500.view.GUIView;

import static cs3500.controller.Controller.getiBlending;

/**
 * This class handles the commands that will be used in the GUI class, allowing for clean
 * access of the commands made by the model to be used in the GUI.
 * Implements the commands interface for that purpose.
 */
public class ControllerGUI implements Commands {

  private GUIView guiView;

  private final ICollage model;


  public ControllerGUI(ICollage image, GUIView view) {
    this.guiView = view;
    this.model = image;
  }

  /**
   * This is a getter method to get the filter choice based off a string.
   * @param filterOption the string representation of the filter option.
   * @return the actual filter option that can modify the layer.
   */
  private IFilter filterChoice(String filterOption) {
    return getIFilterName(filterOption);
  }

  /**
   * This is a getter method to get the blender choice based off a string.
   * @param blenderOption the string representation of the blender option.
   * @return the actual blender option that can modify the layer.
   */
  private IBlending getIBlenderName(String blenderOption) {
    return getiBlending(blenderOption);
  }

  /**
   * Allows to get filter option from string.
   * @param filterOption the string to be used for comparison of filters.
   * @return the actual filter option.
   */
  static IFilter getIFilterName(String filterOption) {
    switch (filterOption) {
      case "red-component":
        return new FilterRed();
      case "green-component":
        return new FilterGreen();
      case "blue-component":
        return new FilterBlue();
      case "darken-intensity":
        return new DarkenByIntensity();
      case "darken-luma":
        return new DarkenByLuma();
      case "darken-value":
        return new DarkenByValue();
      case "brighten-intensity":
        return new BrightenByIntensity();
      case "brighten-luma":
        return new BrightenByLuma();
      case "brighten-value":
        return new BrightenByValue();
      default:
        return null;
    }
  }

  public void setView(GUIView v) {
    guiView = v;
    guiView.addCommands(this);
  }

  @Override
  public void createProject(String height, String width) {
    this.model.createProject(Integer.parseInt(height), Integer.parseInt(width));
    guiView.refresh();
  }

  @Override
  public void addLayer(String name) {
    this.model.addLayer(name);
    guiView.refresh();
  }

  @Override
  public void addImageToLayer(String layerName, String imageName, String y, String x) {
    this.model.addImageToLayer(layerName, imageName, Integer.parseInt(y), Integer.parseInt(x));
    guiView.refreshImage(this.getImage());
    guiView.refresh();
  }

  @Override
  public void saveImage(String nameOfImage) {
    this.model.savePPMImage(nameOfImage);
    guiView.refreshImage(this.getImage());
    guiView.refresh();
  }

  @Override
  public void saveProject(String nameOfProject) {
    this.model.saveProject(nameOfProject);
    guiView.refreshImage(this.getImage());
    guiView.refresh();
  }

  @Override
  public void setFilter(String name, String filter) {
    this.model.setFilter(name, this.filterChoice(filter));
    guiView.refreshImage(this.getImage());
    guiView.repaint();
  }


  @Override
  public void setBlend(String layerName, String blend) {
    this.model.setBlend(layerName, this.getIBlenderName(blend));
    guiView.refreshImage(this.getImage());
    guiView.repaint();
  }

  @Override
  public void loadProject(String filename) {
    this.model.loadProject(filename);
    guiView.refreshImage(this.getImage());
    guiView.refresh();
  }

  /**
   * This method is used for displaying a 2D array of pixels as a buffered image,
   * this will be helpful visualization of an image in the GUI.
   * @return a buffered image of the 2D array of pixels.
   */
  public Image getImage() {
    RGBA[][] pixels = this.model.finalPixel();
    BufferedImage img = new BufferedImage(this.model.getWidth(),
            this.model.getHeight(), BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < this.model.getHeight(); y++) {
      for (int x = 0; x < this.model.getWidth(); x++) {
        double a = pixels[y][x].getAlpha();
        int r = (int) (pixels[y][x].getRed() * a / 255);
        int g = (int) (pixels[y][x].getGreen() * a / 255);
        int b = (int) (pixels[y][x].getBlue() * a / 255);
        Color c = new Color(r, g, b);
        img.setRGB(x, y, c.getRGB());
      }
    }
    return img;
  }

}
