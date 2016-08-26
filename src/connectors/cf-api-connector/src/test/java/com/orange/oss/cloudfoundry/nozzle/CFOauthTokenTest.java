package com.orange.oss.cloudfoundry.nozzle;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import com.orange.oss.cloudfoundry.nozzle.config.CfClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CFOauthTokenTest {

	private static Logger logger=LoggerFactory.getLogger(CFOauthTokenTest.class.getName());
	
	@Autowired
	CfClient client;
	
	@Test
	public void testGetBearer(){
		String bearer=client.bearerToken();
		
		
	}
	

}
