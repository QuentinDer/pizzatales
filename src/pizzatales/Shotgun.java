package pizzatales;

import java.awt.Image;

public class Shotgun extends Firearm {

	public static Image leftSprite, rightSprite, upSprite, downSprite;
	
	/*private float cos45 = (float)0.7;
	private float cos22 = (float)0.9;
	private float sin22 = (float)0.4;*/
	private float xl = (float)0.3;
	//private float xm = (float)0.2;
	private float xs = (float)0.1;
	
	public Shotgun() {
		super();
		this.setFireRate(50);
	}
	
	@Override
	public void shootUp(int x, int y) {
		setSpriteUp();
		shootingCounter++;
		//holderprojectiles.add(new ShotgunBullet(x, y, cos45, -cos45));
		//holderprojectiles.add(new ShotgunBullet(x, y, -cos45, -cos45));
		//holderprojectiles.add(new ShotgunBullet(x, y, -sin22, -cos22));
		//holderprojectiles.add(new ShotgunBullet(x, y, sin22, -cos22));
		//holderprojectiles.add(new ShotgunBullet(x, y, 0, -1));
		holderprojectiles.add(new ShotgunBullet(x, y, xl, -1));
		holderprojectiles.add(new ShotgunBullet(x, y, -xl, -1));
		//holderprojectiles.add(new ShotgunBullet(x, y, xm, -1));
		//holderprojectiles.add(new ShotgunBullet(x, y, -xm, -1));
		holderprojectiles.add(new ShotgunBullet(x, y, -xs, -1));
		holderprojectiles.add(new ShotgunBullet(x, y, xs, -1));
	}

	@Override
	public void shootDown(int x, int y) {
		setSpriteDown();
		shootingCounter++;
		//holderprojectiles.add(new ShotgunBullet(x, y, cos45, cos45));
		//holderprojectiles.add(new ShotgunBullet(x, y, -cos45, cos45));
		//holderprojectiles.add(new ShotgunBullet(x, y, -sin22, cos22));
		//holderprojectiles.add(new ShotgunBullet(x, y, sin22, cos22));
		//holderprojectiles.add(new ShotgunBullet(x, y, 0, 1));
		holderprojectiles.add(new ShotgunBullet(x, y, xl, 1));
		holderprojectiles.add(new ShotgunBullet(x, y, -xl, 1));
		//holderprojectiles.add(new ShotgunBullet(x, y, xm, 1));
		//holderprojectiles.add(new ShotgunBullet(x, y, -xm, 1));
		holderprojectiles.add(new ShotgunBullet(x, y, -xs, 1));
		holderprojectiles.add(new ShotgunBullet(x, y, xs, 1));
	}

	@Override
	public void shootLeft(int x, int y) {
		setSpriteLeft();
		shootingCounter++;
		//holderprojectiles.add(new ShotgunBullet(x, y, -cos45, cos45));
		//holderprojectiles.add(new ShotgunBullet(x, y, -cos45, -cos45));
		//holderprojectiles.add(new ShotgunBullet(x, y, -cos22, sin22));
		//holderprojectiles.add(new ShotgunBullet(x, y, -cos22, -sin22));
		//holderprojectiles.add(new ShotgunBullet(x, y, -1, 0));
		holderprojectiles.add(new ShotgunBullet(x, y, -1, xl));
		holderprojectiles.add(new ShotgunBullet(x, y, -1, -xl));
		//holderprojectiles.add(new ShotgunBullet(x, y, -1, xm));
		//holderprojectiles.add(new ShotgunBullet(x, y, -1, -xm));
		holderprojectiles.add(new ShotgunBullet(x, y, -1, xs));
		holderprojectiles.add(new ShotgunBullet(x, y, -1, -xs));
	}

	@Override
	public void shootRight(int x, int y) {
		setSpriteRight();
		shootingCounter++;
		//holderprojectiles.add(new ShotgunBullet(x, y, cos45, cos45));
		//holderprojectiles.add(new ShotgunBullet(x, y, cos45, -cos45));
		//holderprojectiles.add(new ShotgunBullet(x, y, cos22, sin22));
		//holderprojectiles.add(new ShotgunBullet(x, y, cos22, -sin22));
		//holderprojectiles.add(new ShotgunBullet(x, y, 1, 0));
		holderprojectiles.add(new ShotgunBullet(x, y, 1, xl));
		holderprojectiles.add(new ShotgunBullet(x, y, 1, -xl));
		//holderprojectiles.add(new ShotgunBullet(x, y, 1, xm));
		//holderprojectiles.add(new ShotgunBullet(x, y, 1, -xm));
		holderprojectiles.add(new ShotgunBullet(x, y, 1, xs));
		holderprojectiles.add(new ShotgunBullet(x, y, 1, -xs));
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
