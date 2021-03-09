package gui;

import org.junit.Test;

public class TestMain {
	Main m = new Main();
	String[] args = new String[] {"none"};
	
	@SuppressWarnings("static-access")
	@Test
	public void testMain()
	{
		m.main(args);
	}
}
