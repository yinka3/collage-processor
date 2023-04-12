package cs3500.imageutil;

public interface IRGBA {
  String toString();
  IRGBA copy();
  IRGBA transparency(IRGBA bg);
  int intensity(int r, int b, int g);
  double luma(int r, int b, int g);
  int value(int r, int b, int g);
  int getRed();
  int getGreen();
  int getBlue();
  double getAlpha();
}
