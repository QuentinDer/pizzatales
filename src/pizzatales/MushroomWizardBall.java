package pizzatales;

import java.awt.Image;

public class MushroomWizardBall extends Projectile {

	public static Image greenball, yellowball, redball, blueball;
	private Image currentball;
	private int type;
	private int color;
	private int cX, cY;
	private boolean newpattern;
	private float speed = 10.f;
	private int movementTime;
	
	public MushroomWizardBall(int startX, int startY, float vectorX,
			float vectorY, int color, boolean newpattern) {
		super(startX, startY, vectorX, vectorY, 10, 1, 15, 7, 1000);
		this.color = color;
		this.newpattern = newpattern;
		switch (color) {
		case 1:
			currentball = greenball;
			if (newpattern)
				movementTime = ((int) (Math.random() * 50));
			break;
		case 2:
			currentball = yellowball;
			damage = 5;
			if (newpattern) {
				speed = 5;
				range = 600;
			}
			speedX = vectorX * speed;
			speedY = vectorY * speed;
			
			break;
		case 3:
			currentball = blueball;
			speed = 12;
			if (newpattern)
				speed = 14;
			damage = 0;
			speedX = vectorX * speed;
			speedY = vectorY * speed;
			break;
		case 4:
			currentball = redball;
			speed = 5;
			damage = 0;
			if (newpattern) {
				speedX = vectorX * speed + (int)(Math.random()*3)-1;
				speedY = vectorY * speed + (int)(Math.random()*3)-1;
			}
			break;
		}
		
	}
	
	public MushroomWizardBall(int startX, int startY, float vectorX, float vectorY, int cX, int cY, int color, boolean newpattern) {
		super(startX, startY, vectorX, vectorY, StartingClass.difficultylevel==4?14:10, 1, 15, 7, 1000);
		if (StartingClass.difficultylevel==4)
			speed = 14;
		this.cX = cX;
		this.cY = cY;
		type = 1;
		this.newpattern = newpattern;
		range = 4000;
		this.color = color;
		switch (color) {
		case 1:
			currentball = greenball;
			break;
		case 2:
			currentball = yellowball;
			if (newpattern) {
				speed = 5;
				range = 600;
			}
			damage = 5;
			break;
		case 3:
			currentball = blueball;
			speed = 12;
			damage = 0;
			break;
		case 4:
			currentball = redball;
			speed = 5;
			damage = 0;
			break;
		}
		speedX = vectorX * speed;
		speedY = vectorY * speed;
	}
	
	@Override
	public void update() {
		if (color == 2 && newpattern) {
			float diffX = player.centerX - fcenterX;
			float diffY = player.centerY - fcenterY;
			speedX = speed * diffX / (float)(Math.sqrt(Math.abs(diffX)*Math.abs(diffX)+Math.abs(diffY)*Math.abs(diffY)));
			speedY = speed * diffY / (float)(Math.sqrt(Math.abs(diffX)*Math.abs(diffX)+Math.abs(diffY)*Math.abs(diffY)));
			super.update();
		} else if (type == 1) {
			float diffX = fcenterX - cX;
			float diffY = fcenterY - cY;
			speedX = (diffY*speed) / (Math.abs(diffX)+Math.abs(diffY));
			speedY = - (diffX*speed) / (Math.abs(diffX)+Math.abs(diffY));
			super.update();
		} else if (color == 1 && newpattern) {
			float basefspeedX = speedX;
			float basefspeedY = speedY;
			if (movementTime % 20 < 10) {
				speedX += speedY*4/speed;
				speedY -= speedX*4/speed;
			} else {
				speedX -= speedY*4/speed;
				speedY += speedX*4/speed;
			}
			movementTime++;
			super.update();
			speedX = basefspeedX;
			speedY = basefspeedY;
		} else
			super.update();
	}

	@Override
	public Image getSprite() {
		return currentball;
	}

	@Override
	public void doOnCollision(Player p) {
		visible = false;
		StartingClass.hitpoints.add(new HitPoint(p,(getCenterX()-p.getCenterX())/2,(getCenterY()-p.getCenterY())/2));
		if (color == 4)
			p.setHealth(p.getMaxHealth());
		if (color == 3)
			StartingClass.leavingitems.add(new FakeItemForSlow(90,0));
	}

	@Override
	public void doOnCollision(Enemy e) {
		visible = false;
		StartingClass.hitpoints.add(new HitPoint(e,(getCenterX()-e.getCenterX())/2,(getCenterY()-e.getCenterY())/2));
	}

	@Override
	public void doOnCollision(Tile t) {
		visible = false;
		if (DestroyableTile.class.isInstance(t))
			StartingClass.hitpoints.add(new HitPoint(t,(getCenterX()-t.getCenterX())/2,(getCenterY()-t.getCenterY())/2));
	}

	@Override
	public void doOnLimitRange() {
		visible = false;
	}

	@Override
	public void doOnCollision(Projectile p) {
		visible = false;
	}

	@Override
	public void doOnLimitScreen() {
		visible = false;
	}

}
