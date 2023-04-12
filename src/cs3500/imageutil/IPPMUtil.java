package cs3500.imageutil;

public interface IPPMUtil {


  int getHeight();

  int getWidth();

  void readPPM(String filename);

  IRGBA getPixelAt(int y, int x);

  void savePPM(String filepath);
}
