package au.com.sixtree.esb;

import java.io.File;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mule.api.config.MuleProperties;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

import com.google.common.base.Joiner;

public class DemoMunitTestCase extends FunctionalMunitSuite {
	
	@Test
	public void testSomething() throws Exception {
		System.out.println("hello test");
	}
	
	private static Properties serviceProperties = new Properties();// Loading
	
	protected Properties getServiceProperties() {
		return serviceProperties;
	}
	
	@BeforeClass
	public static void setupBeforeClass() throws InterruptedException {
		System.getProperties().put("password.vault.key", "nothing");
	}
	
	@Override
	protected boolean haveToDisableInboundEndpoints() {
		return false;
	}

	@Override
	protected boolean haveToMockMuleConnectors() {
		return false;
	}
	
	@Override
	protected Properties getStartUpProperties() {
		Properties properties = new Properties(super.getStartUpProperties());
		properties.put(MuleProperties.APP_HOME_DIRECTORY_PROPERTY, new File(
				"mappings").getAbsolutePath());
		return properties;
	}
	
	@Override
	protected String getConfigResources() {
		return Joiner.on(" ").join(
			"anypoint-demo-api.xml", ""
		);
	}
}
