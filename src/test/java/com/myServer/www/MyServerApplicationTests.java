package com.myServer.www;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyServerApplicationTests {

	
   
	@Test
	public void contextLoads() {
		
	}
	
    @Test
    public void shouldAccessSystemProperty_WhenDefinedInSystemPropertyVariables() {
        Assert.assertEquals("Hello", System.getProperty("greet.spanish"));
    }

}
