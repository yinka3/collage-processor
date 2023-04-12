import org.junit.Before;
import org.junit.Test;

import cs3500.filter.BlendingBrighten;
import cs3500.filter.BlendingDarken;
import cs3500.filter.BlendingDifference;
import cs3500.filter.BrightenByIntensity;
import cs3500.filter.BrightenByLuma;
import cs3500.filter.BrightenByValue;
import cs3500.filter.DarkenByIntensity;
import cs3500.filter.DarkenByLuma;
import cs3500.filter.DarkenByValue;
import cs3500.filter.FilterBlue;
import cs3500.filter.FilterGreen;
import cs3500.filter.FilterRed;
import cs3500.filter.IBlending;
import cs3500.filter.IFilter;
import cs3500.imageutil.HSL;
import cs3500.imageutil.IHSL;
import cs3500.imageutil.IRGBA;
import cs3500.imageutil.RGBA;

import static org.junit.Assert.assertEquals;

/**
 * Testing filters.
 */
public class TestFilters {
  IFilter testFilter;
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


  @Before
  public void init() {
    testHSL = new HSL(200, .3, .9);
    test1 = new RGBA(100, 100, 100, 1.0);
    test2 = new RGBA(10, 20, 30);
    test3 = new RGBA(30, 40, 50);
    test4 = new RGBA(testHSL);
    //221 232 237

    value1 = test1.value(100, 100, 100); // 100
    value2 = test1.value(10, 20, 30); // 30
    value3 = test1.value(30, 40, 50); // 50
    value4 = test1.value(221, 232, 237); // 237


  }

  @Test
  public void testBrightValue() {
    testHSL = new HSL(200, .3, .9);
    test1 = new RGBA(100, 100, 100, 1.0);
    test2 = new RGBA(10, 20, 30);
    test3 = new RGBA(30, 40, 50);
    test4 = new RGBA(testHSL);

    testFilter = new BrightenByValue();
    filtered1 = testFilter.apply(test1);
    filtered2 = testFilter.apply(test2);
    filtered3 = testFilter.apply(test3);
    filtered4 = testFilter.apply(test4);

    expected1 = new RGBA(200, 200, 200);
    expected2 = new RGBA(40, 50, 60);
    expected3 = new RGBA(80, 90, 100);
    expected4 = new RGBA(255, 255, 255);

    assertEquals(filtered1.toString(), expected1.toString());
    assertEquals(filtered2.toString(), expected2.toString());
    assertEquals(filtered3.toString(), expected3.toString());
    assertEquals(filtered4.toString(), expected4.toString());

  }

  @Test
  public void testBrightLuma() {
    testHSL = new HSL(200, .3, .9);
    test1 = new RGBA(100, 100, 100, 1.0);
    test2 = new RGBA(10, 20, 30);
    test3 = new RGBA(30, 40, 50);
    test4 = new RGBA(testHSL);

    testFilter = new BrightenByLuma();
    filtered1 = testFilter.apply(test1);
    filtered2 = testFilter.apply(test2);
    filtered3 = testFilter.apply(test3);
    filtered4 = testFilter.apply(test4);

    expected1 = new RGBA(200, 200, 200);
    expected2 = new RGBA(35, 45, 55);
    expected3 = new RGBA(75, 85, 95);
    expected4 = new RGBA(255, 255, 255);

    assertEquals(filtered1.toString(), expected1.toString());
    assertEquals(filtered2.toString(), expected2.toString());
    assertEquals(filtered3.toString(), expected3.toString());
    assertEquals(filtered4.toString(), expected4.toString());
  }

  @Test
  public void testBrightIntensity() {
    testHSL = new HSL(200, .3, .9);
    test1 = new RGBA(100, 100, 100, 1.0);
    test2 = new RGBA(10, 20, 30);
    test3 = new RGBA(30, 40, 50);
    test4 = new RGBA(testHSL);

    testFilter = new BrightenByIntensity();
    filtered1 = testFilter.apply(test1);
    filtered2 = testFilter.apply(test2);
    filtered3 = testFilter.apply(test3);
    filtered4 = testFilter.apply(test4);

    expected1 = new RGBA(200, 200, 200);
    expected2 = new RGBA(30, 40, 50);
    expected3 = new RGBA(70, 80, 90);
    expected4 = new RGBA(255, 255, 255);

    assertEquals(filtered1.toString(), expected1.toString());
    assertEquals(filtered2.toString(), expected2.toString());
    assertEquals(filtered3.toString(), expected3.toString());
    assertEquals(filtered4.toString(), expected4.toString());
  }

  @Test
  public void testDarkValue() {
    testHSL = new HSL(200, .3, .9);
    test1 = new RGBA(100, 100, 100, 1.0);
    test2 = new RGBA(10, 20, 30);
    test3 = new RGBA(30, 40, 50);
    test4 = new RGBA(testHSL);

    testFilter = new DarkenByValue();
    filtered1 = testFilter.apply(test1);
    filtered2 = testFilter.apply(test2);
    filtered3 = testFilter.apply(test3);
    filtered4 = testFilter.apply(test4);

    expected1 = new RGBA(0,0,0);
    expected2 = new RGBA(0,0,0);
    expected3 = new RGBA(0,0,0);
    expected4 = new RGBA(0,0,0);

    assertEquals(filtered1.toString(), expected1.toString());
    assertEquals(filtered2.toString(), expected2.toString());
    assertEquals(filtered3.toString(), expected3.toString());
    assertEquals(filtered4.toString(), expected4.toString());
  }

  @Test
  public void testDarkLuma() {
    testHSL = new HSL(200, .3, .9);
    test1 = new RGBA(100, 100, 100, 1.0);
    test2 = new RGBA(10, 20, 30);
    test3 = new RGBA(30, 40, 50);
    test4 = new RGBA(testHSL);

    testFilter = new DarkenByLuma();
    filtered1 = testFilter.apply(test1);
    filtered2 = testFilter.apply(test2);
    filtered3 = testFilter.apply(test3);
    filtered4 = testFilter.apply(test4);

    expected1 = new RGBA(0,0,0);
    expected2 = new RGBA(0,0,4);
    expected3 = new RGBA(0,0,4);
    expected4 = new RGBA(0,0,3);

    assertEquals(filtered1.toString(), expected1.toString());
    assertEquals(filtered2.toString(), expected2.toString());
    assertEquals(filtered3.toString(), expected3.toString());
    assertEquals(filtered4.toString(), expected4.toString());
  }

  @Test
  public void testDarkIntensity() {
    testHSL = new HSL(200, .3, .9);
    test1 = new RGBA(100, 100, 100, 1.0);
    test2 = new RGBA(10, 20, 30);
    test3 = new RGBA(30, 40, 50);
    test4 = new RGBA(testHSL);

    testFilter = new DarkenByIntensity();
    filtered1 = testFilter.apply(test1);
    filtered2 = testFilter.apply(test2);
    filtered3 = testFilter.apply(test3);
    filtered4 = testFilter.apply(test4);

    expected1 = new RGBA(0,0,0);
    expected2 = new RGBA(0,0,10);
    expected3 = new RGBA(0,0,10);
    expected4 = new RGBA(0,2,7);

    assertEquals(filtered1.toString(), expected1.toString());
    assertEquals(filtered2.toString(), expected2.toString());
    assertEquals(filtered3.toString(), expected3.toString());
    assertEquals(filtered4.toString(), expected4.toString());

  }

  @Test
  public void testRedFilter() {
    testHSL = new HSL(200, .3, .9);
    test1 = new RGBA(100, 100, 100, 1.0);
    test2 = new RGBA(10, 20, 30);
    test3 = new RGBA(30, 40, 50);
    test4 = new RGBA(testHSL);

    testFilter = new FilterRed();
    filtered1 = testFilter.apply(test1);
    filtered2 = testFilter.apply(test2);
    filtered3 = testFilter.apply(test3);
    filtered4 = testFilter.apply(test4);

    expected1 = new RGBA(100, 0, 0);
    expected2 = new RGBA(10, 0, 0);
    expected3 = new RGBA(30, 0, 0);
    expected4 = new RGBA(221, 0, 0);

    assertEquals(filtered1.toString(), expected1.toString());
    assertEquals(filtered2.toString(), expected2.toString());
    assertEquals(filtered3.toString(), expected3.toString());
    assertEquals(filtered4.toString(), expected4.toString());
  }

  @Test
  public void testBlueFilter() {
    testHSL = new HSL(200, .3, .9);
    test1 = new RGBA(100, 100, 100, 1.0);
    test2 = new RGBA(10, 20, 30);
    test3 = new RGBA(30, 40, 50);
    test4 = new RGBA(testHSL);

    testFilter = new FilterBlue();
    filtered1 = testFilter.apply(test1);
    filtered2 = testFilter.apply(test2);
    filtered3 = testFilter.apply(test3);
    filtered4 = testFilter.apply(test4);

    expected1 = new RGBA(0,0,100);
    expected2 = new RGBA(0,0,30);
    expected3 = new RGBA(0,0,50);
    expected4 = new RGBA(0,0,237);

    assertEquals(filtered1.toString(), expected1.toString());
    assertEquals(filtered2.toString(), expected2.toString());
    assertEquals(filtered3.toString(), expected3.toString());
    assertEquals(filtered4.toString(), expected4.toString());
  }

  @Test
  public void testGreenFilter() {
    testHSL = new HSL(200, .3, .9);
    test1 = new RGBA(100, 100, 100, 1.0);
    test2 = new RGBA(10, 20, 30);
    test3 = new RGBA(30, 40, 50);
    test4 = new RGBA(testHSL);

    testFilter = new FilterGreen();
    filtered1 = testFilter.apply(test1);
    filtered2 = testFilter.apply(test2);
    filtered3 = testFilter.apply(test3);
    filtered4 = testFilter.apply(test4);

    expected1 = new RGBA(0,100,0);
    expected2 = new RGBA(0,20,0);
    expected3 = new RGBA(0,40,0);
    expected4 = new RGBA(0,232,0);

    assertEquals(filtered1.toString(), expected1.toString());
    assertEquals(filtered2.toString(), expected2.toString());
    assertEquals(filtered3.toString(), expected3.toString());
    assertEquals(filtered4.toString(), expected4.toString());
  }

  @Test
  public void testBlendingBright() {
    testHSL = new HSL(200, .3, .9);
    test1 = new RGBA(100, 100, 100, 1.0);
    test2 = new RGBA(10, 20, 30);
    test3 = new RGBA(30, 40, 50);
    test4 = new RGBA(testHSL);

    blend = new BlendingBrighten();

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

    expected1 = new RGBA(90,80,70);
    expected2 = new RGBA(191,192,187);

    assertEquals(expected1.toString(), blend.apply(test1, test2).toString());
    assertEquals(expected2.toString(), blend.apply(test3, test4).toString());
  }
}
