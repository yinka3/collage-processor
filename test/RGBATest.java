import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.imageutil.RGBA;

/**
 * Testing for RGBA class.
 */
public class RGBATest {

  RGBA rgba1;
  RGBA rgba2;
  RGBA rgba3;
  RGBA rgba4;

  @Before
  public void init() {
    rgba1 = new RGBA(200, 150, 100);
  }

  @Test
  public void testValidConstructor1() {
    rgba2 = new RGBA(100, 123, 122);
    Assert.assertEquals(100, rgba2.getRed());
    Assert.assertEquals(123, rgba2.getGreen());
    Assert.assertEquals(122, rgba2.getBlue());

  }

  @Test
  public void testValidConstructor2() {
    rgba2 = new RGBA(100, 123, 122, 12);
    Assert.assertEquals(100, rgba2.getRed());
    Assert.assertEquals(123, rgba2.getGreen());
    Assert.assertEquals(122, rgba2.getBlue());
    Assert.assertEquals(12, rgba2.getAlpha());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor1() {
    rgba2 = new RGBA(300, 123, 122);
    rgba3 = new RGBA(100, 344, 122);
    rgba4 = new RGBA(100, 123, 256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor1Negative() {
    rgba2 = new RGBA(-100, 123, 122);
    rgba3 = new RGBA(100, -133, 122);
    rgba4 = new RGBA(100, 123, -90);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor2() {
    rgba2 = new RGBA(100, 123, 122, 300);
    rgba3 = new RGBA(100, 123, 122, -100);
  }

  @Test
  public void testSetAlpha() {
    Assert.assertEquals(20, rgba1.getAlpha());
  }

  @Test
  public void testClamp() {
    Assert.assertEquals(0, rgba1.clamp(-100));
    Assert.assertEquals(0, rgba1.clamp(-10));
    Assert.assertEquals(12, rgba1.clamp(12));
    Assert.assertEquals(20, rgba1.clamp(20));
    Assert.assertEquals(255, rgba1.clamp(300));
    Assert.assertEquals(255, rgba1.clamp(256));
    Assert.assertEquals(255, rgba1.clamp(255));
  }

  @Test
  public void testCopy() {
    RGBA rgba2Copy;
    RGBA rgba3Copy;
    RGBA rgba4Copy;

    rgba2 = new RGBA(102, 123, 126);
    rgba3 = new RGBA(0, 0, 0);
    rgba4 = new RGBA(27, 33, 99, 12);

    rgba2Copy = rgba2.copy();
    rgba3Copy = rgba3.copy();
    rgba4Copy = rgba4.copy();

    Assert.assertEquals(rgba2Copy, rgba2);
    Assert.assertEquals(rgba3Copy, rgba3);
    Assert.assertEquals(rgba4Copy, rgba4);
  }

  @Test
  public void testToString() {
    rgba2 = new RGBA(102, 123, 126, 4);
    rgba3 = new RGBA(0, 0, 0, 0);
    rgba4 = new RGBA(27, 33, 99, 12);

    Assert.assertEquals("[102, 123, 126, 4]", rgba2.toString());
    Assert.assertEquals("[0, 0, 0, 0]", rgba3.toString());
    Assert.assertEquals("[27, 33, 99, 12]", rgba4.toString());

  }
}