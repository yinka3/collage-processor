import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import cs3500.controller.Controller;
import cs3500.controller.IController;
import cs3500.model.Collage;
import cs3500.model.ICollage;
import cs3500.view.CollageView;
import cs3500.view.View;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing controller.
 */
public class TestController {
  IController controller;
  ICollage collage1;
  CollageView view1;
  ICollage collage2;
  CollageView view2;
  Readable in;
  Appendable out;
  String string;

  Readable in1;

  @Before
  public void init() {
    in = new StringReader("");
    out = new StringBuilder();
    collage1 = new Collage();
    view1 = new View(collage1, out);
    collage1 = new Collage();
    view1 = new View(collage1, out);
  }

  @Test
  public void testCreate() {
    Readable in1 = new StringReader("create-project 20 20");
    View viewNew = new View(collage1, out);
    IController controllerNew = new Controller(collage1, in1, viewNew);
    assertEquals(20, collage1.getHeight());
  }

  @Test
  public void testAddLayer() {
    Readable in1 = new StringReader("create-project 20 20 add-layer abi");
    View viewNew = new View(collage1, out);
    IController controllerNew = new Controller(collage1, in1, viewNew);
    assertTrue(collage1.getKnownImages().containsKey("abi"));
  }


}
