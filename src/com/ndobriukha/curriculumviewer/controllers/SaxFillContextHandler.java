package com.ndobriukha.curriculumviewer.controllers;

import org.xml.sax.helpers.DefaultHandler;

import com.ndobriukha.curriculumviewer.Context;

public class SaxFillContextHandler extends DefaultHandler {
	private Context context;
	
	public SaxFillContextHandler(Context context) {
		this.context = context;
	}
}
