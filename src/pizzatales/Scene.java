package pizzatales;

import java.awt.Image;

public class Scene {

	private String fulltext;
	private int ratetext;
	private String title;
	private Image picture;
	private int i;
	private String renderedtext;
	private int textsize;
	private static final float D_ALPHA = 1.f/120.f;
	private boolean withAlphaTitle;
	private boolean withAlphaPicture;
	private float alphatitle;
	private float alphapicture;
	
	public Scene(String fulltext, int ratetext, String title, Image picture, boolean alphatitle, boolean alphapicture) {
		this.fulltext = fulltext;
		this.ratetext = ratetext;
		this.title = title;
		textsize = fulltext.length();
		this.picture = picture;
		renderedtext = "";
		i = 0;
		withAlphaTitle = alphatitle;
		withAlphaPicture = alphapicture;
	}
	
	public String getRenderedText() {
		return renderedtext;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Image getPicture() {
		return picture;
	}
	
	public void incrementCutsceneViewing() {
		i++;
		renderedtext = fulltext.substring(0, Math.min(i/ratetext, textsize));
		if (withAlphaTitle) {
			alphatitle = Math.min(1.0f, i*D_ALPHA);
		}
		if (withAlphaPicture) {
			alphapicture = Math.min(1.0f, i*D_ALPHA);
		}
	}
	
	public boolean hasAlphaTitle() {
		return this.withAlphaTitle;
	}
	
	public boolean hasAlphaPicture() {
		return this.withAlphaPicture;
	}
	
	public float getAlphaTitle() {
		return this.alphatitle;
	}
	
	public float getAlphaPicture() {
		return this.alphapicture;
	}
	
	public boolean isFullyRendered() {
		return i/ratetext >= textsize;
	}
	
	public void fullRender() {
		i = textsize*ratetext;
		if (withAlphaTitle)
			alphatitle = 1.0f;
		if (withAlphaPicture)
			alphapicture = 1.0f;
		renderedtext = fulltext;
	}
	
}
