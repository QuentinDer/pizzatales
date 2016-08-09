package pizzatales;

import java.awt.Image;
import java.net.URL;

public class Rocket extends Firearm {
	public static Image leftSprite, rightSprite, upSprite, downSprite, addSprite;
	public static URL url;
	
	public Rocket() {
		super(7,750,7,75);
	}
	
	@Override
	public void shootUp(int x, int y) {
		setSpriteUp();
		shootingCounter++;
		holderprojectiles.add(new BazookaBullet(x, y, 0, -1, speed, projectiledmg, range));
	}

	@Override
	public void shootDown(int x, int y) {
		setSpriteDown();
		shootingCounter++;
		holderprojectiles.add(new BazookaBullet(x, y, 0, 1, speed, projectiledmg, range));
	}

	@Override
	public void shootLeft(int x, int y) {
		setSpriteLeft();
		shootingCounter++;
		holderprojectiles.add(new BazookaBullet(x, y, -1, 0, speed, projectiledmg, range));
	}

	@Override
	public void shootRight(int x, int y) {
		setSpriteRight();
		shootingCounter++;
		holderprojectiles.add(new BazookaBullet(x, y, 1, 0, speed, projectiledmg, range));
	}

	@Override
	public void setSpriteLeft() {
		currentSprite = leftSprite;
	}

	@Override
	public void setSpriteRight() {
		currentSprite = rightSprite;
	}

	@Override
	public void setSpriteUp() {
		currentSprite = upSprite;
	}

	@Override
	public void setSpriteDown() {
		currentSprite = downSprite;
	}

	@Override
	public void shootUpLeft(int x, int y) {
		shootLeft(x,y);
	}

	@Override
	public void shootUpRight(int x, int y) {
		shootRight(x,y);
	}

	@Override
	public void shootDownLeft(int x, int y) {
		shootLeft(x,y);
	}

	@Override
	public void shootDownRight(int x, int y) {
		shootRight(x,y);
	}
	
	@Override
	public URL getAudioURL() {
		return url;
	}

	@Override
	public String getID() {
		return "ROCKET";
	}

	@Override
	public void setSpriteLeftUp() {
		currentSprite = leftSprite;
	}

	@Override
	public void setSpriteRightUp() {
		currentSprite = rightSprite;
	}

	@Override
	public void setSpriteLeftDown() {
		currentSprite = leftSprite;
	}

	@Override
	public void setSpriteRightDown() {
		currentSprite = rightSprite;
	}

	@Override
	public Image getAddSprite() {
		return addSprite;
	}
}
