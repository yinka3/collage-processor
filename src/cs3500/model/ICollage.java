package cs3500.model;

import cs3500.Filter.IFilter;
import cs3500.ImageUtil.RGBA;

public interface ICollage {

  int getHeight();

  int getWidth();

  RGBA[][] finalPixel();

  void savePPMImage(String filepath);

 void saveProject(String filepath);

  void loadProject(String filepath);


  void createProject(int height, int width);

  void addLayer(String layer);

  void addImageToLayer(String LayerName, String imgName, int yOffset, int xOffset);

  void setFilter(String currentLayer, IFilter filterName);

}
