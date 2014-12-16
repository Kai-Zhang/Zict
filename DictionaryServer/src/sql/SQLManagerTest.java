package sql;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;

import org.junit.Test;

import server.Answer;
import server.Query;

public class SQLManagerTest {

	@Test
	public void test() throws Exception {
		SQLManager.init();
		SQLManager.LocalAnswer("test");
	}

}
