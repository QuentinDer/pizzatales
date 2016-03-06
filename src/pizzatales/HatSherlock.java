package pizzatales;

import java.awt.Image;
import java.util.ArrayList;

public class HatSherlock extends Hat {
	
	private Player player = StartingClass.getPlayer();
	private ArrayList<Firearm> playerweapons = StartingClass.playerweapons;

	public static Image hatsprite;
	
	@Override
	public Image getSprite() {
		return hatsprite;
	}
	
	@Override
	public void effect(){
		//playerweapons.get(index).setFireRate((int)(playerweapons.get(index).getFireRate()*1.2));
	}
	
	@Override
	public void undoEffect(){
		//playerweapons.get(index).setFireRate((int)(playerweapons.get(index).getFireRate()-2));
	}
	
}
