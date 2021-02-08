package com.my.practice.app;

import java.io.IOException;

import com.my.practice.model.SearchTiming;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;

@Interceptor
public class ClientInterceptorImpl implements IClientInterceptor  {

	private SearchTiming timing;
	
	public ClientInterceptorImpl() {
		super();
	}

	
	public ClientInterceptorImpl(SearchTiming timing) {
		this.timing =timing;
	}

	@Override
	@Hook(value = Pointcut.CLIENT_REQUEST)
	public void interceptRequest(IHttpRequest theRequest) {
		
	}

	@Override
	@Hook(value = Pointcut.CLIENT_RESPONSE)
	public void interceptResponse(IHttpResponse theResponse) throws IOException {
		 timing.setTimingms(theResponse.getRequestStopWatch().toString());
	}		
	
}
