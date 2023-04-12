package cs3500.filter;

import cs3500.imageutil.IRGBA;
import cs3500.imageutil.RGBA;

/**
 * Brightens a single RGBA pixel by its value.
 * Implements the iFilter interface.
 */
public class BrightenByValue implements IFilter {
  @Override
  public IRGBA apply(IRGBA input) {
    int r = RGBA.clamp(input.getRed() + input.value(input.getRed(), input.getGreen(),
            input.getBlue()));
    int g = RGBA.clamp(input.getGreen() + input.value(input.getRed(), input.getGreen(),
            input.getBlue()));
    int b = RGBA.clamp(input.getBlue() + input.value(input.getRed(), input.getGreen(),
            input.getBlue()));
    return new RGBA(r, g, b, input.getAlpha());
  }

  @Override
  public String toString() {
    return "Filter.BrightenByValue";
  }
}
