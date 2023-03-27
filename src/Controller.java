import java.util.Scanner;

public class Controller implements IController, Commands {


  private final Collage model;

  private final Readable readable;

  private final View view;

  public Controller(Collage model, Readable readable, View view) {
    if ((model == null) || (view == null) || (readable == null)) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.model = model;
    this.readable = readable;
    this.view = view;
  }

  @Override
  public void createProject(int height, int width) {
    model.createProject(height, width);
  }

  @Override
  public void addLayer(String name) {
    model.addLayer(name);
  }

  @Override
  public void addImageToLayer(String layerName, String imageName, int y, int x) {
    model.addImageToLayer(layerName, imageName, y, x);
  }

  @Override
  public void save(String nameOfImage, String path) {
    model.getLayer(nameOfImage);
    model.save(path);
    view.renderMessage(nameOfImage
            + " has been saved to " + path + " as a type "
            + "ppm" + ".\n");
  }

  @Override
  public void setFilter(String nameOf, String filter) {
    model.setFilter(nameOf, filter);
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
          case "add-image-to-layer":
            String nameLayer = s.next();
            String imgName = s.next();
            int offsetY = s.nextInt();
            int offsetX = s.nextInt();
            addImageToLayer(nameLayer, imgName, offsetY, offsetX);
            view.renderMessage("Done\n");
            break;
          case "set-filter":
            String name = s.next();
            String filter = s.next();
            setFilter(name, filter);
            view.renderMessage("Done\n");
            break;
          case "save":
            String nameOfImage = s.next();
            String file = s.next();
            save(nameOfImage, file);
            view.renderMessage("Done\n");
            break;
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
