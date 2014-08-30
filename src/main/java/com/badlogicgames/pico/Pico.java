
package com.badlogicgames.pico;

import java.io.File;
import java.io.IOException;

public class Pico {
	public static void main (String[] args) {
		if(args.length != 1) {
			System.out.println("pico <directory-name>");
			System.exit(-1);
		}
		
		try {
			File directory = new File(args[0]);
			new ThumbnailGenerator().generate(directory, 64, 64, true);
			new MetadataGenerator().generate(directory, true);
			new AppGenerator().generate(directory, true);
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
	}
}
