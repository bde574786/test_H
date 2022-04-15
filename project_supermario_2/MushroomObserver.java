package project_supermario_2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MushroomObserver {

	private static int RIGHT_XPOINT = 30;
	private static int LEFT_XPOINT = 5;
	private static int BOTTOM_XPOINT = 15;

	private BufferedImage image;
	private Mushroom mushroom;

	public MushroomObserver(Mushroom mushroom) {
		this.mushroom = mushroom;

		try {
			image = ImageIO.read(new File("images/unknown.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean checkRightWall() {
		return isCrashColor();
	}

	public boolean checkbottom() {
		return isCrashColor();
	}

	private boolean isCrashColor( ) {
		Color rightColor = new Color(image.getRGB(mushroom.getX() + 30, mushroom.getY()) + 15);
		Color bottomColor = new Color(image.getRGB(mushroom.getX(), mushroom.getY() + 30));
		Color bottomColor1 = new Color(image.getRGB(mushroom.getX()+1, mushroom.getY() + 30));
		
		
		if(bottomColor1.getRed() == 255 && bottomColor1.getGreen() == 255 && bottomColor1.getBlue() == 255){
			mushroom.down();
		} 
		
		
		if ((rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0)) {
			return true;
		} else if ((bottomColor.getRed() == 0 && bottomColor.getGreen() == 0 && bottomColor.getBlue() == 0)){
			System.out.println("검은 바닥");
			return true;
		} else if(bottomColor.getRed() == 255 && bottomColor.getGreen() == 0 && bottomColor.getBlue() == 0) {
			System.out.println("빨간 바닥");
			mushroom.setDown(false);
			return true;
		}
		return false;
	}

}
