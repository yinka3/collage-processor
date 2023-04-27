package cs3500.controller;

/**
 * The interface Commands defines the user commands possible for a collage. This will be used a
 * bridge between the accessing abilities from the model and it being used in the controller portion
 * of the program.
 */
public interface Commands {

  /**
   * Creates new empty collage project where the dimensions are the user desired height and width.
   *
   * @param height desired height of the project.
   * @param width  desired width of the project.
   */
  void createProject(String height, String width);

  /**
   * Adds a new layer to a collage. A Layer is a group of images that are collectively affected
   * by filters.
   * @param name the name of the new layer.
   */
  void addLayer(String name);

  /**
   * Adds an image to a layer of a collage.
   * @param layerName name of the layer being added to.
   * @param imageName name of image being added.
   * @param y y position of the image on a layer.
   * @param x x position of the image on a layer.
   */
  void addImageToLayer(String layerName, String imageName, String y, String x);

  /**
   * Saves a collage as an image.
   * @param nameOfImage the name the collage will be saved as.
   */
  void saveImage(String nameOfImage);

  /**
   * Saves a collage as a project. When a collage is saved like this, it can continue to be edited.
   * @param nameOfProject the name the collage will be saved as.
   */
  void saveProject(String nameOfProject);

  /**
   * Sets a filter to a layer, applying to all the images in the layer.
   * @param name name of the layer being filtered.
   * @param filter filter being applied to the layer.
   */
  void setFilter(String name, String filter);

  /**
   * Sets a blend filter to a collage.
   * @param currentLayer most recent layer of the project.
   * @param blend the blend being applied.
   */
  void setBlend(String currentLayer,  String blend);

  /**
   * Loads a project to a certain filename.
   * @param filename filename being loaded to.
   */
  void loadProject(String filename);

}
