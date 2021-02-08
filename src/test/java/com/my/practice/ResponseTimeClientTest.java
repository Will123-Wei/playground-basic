package com.my.practice;

import java.util.ArrayList;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.my.practice.app.ResponseTimeClient;
import com.my.practice.model.SearchTiming;

public class ResponseTimeClientTest {
	
	private static ResponseTimeClient rtc;
	@BeforeClass
	public static void setup() {
		 rtc = new ResponseTimeClient();
	}
	
	
	@Test
	public void testEmptyMovingAverageReturnSize() {
		//ResponseTimeClient rtc = new ResponseTimeClient();
		Assert.assertEquals("Test size of return list: ", 3, rtc.printResponseTime().size());
	}
	

	@Test
	public void testEmptyMovingAverageReturnContents() {
		ArrayList<SearchTiming> res = rtc.printResponseTime();
		for(SearchTiming timing: res) {
			Assert.assertThat("test timing return: ", timing.getTimingms(), CoreMatchers.containsString("ms"));
		}
	}

}
