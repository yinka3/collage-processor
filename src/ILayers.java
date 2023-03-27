public interface ILayers {

  RGBA getPixelAt(int row, int col);

  void addImage(String newName, RGBA[][] image);
}
