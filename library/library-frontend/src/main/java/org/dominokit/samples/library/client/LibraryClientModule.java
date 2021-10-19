package org.dominokit.samples.library.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="Library")
public class LibraryClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Library frontend module ...");
		new ModuleConfigurator().configureModule(new LibraryModuleConfiguration());
	}
}
