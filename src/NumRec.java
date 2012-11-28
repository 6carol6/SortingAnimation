import java.awt.*;

/**
 * 表示数字的矩形，数字越大矩形越高
 * @author 6carol6
 *
 */
public class NumRec {
	private int length;
	private Color color;
	private final int width = 5;
	private int location;
	
	public NumRec(){
		this.length = 0;
		this.color = Color.RED;
	}
	public NumRec(int length, Color color){
		this.length = length;
		this.color = color;
	}
	public int getLength(){
		return length;
	}
	public int getWidth(){
		return width;
	}
	public Color getColor(){
		return color;
	}
	public int getLocation(){
		return location;
	}
	public void setLength(int length){
		this.length = length;
	}
	public void setColor(Color color){
		this.color = color;
	}
	public void setLocation(int location){
		this.location = location;
	}
}
