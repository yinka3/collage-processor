package cs3500.controller;

import java.util.Scanner;
import cs3500.filter.BlendingBrighten;
import cs3500.filter.BlendingDarken;
import cs3500.filter.BlendingDifference;
import cs3500.filter.IBlending;
import cs3500.filter.IFilter;
import cs3500.model.ICollage;
import cs3500.view.CollageView;
import static cs3500.controller.ControllerGUI.getIFilterName;

/**
 * This class handles the commands from the model to allow the user to type in those
 * commands in the terminal without having to actually worry about what is in the model.
 * It implements the IController interface and Commands interface.
 */
public class Controller implements IController {


  private final ICollage model;

  private final Readable readable;

  private final CollageView view;

  /**
   * This is the constructor to initialize a controller made fot text input in the terminal.
   * @param model the model to be connected to the controller.
   * @param readable an object used for reading in inputs.
   * @param view has a visual ability for the user to see anything in the terminal.
   */
  public Controller(ICollage model, Readable readable, CollageView view) {
    if ((model == null) || (view == null) || (readable == null)) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.model = model;
    this.readable = readable;
    this.view = view;
  }

  /**
   * Allows a given string to dictate which Filter class should be called.
   * @param filterOption string representing the desired filer option
   * @return IFilter class that applies filter to image
   */
  private IFilter filterChoice(String filterOption) {
    return getIFilterName(filterOption);
  }


  /**
   * Allows a given string to dictate which blending class should be called.
   * @param blenderOption string representing the desired blending option.
   * @return IBlending class that applies blending to image
   */
  private IBlending getIBlenderName(String blenderOption) {
    return getiBlending(blenderOption);
  }

  /**
   * This method delegated to different blending classes depending of the blendingOption.
   * @param blenderOption that represented the wanted blending type to be done to image.
   * @return Corresponding IBlending class that allows the wanted blending filer to be applied.
   */
  static IBlending getiBlending(String blenderOption) {
    switch (blenderOption) {
      case "BlendingBrighten":
        return new BlendingBrighten();
      case "BlendingDarken":
        return new BlendingDarken();
      case "BlendingDifference":
        return new BlendingDifference();
      default:
        return null;
    }
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
            this.model.createProject(height, width);
            view.renderMessage("Done\n");
            break;
          case "add-layer":
            String nameOfLayer = s.next();
            this.model.addLayer(nameOfLayer);
            view.renderMessage("Done\n");
            break;
          case "load-image":
            String nameLayer = s.next();
            String imgName = s.next();
            int yOffset = s.nextInt();
            int xOffset = s.nextInt();
            this.model.addImageToLayer(nameLayer, imgName, yOffset, xOffset);
            view.renderMessage("Done\n");
            break;
          case "set-filter":
            String name = s.next();
            String filter = s.next();
            this.model.setFilter(name, this.filterChoice(filter));
            view.renderMessage("Done\n");
            break;
          case "set-blend":
            String currLayer = s.next();
            String blendChoice = s.next();
            this.model.setBlend(currLayer, this.getIBlenderName(blendChoice));
            view.renderMessage("Done\n");
            break;
          case "save-image":
            String nameOfImage = s.next();
            this.model.saveImage(nameOfImage);
            view.renderMessage("Done\n");
            break;
          case "save-project":
            String nameOfProject = s.next();
            this.model.saveProject(nameOfProject);
            view.renderMessage("Done\n");
            break;
          case "load-project":
            String project = s.next();
            this.model.loadProject(project);
            view.renderMessage("Done\n");
            break;
          default:
            view.renderMessage("Error: Unknown command");
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

}
