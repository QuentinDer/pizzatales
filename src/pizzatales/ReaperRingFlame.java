package pizzatales;

public class ReaperRingFlame extends FlamerFlame {

	private boolean forward;
	
	public ReaperRingFlame(int startX, int startY, float vectorX,
			float vectorY, int speed, float dmg, int range) {
		super(startX, startY, vectorX, vectorY, speed, dmg, range);
		forward = true;
	}

	@Override
	public void doOnLimitRange() {
		if (forward) {
			travelleddist = 0.f;
			speedX = -speedX;
			speedY = -speedY;
			forward = false;
		} else
			visible = false;
	}
}
