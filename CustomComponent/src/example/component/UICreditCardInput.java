package example.component;

import javax.faces.component.UIInput;

public class UICreditCardInput extends UIInput {
  public static final String 
         CREDIT_CARD_FAMILY = "CCFAMILY";

  public UICreditCardInput() {
    super();
    addValidator(new CreditCardValidator());
  } 

  public String getFamily() {
    return CREDIT_CARD_FAMILY;
  }
}
