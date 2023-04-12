import org.junit.Before;
import org.junit.Test;

import cs3500.filter.FilterBlue;
import cs3500.imageutil.ILayer;
import cs3500.imageutil.IRGBA;
import cs3500.imageutil.Layer;
import cs3500.imageutil.PPMUtil;
import cs3500.imageutil.RGBA;

import static org.junit.Assert.assertEquals;

/**
 * testing for layer class.
 */
public class LayerTest {

  PPMUtil testImg;

  String imgName;

  int height;
  int width;
  int alpha;

  @Before
  public void init() {
    this.alpha = 255;
  }


  @Test
  public void testGoodConstructor() {
    ILayer layer1 = new Layer("first layer", 500, 500);
    //assertEquals("first layer", layer1.getImgName());
    //assertEquals(255.0, layer1.newAlpha(this.alpha), 0.001);
    ILayer layer2 = new Layer("test2", 500, 500);
    //assertEquals("test2", layer2.getImgName());
    //layer2.newAlpha(79.5);
    assertEquals(79.5, layer2.getAlpha(), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor() {
    ILayer layer1 = new Layer("first layer", 0, 5);
    ILayer layer2 = new Layer("second layer", 5, 0);
    ILayer layer3 = new Layer("third layer", -5, 5);
    ILayer layer4 = new Layer("fourth layer", 5, -5);
  }


  @Test
  public void testNewAlpha() {
    this.imgName = "Yinka-Abi.ppm";
    this.testImg = new PPMUtil(this.imgName);
    ILayer layer1 = new Layer("firstLayer", this.height, this.width);
    IRGBA[][] tempLayer = layer1.visualize();
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        assertEquals(this.alpha, tempLayer[i][j].getAlpha(), 0.001);
      }
    }
    //layer1.newAlpha(55);
    tempLayer = layer1.visualize();
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        assertEquals(55, tempLayer[i][j].getAlpha(), 0.001);
      }
    }
  }

  @Test
  public void testNewFilter() {
    this.imgName = "Yinka-Abi.ppm";
    this.testImg = new PPMUtil(this.imgName);
    ILayer layer1 = new Layer("firstLayer", this.height, this.width);
    IRGBA[][] tempLayer = layer1.visualize();
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        // assertEquals(testImg.getImage(i, j).toString(), tempLayer[i][j].toString());
      }
    }

    layer1.setFilter(new FilterBlue());
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        assertEquals(new FilterBlue(), layer1.getFilter());
      }
    }
  }


  @Test
  public void testVisualize() {
    Layer l1 = new Layer("test", this.height, this.width);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        assertEquals("0 0 0", l1.visualize()[i][j].toString());
      }
    }
  }
}
