package example.renderer;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class CreditCardInputRenderer extends
    Renderer {

  public CreditCardInputRenderer() {
    super();
  }

  public void decode(FacesContext context,
      UIComponent component) {
    assertValidInput(context, component);

    if (component instanceof UIInput) {
      UIInput input = (UIInput) component;
      String clientId = input
          .getClientId(context);
      Map requestMap = context
          .getExternalContext()
          .getRequestParameterMap();
      String newValue = (String) requestMap
          .get(clientId);
      if (null != newValue) {
        input.setSubmittedValue(newValue);
      }
    }
  }

  public void encodeEnd(FacesContext ctx,
      UIComponent component) throws IOException {
    assertValidInput(ctx, component);
    encodeEnd_Default(ctx, component);
  }

  public void encodeEnd_Default(FacesContext ctx,
      UIComponent component) throws IOException {
    ResponseWriter writer = ctx
        .getResponseWriter();
    writer.startElement("input", component);
    writer.writeAttribute("type", "text", "text");
    String id = (String) component
        .getClientId(ctx);
    writer.writeAttribute("id", id, "id");
    writer.writeAttribute("name", id, "id");
    String size = (String) component
        .getAttributes().get("size");
    if (null != size) {
      writer.writeAttribute("size", size, "size");
    }
    Object currentValue = getValue(component);
    writer.writeAttribute("value",
        formatValue(currentValue), "value");
    writer.endElement("input");
  }

  public void encodeEnd_JDOM(FacesContext ctx,
      UIComponent component) throws IOException {
    ResponseWriter writer = ctx
        .getResponseWriter();
    Element input = new Element("input");
    input.setAttribute(new Attribute("type",
        "text"));
    String id = (String) component
        .getClientId(ctx);
    input.setAttribute(new Attribute("id", id));
    input.setAttribute(new Attribute("name", id));
    String size = (String) component
        .getAttributes().get("size");
    if (null != size) {
      input.setAttribute(new Attribute("size",
          size));
    }
    Object currentValue = getValue(component);
    input.setAttribute(new Attribute("value",
        formatValue(currentValue)));
    XMLOutputter out = new XMLOutputter(Format
        .getPrettyFormat());
    out.output(input, writer);
  }

  protected Object getValue(UIComponent component) {
    Object value = null;
    if (component instanceof UIInput) {
      value = ((UIInput) component)
          .getSubmittedValue();
    }
    // if its not a UIInput or the submitted value
    // was null then get the value (it should
    // always be a UIInput)
    if (null == value
        && component instanceof ValueHolder) {
      value = ((ValueHolder) component)
          .getValue();
    }

    return value;
  }

  private String formatValue(Object currentValue) {
    // this should be a bit more sophisiticated
    // in essence what should happen here is any
    // conversion that needs to take place.
    return currentValue.toString();
  }

  private void assertValidInput(
      FacesContext context, UIComponent component) {
    if (context == null) {
      throw new NullPointerException(
          "context should not be null");
    } else if (component == null) {
      throw new NullPointerException(
          "component should not be null");
    }
  }

}