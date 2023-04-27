package cs3500.imageutil;


/**
 * The HSL interface that represent another form of pixels that can be used in terms of hue (color
 * being displayed) saturation (intensity of the displayed color), and light (amount of light in
 * the displayed color). The interface also shows the different getters of the HSL.
 */
public interface IHSL {
  /**
   * Gets the saturation of the HSL.
   * @return the saturation field of the HSL as double
   */
  double getSaturation();

  /**
   * Gets the hue of the HSL.
   * @return the hue field of the HSL as double
   */
  double getHue();

  /**
   * Gets the light of the HSL.
   * @return the hue field of the light as double
   */
  double getLight();
}
