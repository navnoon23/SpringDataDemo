package com.nvisia.demo.springdata.util;

import javax.naming.NamingException;

import org.junit.Assert;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

public final class TestUtil {
	
	private TestUtil() {}


	private static final String DS_NAME = "jdbc/datasource";
	public static void setUpInitialContextForDerby(String[] sqlScripts) throws NamingException {
		EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
		databaseBuilder = databaseBuilder.setType(EmbeddedDatabaseType.DERBY);
		for(String sql : sqlScripts){
			databaseBuilder = databaseBuilder.addScript(sql);
		}

		EmbeddedDatabase dataSource = databaseBuilder.build();
		SimpleNamingContextBuilder contextBuilder = new SimpleNamingContextBuilder();
		contextBuilder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		Assert.assertTrue(null != SimpleNamingContextBuilder.getCurrentContextBuilder());
		contextBuilder.bind(DS_NAME, dataSource);
		Assert.assertTrue("Correct datasource", contextBuilder.createInitialContextFactory(null)
				.getInitialContext(null).lookup(DS_NAME) == dataSource);

	}
}
