package pizzatales;

public abstract class DestroyableTile extends Tile {

	public float life;
	
	public DestroyableTile(int x, int y, int life) {
		super(x, y);
		this.life = life;
	}
	
	public boolean damage(float dmg) {
		life -= dmg;
		if (life <= 0) {
			StartingClass.getTilearray().remove(this);
			StartingClass.destroyabletiles.remove(this);
			StartingClass.map[posx][posy] = null;
			StartingClass.heightitemmap[posx][posy]--;
			doOnDestruction();
			return true;
		}
		return false;
	}
	
	protected abstract void doOnDestruction();
}
