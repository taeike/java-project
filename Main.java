import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Main implements  ActionListener{//implements ActionListener{
	Paint_Thread explan_Thread = new Paint_Thread();//���Ӽ��������� ����� ���� ������.
	private Cursor cursor;//���콺�� �̹����� �ֱ����Ѱ�
	private Image img;
	private Point point = new Point(0,0);

	private JLayeredPane layerPane = new JLayeredPane();//���������� ȭ���� �ٲٱ����� �г��غ�
	private FirstButton_Panel firstButton_Panel = new FirstButton_Panel();//�ٷ� ó�� ���ִ� ȭ��
	JFrame frame = new JFrame();
	private GameStartButton_Panel gameStartButton_Panel = new GameStartButton_Panel(frame);

	//******************���� Ŭ����� ������ ��ư �г�***********************************//
	private Next_Button next_Button1 = new Next_Button();//1�ܰ� Ŭ����
	private Next_Button next_Button2 = new Next_Button();//2�ܰ� Ŭ����
	private Next_Button next_Button3 = new Next_Button();//3�ܰ� Ŭ����
	private False_Panel false_Panel;//���������� ...

	private Timer gameStartTimer;//    3/2/1/����ȭ���� ���ֱ����� Ÿ�̸�
	private Timer repaintTimer;//      ����ؼ� ���ǹ��� �˻��ؼ� Ȯ��j���ִ� Ÿ�̸�
	//************************�ϴܰ� Ʋ����ġ�� Ʋ���׸��� ũ��**************************
	private final int[][] Level1_Point = {{744,1004,1110,1065,615,697,905},
										  {480,430,397,600,634,308,592}};	

	private final int[][] Level1_Point_Size ={{40,40,40,70,70,60,50},
			                                  {40,40,50,50,50,70,50}};

	//************************�̴ܰ� Ʋ����ġ�� Ʋ���׸��� ũ��**************************
	private final int[][] Level2_Point = {{938,1044,995,968,957,1094,1117},
			                              {205,503,358,499,713,540,690}};	

	private final int[][] Level2_Point_Size ={{40,50,40,40,40,60,50},
			                                  {50,50,40,40,40,40,50}};

	//************************��ܰ� Ʋ����ġ�� Ʋ���׸��� ũ��**************************
	private final int[][] Level3_Point = {{785,1100,770,775,1124,998,919},
			                              {224,247,720,542,137,523,642}};	

	private final int[][] Level3_Point_Size ={{50,40,70,40,30,40,40},
			                                  {50,150,40,40,40,40,50}};
	//************************��ܰ� Ʋ����ġ�� Ʋ���׸��� ũ��**************************
	private final int[][] Level4_Point = {{695,914,650,1005,810,1086,995},
			                              {270,272,682,420,243,222,693}};	

	private final int[][] Level4_Point_Size ={{50,40,50,40,50,50,100},
			                                  {50,50,40,50,50,80,60}};


	private String playerName; //�̸��� �ޱ�����
	//**************************1/2/3/4 �ܰ� Ʋ���׸�����ȭ���� ���ִ� ��************************************
	private Make_GamePanel Level1 = new Make_GamePanel("�ϴܰ� ����ȭ��.jpg",70,"3�ܰ� �������.wav",Level1_Point,Level1_Point_Size);
	private Make_GamePanel Level2 = new Make_GamePanel("�̴ܰ� ����ȭ��.jpg",70,"2�ܰ� �������.wav",Level2_Point,Level2_Point_Size);
	private Make_GamePanel Level3 = new Make_GamePanel("��ܰ� ����ȭ��.jpg",70,"1�ܰ� �������.wav",Level3_Point,Level3_Point_Size);	
	private Make_GamePanel Level4 = new Make_GamePanel("��ܰ� ����ȭ��.jpg",70,"4�ܰ� �������.wav",Level4_Point,Level4_Point_Size);
	//*************************��� Ŭ����������**************************************************************
	private Final_Panel final_Panel = new Final_Panel(frame,playerName , Level2.totalScore , 60-Level1.progressBar.scoreTime+60+Level2.progressBar.scoreTime+Level3.progressBar.scoreTime);
	//
	private int Levelclear_Count=0;//���ǹ��� �ɸ��� �ߺ��Ǽ� �Ȱɸ��� �ϱ�����
	private int count = 4;         //3/2/1/ȭ��
	private int Level = 0;		   //��ܰ迡�� �������� �˱�����

	public static void main(String[] args){
		Main project = new Main();
		project.setUp();

	}
	public void setUp(){
		//*********************���콺 �̹����� ����*******************************
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("���̹���.jpg");
		cursor = tk.createCustomCursor(img,point,"���̹���.jpg");
		frame.setCursor(cursor);
		//**********************�̸��� �޾��ְ�**********************************
		playerName=JOptionPane.showInputDialog("�̸��� �Է����ּ��� :");
		//**********************������ ����*************************************
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200,850);
		frame.setLocation(300,70);
		frame.setResizable(false);//�������̰�
		frame.setTitle("                                                                                                              ����ǳ��ȭ �� ���뱹���� �Բ� �ϴ� Ʋ�� �׸� ã��");
		//**********************Ÿ�̸� ����*************************************
		gameStartTimer = new Timer(1000,new CountTimerAction());
		repaintTimer = new Timer(1,new RepaintTimerAction());
		//***********************ó������ ��� �г�******************************
		firstButton_Panel.setup_GUI();
		firstButton_Panel.FirstButton.addActionListener(this);
		layerPane.add(firstButton_Panel, new Integer(12));
		firstButton_Panel.setSize(1200,850);
		//***********************�ι�°�� ��� ���ӽ��� ��ư***********************
		gameStartButton_Panel.setup_GUI();
		gameStartButton_Panel.gameStartButton.addActionListener(this);
		gameStartButton_Panel.explanation_Button.addActionListener(this);
		layerPane.add(gameStartButton_Panel.panel, new Integer(11));
		gameStartButton_Panel.panel.setSize(1200,850);	
		//***********************3�ʳ���***************************************
		layerPane.add(gameStartButton_Panel.sec_3,new Integer(10));
		gameStartButton_Panel.sec_3.setSize(1200,850);
		//***********************2�ʳ���***************************************
		layerPane.add(gameStartButton_Panel.sec_2,new Integer(9));
		gameStartButton_Panel.sec_2.setSize(1200,850);
		//***********************1�ʳ���***************************************
		layerPane.add(gameStartButton_Panel.sec_1,new Integer(8));
		gameStartButton_Panel.sec_1.setSize(1200,850);
		//***********************1�ܰ��г�*************************************
		Level1.setUp();
		layerPane.add(Level1,new Integer(7));
		Level1.setSize(1200,850);
		//***********************1�ܰ�Ŭ����************************************
		layerPane.add(next_Button1.panel,new Integer(6));
		next_Button1.panel.setSize(1200,850);
		next_Button1.addActionListener(this);
		//***********************2�ܰ��г�*************************************
		layerPane.add(Level2,new Integer(5));
		Level2.setSize(1200,850);
		//***********************2�ܰ�Ŭ����***********************************
		layerPane.add(next_Button2.panel,new Integer(4));
		next_Button2.panel.setSize(1200,850);
		next_Button2.addActionListener(this);
		//***********************3�ܰ��г�*************************************
		layerPane.add(Level3,new Integer(3));
		Level2.setSize(1200,850);
		//***********************3�ܰ�Ŭ����************************************
		layerPane.add(next_Button3.panel,new Integer(2));
		next_Button3.panel.setSize(1200,850);
		next_Button3.addActionListener(this);
		//***********************4�ܰ��г�**************************************
		layerPane.add(Level4,new Integer(1));
		Level4.setSize(1200,850);
		//***********************4�ܰ� Ŭ���� �г�********************************
		layerPane.add(final_Panel,new Integer(0));
		final_Panel.setSize(1200,850);

		repaintTimer.start();
		frame.add(layerPane);
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		//************Ʋ���׸�ã���°��� �˷��ص� �����ϱ� �� ��ư�г�**************************************************
		if(e.getSource()==firstButton_Panel.FirstButton){
			layerPane.setLayer(gameStartButton_Panel.panel,13);
		}
		//************������ �����̵Ǵ� ��ư�г�*********************************************************************
		else if(e.getSource()==gameStartButton_Panel.gameStartButton){
			firstButton_Panel.FirstButton.setEnabled(false);
			gameStartTimer.start();
		}
		//************Ŭ�����ϰ� �غ� �� �� �ֵ��� ���� ��ư�г��� ������ ��� 2�ܰ� ������ ���۵ȴ�***************************
		else if(e.getSource()==next_Button1){ 
			next_Button1.setEnabled(false);
			Level++;
			Level2.setUp();
			Level2.Level_BGM.startPlay();
			Level2.progressBarTimer.start();
			next_Button1.next_BGM.stopPlayer();
			layerPane.setLayer(Level2,19);
		}
		//************Ŭ�����ϰ� �غ� �� �� �ֵ��� ���� ��ư�г��� ������ ��� 3�ܰ� ������ ���۵ȴ�***************************
		else if(e.getSource()==next_Button2){ 
			next_Button2.setEnabled(false);
			next_Button2.next_BGM.stopPlayer();
			Level++;
			Level3.setUp();
			Level3.Level_BGM.startPlay();
			Level3.progressBarTimer.start();
			next_Button1.next_BGM.stopPlayer();
			layerPane.setLayer(Level3,21);
		}
		//************Ŭ�����ϰ� �غ� �� �� �ֵ��� ���� ��ư�г��� ������ ��� 4�ܰ� ������ ���۵ȴ�***************************
		else if(e.getSource()==next_Button3){ 
			next_Button3.setEnabled(false);
			next_Button3.next_BGM.stopPlayer();
			Level++;
			Level4.setUp();
			Level4.Level_BGM.startPlay();
			Level4.progressBarTimer.start();
			next_Button1.next_BGM.stopPlayer();
			layerPane.setLayer(Level4,23);
		}
		//*******************������ �����ִ� �����带 �������ش�*****************************************************
		else if(e.getSource()==gameStartButton_Panel.explanation_Button){
			explan_Thread.setUp();
			explan_Thread.start();

		}
	}
	class CountTimerAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//*********************3��*******************************************
			if(count==4){
				layerPane.setLayer(gameStartButton_Panel.sec_3,14);
				gameStartButton_Panel.gameStartButton.setEnabled(false);
			}
			//*********************2��*******************************************
			else if(count==3){layerPane.setLayer(gameStartButton_Panel.sec_2,15);}
			//*********************1��*******************************************
			else if(count==2){layerPane.setLayer(gameStartButton_Panel.sec_1,16);}
			//*********************���ӽ���****************************************
			else{
				firstButton_Panel.start_BGM.stopPlayer();
				Level1.Level_BGM.startPlay();
				layerPane.setLayer(Level1,17);
				Level1.progressBarTimer.start();
				Level++;
				gameStartTimer.stop();
			}
			count--;
		}
	}

	class RepaintTimerAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//*********************1�ܰ� Ŭ����*************************************
			if(Level1.clearLevel()&&Levelclear_Count==0){
				Levelclear_Count++;
				Level1.Level_BGM.stopPlayer();
				Level1.progressBarTimer.stop();
				next_Button1.setUp();
				next_Button1.next_BGM.startPlay();
				layerPane.setLayer(next_Button1.panel,18);		
			}
			//*********************1�ܰ� ����***************************************
			if(Level1.progressBar.time==0&&Levelclear_Count==0){
				frame.setVisible(false);
				Level1.Level_BGM.stopPlayer();
				false_Panel = new False_Panel(Level,playerName , Level1.totalScore , 60-Level1.progressBar.scoreTime); 
				false_Panel.setUp();
				repaintTimer.stop();
			}
			//*********************2�ܰ� Ŭ����*************************************
			if(Level2.clearLevel()&&Levelclear_Count==1){
				Level2.progressBarTimer.stop();
				Level2.Level_BGM.stopPlayer();
				Levelclear_Count++;
				next_Button2.setUp();
				next_Button2.next_BGM.startPlay();
				layerPane.setLayer(next_Button2.panel,20);	
			}
			//*************2�ܰ� ����************************************************	
			if(Level3.progressBar.time==0&&Levelclear_Count==1){
				frame.setVisible(false);
				false_Panel = new False_Panel(Level,playerName,Level2.totalScore,(60-Level1.progressBar.scoreTime)-(60-Level2.progressBar.scoreTime)); 
				false_Panel.setUp();
				repaintTimer.stop();
				Level2.Level_BGM.stopPlayer();
				Level2.progressBarTimer.stop();
				Level2.progressBar.time--;
			}
			//*********************3�ܰ� Ŭ����*************************************
			if(Level3.clearLevel()&&Levelclear_Count==2){
				Level3.progressBarTimer.stop();
				Level3.Level_BGM.stopPlayer();
				Levelclear_Count++;
				next_Button3.setUp();
				next_Button3.next_BGM.startPlay();
				layerPane.setLayer(next_Button3.panel,22);	
			}
			//*************3�ܰ� ����************************************************	
			if(Level3.progressBar.time==0&&Levelclear_Count==2){
				frame.setVisible(false);
				false_Panel = new False_Panel(Level,playerName,Level2.totalScore,(60-Level1.progressBar.scoreTime)-(60-Level2.progressBar.scoreTime)-(60-Level3.progressBar.scoreTime)); 
				false_Panel.setUp();
				repaintTimer.stop();
				Level3.Level_BGM.stopPlayer();
				Level3.progressBarTimer.stop();
				Level3.progressBar.time--;
			}
			//*********************4�ܰ� Ŭ����*************************************
			if(Level4.clearLevel()&&Levelclear_Count==3){
				final_Panel.set_newPlayerName(playerName);
				final_Panel.set_newTotalScore(Level4.totalScore);
				final_Panel.set_newTotalTime((60-Level1.progressBar.scoreTime)-(60-Level2.progressBar.scoreTime)-(60-Level3.progressBar.scoreTime)-(60-Level4.progressBar.scoreTime));
				Level4.progressBarTimer.stop();
				repaintTimer.stop();
				Level4.Level_BGM.stopPlayer();
				final_Panel.Final_BGM.startPlay();
				layerPane.remove(Level4);
				layerPane.setLayer(final_Panel,24);	
				Levelclear_Count++;
			}
			//*************4�ܰ� ����************************************************	
			if(Level4.progressBar.time==0&&Levelclear_Count==3){
				frame.setVisible(false);
				false_Panel = new False_Panel(Level,playerName,Level4.totalScore,(60-Level1.progressBar.scoreTime)-(60-Level2.progressBar.scoreTime)-(60-Level3.progressBar.scoreTime)-(60-Level4.progressBar.scoreTime)); 
				false_Panel.setUp();
				repaintTimer.stop();
				Level4.Level_BGM.stopPlayer();
				Level4.progressBarTimer.stop();
				Level4.progressBar.time--;

			}
		}	
	}//repaint

	class Paint_Thread extends Thread implements Runnable{
		Explan_Panel explan_Panel  = new Explan_Panel();
		PosImageIcon explanation_1 = new PosImageIcon("����1.jpg",0,0,1200,850);
		PosImageIcon explanation_2 = new PosImageIcon("����2.jpg",0,0,1200,850);
		PosImageIcon explanation_3 = new PosImageIcon("����3.jpg",0,0,1200,850);
		PosImageIcon explanation_4 = new PosImageIcon("����4.jpg",0,0,1200,850);
		PosImageIcon explanation_5 = new PosImageIcon("����5.jpg",0,0,1200,850);
		int count = 1;
		JFrame explan_Frame = new JFrame();
  
		public void setUp(){
			explan_Frame.setSize(1200,880);
			explan_Frame.setLocation(160,30);
			explan_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			explan_Frame.setLayout(null);
			explan_Panel.setBounds(0,0,1200,850);
			explan_Frame.add(explan_Panel);
			explan_Frame.setTitle("���� ��Ģ ����");
			explan_Frame.setResizable(false);
			explan_Frame.setVisible(true);
		}
		public void run(){
			System.out.println("����2");
			while(count<7){
				try {
					if(count>=1){explan_Panel.repaint();}
					explan_Thread.sleep(5000);//���ʴ����� �̹����� �ѱ����
				} catch (Exception e2) {
					System.out.println("�����");
				}
			}
		}
		class Explan_Panel extends JPanel{
			protected void paintComponent(Graphics g) {
				System.out.println("����1");
				System.out.println(count);
				if(count==1)      explanation_1.draw(g);
				else if(count==2) explanation_2.draw(g);
				else if(count==3) explanation_3.draw(g);
				else if(count==4) explanation_4.draw(g);
				else if(count==5) explanation_5.draw(g);
				else if(count==6){
					explan_Frame.setVisible(false);
					explan_Frame=null;
					explan_Thread=null;
				}
				count++;
			}
		}
	}
}//class
