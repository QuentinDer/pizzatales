package pizzatales;

import java.awt.Image;

public class Smg extends Firearm {
	public static Image leftSprite, rightSprite, upSprite, downSprite;
	
	public Smg() {
		super();
		this.setFireRate(10);
	}
	
	@Override
	public void shootUp(int x, int y) {
		setSpriteUp();
		shootingCounter++;
		holderprojectiles.add(new SmgBullet(x, y, 0, -1));
	}

	@Override
	public void shootDown(int x, int y) {
		setSpriteDown();
		shootingCounter++;
		holderprojectiles.add(new SmgBullet(x, y, 0, 1));
	}

	@Override
	public void shootLeft(int x, int y) {
		setSpriteLeft();
		shootingCounter++;
		holderprojectiles.add(new SmgBullet(x, y, -1, 0));
	}

	@Override
	public void shootRight(int x, int y) {
		setSpriteRight();
		shootingCounter++;
		holderprojectiles.add(new SmgBullet(x, y, 1, 0));
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
}
