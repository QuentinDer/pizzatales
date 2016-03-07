package pizzatales;

import java.awt.Image;

public class Rocket extends Firearm {
	public static Image leftSprite, rightSprite, upSprite, downSprite;
	
	public Rocket() {
		super();
		this.setFireRate(150);
		range = 750;
		projectiledmg = 4;
		speed = 5;
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
}
