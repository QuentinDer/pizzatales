package pizzatales;

import java.awt.FontMetrics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Scene {

	private String fulltext;
	private int ratetext;
	private String title;
	private Image picture;
	private int i;
	private List<String> renderedlist;
	private int textsize;
	private static final float D_ALPHA = 1.f/120.f;
	private boolean withAlphaTitle;
	private boolean withAlphaPicture;
	private float alphatitle;
	private float alphapicture;
	private boolean init;
	private List<String> stringlist;
	
	public Scene(String fulltext, int ratetext, String title, Image picture, boolean alphatitle, boolean alphapicture) {
		this.fulltext = fulltext;
		this.ratetext = ratetext;
		this.title = title;
		textsize = fulltext.length();
		this.picture = picture;
		renderedlist = new ArrayList<String>();
		i = 0;
		withAlphaTitle = alphatitle;
		withAlphaPicture = alphapicture;
	}
	
	public void initScene(FontMetrics fm, int maxWidth) {
		if (!init) {
			stringlist = StringUtils.wrap(fulltext, fm, maxWidth);
			init = true;
		}
	}
	
	public List<String> getRenderedText() {
		return renderedlist;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Image getPicture() {
		return picture;
	}
	
	public void incrementCutsceneViewing() {
		if (init) {
			i++;
			renderedlist.clear();
			int j = i/ratetext;
			int z = 0;
			while (j >= 0) {
				if (z < stringlist.size()) {
					renderedlist.add(stringlist.get(z).substring(0,Math.min(j, stringlist.get(z).length())));
					j -= stringlist.get(z).length();
					z++;
				} else
					j = -1;
			}
			
			if (withAlphaTitle) {
				alphatitle = Math.min(1.0f, i*D_ALPHA);
			}
			if (withAlphaPicture) {
				alphapicture = Math.min(1.0f, i*D_ALPHA);
			}
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
		renderedlist.clear();
		for (String ss : stringlist)
			renderedlist.add(ss);
	}
	
}
