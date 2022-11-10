package life;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SpeedFrame extends JPanel{
	final int MAX_INTERVALS = 12;
	final int DELAY_INTERVAL = 50;
	final int MIN_DELAY = 5;
	
	int[] speedBar = new int[MAX_INTERVALS];
	int speedSetting = 0;
	int speedVal;
	
	Timer timer = new Timer(5, new ActionListener(){
		public void actionPerformed(ActionEvent e){
			speedVal = ((DELAY_INTERVAL * MAX_INTERVALS) - (DELAY_INTERVAL * speedSetting)) + MIN_DELAY;
		}
	});
	
	public SpeedFrame(){
		timer.start();
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				speedSetting = e.getX()/ (getWidth()/MAX_INTERVALS);
				
				for (int i = 0; i < speedBar.length; i++){
					if (i <= speedSetting)
						speedBar[i] = 1;
					
					else
						speedBar[i] = 0;
				}
				
				repaint();
			}
		});
		
	}
	
	public int getSpeed(){
		return speedVal;
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < speedBar.length; i++){
			if (i == 11)
				g.drawRect((getWidth()/12) * i, 0, getWidth()/12 + 3, getHeight());
			
			else
				g.drawRect((getWidth()/12) * i, 0, getWidth()/12, getHeight());
		}
		
		for (int i = 0; i < speedBar.length; i++){
			if (speedBar[i] == 1){
				if (i == 11){
					g.setColor(Color.YELLOW);
					g.fillRect((getWidth()/12) * i, 0, getWidth()/12 + 7, getHeight());
				}
				
				else{
					g.setColor(Color.YELLOW);
					g.fillRect((getWidth()/12) * i, 0, getWidth()/12, getHeight());
				}
			}
			
			else {
				g.setColor(Color.GRAY);
				g.fillRect((getWidth()/12) * i, 0, getWidth()/12, getHeight());
			}
		}
		
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < speedBar.length; i++){
			if (i == 11)
				g.drawRect((getWidth()/12) * i, 0, getWidth()/12 + 7, getHeight());
			
			else
				g.drawRect((getWidth()/12) * i, 0, getWidth()/12, getHeight());
		}
	}
}