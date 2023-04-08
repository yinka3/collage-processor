package cs3500.filter;

import cs3500.imageutil.HSL;
import cs3500.imageutil.RGBA;
import cs3500.imageutil.RepresentationConverter;

/**
 * This filter is used to take the layers underneath a certain layer and have a composite image to
 * apply this filter to. The effect gives a better brighten effect to the layers to see.
 * It implements the IBlending interface that gives the main method of applying this to two
 * different rgba components.
 */
public class BlendingBrighten implements IBlending {


  @Override
  public RGBA apply(RGBA rgba1, RGBA rgba2) {
    double rb = rgba1.getRed() / 255.0;
    double gb = rgba1.getGreen() / 255.0;
    double bb = rgba1.getBlue() / 255.0;
    double r1 = rgba2.getRed() / 255.0;
    double g1 = rgba2.getGreen() / 255.0;
    double b1 = rgba2.getBlue() / 255.0;
    HSL hsl1 = RepresentationConverter.convertRGBToHSL(rb, gb, bb);
    HSL hsl2 = RepresentationConverter.convertRGBToHSL(r1, g1, b1);
    double newLight = (1 - ((1 - hsl1.getLight()) * (1 - hsl2.getLight())));
    return RepresentationConverter.convertHSLToRGB(hsl1.getHue(), hsl1.getSaturation(),
            newLight, rgba1.getAlpha());
  }

  @Override
  public String toString() {
    return "BlendingBrighten";
  }
}
