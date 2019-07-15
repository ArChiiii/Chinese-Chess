import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CCAI_v4 extends ChessGamePlayer {
	
	private Map<Point, ChessPiece> allChess = new HashMap<>();
	//private Map<ChessPiece, ArrayList<Point>> possiMove= new HashMap<>();
	private ChineseChess game;
	boolean maximizing=true;
	ArrayList<Point> BestMove = new ArrayList<>();
	Map<Point, ChessPiece> bestMoveBoard = new HashMap<>();
	int depthtogo = 5;
	int c =0;
	int alpha = -9999;
	int beta = 9999;
	
	
	public CCAI_v4 (Color color, ChineseChess game) throws CloneNotSupportedException {
		super("CPT_v4",color);
		this.game=game;
	}
	
	public ArrayList<Point> genPossiMove (Map<Point, ChessPiece> allChesses, ChessPiece cp){
		ArrayList<Point> possiMove = new ArrayList<>();
		if (cp.getOrder()==1 || cp.getOrder()==3) {
			for (int y=0; y<10;y++) {
				if (cp.isValid(new Point (cp.getLocation().x,y), cp.chessRange(cp, new Point (cp.getLocation().x,y),allChesses))){
					if (!allChesses.containsKey(new Point (cp.getLocation().x,y))) {
						possiMove.add(new Point (cp.getLocation().x,y));
					}else if (!allChesses.get(new Point (cp.getLocation().x,y)).getColor().equals(cp.getColor())) {
						possiMove.add(new Point (cp.getLocation().x,y));
					}
				}
			}
			
			for (int x=-4; x<5;x++) {
				if (cp.isValid(new Point (x,cp.getLocation().y), cp.chessRange(cp, new Point (x,cp.getLocation().y),allChesses))){
					if (!allChesses.containsKey(new Point (x,cp.getLocation().y))) {
						possiMove.add(new Point (x,cp.getLocation().y));
					}else if (!allChesses.get(new Point (x,cp.getLocation().y)).getColor().equals(cp.getColor())) {
						possiMove.add(new Point (x,cp.getLocation().y));
					}
				}
			}
		}else {
			for (int y=cp.getLocation().y-2 ; y<cp.getLocation().y+3;y++) {
				for (int x=cp.getLocation().x-2; x<cp.getLocation().x+3;x++) {
					if (x<-4 || x>4 || y>9 || y<0) {
						continue;
					}
					if (cp.isValid(new Point (x,y), cp.chessRange(cp, new Point (x,y),allChesses))){
						if ((!allChesses.containsKey(new Point (x,y)))) {
							possiMove.add(new Point (x,y));
						}else if (!allChesses.get(new Point (x,y)).getColor().equals(cp.getColor())) {
							possiMove.add(new Point (x,y));
						}
					}
				}
			}
		}
		/*
		for (int x=-4; x<5;x++) {
			for (int y=0;y<10;y++) {
				if (cp.isValid(new Point (x,y), cp.chessRange(cp, new Point (x,y),game.getAllChesses()))){
					if (!allChesses.containsKey(new Point (x,y))) {
						possiMove.add(new Point (x,y));
					}else if (!allChesses.get(new Point (x,y)).getColor().equals(this.getColor())) {
						possiMove.add(new Point (x,y));
					}
				}
			}
		}
		*/
		return possiMove;
	}
	
	public ArrayList <Map<Point, ChessPiece>> allboardpossimove (Map<Point, ChessPiece> allChesses, ChessGamePlayer player) throws CloneNotSupportedException{
		ArrayList <Map<Point, ChessPiece>> allboardpossimove =new ArrayList<>();
		
		ChessPieceList clonecpl =new ChessPieceList();
		for(Map.Entry<Point, ChessPiece> entry : allChesses.entrySet()){
			if (entry.getValue().getColor().equals(player.getColor())){
				ChessPiece cpclone = (ChessPiece) entry.getValue().clone();
				cpclone.setLocation(entry.getKey());
				clonecpl.addChessPiece(cpclone);
			}
		}
		clonecpl.sort();
		/*
		if (test) {
			for (int i= 0; i<clonecpl.size();i++) {
				System.out.println(clonecpl.getChessPiece(i));
				System.out.print(clonecpl.getChessPiece(i).getOrder());
			}
			test =false;
		}
		*/
		for (int i=0; i<clonecpl.size(); i++) {
			for (Point target : genPossiMove(allChesses,clonecpl.getChessPiece(i))) {
				Map<Point, ChessPiece> move = new HashMap<>();
				move.putAll(allChesses);
				allboardpossimove.add(boardpossimove(move, clonecpl.getChessPiece(i),target));
			}
		}
		return allboardpossimove ;
	}
	
	public Map<Point, ChessPiece> boardpossimove (Map<Point, ChessPiece> copyallChesses, ChessPiece cp, Point target){
		if (copyallChesses.containsKey(target) && !copyallChesses.get(target).getColor().equals(cp.getColor())) {
			copyallChesses.remove(target);
			copyallChesses.remove(cp.getLocation());
			copyallChesses.put(target, cp);
			//cp.setLocation(target);
		}else if (!copyallChesses.containsKey(target)) {
			copyallChesses.remove(cp.getLocation());
			copyallChesses.put(target, cp);
			//cp.setLocation(target);
		}
		return copyallChesses;
	}
	
	public int minimax (Map<Point, ChessPiece> allChesses, int depth,int alpha, int beta, boolean maximizing) throws CloneNotSupportedException {
		
		if (depth==0 || endOfGame(allChesses)>-1) {
			
			/*
			if (boardEvalute(allChesses)==-110) {
				printAllChess print = new printAllChess(allChesses);
			}
			
			*/
			
			//System.out.println(c);
			c++;
			//System.out.println(boardEvalute(allChesses));
			return boardEvalute(allChesses);
		}
		if (maximizing==true) {
			int max = -9999;
			for (Map<Point, ChessPiece> possi : allboardpossimove(allChesses, game.getPlayerList().get(1))) {
				int evalu = minimax(possi, depth-1,alpha,beta,false);
				
				
				if (evalu>max && depth == depthtogo) {
					//System.out.println(evalu + "true");
					bestMoveBoard=possi;
				}
				max = Math.max(max, evalu);
				
				alpha = Math.max(alpha, evalu);
				if (beta <= alpha) {
					break;
				}
				
				
				
			}
			return max;
			
		}else {
			int min =9999;
			for (Map<Point, ChessPiece> possi : allboardpossimove(allChesses, game.getPlayerList().get(0))) {
				int evalu = minimax(possi, depth-1,alpha,beta,true);
				
				min = Math.min(min, evalu);
				
				beta = Math.min(beta, evalu);
				if (beta <= alpha) {
					break;
				}
				
				
				
			}
			return min;
		}
	}
	
	private int endOfGame(Map<Point, ChessPiece> allChesses) {
		if (!allChesses.values().contains(game.getPlayerList().get(0).getChessInHand().getChessPiece(0))){
			return 0;
		}else if (!allChesses.values().contains(game.getPlayerList().get(1).getChessInHand().getChessPiece(0)))
			return 1;
		return -1;
	}

	public int boardEvalute (Map<Point, ChessPiece> allChesses) throws CloneNotSupportedException {
		
		int boardpoint=0;
		int [] point = {General.VALUE,Chariot.VALUE,Horse.VALUE,Cannon.VALUE,Advisor.VALUE,Elephant.VALUE,Soildier.VALUE};
		
		ChessPieceList redtroops =new ChessTroops(Color.RED);
		ChessPieceList blacktroops =new ChessTroops(Color.BLACK);
		
		for(Map.Entry<Point, ChessPiece> entry : allChesses.entrySet()){
			ChessPiece cpclone = (ChessPiece) entry.getValue().clone();
			cpclone.setLocation(entry.getKey());
			
			if (entry.getValue().getColor().equals(Color.black)){
				blacktroops.addChessPiece(cpclone);
			}else {
				redtroops.addChessPiece(cpclone);
			}
			
		}
		((ChessTroops) redtroops).updateValue(allChesses);
		((ChessTroops) blacktroops).updateValue(allChesses);
		
		for (int i=0;i<redtroops.size();i++) {
			ChessPiece cp = redtroops.getChessPiece(i);
			boardpoint-=point[cp.getOrder()]*1.01;
			boardpoint-=cp.getValue()*1.01;
		}
		
		for (int i=0;i<blacktroops.size();i++) {
			ChessPiece cp = blacktroops.getChessPiece(i);
			boardpoint+=point[cp.getOrder()];
			boardpoint+=cp.getValue();
		}
		
		if (endOfGame(allChesses)==0) {
			boardpoint+=9999;
		}else if (endOfGame(allChesses)==1) {
			boardpoint-=9999;
		}
		
		
		
		((ChessTroops) redtroops).resetValue();
		((ChessTroops) blacktroops).resetValue();
			
		return boardpoint;
	}
	
	public ArrayList<Point> calBestMove (Map<Point, ChessPiece> copyallChesses, Map<Point, ChessPiece> afterallChess){
		
		ArrayList<Point> bestMove = new ArrayList<>();
		ChessPiece[] aftervalues =afterallChess.values().toArray(new ChessPiece[0]);
		Point [] afterkeys = afterallChess.keySet().toArray(new Point[0]);
		int onlykeyleft =0;
	
		for (int i=0;i <afterkeys.length;i++) {
				if (copyallChesses.get(afterkeys[i])!=null && copyallChesses.get(afterkeys[i])==aftervalues[i]) {
					copyallChesses.remove(afterkeys[i]);
				}else {
					onlykeyleft=i;
					continue;
				}
		}
		if (copyallChesses.keySet().toArray().length==2) {
			if (afterallChess.containsKey(copyallChesses.keySet().toArray()[0])) {
				System.out.println(copyallChesses.keySet().toArray()[0]);
				System.out.println(copyallChesses.keySet().toArray()[1]);
				copyallChesses.remove(copyallChesses.keySet().toArray()[0]);
			}else {
				copyallChesses.remove(copyallChesses.keySet().toArray()[1]);
			}
		}
		bestMove.add((Point) copyallChesses.keySet().toArray()[0]);
		bestMove.add(afterkeys[onlykeyleft]);
		return bestMove;
	}
	
	public void makeMove () {
		allChess=new HashMap<>();
		allChess.putAll(game.getAllChesses());
		int best =0;
		try {
			best = minimax (allChess, depthtogo,alpha,beta, true);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Show best "+best);
		//System.out.println(bestMoveBoard.values());
		//System.out.println("Total move "+c);
		ArrayList<Point> bestMove = calBestMove(allChess, this.bestMoveBoard);
		System.out.println(bestMove.get(0));
		System.out.println(bestMove.get(1));
		game.makeMove(1, bestMove.get(0), bestMove.get(1));
		
	}
	
	
	
	

}
