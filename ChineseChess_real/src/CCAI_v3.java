import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//random boot

public class CCAI_v3 extends ChessGamePlayer {
	
	private Map<Point, ChessPiece> allChess = new HashMap<>();
	//private Map<ChessPiece, ArrayList<Point>> possiMove= new HashMap<>();
	private ChineseChess game;
	boolean maximizing=true;
	ArrayList<Point> BestMove = new ArrayList<>();
	Map<Point, ChessPiece> bestMoveBoard = new HashMap<>();
	int depthtogo = 3;
	
	public CCAI_v3 (Color color, ChineseChess game) throws CloneNotSupportedException {
		super("CPT_v3",color);
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
		return possiMove;
	}
	
	
	public ArrayList <Map<Point, ChessPiece>> allboardpossimove (Map<Point, ChessPiece> allChesses, ChessGamePlayer player) throws CloneNotSupportedException{
		ArrayList <Map<Point, ChessPiece>> allboardpossimove = new ArrayList<>();
		
		int [] order = {6,5,7,8,9,10,11,12,13,14,15,3,4,1,2,0};
		/*
		for (int i=0; i<player.getNumOfChess(); i++) {
			if (player.getChessInHand().getChessPiece(order[i])==null) {
				continue;
			}
			ChessPiece cp=player.getChessInHand().getChessPiece(order[i]);
			if (cp!=null) {
				for (Point target : genPossiMove(allChesses,cp)) {
					Map<Point, ChessPiece> copyallChesses=new HashMap<>();
					copyallChesses.putAll(allChesses);
					allboardpossimove.add(boardpossimove(copyallChesses,cp,target));
				}
			}
		}
		*/
	
		
		ChessPiece[] allchessPiece= allChesses.values().toArray(new ChessPiece[0]);
		ArrayList<ChessPiece> playerChess = new ArrayList<>();
		for (ChessPiece cp :allchessPiece) {
			if (cp.getColor().equals(player.getColor())) {
				playerChess.add(cp);
			}
		}
		//System.out.println(allchessPiece);
		for (ChessPiece cp :playerChess) {
			for (Point target : genPossiMove(allChesses,cp)) {
				Map<Point, ChessPiece> copyallChesses=new HashMap<>();
				copyallChesses.putAll(allChesses);
				allboardpossimove.add(boardpossimove(copyallChesses,cp,target));
			}
		}
		
		return allboardpossimove;
	}
	
	public Map<Point, ChessPiece> boardpossimove (Map<Point, ChessPiece> allChesses, ChessPiece cp, Point target){
		
		Map<Point, ChessPiece> copyallChesses=new HashMap<>();
		copyallChesses.putAll(allChesses);
		
		if (copyallChesses.containsKey(target) && !copyallChesses.get(target).getColor().equals(cp.getColor())) {
			copyallChesses.remove(cp.getLocation());
			copyallChesses.remove(target);
			copyallChesses.put(target, cp);
			
		}else if(!copyallChesses.containsKey(target)) {
			copyallChesses.remove(cp.getLocation());
			copyallChesses.put(target, cp);
			
			
		}
		return copyallChesses;
	}

	
	public int minimax (Map<Point, ChessPiece> allChesses, int depth,boolean maximizing) throws CloneNotSupportedException{ 
		
		if (depth ==0 ) {
			/*
			if (boardEvalute(allChesses)==160) {
				printAllChess print = new printAllChess(allChesses);
				
			}
			*/
			
			
			
			return boardEvalute(allChesses);
		}
		if (maximizing == true) {
			int max= -9999;
			//System.out.println(maximizing+" "+allboardpossimove(allChesses,game.getPlayerList().get(1)).size());
			for (int i=0 ;i<allboardpossimove(allChesses,game.getPlayerList().get(1)).size();i++) {
				Map<Point, ChessPiece> possi = allboardpossimove(allChesses,game.getPlayerList().get(1)).get(i);
				int evalu = minimax (possi, depth-1,false);
				//System.out.println("return from depth 0 "+evalu);
				/*
				alpha=Math.max(alpha, evalu);
				if (beta<=alpha) {
					break;
				}
				*/
				if (evalu>max && depth == this.depthtogo) {
					System.out.println(evalu);
					System.out.println(max+" trueeeeeeeeeeeee");
					bestMoveBoard=possi;
					System.out.println(possi);
				}
				
				
				//System.out.println("evalu: "+evalu);
				max= Math.max(max, evalu);
			}
			//System.out.println("return max: "+max);
			//System.out.println("return depth: "+depth);
			return max;
	
		}else {
			int min = 9999;
			//System.out.println(maximizing+" "+allboardpossimove(allChesses,game.getPlayerList().get(0)).size());
			for (int i=0; i<allboardpossimove(allChesses,game.getPlayerList().get(0)).size();i++) {
				Map<Point, ChessPiece> possi =allboardpossimove(allChesses,game.getPlayerList().get(0)).get(i);
				int evalu= minimax (possi, depth-1,true);
				min= Math.min(min, evalu);
				/*
				beta= Math.min(beta, evalu);
				if (beta<=alpha) {
					break;
				}
				*/
			}
			//System.out.println(" return min: "+min);
			//System.out.println("return depth: "+depth);
			return min;
		}
	}
	
	public int boardEvalute (Map<Point, ChessPiece> allChesses) {
		Map<ChessPiece, Point> allChessesInversed = new HashMap<>();
		for(Map.Entry<Point, ChessPiece> entry : allChesses.entrySet()){
			allChessesInversed.put(entry.getValue(), entry.getKey());
		}
		
		int boardpoint=0;
		//System.out.println(allChessesInversed.get(this.getChessInHand().getChessPiece(0)));
		int [] point = {General.VALUE,Chariot.VALUE,Horse.VALUE,Cannon.VALUE,Advisor.VALUE,Elephant.VALUE,Soildier.VALUE};
		ChessPiece[] allchessPiece= allChesses.values().toArray(new ChessPiece[0]);
		/*
		for (int i =0; i<allChessesInversed.size();i++) {
			if (Math.abs(allChessesInversed.get(allchessPiece[i]).getX())<3 && Math.abs(allChessesInversed.get(allchessPiece[i]).getY()-4)<3) {
				allchessPiece[i].setValue(allchessPiece[i].getValue()+10);
			}
		}
		*/
		((ChessTroops) game.getPlayerList().get(0).getChessInHand()).updateValue(allChesses);
		((ChessTroops) game.getPlayerList().get(1).getChessInHand()).updateValue(allChesses);
		for (ChessPiece cp : allchessPiece) {
			if(cp.getColor().equals(this.getColor())) {
				boardpoint+=point[cp.getOrder()];
				boardpoint+=cp.getValue();
			}else {
				boardpoint-=point[cp.getOrder()];
				boardpoint-=cp.getValue();
			}
		}
		
		if (!allChessesInversed.containsKey(this.getChessInHand().getChessPiece(0))) {
			boardpoint-=999;
		}else if (!allChessesInversed.containsKey(game.getPlayerList().get(0).getChessInHand().getChessPiece(0))) {
			boardpoint+=999;
		}
		((ChessTroops) game.getPlayerList().get(0).getChessInHand()).resetValue();
		((ChessTroops) game.getPlayerList().get(1).getChessInHand()).resetValue();
		
		return boardpoint;
	}
	
	public ArrayList<Point> calBestMove (Map<Point, ChessPiece> copyallChesses, Map<Point, ChessPiece> afterallChess){
		ArrayList<Point> bestMove = new ArrayList<>();
		/*
		Map<Point, ChessPiece> copyallChesses = new HashMap<>();
		copyallChesses.putAll(allChesses);
		*/
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
		System.out.print(copyallChesses.keySet().toArray().length+"\n");
		bestMove.add((Point) copyallChesses.keySet().toArray()[0]);
		bestMove.add(afterkeys[onlykeyleft]);
		return bestMove;
	}
	
	public void makeMove () {
		//System.out.print(getChessInHand().getChessPiece(5)+ " " +genPossiMove(game.getAllChesses(), getChessInHand().getChessPiece(5)));
		this.allChess=new HashMap<>();
		this.allChess.putAll(game.getAllChesses());
		//printAllChess print = new printAllChess(allChess);
		int best = 0;
		try {
			best = minimax(allChess,depthtogo,true );
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Show best "+best);
		System.out.println(bestMoveBoard.values());
		
		
		ArrayList<Point> bestMove = calBestMove(allChess, this.bestMoveBoard);
		System.out.println(bestMove.get(0));
		System.out.println(bestMove.get(1));
		game.makeMove(1, bestMove.get(0), bestMove.get(1));
		//game.makeMove(bestMoveBoard);
	}

}
