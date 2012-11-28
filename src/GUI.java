import java.awt.Color;
import java.awt.GridLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;

/**
 * 图形用户界面类，画出3组矩形并显示排序动画
 * @author 6carol6
 *
 */
public class GUI extends JFrame{
	private RecPanel recRed = new RecPanel(0);
	private RecPanel recBlue = new RecPanel(1);
	private RecPanel recGreen = new RecPanel(2);
	
	public GUI(){
		setLayout(new GridLayout(1,3,5,5));

		add(recRed);
		add(recBlue);
		add(recGreen);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		executor.execute(new SelectionSortThread());
		executor.execute(new InsertSortThread());
		executor.execute(new BubbleSortThread());
		
		executor.shutdown();
	}
	
	/**
	 * 内部类，选择排序
	 */
	class SelectionSortThread implements Runnable{
		public void run(){
			int TempMin = 0;//记录目前最小值的下标

			for(int i = 0; i < recRed.getMaxn(); i++){
				TempMin = i;
				//循环一圈寻找目前最小的下标
				for(int j = i; j < recRed.getMaxn(); j++){
					if(recRed.getRec()[j].getLength() < recRed.getRec()[TempMin].getLength())
						TempMin = j;
				}
				//把最小的和第i个换位置
				recRed.recEXChange(recRed.getRec()[i], recRed.getRec()[TempMin]);
				
				//每交换一次值就重新画一下
				recRed.repaint();
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	/**
	 * 内部类，插入排序
	 */
	class InsertSortThread implements Runnable{
		public void run(){
			int xindex;
			for(xindex = 1; xindex < recBlue.getMaxn(); xindex++){
				Color tempC = recBlue.getRec()[xindex].getColor();
				int tempL = recBlue.getRec()[xindex].getLength();
				int xLoc = shiftVac(recBlue.getRec(), xindex, tempL);
				recBlue.getRec()[xLoc].setColor(tempC);
				recBlue.getRec()[xLoc].setLength(tempL);
				recBlue.repaint();
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		int shiftVac(NumRec[] num, int xindex, int x){
			int vacant, xLoc;
			vacant = xindex;
			xLoc = 0; //Assume failure
			while(vacant > 0){
				if(num[vacant-1].getLength() <= x){
					xLoc = vacant;
					break;
				}
				num[vacant].setColor(num[vacant-1].getColor());
				num[vacant].setLength(num[vacant-1].getLength());
				vacant--;
			}
			return xLoc;
		}
	}
	/**
	 * 内部类，冒泡排序
	 */
	class BubbleSortThread implements Runnable{
		public void run(){
			for(int k = 1; k < recGreen.getMaxn(); k++){
				for(int i = 0; i < recGreen.getMaxn()-k; i++){
					if(recGreen.getRec()[i].getLength() > recGreen.getRec()[i+1].getLength()){
						recGreen.recEXChange(recGreen.getRec()[i], recGreen.getRec()[i+1]);
						recGreen.repaint();
						
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
