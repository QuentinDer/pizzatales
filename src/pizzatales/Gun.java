package pizzatales;

public class Gun extends Firearm {
	
	public Gun() {
		super();
		this.setFireRate(15);
	}
	
	@Override
	public void shootUp(int x, int y) {
		shootingCounter++;
		holderprojectiles.add(new Bullet(x, y, 0, -1));
	}

	@Override
	public void shootDown(int x, int y) {
		shootingCounter++;
		holderprojectiles.add(new Bullet(x, y, 0, 1));
	}

	@Override
	public void shootLeft(int x, int y) {
		shootingCounter++;
		holderprojectiles.add(new Bullet(x, y, -1, 0));
	}

	@Override
	public void shootRight(int x, int y) {
		shootingCounter++;
		holderprojectiles.add(new Bullet(x, y, 1, 0));
	}
}
