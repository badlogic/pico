package com.badlogicgames.pico;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * Writes the HTML/CSS/JS from the resources folder to the specified folder
 * so one can easily fire up a browser pointed at index.html in that folder.
 * @author badlogic
 *
 */
public class AppGenerator {
	public void generate(File directory, boolean recursive) throws IOException {
		if(!directory.exists()) throw new IOException("directory " + directory.getAbsolutePath() + " doesn't exist");
		
		copy("/index.html", new File(directory, "index.html"));
		copy("/jquery-1.11.1.min.js", new File(directory, "jquery-1.11.1.min.js"));
		copy("/angular.js", new File(directory, "angular.js"));
		System.out.println("Copied app to " + directory.getAbsolutePath());
		
		for(File file: directory.listFiles()) {
			if(file.isDirectory() && recursive) {
				generate(file, recursive);
			}
		}
	}
	
	private void copy(String resource, File output) throws IOException {
		try(FileWriter out = new FileWriter(output); 
			 InputStream in = AppGenerator.class.getResourceAsStream(resource)) {
			IOUtils.copy(in, out);
		}
	}
}
