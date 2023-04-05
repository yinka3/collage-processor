package cs3500.controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import cs3500.Filter.BlendingBrighten;
import cs3500.Filter.BlendingDarken;
import cs3500.Filter.BlendingDifference;
import cs3500.Filter.BrightenByIntensity;
import cs3500.Filter.BrightenByLuma;
import cs3500.Filter.BrightenByValue;
import cs3500.Filter.DarkenByIntensity;
import cs3500.Filter.DarkenByLuma;
import cs3500.Filter.DarkenByValue;
import cs3500.Filter.FilterBlue;
import cs3500.Filter.FilterGreen;
import cs3500.Filter.FilterRed;
import cs3500.Filter.IBlending;
import cs3500.Filter.IFilter;
import cs3500.ImageUtil.RGBA;
import cs3500.model.ICollage;
import cs3500.view.GUIView;

import static cs3500.controller.Controller.getiBlending;

public class ControllerGUI implements Commands {

  private GUIView guiView;

  private final ICollage model;


  public ControllerGUI(ICollage image, GUIView view) {
    this.guiView = view;
    this.model = image;
  }


  private IFilter filterChoice(String filterOption) {
    return getIFilterName(filterOption);
  }

  private IBlending getIBlenderName(String blenderOption) {
    return getiBlending(blenderOption);
  }


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
