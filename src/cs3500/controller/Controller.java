package cs3500.controller;

import java.util.Scanner;

import cs3500.Filter.BrightenByIntensity;
import cs3500.Filter.BrightenByLuma;
import cs3500.Filter.BrightenByValue;
import cs3500.Filter.DarkenByIntensity;
import cs3500.Filter.DarkenByLuma;
import cs3500.Filter.DarkenByValue;
import cs3500.Filter.FilterBlue;
import cs3500.Filter.FilterGreen;
import cs3500.Filter.FilterRed;
import cs3500.Filter.IFilter;
import cs3500.controller.Commands;
import cs3500.model.ICollage;
import cs3500.view.View;

public class Controller implements IController, Commands {


  private final ICollage model;

  private final Readable readable;

  private final View view;

  public Controller(ICollage model, Readable readable, View view) {
    if ((model == null) || (view == null) || (readable == null)) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.model = model;
    this.readable = readable;
    this.view = view;
  }


  private IFilter filterChoice(String filterOption) {
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


  @Override
  public void createProject(int height, int width) {
    this.model.createProject(height, width);
  }

  @Override
  public void addLayer(String name) {
    this.model.addLayer(name);
  }

  @Override
  public void addImageToLayer(String layerName, String imageName, int y, int x) {
    this.model.addImageToLayer(layerName, imageName, y, x);
  }

  @Override
  public void saveImage(String nameOfImage) {
    this.model.savePPMImage(nameOfImage);
  }

  @Override
  public void saveProject(String nameOfProject) {
    this.model.saveProject(nameOfProject);
  }

  @Override
  public void setFilter(String name, String filter) {
    this.model.setFilter(name, this.filterChoice(filter));
  }

  @Override
  public void loadProject(String filename) {
    this.model.loadProject(filename);
  }

  @Override
  public void apply() {
    Scanner s = new Scanner(readable);
    while (s.hasNext()) {
      String in = s.next();
      try {
        switch (in) {
          case "q":
          case "quit":
            view.renderMessage("program has been quit.");
            System.exit(0);
            return;
          case "new-project":
            int width = s.nextInt();
            int height = s.nextInt();
            createProject(height, width);
            view.renderMessage("Done\n");
            break;
          case "add-layer":
            String nameOfLayer = s.next();
            addLayer(nameOfLayer);
            view.renderMessage("Done\n");
            break;
          case "load-image":
            String nameLayer = s.next();
            String imgName = s.next();
            int yOffset = s.nextInt();
            int xOffset = s.nextInt();
            addImageToLayer(nameLayer, imgName, yOffset, xOffset);
            view.renderMessage("Done\n");
            break;
          case "set-filter":
            String name = s.next();
            String filter = s.next();
            setFilter(name, filter);
            view.renderMessage("Done\n");
            break;
          case "save-image":
            String nameOfImage = s.next();
            saveImage(nameOfImage);
            view.renderMessage("Done\n");
            break;
          case "save-project":
            String nameOfProject = s.next();
            saveProject(nameOfProject);
            view.renderMessage("Done\n");
            break;
          case "load-project":
            String project = s.next();
            loadProject(project);
            view.renderMessage("Done\n");
            break;
        }
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
    }
  }

}
