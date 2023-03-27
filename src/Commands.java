public interface Commands {

  void createProject(int height, int width);

  void addLayer(String name);

  void addImageToLayer(String layerName, String imageName, int x, int y);

  //void filterRed(String name, String newName);

  void save(String nameOfImage, String path);

  void setFilter(String name, String filter);

 // void load(String address);


}
