package sql;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import server.Query;

public class SQLManagerTest {

	@Test
	public void test() throws Exception {
		
		SQLManager.init();
		Query query=new Query();
		query.getExplaination("test", "111");
		SQLManager.close();
	}

}
