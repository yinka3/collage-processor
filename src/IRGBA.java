public interface IRGBA {
  int getRed();

  int getGreen();

  int getBlue();

  int getAlpha();

  int setRed(int newValue);

  int setGreen(int newValue);

  int setBlue(int newValue);

  int setAlpha(int newValue);

  int value(int r, int b, int g);

  double luma(int r, int b, int g);

  int intensity(int r, int b, int g);

  int clamp(int value);

  RGBA transparency(RGBA bg);

  RGBA copy();
}
