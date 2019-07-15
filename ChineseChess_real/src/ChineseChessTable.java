import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ChineseChessTable implements ChessGameTable {
	
	private ChessGame game;
	private int activePlayer;
	private Point selected ;
	private Point target;
	private Map<Point, ChessPiece> allChesses;
	
	private JFrame frame;
	private JPanel chineseChessPanel;
	private JTextArea msgArea;
	//private Image [][] chessImages = new Image [2][16];
	private JMenuBar menuBar;
	private JPanel rightPanel;
	private Image background;
	private Image[] selectedImg =new Image [3];
	
	public ChineseChessTable (ChineseChess game) {
		this.game=game;
		this.allChesses=game.getAllChesses();
		
		frame = new JFrame("Chinese Chess Game by Archie");
		frame.setSize(750,715);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		chineseChessPanel = new ChineseChessPanel();
		frame.add(chineseChessPanel, BorderLayout.CENTER);
		
		rightPanel= new JPanel(new BorderLayout());
		frame.add(rightPanel, BorderLayout.EAST);
		
		msgArea= new JTextArea();
		msgArea.setLineWrap(true);
		msgArea.setSize(750/5, 715);
		msgArea.setForeground(Color.black);
		msgArea.setBackground(Color.WHITE);
		msgArea.setEditable(false);
		msgArea.setFont(new Font("Serif", Font.PLAIN, 20));
		JScrollPane jsp = new JScrollPane(msgArea);
		rightPanel.add(jsp, BorderLayout.CENTER);
		
		menuBar =new JMenuBar();
		JMenu menu =new JMenu("Game");
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new QuitMenuItemListener());
		JMenuItem restart = new JMenuItem("Restart");
		restart.addActionListener(new RestartMenuItemListener());
		menu.add(restart);
		menu.add(quit);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
	
		frame.setVisible(true);
		
		
	}

	@Override
	public void setActivePlayer(int activePlayer) {
		if (activePlayer < 0 || activePlayer >= game.getPlayerList().size()) {
			this.activePlayer = -1;
		} else {
			this.activePlayer = activePlayer;
		}
		
	}

	@Override
	public Point getSelected() {
		// TODO Auto-generated method stub
		return selected;
	}
	
	@Override
	public Point getTarget() {
		// TODO Auto-generated method stub
		return target;
	}

	@Override
	public void resetSelected() {
		selected=null;
		
	}

	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printMsg(String msg) {
		this.msgArea.append(msg);
	}

	@Override
	public void clearMsgArea() {
		// TODO Auto-generated method stub
		this.msgArea.setText(null);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}
	
	public void restart() {
		// TODO Auto-generated method stub
		
	}
	
	
	class ChineseChessPanel extends JPanel implements MouseListener, MouseMotionListener{

		private Point closestToMousePoint=null;
		
		public ChineseChessPanel() {
			setLayout(null);
			this.setBackground(Color.DARK_GRAY);
			insertImage();
			
			addMouseListener(this);
			addMouseMotionListener(this);
		}
		
		public void insertImage () {
			//insert background image 
			background = new ImageIcon("chessboard.png").getImage();
			selectedImg[0]= new ImageIcon("selected_red.png").getImage();
			selectedImg[1]= new ImageIcon("selected_black.png").getImage();
			selectedImg[2]= new ImageIcon("selected.png").getImage();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(background, 0, 0,586,660,this);
			//System.out.println(background);
			
			for (int x=-4;x<5;x++) {
				for (int y=9;y>-1;y--) {
					Point loca =new Point (x,y);
					if (allChesses.containsKey(loca)) {
						g.drawImage(allChesses.get(loca).getChessimage(), (int) (29+59*(loca.getX()+4)-5), (int)(26+61*(9-loca.getY())-15),59,61,this);
					}
				}
			}
			drawMouseBlock(g);
			
			
		}
		
		private void drawMouseBlock(Graphics g) {
			if (closestToMousePoint!=null) {
				int blockIdx=(activePlayer==0)?0:1;
				g.drawImage(selectedImg[blockIdx],(int) (29+59*(closestToMousePoint.getX()+4)-5), (int)(26+61*(9-closestToMousePoint.getY())-15),59,61,this);
				//System.out.println(selectedImg[blockIdx]);
			}
			if (selected!=null) {
				int blockIdx=(activePlayer==0)?0:1;
				g.drawImage(selectedImg[blockIdx],(int) (29+59*(selected.getX()+4)-5), (int)(26+61*(9-selected.getY())-15),59,61,this);
				//System.out.println(selectedImg[blockIdx]);
			}
		}
		
		
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
		/*
			if (arg0.getX()>23 && arg0.getY()>10 && arg0.getX()<556 && arg0.getY()<621) {
				int x = (int) Math.floor((arg0.getX()-24)/59)-4;
				int y = 9-(int) Math.floor((arg0.getY()-11)/61);
				closestToMousePoint= new Point (x,y);
			}*/
			if (allChesses.containsKey(closestToMousePoint) && allChesses.get(closestToMousePoint).color.equals(game.getPlayerList().get(activePlayer).getColor())) {
				selected=closestToMousePoint;
			}else if (selected == null && allChesses.containsKey(closestToMousePoint) && !allChesses.get(closestToMousePoint).color.equals(game.getPlayerList().get(activePlayer).getColor())){
				printMsg("Not Your chess\n");
			}else if (selected!=null) {
				target=closestToMousePoint;
				game.makeMove(activePlayer, selected, target);
			}
			repaint();
			if(activePlayer==1) {
				game.getPlayerList().get(1).makeMove();
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			closestToMousePoint=null;
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			if (arg0.getX()>23 && arg0.getY()>10 && arg0.getX()<556 && arg0.getY()<621) {
				int x = (int) Math.floor((arg0.getX()-24)/59)-4;
				int y = 9-(int) Math.floor((arg0.getY()-11)/61);
				if(x>-5 && x<5 && y>-1 && y<10)
					closestToMousePoint= new Point (x,y);
				//System.out.println(closestToMousePoint);
				repaint();
			}
		}
		
	}
	
	class RestartMenuItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			restart() ;
		}
		
	}
	
	class QuitMenuItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
			
		}
		
	}

}
