import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Image {
	private String type;
	private String classOfImage;
	private int width;
	private int height;
	//1 = black, 0 = white
	private boolean[][] pixels;
	private boolean matched;
	
	public Image(String type, String classOfImage, int width, int height, boolean[][] pixels){
		this.type = type;
		this.classOfImage = classOfImage;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}
	
	public String getType(){
		return type;
	}
	
	public String getClassOfImage(){
		return classOfImage;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public boolean[][] getPixels(){
		return pixels;
	}
	public void setMatched(boolean trueOrFalse){
		matched = trueOrFalse;
	}
	public boolean getMatched(){
		return matched;
	}

}
