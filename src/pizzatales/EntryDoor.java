package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public class EntryDoor extends Item {

	private EntryDoor out;
	private int isGoingIn;
	private int isGoingOut;
	private ArrayList<Tile> doors;
	
	public EntryDoor(int x, int y, boolean onetimeeffect) {
		super(x, y, onetimeeffect);
		isGoingIn = -1;
		isGoingOut = -1;
	}

	@Override
	protected boolean canDoEffect() {
		return true;
	}

	@Override
	protected void doEffect() {
		StartingClass.activatedentry = this;
	}

	@Override
	protected Image getSprite() {
		return null;
	}
	
	public void setEntryOut(EntryDoor out) {
		this.out = out;
	}
	
	public EntryDoor getOut() {
		return out;
	}
	
	
	public void setIn(int isGoingIn_) {
		isGoingIn = isGoingIn_;
	}
	
	public void setOut(int isGoingOut_) {
		isGoingOut = isGoingOut_;
	}
	
	public int isGoingIn() {
		return isGoingIn;
	}
	
	public int isGoingOut() {
		return isGoingOut;
	}
	
	public void addDoor(Tile door) {
		if (null == doors)
			doors = new ArrayList<Tile>();
		this.doors.add(door);
	}
	
	public ArrayList<Tile> getDoors() {
		return doors;
	}
}
