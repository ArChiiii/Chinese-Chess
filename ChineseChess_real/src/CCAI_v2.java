import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//random boot

public class CCAI_v2 extends ChessGamePlayer {
	
	private Map<Point, ChessPiece> allChess;
	//private Map<ChessPiece, ArrayList<Point>> possiMove= new HashMap<>();
	private ChineseChess game;
	
	public CCAI_v2 (Color color, ChineseChess game) throws CloneNotSupportedException {
		super("CPT",color);
		this.game=game;
		this.allChess = game.getAllChesses();
	}
	
	public ArrayList<Point> genPossiMove (Map<Point, ChessPiece> allChesses, ChessPiece cp){
		ArrayList<Point> possiMove = new ArrayList<>();
		for (int x=-4; x<5;x++) {
			for (int y=0;y<10;y++) {
				//System.out.println(cp.getLocation()+"gen");
				//System.out.println(new Point (x,y)+"gen");
				//System.out.println(cp.isValid(new Point (x,y), cp.chessRange(cp, new Point (x,y),game.getAllChesses())));
				if (cp.isValid(new Point (x,y), cp.chessRange(cp, new Point (x,y),game.getAllChesses()))){
					//System.out.println(cp.getLocation());
					//System.out.println(new Point (x,y));
					//System.out.println(allChesses.containsKey(new Point (x,y)));
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
		
		int boardpoint=-9999;
		int chessI=-1;
		Point target=null;
		
		//int i = (int)Math.floor(Math.random()*100%getChessInHand().size());
		
		for (int i=0 ;i<getChessInHand().size();i++) {
			if (getChessInHand().getChessPiece(i)==null) {
				continue;
			}
			ChessPiece cp =getChessInHand().getChessPiece(i);
			//System.out.println(cp.toString());
			possiMove=genPossiMove(allChesses,cp);
			for (int j=0 ;j<possiMove.size();j++) {
				Map<Point, ChessPiece> copyallChesses=new HashMap<>();
				copyallChesses.putAll(allChesses);
				if (copyallChesses.containsKey(possiMove.get(j)) && !copyallChesses.get(possiMove.get(j)).getColor().equals(this.getColor())) {
					copyallChesses.remove(cp.getLocation());
					copyallChesses.replace(possiMove.get(j), cp);
				}else {
					copyallChesses.remove(cp.getLocation());
					copyallChesses.put(possiMove.get(j), cp);
				}
				if(boardEvalute(copyallChesses)>boardpoint) {
					chessI=i;
					boardpoint=boardEvalute(copyallChesses);
					target=possiMove.get(j);
				}
			}
		}
		
		/*
		while (possiMove.isEmpty()) {
			i = (int)Math.floor(Math.random()*100%getChessInHand().size());
			cp = getChessInHand().getChessPiece(i);
			possiMove=genPossiMove(allChesses,cp);
		}
		*/
		
		
		//int index = (int)Math.floor(Math.random()*100%possiMove.size());
		bestMove.add(getChessInHand().getChessPiece(chessI).getLocation());
		bestMove.add(target);
		return bestMove;
	}
	
	public int boardEvalute (Map<Point, ChessPiece> allChesses) {
		int boardpoint=0;
		//System.out.println(allChesses.values().toArray(new ChessPiece[0]));
		int [] point = {General.VALUE,Chariot.VALUE,Horse.VALUE,Cannon.VALUE,Advisor.VALUE,Elephant.VALUE,Soildier.VALUE};
		ChessPiece[] allchessPiece= allChesses.values().toArray(new ChessPiece[0]);
		
		for (ChessPiece cp : allchessPiece) {
			if(cp.getColor().equals(this.getColor())) {
				boardpoint+=point[cp.getOrder()];
				boardpoint+=cp.getValue();
			}else {
				boardpoint-=point[cp.getOrder()];
				boardpoint+=cp.getValue();
			}
		}
		return boardpoint;
	}
	
	public void makeMove () {
		//System.out.print(game.getAllChesses());
		//System.out.print(this.allChess);
		
		ArrayList<Point> bestMove =calBestMove(game.getAllChesses());
		//System.out.println(bestMove.get(0));
		//System.out.println(bestMove.get(1));
		game.makeMove(1, bestMove.get(0), bestMove.get(1));
	}

}
