import org.junit.Test;
import cs3500.filter.BlendingBrighten;
import cs3500.filter.BlendingDarken;
import cs3500.filter.BlendingDifference;
import cs3500.filter.IBlending;
import cs3500.imageutil.HSL;
import cs3500.imageutil.IHSL;
import cs3500.imageutil.IRGBA;
import cs3500.imageutil.RGBA;

import static org.junit.Assert.assertEquals;

/**
 * Testing blend filters.
 */
public class TestBlend {
  IHSL testHSL;
  IRGBA test1;
  IRGBA test2;
  IRGBA test3;
  IRGBA test4;
  int value1;
  int value2;
  int value3;
  int value4;

  IRGBA filtered1;
  IRGBA filtered2;
  IRGBA filtered3;
  IRGBA filtered4;
  IRGBA expected1;
  IRGBA expected2;
  IRGBA expected3;
  IRGBA expected4;

  IBlending blend;



  @Test
  public void testBlendingBright() {
    testHSL = new HSL(200, .3, .9);
    test1 = new RGBA(100, 100, 100, 1.0);
    test2 = new RGBA(10, 20, 30);
    test3 = new RGBA(30, 40, 50);
    test4 = new RGBA(testHSL);

    blend = new BlendingBrighten();
    assertEquals("BlendingBrighten", blend.toString());


    expected1 = new RGBA(112, 112, 112);
    expected2 = new RGBA(227, 233, 238);

    assertEquals(expected1.toString(), blend.apply(test1, test2).toString());
    assertEquals(expected2.toString(), blend.apply(test3, test4).toString());
  }

  @Test
  public void testBlendingDark() {
    testHSL = new HSL(200, .3, .9);
    test1 = new RGBA(100, 100, 100, 1.0);
    test2 = new RGBA(10, 20, 30);
    test3 = new RGBA(30, 40, 50);
    test4 = new RGBA(testHSL);

    blend = new BlendingDarken();
    assertEquals("BlendingDarken", blend.toString());


    expected1 = new RGBA(7,7,7);
    expected2 = new RGBA(26,35,44);

    assertEquals(expected1.toString(), blend.apply(test1, test2).toString());
    assertEquals(expected2.toString(), blend.apply(test3, test4).toString());
  }

  @Test
  public void testBlendingDiff() {
    testHSL = new HSL(200, .3, .9);
    test1 = new RGBA(100, 100, 100, 1.0);
    test2 = new RGBA(10, 20, 30);
    test3 = new RGBA(30, 40, 50);
    test4 = new RGBA(testHSL);

    blend = new BlendingDifference();

    assertEquals("BlendingDifference", blend.toString());

    expected1 = new RGBA(90,80,70);
    expected2 = new RGBA(191,192,187);

    assertEquals(expected1.toString(), blend.apply(test1, test2).toString());
    assertEquals(expected2.toString(), blend.apply(test3, test4).toString());
  }
}
