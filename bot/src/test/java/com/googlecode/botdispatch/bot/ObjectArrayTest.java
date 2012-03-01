package com.googlecode.botdispatch.bot;

import junit.framework.TestCase;

public class ObjectArrayTest extends TestCase {

	Getter getter = new GetterImpl();
	
	public void testArrays(){
		Object[] result = (Object[]) getter.get();
		assertNull(result[0]);
		
	}
	
	public void testEquals(){
		Object[] result1 = new Object[]{};
		Object[] result2 = new Object[]{};
		assertNotSame(result1,result2);
	}
}

interface Getter {
	Object get();
}

class GetterImpl implements Getter {

	@Override
	public Object get() {
		// TODO Auto-generated method stub
		return new Object[] { null };
	}
	
}