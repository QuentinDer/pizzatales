package pizzatales;

import java.awt.Image;

public class Flamer extends Firearm {
	public static Image leftSprite, rightSprite, upSprite, downSprite;
	
	/*private float cos45 = (float)0.7;
	private float cos22 = (float)0.9;
	private float sin22 = (float)0.4;*/
	private float xl = 0.3f;
	private float xm = 0.2f;
	private float xs = 0.1f;
	
	public Flamer() {
		super();
		this.setFireRate(25);
		speed = 10;
		projectiledmg = 1.0f;
		range = 100;
		setBaseFirerate(getFireRate());
	}
	
	@Override
	public void shootUp(int x, int y) {
		setSpriteUp();
		shootingCounter++;
		//holderprojectiles.add(new FlamerFlame(x, y, cos45, -cos45));
		//holderprojectiles.add(new FlamerFlame(x, y, -cos45, -cos45));
		//holderprojectiles.add(new FlamerFlame(x, y, -sin22, -cos22));
		//holderprojectiles.add(new FlamerFlame(x, y, sin22, -cos22));
		holderprojectiles.add(new FlamerFlame(x, y, 0, -1, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, xl, -1, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -xl, -1, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, xm, -1, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -xm, -1, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -xs, -1, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, xs, -1, speed, projectiledmg, range));
	}

	@Override
	public void shootDown(int x, int y) {
		setSpriteDown();
		shootingCounter++;
		//holderprojectiles.add(new FlamerFlame(x, y, cos45, cos45));
		//holderprojectiles.add(new FlamerFlame(x, y, -cos45, cos45));
		//holderprojectiles.add(new FlamerFlame(x, y, -sin22, cos22));
		//holderprojectiles.add(new FlamerFlame(x, y, sin22, cos22));
		holderprojectiles.add(new FlamerFlame(x, y, 0, 1, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, xl, 1, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -xl, 1, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, xm, 1, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -xm, 1, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -xs, 1, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, xs, 1, speed, projectiledmg, range));
	}

	@Override
	public void shootLeft(int x, int y) {
		setSpriteLeft();
		shootingCounter++;
		//holderprojectiles.add(new FlamerFlame(x, y, -cos45, cos45));
		//holderprojectiles.add(new FlamerFlame(x, y, -cos45, -cos45));
		//holderprojectiles.add(new FlamerFlame(x, y, -cos22, sin22));
		//holderprojectiles.add(new FlamerFlame(x, y, -cos22, -sin22));
		holderprojectiles.add(new FlamerFlame(x, y, -1, 0, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -1, xl, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -1, -xl, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -1, xm, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -1, -xm, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -1, xs, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -1, -xs, speed, projectiledmg, range));
	}

	@Override
	public void shootRight(int x, int y) {
		setSpriteRight();
		shootingCounter++;
		//holderprojectiles.add(new FlamerFlame(x, y, cos45, cos45));
		//holderprojectiles.add(new FlamerFlame(x, y, cos45, -cos45));
		//holderprojectiles.add(new FlamerFlame(x, y, cos22, sin22));
		//holderprojectiles.add(new FlamerFlame(x, y, cos22, -sin22));
		holderprojectiles.add(new FlamerFlame(x, y, 1, 0, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 1, xl, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 1, -xl, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 1, xm, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 1, -xm, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 1, xs, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 1, -xs, speed, projectiledmg, range));
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
		holderprojectiles.add(new FlamerFlame(x, y, -0.7f, -0.7f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -0.4f, -0.9f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -0.5f, -0.8f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -0.6f, -0.7f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -0.7f, -0.6f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -0.8f, -0.5f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -0.9f, -0.4f, speed, projectiledmg, range));
	}

	@Override
	public void shootUpRight(int x, int y) {
		setSpriteRight();
		shootingCounter++;
		holderprojectiles.add(new FlamerFlame(x, y, 0.7f, -0.7f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 0.4f, -0.9f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 0.5f, -0.8f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 0.6f, -0.7f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 0.7f, -0.6f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 0.8f, -0.5f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 0.9f, -0.4f, speed, projectiledmg, range));
	}

	@Override
	public void shootDownLeft(int x, int y) {
		setSpriteLeft();
		shootingCounter++;
		holderprojectiles.add(new FlamerFlame(x, y, -0.7f, 0.7f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -0.4f, 0.9f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -0.5f, 0.8f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -0.6f, 0.7f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -0.7f, 0.6f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -0.8f, 0.5f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, -0.9f, 0.4f, speed, projectiledmg, range));
	}

	@Override
	public void shootDownRight(int x, int y) {
		setSpriteRight();
		shootingCounter++;
		holderprojectiles.add(new FlamerFlame(x, y, 0.7f, 0.7f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 0.4f, 0.9f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 0.5f, 0.8f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 0.6f, 0.7f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 0.7f, 0.6f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 0.8f, 0.5f, speed, projectiledmg, range));
		holderprojectiles.add(new FlamerFlame(x, y, 0.9f, 0.4f, speed, projectiledmg, range));
	}

}
