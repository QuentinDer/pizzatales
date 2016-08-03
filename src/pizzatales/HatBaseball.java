package pizzatales;

import java.awt.Image;

public class HatBaseball extends Hat {
	
	private Player player = StartingClass.getPlayer();
	
	public static Image hatsprite, addSprite;
	
	@Override
	public Image getSprite() {
		return hatsprite;
	}
	
	@Override
	public void effect(){
		player.setMOVESPEED(player.getMOVESPEED()+1);
	}

	@Override
	public String getID() {
		return "HATBASEBALL";
	}

	@Override
	public Image getAddSprite() {
		return addSprite;
	}

}
