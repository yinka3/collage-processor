package cs3500.filter;

import cs3500.imageutil.IRGBA;
import cs3500.imageutil.RGBA;

/**
 * Darkens a single RGBA pixel by its luma value.
 * Implements the IFilter interface.
 */
public class DarkenByLuma implements IFilter {
  @Override
  public IRGBA apply(IRGBA input) {
    int r = RGBA.clamp((int) (input.getRed() - input.luma(input.getRed(), input.getGreen(),
            input.getBlue())));
    int g = RGBA.clamp((int) (input.getGreen() - input.luma(input.getRed(), input.getGreen(),
            input.getBlue())));
    int b = RGBA.clamp((int) (input.getBlue() - input.luma(input.getRed(), input.getGreen(),
            input.getBlue())));
    return new RGBA(r, g, b, input.getAlpha());
  }

  @Override
  public String toString() {
    return "Filter.DarkenByLuma";
  }
}
