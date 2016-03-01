package pizzatales;

import java.awt.Image;

import pizzatales.StartingClass.GameState;

public class LevelExit extends Item {

	public LevelExit(int x, int y, int deltapx, int deltapy, boolean onetimeeffect) {
		super(x, y, deltapx, deltapy, onetimeeffect);
	}

	@Override
	protected boolean canDoEffect() {
		return true;
	}

	@Override
	protected void doEffect() {
		StartingClass.state = GameState.LevelEnded;
	}

	@Override
	protected Image getSprite() {
		return null;
	}

	@Override
	protected Image getEffectSprite() {
		return null;
	}

}
