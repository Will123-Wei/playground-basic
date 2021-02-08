package com.my.practice.app;

import java.util.ArrayList;

import org.hl7.fhir.r4.model.Bundle;

import com.my.practice.model.SearchTiming;
import com.my.practice.service.IService;
import com.my.practice.service.PatientFileSourceService;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;

public class ResponseTimeClient {
	
	public ArrayList<SearchTiming> printResponseTime() {
		IService service = new PatientFileSourceService();
		boolean noCache =false;
		ArrayList<SearchTiming> timingList = new ArrayList<>();
		for(int i=0;i<3;i++) {
			SearchTiming timing = new SearchTiming();
			timing.setLoopId(i);
			try {
				if(i==2) noCache=true;
				Bundle b =service.searchPatients(noCache, timing); 
				timingList.add(timing);
				
				FhirContext fhirContext = FhirContext.forR4();
				IParser parser = fhirContext.newJsonParser();
				String parsed = parser.encodeResourceToString(b);	
				System.out.println(parsed);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("<===========================================>");
		for(SearchTiming timing:timingList) {
			System.out.println("loop #:"+timing.getLoopId()+",  timing:"+timing.getTimingms());
		}
		
		/* Sample output:
		 loop #:0,  timing:170ms
		 loop #:1,  timing:93ms
		 loop #:2,  timing:156ms
		 */
		return timingList;
	}
}
