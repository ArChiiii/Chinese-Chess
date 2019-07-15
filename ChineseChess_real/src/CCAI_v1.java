import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//random boot

public class CCAI_v1 extends ChessGamePlayer {
	
	private Map<Point, ChessPiece> allChess;
	//private Map<ChessPiece, ArrayList<Point>> possiMove= new HashMap<>();
	private ChineseChess game;
	
	public CCAI_v1 (Color color, ChineseChess game) throws CloneNotSupportedException {
		super("CPT",color);
		this.game=game;
		this.allChess = game.getAllChesses();
	}
	
	public ArrayList<Point> genPossiMove (Map<Point, ChessPiece> allChesses, ChessPiece cp){
		ArrayList<Point> possiMove = new ArrayList<>();
		for (int x=-4; x<5;x++) {
			for (int y=0;y<10;y++) {
				if (cp.isValid(new Point (x,y), game.chessRange(cp, new Point (x,y)))){
					System.out.println(cp.getLocation());
					System.out.println(new Point (x,y));
					System.out.println(allChesses.containsKey(new Point (x,y)));
					if (!allChesses.containsKey(new Point (x,y))) {
						possiMove.add(new Point (x,y));
					}else if (!allChesses.get(new Point (x,y)).getColor().equals(this.getColor())) {
						possiMove.add(new Point (x,y));
					}
				}
			}
		}
		return possiMove;
	}
	
	public ArrayList<Point> calBestMove (Map<Point, ChessPiece> allChesses){
		ArrayList<Point> possiMove = new ArrayList<>();
		ArrayList<Point> bestMove = new ArrayList<>();
		int i = (int)Math.floor(Math.random()*100%getChessInHand().size());
		ChessPiece cp =getChessInHand().getChessPiece(i);
		//System.out.print(allChesses);
		possiMove=genPossiMove(allChesses,cp);
	
		while (possiMove.isEmpty()) {
			i = (int)Math.floor(Math.random()*100%getChessInHand().size());
			cp = getChessInHand().getChessPiece(i);
			possiMove=genPossiMove(allChesses,cp);
		}
		int index = (int)Math.floor(Math.random()*100%possiMove.size());
		bestMove.add(cp.getLocation());
		bestMove.add(possiMove.get(index));
		return bestMove;
	}
	
	public void makeMove () {
		System.out.print(game.getAllChesses());
		System.out.print(this.allChess);
		
		ArrayList<Point> bestMove =calBestMove(game.getAllChesses());
		System.out.println(bestMove.get(0));
		System.out.println(bestMove.get(1));
		game.makeMove(1, bestMove.get(0), bestMove.get(1));
	}

}
