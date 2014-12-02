package getnetexplain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

public class GetBaiduTest {

	@Test
	public void test() throws Exception {
		String word="testsdfs";
		String explain=GetBaidu.getBaiduExplaination(word);
		System.out.println(explain);
		//assertTrue(explain!=null);
	}

}
