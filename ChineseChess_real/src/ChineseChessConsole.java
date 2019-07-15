import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class is used for modeling a console for the Chinese Chess game. It
 * provides the user interface for the game.
 * 
 * @author Kenneth Wong n Archie
 */
public class ChineseChessConsole {
	private final static int MAX_CHESS_NUM = 16; // max. no. of cards each player
												// holds
	private ArrayList<ChessGamePlayer> playerList; // the list of players
	//private ArrayList<Hand> handsOnTable; // the list of hands played on the
	private Scanner scanner; // the scanner for reading user in put
	private int activePlayer = -1; // the index of the active player
	private Map<Point, ChessPiece> allChesses;
	
	/**
	 * Creates and returns an instance of the BigTwoConsole class.
	 * 
	 * @param game
	 *            a Chinese Chess object associated with this console
	 */
	public ChineseChessConsole (ChineseChess CC) {
		playerList = CC.getPlayerList();
		allChesses = CC.getAllChesses();
		scanner = new Scanner(System.in);
	}

	/**
	 * Sets the index of the active player.
	 * 
	 * @param activePlayer
	 *            the index of the active player (i.e., the player who can make
	 *            a move)
	 */
	public void setActivePlayer(int activePlayer) {
		if (activePlayer < 0 || activePlayer >= playerList.size()) {
			this.activePlayer = -1;
		} else {
			this.activePlayer = activePlayer;
		}
	}

	/**
	 * Returns an array of indices of the cards selected through the console.
	 * 
	 * @return an array of indices of the cards selected, or null if no valid
	 *         cards have been selected
	 */
	public Point getSelected() {
		//boolean[] selected = new boolean[MAX_CHESS_NUM];
		Point chessLoca = null;

		ChessGamePlayer player = playerList.get(activePlayer);
		System.out.print(player.getName() + "'s turn: ");
		String input = scanner.nextLine();

		StringTokenizer st = new StringTokenizer(input);
		while (st.hasMoreTokens()) {
			try {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if (x >= -4 && x < 5) {
					if (y >=0 && y < 10)
						chessLoca = new Point(x,y);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		return chessLoca;
	}
	
	public Point toTarget() {
		//boolean[] selected = new boolean[MAX_CHESS_NUM];
		Point target = null;

		ChessGamePlayer player = playerList.get(activePlayer);
		System.out.print(player.getName() + "'s move: ");
		String input = scanner.nextLine();

		StringTokenizer st = new StringTokenizer(input);
		while (st.hasMoreTokens()) {
			try {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if (x >= -4 && x < 5) {
					if (y >=0 && y < 10)
					target = new Point(x,y);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		return target;
	}
	
	/**
	 * Redraws the console.
	 */
	public void repaint() {
			for (int y= 10;y>-1 ;y-- ) {
				if (y!=10) {
					System.out.print(y+"|");
				}else {
					System.out.print(" ");
				}
				for (int x = -4; x < 5; x++) {
				if (y==10) {
					System.out.print(x+"");
				}
				if (allChesses.containsKey(new Point(x,y))){
					System.out.print(allChesses.get(new Point(x,y)).toString()+" ");
				}else {
					System.out.print(" ");
				}
				}
				System.out.println("");

		}
		
	}
}
