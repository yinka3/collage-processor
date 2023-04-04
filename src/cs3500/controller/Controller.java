package cs3500.controller;

import java.util.Scanner;
import cs3500.Filter.IFilter;
import cs3500.model.ICollage;
import cs3500.view.View;

import static cs3500.controller.ControllerGUI.getIFilterName;

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
    return getIFilterName(filterOption);
  }


  @Override
  public void createProject(String height, String width) {
    try {
      this.model.createProject(Integer.parseInt(height), Integer.parseInt(width));
    } catch (NumberFormatException e) {
      //do nothing
    }
  }

  @Override
  public void addLayer(String name) {
    this.model.addLayer(name);
  }

  @Override
  public void addImageToLayer(String layerName, String imageName, String y, String x) {
    try {
      this.model.addImageToLayer(layerName, imageName, Integer.parseInt(y), Integer.parseInt(x));
    } catch (NumberFormatException e) {
      //do nothing
    }
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
            String width = s.next();
            String height = s.next();
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
            String yOffset = s.next();
            String xOffset = s.next();
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
