package org.jasig.cas;

import java.io.File;
import java.io.IOException;

import org.jasig.cas.authentication.principal.DefaultPrincipalAttributesRepository;
import org.jasig.cas.services.DefaultServicesManagerImpl;
import org.jasig.cas.services.JsonServiceRegistryDao;
import org.jasig.cas.services.RegexRegisteredService;
import org.jasig.cas.services.ReturnAllowedAttributeReleasePolicy;

public class CASTest {

	public static void main(String[] args) throws IOException {
		new ReturnAllowedAttributeReleasePolicy();
		new RegexRegisteredService();
		new DefaultPrincipalAttributesRepository();
		new JsonServiceRegistryDao(new File(""));
		new DefaultPrincipalAttributesRepository();
		new ReturnAllowedAttributeReleasePolicy();
		new DefaultServicesManagerImpl(null); 
	}

}
