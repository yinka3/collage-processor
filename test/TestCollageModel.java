import org.junit.Before;
import org.junit.Test;

import cs3500.filter.BlendingDifference;
import cs3500.filter.DarkenByLuma;
import cs3500.filter.IBlending;
import cs3500.filter.IFilter;
import cs3500.imageutil.Layer;
import cs3500.imageutil.RGBA;
import cs3500.model.Collage;
import cs3500.model.ICollage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Testing the collage class.
 */
public class TestCollageModel {
  ICollage collage1;
  ICollage collage2;
  IFilter filter;
  IBlending blend;


  @Before
  public void init() {
    collage1 = new Collage();
    collage2 = new Collage();
  }


  @Test
  public void testCreate() {
    collage1.createProject(30, 30);

    assertEquals(30, collage1.getHeight());
    assertEquals(30, collage1.getWidth());

    assertEquals(0, collage1.getKnownImages().size());

    // assures we cannot create it twice

    try {
      collage1.createProject(40, 40);
      fail();
    } catch (IllegalStateException e) {
      // do nothing
    }
  }

  @Test
  public void testAddLayer() {
    try {
      collage2.addLayer("Test Layer");
      fail();
    } catch (IllegalStateException e) {
      // do nothing
    }

    collage2.createProject(40, 40);
    collage2.addLayer("Test Layer");
    assertTrue(collage2.getKnownImages().containsKey("Test Layer"));

    Layer testLayer;
    testLayer = collage2.getKnownImages().get("Test Layer");

    assertEquals("Test Layer", testLayer.getName());

    // can't add same layer twice
    try {
      collage2.addLayer("Test Layer");
      fail();
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }


  @Test
  public void testFinalPixel() {
    try {
      collage2.finalPixel();
      fail();
    } catch (IllegalStateException e) {
      // do nothing
    }
    collage2.createProject(2, 2);
    RGBA[][] expected;

    RGBA rgba1 = new RGBA(0, 0, 0);
    RGBA rgba2 = new RGBA(10, 9, 8);
    RGBA rgba3 = new RGBA(4, 5, 6);
    RGBA rgba4 = new RGBA(5, 6, 7);
    expected = new RGBA[][]{
            {rgba1, new RGBA(10, 9, 8)},
            {new RGBA(4, 5, 6), new RGBA(5, 6, 7)}};

    assertEquals(rgba1.toString(), expected[0][0].toString());
    assertEquals(rgba2.toString(), expected[0][1].toString());
    assertEquals(rgba3.toString(), expected[1][0].toString());
    assertEquals(rgba4.toString(), expected[1][1].toString());
  }


  @Test
  public void testSetFilter() {
    filter = new DarkenByLuma();
    blend = new BlendingDifference();
    try {
      collage2.setFilter("I don't know", filter);
      fail();
    } catch (IllegalStateException e) {
      // do nothing
    }

    collage2.createProject(40, 40);
    collage2.addLayer("Test Layer");
    collage2.setFilter("Test Layer", filter);
    assertEquals(collage2.getKnownImages().get("Test Layer").getFilter().toString(),
            filter.toString());

    collage2.setBlend("Test Layer", blend);
    assertEquals(collage2.getKnownImages().get("Test Layer").getBlend().toString(),
            blend.toString());
  }
}
