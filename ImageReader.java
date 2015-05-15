import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageReader {

	private BufferedImage image;
	
	public ImageReader(String file){
		try {
			this.image = ImageIO.read(new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	double[][] hm;
	
	public double[][] getHeightMap(){
		    hm = new double[image.getHeight()][image.getWidth()];
		        for (int row = 0; row < image.getHeight(); row++) {
		            for (int col = 0; col < image.getWidth(); col++) {
		                hm[row][col] = ((image.getRGB(col, row) & 255)/10.0);
		                System.out.println(""+hm[row][col]);

		            }
		        }
		return hm;
	}
	
	public double[][] getRandomMap(){
		double[][] array = new double[image.getWidth()][image.getHeight()];
		for(int w=0; w<image.getWidth(); w++){
			for(int h=0; h<image.getHeight(); h++){
				array[w][h] = (int) (Math.random()*10);
				System.out.println(""+array[w][h]);
			}
		}
		return array;
	}
}
