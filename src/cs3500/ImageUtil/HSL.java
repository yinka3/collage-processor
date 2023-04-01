package cs3500.ImageUtil;

public class HSL {

  double hue;
  double saturation;
  double light;

  public HSL(double hue, double saturation, double light) {
    this.hue = hue;
    this.saturation = saturation;
    this.light = light;
  }

  public double getHue() {
    return this.hue;
  }

  public double getSaturation() {
    return this.saturation;
  }

  public double getLight() {
    return this.light;
  }
}
