import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.omg.CORBA.INTERNAL;

import res.SoundPlayer;


public class False_Panel extends JPanel implements ActionListener {
	private Cursor cursor;
	private Image img;
	private Point point = new Point(0,0);
	
	public SoundPlayer false_BGM= new SoundPlayer("����.wav");
	private PosImageIcon falseImage = new PosImageIcon("����������.jpg",0,0,1200,850);

	private JLayeredPane layerPane = new JLayeredPane();	
	private Correct1_Panel correct1_Panel = new Correct1_Panel();
	private Correct2_Panel correct2_Panel = new Correct2_Panel();
	private Correct3_Panel correct3_Panel = new Correct3_Panel();
	private Correct4_Panel correct4_Panel = new Correct4_Panel();
	
	RankView_Panel rankView_Panel;
	
	private JButton[] btn = new JButton[4];
	
	private JFrame frame = new JFrame(); 
	private JFrame newFrame = new JFrame();
	
	private String newPlayerName="";
	private int newTotalTime=0;
	private int newTotalScore=0;
	private int Level=0;
	
	public  False_Panel(int Level,String playerName,int totalScore,int totalTime){
		this.Level = Level;
		this.newPlayerName = playerName;
		this.newTotalScore = totalScore;
		this.newTotalTime = totalTime;
	}
	public void setUp(){
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("���̹���.jpg");
		cursor = tk.createCustomCursor(img,point,"���̹���.jpg");
		frame.setCursor(cursor);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200,850);
		frame.setLocation(300,70);
		frame.setTitle("����~");
		this.setLayout(null);
		this.setSize(1200,850);
		false_BGM.startPlay();

		for(int i=0;i<4;i++){
			btn[i] = new JButton();
			btn[i].setSize(300,850);
		}
		for(int i=0;i<4;i++){
			this.btn[i].setOpaque(false);
			this.btn[i].setBorderPainted(false);
			this.btn[i].setContentAreaFilled(false);
			if(i != 2) this.btn[i].addActionListener(this);
			this.add(btn[i]);
		}

		btn[2].addActionListener(new RankView_Panel());

		btn[0].setLocation(0,0);
		btn[1].setLocation(300,0);
		btn[2].setLocation(600,0);
		btn[3].setLocation(900,0);
		//**********���������� ó������ �ߴ� �г�******************************
		layerPane.add(this, new Integer(4));
		//**********1�ܰ迡�� ���亸�������� �г�******************************
		layerPane.add(correct1_Panel, new Integer(3));
		correct1_Panel.Button.addActionListener(this);
		//**********2�ܰ迡�� ���亸�������� �г�******************************
		layerPane.add(correct2_Panel, new Integer(2));
		correct2_Panel.Button.addActionListener(this);
		//**********3�ܰ迡�� ���亸�������� �г�******************************
		layerPane.add(correct3_Panel, new Integer(1));
		correct3_Panel.Button.addActionListener(this);
		//**********4�ܰ迡�� ���亸�������� �г�******************************
		layerPane.add(correct4_Panel, new Integer(0));
		correct4_Panel.Button.addActionListener(this);
		
		this.setSize(1200,850); correct1_Panel.setSize(1200,850); correct2_Panel.setSize(1200,850);
		frame.setResizable(false);
		frame.setSize(1200,850);
		frame.getContentPane().add(layerPane);
		frame.setVisible(true);
	}

	protected void paintComponent(Graphics g) {
		falseImage.draw(g);
	}
	public void actionPerformed(ActionEvent e) {
		//**************������� ��������*******************
		if(e.getSource()==btn[0]){
			frame.setVisible(false);
			false_BGM.stopPlayer();
			Main first_Panel = new Main();
			first_Panel.setUp();
		}
		//**************���亸��������*********************
		else if(e.getSource()==btn[1]){
			frame.setSize(1200,890);
			if(Level==1) layerPane.setLayer(correct1_Panel,5);
			else if(Level==2) layerPane.setLayer(correct2_Panel,5);
			else if(Level==3) layerPane.setLayer(correct3_Panel,5);
			else layerPane.setLayer(correct4_Panel,5);
		}
		//**************������������***************************
		else if(e.getSource()==btn[3]){
			System.exit(1);
		}
		//*************1�ܰ� ���������� �г��� ������ ��������*******
		else if(e.getSource()==correct1_Panel.Button){
			System.exit(1);
		}
		//*************2�ܰ� ���������� �г��� ������ ��������********
		else if(e.getSource()==correct2_Panel.Button){
			System.exit(1);
		}
		else if(e.getSource()==correct3_Panel.Button){
			System.exit(1);
		}
		else if(e.getSource()==correct4_Panel.Button){
			System.exit(1);
		}
		

	}
	class Correct1_Panel extends JPanel{
		JButton Button = new JButton(new ImageIcon("1�ܰ�����.jpg"));
		public Correct1_Panel(){
			setUp();
		}
		private void setUp(){
			this.setLayout(null);
			this.setSize(1200,850);
			Button.setSize(1200,850);
			Button.setLocation(0,0);
			this.add(Button);
		}
	}
	class Correct2_Panel extends JPanel{
		JButton Button = new JButton(new ImageIcon("2�ܰ�����.jpg"));
		public Correct2_Panel(){
			setUp();
		}
		private void setUp(){
			this.setLayout(null);
			this.setSize(1200,850);
			Button.setSize(1200,850);
			Button.setLocation(0,0);
			this.add(Button);
		}
	}
	class Correct3_Panel extends JPanel{
		JButton Button = new JButton(new ImageIcon("3�ܰ�����.jpg"));
		public Correct3_Panel(){
			setUp();
		}
		private void setUp(){
			this.setLayout(null);
			this.setSize(1200,850);
			Button.setSize(1200,850);
			Button.setLocation(0,0);
			this.add(Button);
		}
	}
	class Correct4_Panel extends JPanel{
		JButton Button = new JButton(new ImageIcon("4�ܰ�����.jpg"));
		public Correct4_Panel(){
			setUp();
		}
		private void setUp(){
			this.setLayout(null);
			this.setSize(1200,850);
			Button.setSize(1200,850);
			Button.setLocation(0,0);
			this.add(Button);
		}
	}
	class RankView_Panel extends JPanel implements ActionListener{
		PosImageIcon img = new PosImageIcon("����ȭ��.jpg",0,0,1200,850);
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
	
			ArrayList<Rank_Information> rank_list = new ArrayList<Rank_Information>();
			ArrayList<JLabel> label_list = new ArrayList<JLabel>();
			newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			newFrame.setSize(1200,850);
			newFrame.setLocation(300,70);
			Toolkit tk = Toolkit.getDefaultToolkit();
			
			newFrame.setCursor(cursor);
			
			this.setLayout(null);	
			this.setSize(1200,850);
			
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
			rank_list.add(new Rank_Information(newPlayerName,newTotalScore,newTotalTime));
			//***********************������ �ϱ� ����****************************************************
			Collections.sort(rank_list,Collections.reverseOrder());

			for(Rank_Information rank:rank_list){
				rankPrintCount++;
				label_list.add(new JLabel(rankPrintCount+"�� �̸� :"+rank.playerName+" ���� :"+rank.totalScore));
				if(rankPrintCount==5) break;
			}
			for(JLabel label:label_list){
				label.setForeground(Color.BLACK);
				label.setFont(new Font("1��ȭ�翬ȭ R",Font.CENTER_BASELINE,50));
				label.setSize(600,100);
				label.setLocation(x+20,y+=100);
				this.add(label);
			}
			newFrame.add(this);
			newFrame.setResizable(false);
			newFrame.setTitle("��������");
			newFrame.setVisible(true);
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
}//class

