import java.awt.*;
import javax.swing.*;
/**
 * 画图类，每次随机画出一组大小不一的矩形
 * 可以画出不同颜色的矩形哦~^_^
 * @author 6carol6
 *
 */
public class RecPanel extends JPanel{
	private final int MAXN = 50;
	private NumRec[] recArray;
	int way;
	
	public RecPanel(int color){ //若color=0则为红色；若为1，是蓝色；若为2是绿色
		recArray = new NumRec[MAXN];
		way = color;
		for(int i = 0; i < MAXN; i++){
			recArray[i] = new NumRec();
			if(color == 0)
				recArray[i].setColor(new Color(255, i*4, i*4));
			else if(color == 1)
				recArray[i].setColor(new Color(i*4, i*4, 255));
			else
				recArray[i].setColor(new Color(i*4, 255, i*4));
			recArray[i].setLocation(i*recArray[i].getWidth());
			recArray[i].setLength((i+1)*4);

		}
		
		//随机打乱顺序
		for(int i = 0; i < MAXN; i++)
			recEXChange(recArray[(int)(Math.random()*MAXN)], recArray[(int)(Math.random()*MAXN)]);
		

	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i < MAXN; i++){
			g.setColor(Color.BLACK);
			if(way == 0)
				g.drawString("Selection Sort", 80, 20);
			else if(way == 1)
				g.drawString("Insertion Sort", 100, 20);
			else
				g.drawString("Bubble Sort", 110, 20);
			g.setColor(recArray[i].getColor());
			g.fillRect(recArray[i].getLocation(), getHeight() - recArray[i].getLength(), recArray[i].getWidth(), recArray[i].getLength());
		}
	}
	public void recEXChange(NumRec r1, NumRec r2){
		NumRec temp = new NumRec();
		temp.setColor(r1.getColor());
		r1.setColor(r2.getColor());
		r2.setColor(temp.getColor());
		
		temp.setLength(r1.getLength());
		r1.setLength(r2.getLength());
		r2.setLength(temp.getLength());
	}
	public void setRec(NumRec[] rec){
		this.recArray = rec;
	}
	public NumRec[] getRec(){
		return this.recArray;
	}
	public int getMaxn(){
		return this.MAXN;
	}
}
