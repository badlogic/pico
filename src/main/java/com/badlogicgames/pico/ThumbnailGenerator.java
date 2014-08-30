
package com.badlogicgames.pico;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

/** Generates thumbnails, stretching images to meet the given width/height. Thumbnails are stored in a new directory called
 * "_thumbs", thumbnail images share the same name as the original images. If a directory _thumbs already exists, only thumbnails
 * for images that don't exist yet are generated.
 * @author badlogic */
public class ThumbnailGenerator {
	private static final String THUMBS_DIR = "_thumbs";

	public void generate (File directory, int width, int height, boolean recursive) throws IOException {
		if(!directory.exists()) throw new IOException("directory " + directory + " doesn't exist");
		if(directory.getName().equals(THUMBS_DIR)) return;
		File thumbDir = new File(directory, THUMBS_DIR);
		if (!thumbDir.exists() && !thumbDir.mkdirs()) {
			throw new IOException("Couldn't create thumbnail directory");
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory() && recursive) {
				generate(file, width, height, recursive);
			} else {
				if(file.getName().length() < 4) continue;
				String suffix = file.getName().substring(file.getName().length() - 3, file.getName().length()).toLowerCase();
				if(!(suffix.equals("png") || suffix.equals("jpg") || suffix.equals("bmp"))) continue;
				System.out.println("generating thumbnail for " + file.getAbsolutePath());
				File output = new File(thumbDir, file.getName());
				if(!output.exists()) {
					try {
						BufferedImage img = ImageIO.read(file);
						ImageIO.write(Scalr.resize(img, width, height), "png", output);
					} catch(IOException e) {
						System.out.println("couldn't index " + file.getAbsolutePath() + ": " + e.getMessage());
					}
				}
			}
		}
	}

	public Image scale (File img, int width, int height) throws IOException {
		return ImageIO.read(img).getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
	}
}
