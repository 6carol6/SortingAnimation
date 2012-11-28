import java.awt.Color;
import java.awt.GridLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;

/**
 * ͼ���û������࣬����3����β���ʾ���򶯻�
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
	 * �ڲ��࣬ѡ������
	 */
	class SelectionSortThread implements Runnable{
		public void run(){
			int TempMin = 0;//��¼Ŀǰ��Сֵ���±�

			for(int i = 0; i < recRed.getMaxn(); i++){
				TempMin = i;
				//ѭ��һȦѰ��Ŀǰ��С���±�
				for(int j = i; j < recRed.getMaxn(); j++){
					if(recRed.getRec()[j].getLength() < recRed.getRec()[TempMin].getLength())
						TempMin = j;
				}
				//����С�ĺ͵�i����λ��
				recRed.recEXChange(recRed.getRec()[i], recRed.getRec()[TempMin]);
				
				//ÿ����һ��ֵ�����»�һ��
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
	 * �ڲ��࣬��������
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
	 * �ڲ��࣬ð������
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
