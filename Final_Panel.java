import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.ChangedCharSetException;

import res.SoundPlayer;


public class Final_Panel extends JPanel implements ActionListener {

	private JFrame frame;
	public SoundPlayer Final_BGM = new SoundPlayer("�������ֱ���.wav");
	private ArrayList<PosImageIcon> imgList = new ArrayList<PosImageIcon>();
	private Timer changePanelTimer;
	private JButton rankView_Button = new JButton(new ImageIcon("����������.jpg"));
	private JButton endpicture = new JButton(new ImageIcon("��ġ��.jpg"));
	private False_Panel final_rank;
	private String newPlayerName;
	private int newTotalScore;
	private int newTotalTime;
	private int imgCount=0;
	private boolean rankCont = false;
	private boolean	viewCont = false;
	
	public Final_Panel(JFrame frame,String playerName,int totalScore,int totalTime){

		this.setLayout(null);

		this.frame=frame;

		rankView_Button.setSize(600,850);
		rankView_Button.setLocation(0,0);
		final_rank = new False_Panel(2, playerName, totalScore, totalTime);
		rankView_Button.addActionListener(new RankView_Panel());
		this.add(rankView_Button);
		
		endpicture.setSize(600,850);
		endpicture.setLocation(600,0);
		endpicture.addActionListener(this);
		this.add(endpicture);
		
		this.newTotalScore = totalScore;
		this.newTotalTime = totalTime;
		this.newPlayerName = playerName;

		changePanelTimer = new Timer(6000,this);
		imgList.add(new PosImageIcon("������ȭ��1.jpg",0,0,1200,850));
		imgList.add(new PosImageIcon("������ȭ��2.jpg",0,0,1200,850));
		imgList.add(new PosImageIcon("������ȭ��3.jpg",0,0,1200,850));
		imgList.add(new PosImageIcon("������ȭ��4.jpg",0,0,1200,850));
		imgList.add(new PosImageIcon("������ȭ��5.jpg",0,0,1200,850));
		imgList.add(new PosImageIcon("������.jpg",0,0,1200,850));
	}
	protected void paintComponent(Graphics g) {
		if(viewCont==true){
			if(imgCount<6){
			changePanelTimer.start();
			imgList.get(imgCount).draw(g);
			imgCount++;
			}
			else changePanelTimer.stop();
		}
	}

	public void actionPerformed(ActionEvent e) {
		changePanelTimer.start();
		this.remove(rankView_Button);
		this.remove(endpicture);
		viewCont = true;
		repaint();
	}

	class RankView_Panel extends JPanel{
		String newPlayerName;
		int newTotalScore;
		int newTotalTime;
		
		public RankView_Panel(String user,int total){
			this.newPlayerName = user;
			this.newTotalScore = total;
			setUpRankPanel();
		}
		public RankView_Panel(String user,int total,int totalTime){
			this.newPlayerName = user;
			this.newTotalScore = total;
			this.newTotalTime = totalTime;
			setUpRankPanel();
		}
		//����ȭ���� ���ȭ�� 
		PosImageIcon img = new PosImageIcon("����ȭ��.jpg",0,0,1200,850);
		
		public void setUpRankPanel(){
			ArrayList<Rank_Information> rank_list = new ArrayList<Rank_Information>();
			ArrayList<JLabel> label_list = new ArrayList<JLabel>();
			//�г��� ������
			this.setSize(1200,850);
			//��ġ
			this.setLocation(300,70);
			this.setLayout(null);	
			
			//���� �ҷ��� ���� �̸�
			File file = new File("Ʋ���׸�ã�����.txt");	
			String lineString;
			String playerName;

			int totalScore=0;
			int totalTime =0;
			int totalGame =0;
			int rankPrintCount=0;
			int x=0,y=200;

			//***********************������ �ϴ� ����**************************************************
			try{
				Scanner scan = new Scanner(file);
				while(scan.hasNext()){
					lineString = scan.nextLine();
					Scanner scanFile = new Scanner(lineString);
					playerName = scanFile.next();
					totalScore = Integer.parseInt(scanFile.next());
					totalTime  = Integer.parseInt(scanFile.next());
					rank_list.add(new Rank_Information(playerName, totalScore, totalTime));
				}
				scan.close();
			}catch(Exception ex){System.out.println("���� ������");}
			//***********************���ο� ����Ʈ�� �־���***********************************************
			System.out.println(newPlayerName+" "+newTotalScore+"   "+newTotalTime);
			rank_list.add(new Rank_Information(newPlayerName,newTotalScore,newTotalTime));
			//***********************������ �ϱ� ����****************************************************
			Collections.sort(rank_list,Collections.reverseOrder());
			//���� ��Ŵ
			
			for(Rank_Information rank:rank_list){
				rankPrintCount++;
				label_list.add(new JLabel(rankPrintCount+"�� �̸� :"+rank.playerName+" ���� :"+rank.totalScore));
				if(rankPrintCount==5) break;
			}
			for(JLabel label:label_list){
				label.setForeground(Color.BLACK);
				label.setFont(new Font("1��ȭ�翬ȭ R",Font.CENTER_BASELINE,50));
				label.setSize(1200,100);
				label.setLocation(x+20,y+=100);
				this.add(label);
			}
			//***********************�ٽ� �־� �ش�*******************************************************
			try {
				FileWriter  fileWriter = new FileWriter(file,true); 
				fileWriter.write(newPlayerName+" "+Integer.toString(newTotalScore)+" "+Integer.toString(newTotalTime)+"\n");
				fileWriter.flush();		
				fileWriter.close();
			} catch (Exception e2) {e2.printStackTrace();}
		}//action
		protected void paintComponent(Graphics g) {
			img.draw(g);
		}
	}//rank

}
