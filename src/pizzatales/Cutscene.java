package pizzatales;

import java.awt.FontMetrics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Cutscene {

	private ArrayList<Scene> listScenes;
	private int i;
	
	public Cutscene() {
		listScenes = new ArrayList<Scene>();
		i = 0;
	}
	
	public void addScene(Scene scene) {
		listScenes.add(scene);
	}
	
	public void pass() {
		if (!listScenes.get(i).isFullyRendered())
			listScenes.get(i).fullRender();
		else
			i++;
	}
	
	public void initScene(FontMetrics fm, int maxWidth) {
		listScenes.get(i).initScene(fm, maxWidth);
	}
	
	public void incrementCutsceneViewing() {
		listScenes.get(i).incrementCutsceneViewing();
	}
	
	public List<String> getRenderedText() {
		return listScenes.get(i).getRenderedText();
	}
	
	public Image getPicture() {
		return listScenes.get(i).getPicture();
	}
	
	public boolean isCutsceneFinished() {
		return i>=listScenes.size();
	}
	
	public String getTitle() {
		return listScenes.get(i).getTitle();
	}
	
	public boolean hasAlphaTitle() {
		return listScenes.get(i).hasAlphaTitle();
	}
	
	public boolean hasAlphaPicture() {
		return listScenes.get(i).hasAlphaPicture();
	}
	
	public float getAlphaTitle() {
		return listScenes.get(i).getAlphaTitle();
	}
	
	public float getAlphaPicture() {
		return listScenes.get(i).getAlphaPicture();
	}
	
}
