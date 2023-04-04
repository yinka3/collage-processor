package cs3500.controller;

public interface Commands {

  void createProject(String height, String width);

  void addLayer(String name);

  void addImageToLayer(String layerName, String imageName, String y, String x);

  void saveImage(String nameOfImage);

  void saveProject(String nameOfProject);

  void setFilter(String name, String filter);

  void loadProject(String filename);

}
