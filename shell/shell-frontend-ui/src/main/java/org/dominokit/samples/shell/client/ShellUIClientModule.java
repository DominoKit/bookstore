package org.dominokit.samples.shell.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="ShellUI")
public class ShellUIClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShellUIClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Shell frontend UI module ...");
		new ModuleConfigurator().configureModule(new ShellUIModuleConfiguration());
	}
}
