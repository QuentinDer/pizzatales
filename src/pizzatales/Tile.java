package pizzatales;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile extends BlockingStuff {

	private Image tileImage;
	private Image hiddenImage;

	public Tile(int x, int y) {
		super(x, y, 31, 31, 25, 25);
	}
	
	public void randomizePosition() {
		
		R = new Rectangle(getCenterX() - halfrsizex, getCenterY() - halfrsizey, 2 * halfrsizex, 2 * halfrsizey);
	}
	
	public void hideImage(Image replacing) {
		this.hiddenImage = tileImage;
		this.tileImage = replacing;
	}
	
	public void reveal() {
		this.tileImage = this.hiddenImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	@Override
	public Image getSprite() {
		return tileImage;
	}
}