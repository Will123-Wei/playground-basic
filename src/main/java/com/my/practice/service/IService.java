package com.my.practice.service;

import org.hl7.fhir.r4.model.Bundle;

import com.my.practice.model.SearchTiming;

public interface IService {
	public Bundle searchPatient(String name);
	public Bundle searchPatients(boolean noCache, SearchTiming timing) throws Throwable;

}
