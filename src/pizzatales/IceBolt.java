package pizzatales;

import java.awt.Image;

public class IceBolt extends Projectile {

	public static Image[] boltset = new Image[32];
	
	private float speed;
	private int frozenduration;
	private int style;
	private KaleKing king;
	private int angle;
	private int mysprite;
	private float fccX;
	private float fccY;
	private float fscX;
	private float fscY;
	private int cangle;
	private int dist;
	
	public IceBolt(int startX, int startY, float vectorX, float vectorY,
			int speed, float damage, int range, int frozenduration, int style, KaleKing king) {
		super(startX, startY, vectorX, vectorY, speed, damage, 30, 15, range);
		this.speed = speed;
		this.frozenduration = frozenduration;
		this.style = style;
		this.king = king;
		
		if (vectorY >= 0)
			angle = (int)Math.toDegrees(Math.acos(vectorX)) + 5;
		else {
			angle = 360 - (int)Math.toDegrees(Math.acos(vectorX)) + 5;
		}
		mysprite = (int)((angle % 360) / 11.25f);
	}
	
	public IceBolt(int startX, int startY, float vectorX, float vectorY,
			int speed, float damage, int range, int frozenduration, int style, int cangle, int dist) {
		super(startX, startY, vectorX, vectorY, speed, damage, 30, 15, range);
		this.speed = speed;
		this.frozenduration = frozenduration;
		this.style = style;
		this.cangle = cangle;
		this.dist = dist;
		
		fccX = startX;
		fccY = startY;
		fscX = vectorX*speed;
		fscY = vectorY*speed;
		
		if (vectorY >= 0)
			angle = (int)Math.toDegrees(Math.acos(vectorX)) + 5;
		else {
			angle = 360 - (int)Math.toDegrees(Math.acos(vectorX)) + 5;
		}
		mysprite = (int)((angle % 360) / 11.25f);
	}
	
	@Override
	public void update() {
		if (style == 3) {
			float diffX = player.centerX - fcenterX;
			float diffY = player.centerY - fcenterY;
			float vectorX = diffX / (float)(Math.sqrt(Math.abs(diffX)*Math.abs(diffX)+Math.abs(diffY)*Math.abs(diffY)));
			float vectorY = diffY / (float)(Math.sqrt(Math.abs(diffX)*Math.abs(diffX)+Math.abs(diffY)*Math.abs(diffY)));
			speedX = speed * vectorX;
			speedY = speed * vectorY;
			if (vectorY >= 0)
				angle = (int)Math.toDegrees(Math.acos(vectorX)) + 5;
			else {
				angle = 360 - (int)Math.toDegrees(Math.acos(vectorX)) + 5;
			}
			mysprite = (int)((angle % 360) / 11.25f);
			super.update();
		} else if (style == 6) {
			cangle = (cangle +5) % 360;
			fccX += fscX;
			fccY += fscY;
			int togox = (int)(fccX + dist*Math.cos(Math.toRadians(cangle)));
			int togoy = (int)(fccY + dist*Math.sin(Math.toRadians(cangle)));
			speedX = togox - centerX;
			speedY = togoy - centerY;
			super.update();
		} else
			super.update();
	}

	@Override
	public Image getSprite() {
		return boltset[mysprite];
	}

	@Override
	public void doOnCollision(Player p) {
		if (visible) {
			visible = false;
			StartingClass.leavingitems.add(new FakeItemForFrozen(p,frozenduration));
			if (style == 5)
				StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
			if (style == 3) {
				king.swapdemand = true;
			}
		}
	}

	@Override
	public void doOnCollision(Enemy e) {
		if (visible) {
			visible = false;
			StartingClass.leavingitems.add(new FakeItemForFrozen(e,frozenduration));
			if (style == 5)
				StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
		}
	}

	@Override
	public void doOnCollision(Tile t) {
		if (visible) {
			visible = false;
			if (style == 5)
				StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
			if (style == 7) {
				iceRing();
			}
		}
	}

	@Override
	public void doOnLimitRange() {
		if (visible) {
			visible = false;
			if (style == 5)
				StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
			if (style == 7) {
				iceRing();
			}
		}
	}

	@Override
	public void doOnLimitScreen() {
		if (visible) {
			visible = false;
			if (style == 5)
				StartingClass.getExplosions().add(new BarrelExplosion(this.centerX,this.centerY));
			if (style == 7) {
				iceRing();
			}
		}
	}

	@Override
	public void doOnCollision(Projectile p) {
	}
	
	private void iceRing() {
		king.getProjectiles().add(new IceBolt(centerX,centerY,1.0f,0.0f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,0.96f,0.26f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,0.87f,0.5f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,0.71f,0.71f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,0.5f,0.87f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,0.26f,0.96f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		
		king.getProjectiles().add(new IceBolt(centerX,centerY,-1.0f,0.0f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,-0.96f,0.26f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,-0.87f,0.5f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,-0.71f,0.71f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,-0.5f,0.87f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,-0.26f,0.96f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		
		king.getProjectiles().add(new IceBolt(centerX,centerY,0.0f,1.0f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,0.96f,-0.26f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,0.87f,-0.5f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,0.71f,-0.71f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,0.5f,-0.87f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,0.26f,-0.96f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));

		king.getProjectiles().add(new IceBolt(centerX,centerY,0.0f,-1.0f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,-0.96f,-0.26f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,-0.87f,-0.5f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,-0.71f,-0.71f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,-0.5f,-0.87f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
		king.getProjectiles().add(new IceBolt(centerX,centerY,-0.26f,-0.96f,7,1.0f,200+100*StartingClass.difficultylevel,frozenduration,1,king));
	}

}
