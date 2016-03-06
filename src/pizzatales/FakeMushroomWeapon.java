package pizzatales;

public class FakeMushroomWeapon extends Firearm {

	public FakeMushroomWeapon(int deltapx, int deltapy) {
		super(deltapx,deltapy);
		currentSprite = MushroomWizardBall.greenball;
	}
	
	@Override
	public void shootUp(int x, int y) {
	}

	@Override
	public void shootDown(int x, int y) {
	}

	@Override
	public void shootLeft(int x, int y) {
	}

	@Override
	public void shootRight(int x, int y) {
	}

	@Override
	public void setSpriteLeft() {
	}

	@Override
	public void setSpriteRight() {
	}

	@Override
	public void setSpriteUp() {
	}

	@Override
	public void setSpriteDown() {
	}

	public void setSpriteBall(int i) {
		switch(i) {
		case 1:
			currentSprite = MushroomWizardBall.greenball;
			break;
		case 2:
			currentSprite = MushroomWizardBall.yellowball;
			break;
		case 3:
			currentSprite = MushroomWizardBall.blueball;
			break;
		case 4:
			currentSprite = MushroomWizardBall.redball;
			break;
		}
	}
}