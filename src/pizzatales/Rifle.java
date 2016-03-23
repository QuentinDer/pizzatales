package pizzatales;

import java.awt.Image;

public class Rifle extends Firearm {
	public static Image leftSprite, rightSprite, upSprite, downSprite;
	
	public Rifle() {
		super();
		this.setFireRate(40);
		speed = 14;
		range = 700;
		projectiledmg = 3;
		setBaseFirerate(getFireRate());
	}
	
	@Override
	public void shootUp(int x, int y) {
		setSpriteUp();
		shootingCounter++;
		holderprojectiles.add(new RifleBullet(x, y, 0, -1, speed, projectiledmg, range));
	}

	@Override
	public void shootDown(int x, int y) {
		setSpriteDown();
		shootingCounter++;
		holderprojectiles.add(new RifleBullet(x, y, 0, 1, speed, projectiledmg, range));
	}

	@Override
	public void shootLeft(int x, int y) {
		setSpriteLeft();
		shootingCounter++;
		holderprojectiles.add(new RifleBullet(x, y, -1, 0, speed, projectiledmg, range));
	}

	@Override
	public void shootRight(int x, int y) {
		setSpriteRight();
		shootingCounter++;
		holderprojectiles.add(new RifleBullet(x, y, 1, 0, speed, projectiledmg, range));
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
		setSpriteLeft();
		shootingCounter++;
		holderprojectiles.add(new RifleBullet(x, y, -0.7f, -0.7f, speed, projectiledmg, range));
	}

	@Override
	public void shootUpRight(int x, int y) {
		setSpriteRight();
		shootingCounter++;
		holderprojectiles.add(new RifleBullet(x, y, 0.7f, -0.7f, speed, projectiledmg, range));
	}

	@Override
	public void shootDownLeft(int x, int y) {
		setSpriteLeft();
		shootingCounter++;
		holderprojectiles.add(new RifleBullet(x, y, -0.7f, 0.7f, speed, projectiledmg, range));
	}

	@Override
	public void shootDownRight(int x, int y) {
		setSpriteRight();
		shootingCounter++;
		holderprojectiles.add(new RifleBullet(x, y, 0.7f, 0.7f, speed, projectiledmg, range));
	}
}
