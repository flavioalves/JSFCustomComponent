package example.renderer;

import java.io.IOException;
import java.io.Writer;

import javax.faces.FactoryFinder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import junit.framework.TestCase;

import org.easymock.MockControl;
import org.easymock.classextension.MockClassControl;

import example.component.UICreditCardInput;

public class CreditCardInputRendererTest extends
    TestCase {

  public static void main(String[] args) {
    junit.textui.TestRunner
        .run(CreditCardInputRendererTest.class);
  }

  protected void setUp() throws Exception {
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public CreditCardInputRendererTest(String name) {
    super(name);
  }

  public void testEncodeEndFacesContextUIComponentFoo()
      throws Exception {
    StringBuffer buf = new StringBuffer();
    FacesContext mock = getMockFacesContext(buf);
    UICreditCardInput comp = new UICreditCardInput();
    comp.setId("foo");
    comp.getAttributes().put("size",
        new Integer(19).toString());
    comp.setValue("1111-2222-3333-4444");
    CreditCardInputRenderer renderer = new CreditCardInputRenderer();
    renderer.encodeEnd(mock, comp);
    // JDom adds a space to the end (between 4" and />) so you need to adjust
    // the expected value for the test to pass if you switch to JDom
    assertEquals(
        "<input type=\"text\" id=\"foo\" name=\"foo\" size=\"19\" "
            + "value=\"1111-2222-3333-4444\"/>",
        buf.toString());
  }

  private FacesContext getMockFacesContext(
      StringBuffer buf) {
    MockControl ctxControl = MockClassControl
        .createControl(FacesContext.class);
    FacesContext mockFacesCtx = (FacesContext) ctxControl
        .getMock();
    mockFacesCtx.getResponseWriter();
    ctxControl
        .setReturnValue(new MockResponseWriter(
            buf));
    mockFacesCtx.getViewRoot();
    UIViewRoot root = new UIViewRoot();
    FactoryFinder
        .setFactory(
            FactoryFinder.RENDER_KIT_FACTORY,
            "com.sun.faces.renderkit.RenderKitFactoryImpl");
    ctxControl.setReturnValue(root);
    ctxControl.replay();
    return mockFacesCtx;
  }

  // this is just a hack so that I can be lazy and
  // not tell the easy mock what methods to expect
  // or what to return since Eclipse generates all
  // the empty methods it makes it easy
  private class MockResponseWriter extends
      ResponseWriter {

    private StringBuffer internalBuffer;

    private UIComponent component;

    public MockResponseWriter(StringBuffer buf) {
      internalBuffer = buf;
    }

    public void write(String value) {
      internalBuffer.append(value);
    }

    public ResponseWriter cloneWithWriter(
        Writer arg0) {
      return null;
    }

    public void endDocument() throws IOException {
    }

    public void endElement(String arg0)
        throws IOException {
      internalBuffer.append("/>");
    }

    public void flush() throws IOException {
    }

    public String getCharacterEncoding() {
      return null;
    }

    public String getContentType() {
      return null;
    }

    public void startDocument()
        throws IOException {
    }

    public void startElement(String arg0,
        UIComponent arg1) throws IOException {
      internalBuffer.append("<");
      internalBuffer.append(arg0);
      component = arg1;
    }

    public void writeAttribute(String arg0,
        Object arg1, String arg2)
        throws IOException {
      internalBuffer.append(" ");
      internalBuffer.append(arg0);
      internalBuffer.append("=\"");
      Object value = null;
      if (null == arg1) {
        value = component.getAttributes().get(
            arg2);
      } else {
        value = arg1;
      }
      internalBuffer.append(value.toString());
      internalBuffer.append("\"");
    }

    public void writeComment(Object arg0)
        throws IOException {
    }

    public void writeText(char[] arg0, int arg1,
        int arg2) throws IOException {
    }

    public void writeText(Object arg0, String arg1)
        throws IOException {
    }

    public void writeURIAttribute(String arg0,
        Object arg1, String arg2)
        throws IOException {
    }

    public void close() throws IOException {
    }

    public void write(char[] cbuf, int off,
        int len) throws IOException {
    }
  }
}