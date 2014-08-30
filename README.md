Pico
====
Given a folder of images, pico will generate thumbnails of size 64x?? (keeping the aspect ratio) and place a index.html file in the folder of images (along with jquery and angularjs files). You can then fire up a browser, point it at the folder, and navigate the images in that folder.

## Building Pico
Get a JDK and Maven, then perform

```
mvn clean install
```

This will place a file called `pico.jar` into the `target/` folder.

## Running Pico
```
java -jar pico.jar <name-of-directory>
```

Pico will recursively generate thumbnails and index.html files. Pico will not overwrite existing thumbnails. The thumbnails are stored in a folder called _thumbs.

## License
Pico is in the public domain, do with it what you want.
