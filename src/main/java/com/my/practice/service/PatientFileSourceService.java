package com.my.practice.service;

import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import com.my.practice.app.ClientInterceptorImpl;
import com.my.practice.dao.DaoFactory;
import com.my.practice.dao.DaoFactory.DataSouceType;
import com.my.practice.dao.FileDao;
import com.my.practice.dao.INameDao;
import com.my.practice.model.SearchTiming;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.CacheControlDirective;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;

public class PatientFileSourceService implements IService{
	
	private DataSouceType type = DataSouceType.FILE;	
	private INameDao dao ;
	private final String filePath = "names.txt"; 
	
	@Override
	public Bundle searchPatient(String name) {
		FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        client.registerInterceptor(new LoggingInterceptor(false));
       
        return client
                .search()
                .forResource("Patient").cacheControl(new CacheControlDirective().setNoCache(false))
               .where(Patient.FAMILY.matches().value(name))
                .returnBundle(Bundle.class)
                .execute();
	}
	
	@Override
	public Bundle searchPatients(boolean noCache, SearchTiming timing) throws Throwable{
		dao = DaoFactory.getDao(type);
		((FileDao) dao).setFilePath(filePath);
		List<String> names = dao.getNames();
		
		FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        client.registerInterceptor(new LoggingInterceptor(false));
        client.registerInterceptor(new ClientInterceptorImpl(timing));
        return client
                .search()
                .forResource("Patient")
               .where(Patient.FAMILY.matches().values(names))
                .returnBundle(Bundle.class)
                .cacheControl(new CacheControlDirective().setNoCache(noCache))
                .execute();
	}

}
