package pizzatales;

import java.awt.Image;
import java.net.URL;

public class Smg extends Firearm {
	public static Image leftSprite, rightSprite, upSprite, downSprite;
	public static Image leftUpSprite, rightUpSprite, leftDownSprite, rightDownSprite, addSprite;
	public static URL url;
	
	public Smg() {
		super(1,400,10,10);
	}
	
	@Override
	public void shootUp(int x, int y) {
		setSpriteUp();
		shootingCounter++;
		holderprojectiles.add(new SmgBullet(x+15, y, 0, -1, speed, projectiledmg, range));
	}

	@Override
	public void shootDown(int x, int y) {
		setSpriteDown();
		shootingCounter++;
		holderprojectiles.add(new SmgBullet(x+15, y, 0, 1, speed, projectiledmg, range));
	}

	@Override
	public void shootLeft(int x, int y) {
		setSpriteLeft();
		shootingCounter++;
		holderprojectiles.add(new SmgBullet(x-25, y, -1, 0, speed, projectiledmg, range));
	}

	@Override
	public void shootRight(int x, int y) {
		setSpriteRight();
		shootingCounter++;
		holderprojectiles.add(new SmgBullet(x+30, y, 1, 0, speed, projectiledmg, range));
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
		setSpriteLeftUp();
		shootingCounter++;
		holderprojectiles.add(new SmgBullet(x-25, y-20, -0.7f, -0.7f, speed, projectiledmg, range));
	}

	@Override
	public void shootUpRight(int x, int y) {
		setSpriteRightUp();
		shootingCounter++;
		holderprojectiles.add(new SmgBullet(x+25, y-20, 0.7f, -0.7f, speed, projectiledmg, range));
	}

	@Override
	public void shootDownLeft(int x, int y) {
		setSpriteLeftDown();
		shootingCounter++;
		holderprojectiles.add(new SmgBullet(x-25, y+27, -0.7f, 0.7f, speed, projectiledmg, range));
	}

	@Override
	public void shootDownRight(int x, int y) {
		setSpriteRightDown();
		shootingCounter++;
		holderprojectiles.add(new SmgBullet(x+25, y+27, 0.7f, 0.7f, speed, projectiledmg, range));
	}
	
	@Override
	public URL getAudioURL() {
		return url;
	}

	@Override
	public String getID() {
		return "SMG";
	}

	@Override
	public void setSpriteLeftUp() {
		currentSprite = leftUpSprite;
	}

	@Override
	public void setSpriteRightUp() {
		currentSprite = rightUpSprite;
	}

	@Override
	public void setSpriteLeftDown() {
		currentSprite = leftDownSprite;
	}

	@Override
	public void setSpriteRightDown() {
		currentSprite = rightDownSprite;
	}

	@Override
	public Image getAddSprite() {
		return addSprite;
	}
}
