package com.badlogicgames.pico;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Generates a file describing all images in a folder
 * @author badlogic
 *
 */
public class MetadataGenerator {	
	public void generate(File directory, boolean recursive) throws IOException {
		if(!directory.exists()) throw new IOException("directory " + directory.getAbsolutePath() + " doesn't exist");
		List<File> foundFiles = new ArrayList<>();
		for(File file: directory.listFiles()) {
			if(file.isDirectory() && recursive) {
				generate(file, recursive);
			} else {
				if(file.getName().length() < 4) continue;
				String suffix = file.getName().substring(file.getName().length() - 3, file.getName().length()).toLowerCase();
				if(!(suffix.equals("png") || suffix.equals("jpg") || suffix.equals("bmp"))) continue;
				foundFiles.add(file);
			}
		}
		
		List<String> names = new ArrayList<>();
		for(File file: foundFiles) {
			names.add(file.getName());
		}
		File output = new File(directory, "metadata.json");
		new ObjectMapper().writeValue(output, names);
		System.out.println("Wrote metadata to " + output.getAbsolutePath());
	}
}
