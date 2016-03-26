package pizzatales;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

public class Test {

	public static void main(String[] args) throws Exception {
		  File file= new File(Test.class.getResource("/data/racemap.bmp").toURI());
		  BufferedImage image = ImageIO.read(file);
		  StringBuilder ss = new StringBuilder();
		  for (int y = 0; y < image.getHeight(); y++) {
			  for (int x = 0; x < image.getWidth(); x++) {
				  int clr=  image.getRGB(x,y); 
				  int  red   = (clr & 0x00ff0000) >> 16;
				  if (red < 125)
					  ss.append('I');
				  else
					  ss.append('N');
			  }
			  ss.append('\n');
		  }
		  File outfile = new File(Test.class.getResource("/data/return.txt").toURI());
		  if (!outfile.exists())
			  outfile.createNewFile();
		  Files.copy(new ByteArrayInputStream(ss.toString().getBytes()), outfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
}
