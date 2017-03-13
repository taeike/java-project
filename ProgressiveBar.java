
import java.awt.*;
import java.applet.*;

public class ProgressiveBar extends Canvas {		
	public int time=0, maxTime=0,scoreTime=0;								// maxTime�� ���� �Ѿ󸶱��� �����ΰ� �ϴ� �ð�, time�� ����ð�
	int down=0;
	public ProgressiveBar(int maxTime) {			// �����ڷ� maxTime�� ������
		this.time = maxTime+3;
		this.maxTime = maxTime+3;
		setForeground(new Color(255,255,200));		// RGB���� �̿��� ���� ����
		setBackground(Color.BLACK);		
		setSize(maxTime * 3, 30);			    	// progressbar�� ������ ����
	}
	int getTime() {									// �ð��� ������
		return (time);
	}
	void setTime(int time) {						// time��ŭ �ð��� ����
		this.time = time;
		repaint();
	}
	int getMaxTime() {								// �� �󸶱��� �����ΰ� �ϴ� �ð��� ������
		return (maxTime);
	}
	void setMAxTime(int maxTime) {					// �� �󸶱��� �ɸ��� �ð��� ����
		this.maxTime = maxTime;
		repaint();
	}
	public void repaint() {							// ������Ʈ
		update(getGraphics());
	}
	public void update(Graphics g) {
		paint(g);
	}
	public void paint(Graphics g) {					// ���������� ���α׷����ٸ� �׷���
	
		//System.out.println(time);
		down += 600/maxTime;
		g.fillRect(0, 0, down, 30);
		time--;
		scoreTime++;
	}
}
