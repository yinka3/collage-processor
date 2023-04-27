package cs3500.filter;

import cs3500.imageutil.IHSL;
import cs3500.imageutil.IRGBA;
import cs3500.imageutil.RepresentationConverter;

/**
 * This filter is used to take the layers underneath a certain layer and have a composite image to
 * apply this filter to. The effect gives a better darken effect to the layers to see.
 * It implements the IBlending interface that gives the main method of applying this to two
 * different rgba components.
 */
public class BlendingDarken implements IBlending {
  @Override
  public IRGBA apply(IRGBA rgba1, IRGBA rgba2) {
    double r = rgba1.getRed() / 255.0;
    double g = rgba1.getGreen() / 255.0;
    double b = rgba1.getBlue() / 255.0;
    double r1 = rgba2.getRed() / 255.0;
    double g1 = rgba2.getGreen() / 255.0;
    double b1 = rgba2.getBlue() / 255.0;
    IHSL hsl1 = RepresentationConverter.convertRGBToHSL(r, g, b);
    IHSL hsl2 = RepresentationConverter.convertRGBToHSL(r1, g1, b1);
    double newLight = hsl1.getLight() * hsl2.getLight();
    return RepresentationConverter.convertHSLToRGB(hsl1.getHue(), hsl1.getSaturation(), newLight,
            rgba1.getAlpha());
  }

  @Override
  public String toString() {
    return "BlendingDarken";
  }

}
