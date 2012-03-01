package com.googlecode.botdispatch.test;

import com.google.inject.AbstractModule;

public class HeavyReportModule extends AbstractModule {

	private HeavyReport report;

	public HeavyReportModule(HeavyReport report) {
		this.report = report;
	}

	@Override
	protected void configure() {
		bind(HeavyReport.class).toInstance(report);
	}

}
