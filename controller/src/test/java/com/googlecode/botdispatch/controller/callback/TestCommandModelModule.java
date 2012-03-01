package com.googlecode.botdispatch.controller.callback;

import com.google.inject.AbstractModule;

import com.googlecode.botdispatch.model.api.Commands;

public class TestCommandModelModule extends AbstractModule  {

	Commands commandManager;
	public TestCommandModelModule(Commands commandManager) {
		 this.commandManager = commandManager;
	}
	@Override
	protected void configure() {
		bind(Commands.class).toInstance(commandManager);
	}
}
