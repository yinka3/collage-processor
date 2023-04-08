package cs3500.imageutil;

/**
 * The HSL class is a representation of another form of pixels that can be used,
 * it has a hue, saturation, and light. This can have modifications to the light
 * visibility of photos. They are all double because components tends to be between 0 and 1.
 */
public class HSL {

  private final double hue;
  private final double saturation;
  private final double light;

  /**
   * This is the main constructor of the class that represents an HSL component.
   * @param hue is the actual color being displayed.
   * @param saturation is the intensity of the color being displayed.
   * @param light is the amount of light being displayed in the color.
   */
  public HSL(double hue, double saturation, double light) {
    this.hue = clampHue(hue);
    this.saturation = clampSaturation(saturation);
    this.light = clampLight(light);
  }

  /**
   * Constructs a new HSL with an RGBA.
   * @param rgba RGBA being converted into an HSL.
   */
  public HSL(RGBA rgba) {
    this.hue =
            RepresentationConverter.convertRGBToHSL(rgba.getRed(), rgba.getGreen(),
                    rgba.getBlue()).hue;
    this.saturation =
            RepresentationConverter.convertRGBToHSL(rgba.getRed(), rgba.getGreen(),
                    rgba.getBlue()).saturation;
    this.light =
            RepresentationConverter.convertRGBToHSL(rgba.getRed(), rgba.getGreen(),
                    rgba.getBlue()).light;


  }

  /**
   * This is a getter method for the hue field.
   * @return the hue field of the class.
   */
  public double getHue() {
    return this.hue;
  }

  /**
   * This is a getter method for the saturation field.
   * @return the saturation field of the class.
   */
  public double getSaturation() {
    return this.saturation;
  }

  /**
   * This is a getter method for the light field.
   * @return the light field of the class.
   */
  public double getLight() {
    return this.light;
  }

  /**
   * This is a clamp method to make sure the hue value stays between 0 and 360.
   * @param hue the double hue that will be clamped.
   * @return a clamped hue component.
   */
  private double clampHue(double hue) {
    if (hue < 0) {
      return 0;
    }
    if (hue >= 360) {
      return 360;
    }
    return hue;
  }

  /**
   * This is a clamp method to make sure the saturation value stays between 0 and 1.
   * @param saturation the double saturation that will be clamped.
   * @return a clamped saturation component.
   */
  private double clampSaturation(double saturation) {
    if (saturation < 0) {
      return 0;
    }
    if (saturation > 1) {
      return 1;
    }
    return saturation;
  }

  /**
   * This is a clamp method to make sure the light value stays between 0 and 1.
   * @param light is the double light that will be clamped.
   * @return a clamped light component.
   */
  private double clampLight(double light) {
    if (light < 0) {
      return 0;
    }
    if (light > 1) {
      return 1;
    }
    return light;
  }
}
