package pizzatales;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Garlnstein extends Enemy {

	public static Image staySprite, move1Sprite, move2Sprite, dieSprite;
	public static BufferedImage garlnstein;
	private AffineTransform oldtransform;
	private StartingClass applet;
	public static Graphics2D garlgraphs;
	private int i;
	
	public Garlnstein(int centerX, int centerY, StartingClass applet) {
		super(centerX, centerY, null, 75, 3, 31, 31,
				25, 25);
		movementTime = ((int) (Math.random() * 50));
		this.applet = applet;
		/*garlnstein = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		garlgraphs = garlnstein.createGraphics();
		garlgraphs.drawImage(staySprite, 15, 15, null);
		garlgraphs.setColor(Color.BLACK);*/
	}

	@Override
	public void callAI() {
		/*if (movementTime % 20 == 0) {
			if (garlgraphs != null)
				garlgraphs.dispose();
			garlnstein = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
			garlgraphs = garlnstein.createGraphics();
			garlgraphs.rotate(Math.toRadians(5*i));
			garlgraphs.drawImage(staySprite, 15, 15, null);
			i++;
		}*/
		stopMoving();
	}

	@Override
	public void setStaySprite() {
		//currentSprite = garlnstein;
		currentSprite = staySprite;
	}

	@Override
	public void setMove1Sprite() {
		currentSprite = move1Sprite;
	}

	@Override
	public void setMove2Sprite() {
		currentSprite = move2Sprite;
	}

	@Override
	public void setDieSprite() {
		currentSprite = dieSprite;
	}

	@Override
	public void setGibsSprite() {
		currentSprite = dieSprite;
	}

	@Override
	public void setStaySpriteAlt() {
		currentSprite = staySprite;
	}

	@Override
	public void setMove1SpriteAlt() {
		currentSprite = move1Sprite;
	}

	@Override
	public void setMove2SpriteAlt() {
		currentSprite = move2Sprite;
	}
	
	public void cleanResources() {
		
	}

}
