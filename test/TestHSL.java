import org.junit.Test;

import cs3500.imageutil.HSL;
import cs3500.imageutil.IHSL;
import cs3500.imageutil.IRGBA;
import cs3500.imageutil.RGBA;

import static org.junit.Assert.assertEquals;

/**
 * Testing HSL class.
 */
public class TestHSL {

  @Test
  public void testConstructorValid1() {
    IHSL hsl1 = new HSL(300, .3, .7);
    assertEquals(300.0, hsl1.getHue(), 0);
    assertEquals(.3, hsl1.getSaturation(), 0);
    assertEquals(.7, hsl1.getLight(), 0);

  }

  @Test
  public void testConstructorWithRGB() {
    IRGBA rgb1 = new RGBA(20, 33, 80);
    IHSL hsl2 = new HSL(rgb1);
    assertEquals(227.0, hsl2.getHue(), 0);
    assertEquals(0.0, hsl2.getSaturation(), 0);
    assertEquals(1.0, hsl2.getLight(), 0);

  }
}
