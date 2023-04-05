package cs3500.Filter;

import cs3500.ImageUtil.*;

public class BlendingBrighten implements IBlending {

  @Override
  public RGBA apply(RGBA rgba1, RGBA rgba2) {
    double r = rgba1.getRed() / 255.0;
    double g = rgba1.getGreen() / 255.0;
    double b = rgba1.getBlue() / 255.0;
    double r1 = rgba2.getRed() / 255.0;
    double g1 = rgba2.getGreen() / 255.0;
    double b1 = rgba2.getBlue() / 255.0;
    HSL HSL1 = RepresentationConverter.convertRGBtoHSL(r, g, b);
    HSL HSL2 = RepresentationConverter.convertRGBtoHSL(r1, g1, b1);
    double newLight = (1 - ((1 - HSL1.getLight()) * (1 - HSL2.getLight())));
    return RepresentationConverter.convertHSLtoRGB(HSL1.getHue(), HSL1.getSaturation(), newLight, rgba1.getAlpha());
  }

  @Override
  public String toString() {
    return "BlendingBrighten";
  }
}
