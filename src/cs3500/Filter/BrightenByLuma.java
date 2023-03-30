package cs3500.Filter;

import cs3500.ImageUtil.RGBA;

public class BrightenByLuma implements IFilter {
  @Override
  public RGBA apply(RGBA input) {
    int r = RGBA.clamp((int) (input.getRed() + input.luma(input.getRed(), input.getGreen(), input.getBlue())));
    int g = RGBA.clamp((int) (input.getGreen() + input.luma(input.getRed(), input.getGreen(), input.getBlue())));
    int b = RGBA.clamp((int) (input.getBlue() + input.luma(input.getRed(), input.getGreen(), input.getBlue())));
    return new RGBA(r, g, b, input.getAlpha());
  }

  @Override
  public String toString() {
    return "Filter.BrightenByLuma";
  }
}