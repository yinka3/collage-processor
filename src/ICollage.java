public interface ICollage {

  int getWidth();

  int getHeight();

  void addImage(String newName, Layer image);

  String removeExtension(String filePath);

  void load(String filepath);

  RGBA[][] readPPM(String filename);

  void createProject(int height, int width);

  Layer getLayer(String oldName);

  void addLayer(String layer);

  void addImageToLayer(String LayerName, String imgName, int yOffset, int xOffset);


}
