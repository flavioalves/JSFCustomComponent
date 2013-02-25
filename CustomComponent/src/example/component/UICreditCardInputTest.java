package example.component;

import junit.framework.TestCase;

public class UICreditCardInputTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(UICreditCardInputTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public UICreditCardInputTest(String arg0) {
        super(arg0);
    }

    public void testHasValidator() {
        UICreditCardInput cci = new UICreditCardInput();
        assertTrue(cci.getValidators().length > 0);
    }
    
}
