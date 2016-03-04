/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Test;

import org.junit.*;
import static org.junit.Assert.*;
import BusinessLogic.Controller.MakePurchase;

/**
 *
 * @author Niki
 */
public class MakePurchaseJUnitTest {
    
    MakePurchase test;
    
    public MakePurchaseJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        test = new MakePurchase();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Ignore("Doesnt working yet")
    public void testBuy(){
        assertEquals("Try purchase must return 200", 400, test.buy(0, new int[0], new int[0], 0));
    }
}
