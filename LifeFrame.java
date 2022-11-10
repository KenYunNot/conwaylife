package life;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;
import javax.swing.Timer;


class LifeFrame extends JPanel{
	public final int RECT_WIDTH = 16;
	public final int RECT_HEIGHT = 16;
	public final int BOARD_WIDTH = 300;
	public final int BOARD_HEIGHT = 120;
	int speed;
	
	int[][][] board = new int[BOARD_WIDTH][BOARD_HEIGHT][2];
	int x;
	int y;
	
	Timer timerLife = new Timer(5, new ActionListener(){
		public void actionPerformed(ActionEvent e){
			life(board); 
		}
	});
	
	Timer timerPaint = new Timer(5, new ActionListener(){
		public void actionPerformed(ActionEvent e){
			repaint();
		}
	});
	
	public LifeFrame(){
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				x = e.getX() / 16;
				y = e.getY() / 16;
				
				if (x >= 0 && x <= getWidth() && y >= 0 && y <= getHeight()) {
					board[x][y][0] = (board[x][y][0] + 1) % 2;
					repaint();
				}
			}
		});
		
		timerPaint.start();
		
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				x = e.getX() / 16;
				y = e.getY() / 16;
						
				if (x >= 0 && x <= getWidth() && y >= 0 && y <= getHeight()) {
					board[x][y][0] = 1;
					repaint();
				}
			}			
		});
	}
	
	public void life(int[][][] board){
		int neighbors = 0;
		int numCells = 0;
		
		// Checks each cell, disregarding the border and the cushion space
		try{
			for (int row = 0; row < BOARD_WIDTH; row++){
				for (int column = 0; column < BOARD_HEIGHT; column++){
					
					if (board[row][column][0] == 1)
						numCells++;
					
					//North
					if (row < BOARD_WIDTH && board[row+1][column][0] == 1)
						neighbors += 1;
						
					//Northeast
					if (row < BOARD_WIDTH && column < BOARD_HEIGHT && board[row+1][column+1][0] == 1)
						neighbors += 1;
						
					//East
					if (column < BOARD_HEIGHT && board[row][column+1][0] == 1)
						neighbors += 1;
							
					//Southeast
					if (row >= 0 && column < BOARD_HEIGHT && board[row-1][column+1][0] == 1)
						neighbors += 1;
						
					//South
					if (row >= 0 && board[row-1][column][0] == 1)
						neighbors += 1;
						
					//Southwest
					if (row >= 0 && column >= 0 && board[row-1][column-1][0] == 1)
						neighbors += 1;
						
					//West
					if (column >= 0 && board[row][column-1][0] == 1)
						neighbors += 1;
						
					//Northwest
					if (row < BOARD_WIDTH && column >= 0 && board[row+1][column-1][0] == 1)
						neighbors += 1;
					
					if (board[row][column][0] == 1){
						if (neighbors <= 1 || neighbors >= 4)
							board[row][column][1] = 0;
						
						else if (neighbors == 2 || neighbors == 3)
							board[row][column][1] = 1;
					}
					
					else if (board[row][column][0] == 0 && neighbors == 3)
						board[row][column][1] = 1;	
					
					neighbors = 0;
				}
			}
			
			for (int row = 0; row < board.length; row++){
				for (int column = 0; column < board[row].length; column++){
					board[row][column][0] = board[row][column][1];
					numCells -= board[row][column][0] - board[row][column][1];
				}
			}
		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//Each cell 8 pixels tall, 8 pixels wide
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for (int i = 0; i < board.length-1; i++){
			for (int j = 0; j < board[i].length-1; j++){
				g.setColor(Color.YELLOW);
	
				if (board[i+1][j+1][0] == 1){
					g.fillRect(i*16, j*16, RECT_WIDTH, RECT_HEIGHT);
				}
				
				else{
					g.setColor(Color.GRAY);
					g.fillRect(i*16, j*16, RECT_WIDTH, RECT_HEIGHT);
				}
			}
		}
		
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < getWidth(); i += RECT_WIDTH){
			for (int j = 0; j < getHeight(); j += RECT_HEIGHT){
				g.drawRect(i, j, RECT_WIDTH, RECT_HEIGHT);
			}
		}
	}
}