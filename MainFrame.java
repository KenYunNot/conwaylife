package life;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame{
	SpeedFrame t = new SpeedFrame();
	LifeFrame l = new LifeFrame();
	boolean started = false;
		
	Timer timer = new Timer(5, new ActionListener(){
		public void actionPerformed(ActionEvent e){
			l.timerLife.setDelay(t.getSpeed());
		}
	});
	
	public MainFrame(){
		setLayout(new BorderLayout());
		
		JPanel entireBoard = new JPanel();
		JPanel p = new JPanel();
		JPanel p2 = new JPanel();
		GridLayout g = new GridLayout(1, 4);
		g.setHgap(5);
		p.setLayout(g);
		p2.setLayout(new BorderLayout());
		
		p2.add(new JButton("Speed"), BorderLayout.WEST);
		t.setBackground(Color.GRAY);
		p2.add(t, BorderLayout.CENTER);
		p.add(p2);
		
		JButton start = new JButton("Start");
		start.addActionListener(new Start());
		p.add(start);
		
		JButton stop = new JButton("Stop");
		stop.addActionListener(new Stop());
		p.add(stop);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new Clear());
		p.add(clear);
		
		p.setBorder(new EmptyBorder(5,5,5,5));
		entireBoard.setLayout(new BorderLayout());
		
		entireBoard.add(l, BorderLayout.CENTER);
		entireBoard.add(p, BorderLayout.SOUTH);
		
		add(entireBoard);
		
		timer.start();
	}
	
	class Start implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (started == false) {
				started = true;
				l.timerLife.start();
			}
		}
	}
	
	class Stop implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (started == true) {
				started = false;
				l.timerLife.stop();
			}
		}
	}
	
	class Clear implements ActionListener{
		public void actionPerformed(ActionEvent e){
			l.timerLife.stop();
			l.board = new int[l.board.length][l.board[0].length][l.board[0][0].length];
		}
	}
	
	public static void main(String[] args){
		MainFrame frame = new MainFrame();
		
		frame.setTitle("Life");
		frame.setSize(1200, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}