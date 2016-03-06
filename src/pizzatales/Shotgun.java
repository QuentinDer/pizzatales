package pizzatales;

import java.awt.Image;

public class Shotgun extends Firearm {

	public static Image leftSprite, rightSprite, upSprite, downSprite;
	
	/*private float cos45 = 0.7f;
	private float cos22 = 0.9f;
	private float sin22 = 0.4f;*/
	private float xl = 0.3f;
	//private float xm = 0.2f;
	private float xs = 0.1f;
	
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
