package cs3500.model;

import java.util.Map;

import cs3500.filter.IBlending;
import cs3500.filter.IFilter;
import cs3500.imageutil.ILayer;
import cs3500.imageutil.IRGBA;



/**
 * This interface defines the functionality of a collage. A collage is a collection of layers
 * of images that can be modified with new images and filters.
 */
public interface ICollage {

  /**
   * Returns the height of a collage.
   *
   * @return the height of a collage as an int.
   */
  int getHeight();

  /**
   * Returns the width of a collage.
   *
   * @return the width of a collage as an int.
   */
  int getWidth();

  /**
   * Returns the final RGBAs that make up a layer with commands applied.
   *
   * @return a 2d array of RGBAs that make up a composite image of all the layers.
   */
  IRGBA[][] finalPixel();

  /**
   * Saves a PPM image to a file system.
   *
   * @param filepath the filepath the PPM image will be saved to.
   */
  void saveImage(String filepath);

  /**
   * Saves a project to a given filepath.
   *
   * @param filepath the filepath the project will be saved to.
   */
  void saveProject(String filepath);

  /**
   * Loads a project to a collage processor.
   *
   * @param filepath filepath the project is being retrieved from.
   */
  void loadProject(String filepath);

  /**
   * Sets a blend filter to all the layers below from a given layer name.
   *
   * @param layerName name of the layer the blend is applied to.
   * @param blend     the blend being applied.
   */
  void setBlend(String layerName, IBlending blend);

  /**
   * Creates a new project by initializing its dimensions.
   *
   * @param height height of project.
   * @param width  width of project.
   */
  void createProject(int height, int width);

  /**
   * This method adds a new empty layer to the collage.
   *
   * @param layer is the String name of the layer.
   */
  void addLayer(String layer);

  /**
   * Adds an image to a layer at specified offset cartesian coordinates in the layer.
   *
   * @param layerName the layer being added to.
   * @param imgName   image being added.
   * @param yOffset   y placement of the image onto the layer.
   * @param xOffset   x placement of the image onto the layer.
   */
  void addImageToLayer(String layerName, String imgName, int yOffset, int xOffset);

  /**
   * Sets a filter option to the given layer based on the layer name.
   *
   * @param currentLayer the layer the filter is being added to.
   * @param filterName   the filter being added.
   */
  void setFilter(String currentLayer, IFilter filterName);

  /**
   * Returns a map of strings to layers of the layers in the project.
   *
   * @return a string key to layer value hashmap.
   */
  Map<String, ILayer> getKnownImages();
}
