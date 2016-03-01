package pizzatales;

import java.awt.Image;

public class Tile extends BlockingStuff {

	private Image tileImage;
	private Image hiddenImage;

	public Tile(int x, int y) {
		super((x * 50) + 25, (y * 50) + 40, 31, 31, 25, 25);
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