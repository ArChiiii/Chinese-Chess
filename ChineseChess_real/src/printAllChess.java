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
public class printAllChess {
	
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
		
		public printAllChess (Map<Point, ChessPiece> allChesses) {
			this.allChesses=allChesses;
			frame = new JFrame("Chinese Chess Game by Archie");
			frame.setSize(750,715);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new BorderLayout());
			frame.setResizable(false);
			
			chineseChessPanel = new ChineseChessPanel();
			frame.add(chineseChessPanel, BorderLayout.CENTER);
			
			rightPanel= new JPanel(new BorderLayout());
			frame.add(rightPanel, BorderLayout.EAST);
			frame.setVisible(true);
		
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

				
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		}
}
