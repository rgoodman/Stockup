import junit.framework.TestCase;


public class StatusTest extends TestCase {

	
	Status st = new Status();
	/*
	 * 
	 */
	public void testOpenPriceInvalid (){
		assertEquals("has invalid data", st.compareSharePrice(0,1));
		}
	
	/*
	 * 
	 */
	public void testCurrentPriceInvalid() {
		assertEquals("has invalid data", st.compareSharePrice(3,-1));
		}
	
	/*
	 * 
	 */
	public void testBothValidNoEvent() {
		assertEquals(null, st.compareSharePrice(50,50));
		}
	
	/*
	 * 
	 */
	public void testPlummet() {
		assertEquals("plummeted", st.compareSharePrice(100,80));
		}
	
	/*
	 * 
	 */
	public void testRocket() {
		assertEquals("rocketed", st.compareSharePrice(100,110));
		}
	
	/*
	 * 
	 */
	public void testPlummetBoundaryHigh() {
		assertEquals(null, st.compareSharePrice(100,80.1f));
		}
	/*
	 * 
	 */
	public void testPlummetBoundayLow() {
		assertEquals("plummeted", st.compareSharePrice(100,79.9f));
		}
	/*
	 * 
	 */
	public void testRocketBoundaryHigh() {
		assertEquals("rocketed", st.compareSharePrice(100,110.1f));
		}
	/*
	 * 
	 */
	public void testRocketBoundaryLow() {
		assertEquals(null, st.compareSharePrice(100,109.9f));
		}
	
	

}
