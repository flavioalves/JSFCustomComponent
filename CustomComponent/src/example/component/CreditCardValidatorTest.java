/*
 * Created on Mar 11, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package example.component;

import javax.faces.validator.ValidatorException;

import junit.framework.TestCase;


/**
 * @author bdudney
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class CreditCardValidatorTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(CreditCardValidatorTest.class);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for CreditCardValidatorTest.
     * @param arg0
     */
    public CreditCardValidatorTest(String name) {
        super(name);
    }

    public void testValidateNull() throws Exception {
        CreditCardValidator ccv = new CreditCardValidator();
        try {
            ccv.validate(null, null, null);
        } catch (Exception e) {
            fail("should not have thrown on null inputs");
        }
    }

    public void testValidateDashes() throws Exception {
        CreditCardValidator ccv = new CreditCardValidator();
        try {
            ccv.validate(null, null, "1111-1111-1111-1111");
        } catch (ValidatorException e) {
            fail("1111-1111-1111-1111 is a valid number should not have thrown.");
        }
    }

    public void testValidateNoDashes() throws Exception  {
        CreditCardValidator ccv = new CreditCardValidator();
        try {
            ccv.validate(null, null, "1111111111111111");
        } catch (ValidatorException e) {
            fail("1111111111111111 is a valid number should not have thrown.");
        }
    }

    public void testValidateNoDashesLetter() throws Exception  {
        CreditCardValidator ccv = new CreditCardValidator();
        try {
            ccv.validate(null, null, "11111a1111111111");
            fail("11111a1111111111 is not a valid credit card number, it should have thrown");
        } catch (ValidatorException e) {
            // it should throw
        }
    }

    public void testValidateDashesLetter() throws Exception {
        CreditCardValidator ccv = new CreditCardValidator();
        try {
            ccv.validate(null, null, "1a11-1111-1111-1111");
            fail("1a11-1111-1111-1111 is not a valid number should have thrown.");
        } catch (ValidatorException e) {
            // it should throw
        }
    }

    public void testValidateWrongNumberNoDashes() throws Exception  {
        CreditCardValidator ccv = new CreditCardValidator();
        try {
            ccv.validate(null, null, "111111111111111");
            fail("111111111111111 is not a valid number should have thrown.");
        } catch (ValidatorException e) {
            // it should throw
        }
    }

    public void testValidateWrongNumberDashes() throws Exception {
        CreditCardValidator ccv = new CreditCardValidator();
        try {
            ccv.validate(null, null, "111-1111-1111-1111");
            fail("111-1111-1111-1111 is not a valid number should have thrown.");
        } catch (ValidatorException e) {
            // it should throw
        }
    }

}
