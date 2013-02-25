package example.component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class CreditCardValidator implements
    Validator {

  public CreditCardValidator() {
  }

  public void validate(FacesContext context,
      UIComponent component, Object value)
      throws ValidatorException {
    if (null != value) {
      if (!(value instanceof String)) {
        throw new IllegalArgumentException(
            "The value must be a String");
      }
      String ccNum = (String) value;
      // do some asserts that value matches a
      // regex...
      Pattern withDashesPattern = Pattern
          .compile("\\d\\d\\d\\d-\\d\\d\\d\\d-"
              + "\\d\\d\\d\\d-\\d\\d\\d\\d");
      Pattern noDashesPattern = Pattern
          .compile("\\d\\d\\d\\d\\d\\d\\d\\d"
              + "\\d\\d\\d\\d\\d\\d\\d\\d");
      Matcher withDashesMatcher = withDashesPattern
          .matcher(ccNum);
      Matcher noDashesMatcher = noDashesPattern
          .matcher(ccNum);
      if (!withDashesMatcher.matches()
          && !noDashesMatcher.matches()) {
        throw new ValidatorException(
            new FacesMessage(
                "The Credit Card Number must "
                    + "have 16 digits with or "
                    + "without dashes every "
                    + "four digits"));
      }
    }
  }

}