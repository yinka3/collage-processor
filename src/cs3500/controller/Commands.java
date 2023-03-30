package cs3500.controller;

public interface Commands {

  void createProject(int height, int width);

  void addLayer(String name);

  void addImageToLayer(String layerName, String imageName, int y, int x);

  void saveImage(String nameOfImage);

  void saveProject(String nameOfProject);

  void setFilter(String name, String filter);

  void loadProject(String filename);

}
