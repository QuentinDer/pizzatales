package pizzatales;

import java.awt.Image;

import pizzatales.StartingClass.GameState;

public class HolySauce extends Item {

	public static Image sprite;
	private StartingClass applet;
	
	public HolySauce(int x, int y, int deltapx, int deltapy, boolean onetimeeffect, int height, StartingClass applet) {
		super(x, y, deltapx, deltapy, onetimeeffect, height);
		this.applet = applet;
	}

	@Override
	protected boolean canDoEffect(Player p) {
		return true;
	}

	@Override
	protected boolean canDoEffect(Enemy e) {
		return false;
	}

	@Override
	protected void doEffect(Player p) {
		applet.cutscene = new Cutscene();
		Scene end1 = new Scene("\"The Kale King lay defeated, his army scattered.\"",3,"END",StartingClass.cutsceneboss8,true,true);
		Scene end2 = new Scene("\"With the Holy Sauce recovered, the Pizza people could rebuild their village.\"",3,"END",StartingClass.cutsceneboss9,false,true);
		Scene end3 = new Scene("\"Due to his courage and strength, our hero was chosen to become the new Pizza King. And he led his people to a new era of peace.\"",3,"END",StartingClass.cutscene1,false,true);
		applet.cutscene.addScene(end1);
		applet.cutscene.addScene(end2);
		applet.cutscene.addScene(end3);
		StartingClass.state = GameState.CutScene;
		StartingClass.lastcutscene = true;
	}

	@Override
	protected void doEffect(Enemy e) {
	}

	@Override
	protected void doLeavingEffect() {
	}

	@Override
	protected Image getSprite() {
		return sprite;
	}

	@Override
	protected Image getEffectSprite() {
		return null;
	}

	@Override
	protected int getEffectCenterX() {
		return 0;
	}

	@Override
	protected int getEffectCenterY() {
		return 0;
	}

	@Override
	protected boolean isEffectAbove() {
		return false;
	}

}
