package pizzatales;

import java.awt.Image;

public class EntryDoor extends Item {

	private EntryDoor out;
	
	public EntryDoor(int x, int y, boolean onetimeeffect) {
		super(x, y, onetimeeffect);
	}

	@Override
	protected boolean canDoEffect() {
		return true;
	}

	@Override
	protected void doEffect() {
		// TODO Auto-generated method stub
	}

	@Override
	protected Image getSprite() {
		return null;
	}
	
	public void setOut(EntryDoor out) {
		this.out = out;
	}
	
	public EntryDoor getOut() {
		return out;
	}

}
