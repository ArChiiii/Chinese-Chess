import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChineseChess implements ChessGame {
	
	/*
	 * A list of players.
	 */
	private ArrayList<ChessGamePlayer> playerList = new ArrayList<ChessGamePlayer>();
	/*
	 * An integer specifying the index of the current player.
	 */
	private int currentIdx;
	/*
	 * A chess game table which builds the GUI for the game and handles all user actions.
	 */
	private ChineseChessTable table;
	private ChineseChessConsole console;

	private Map<Point, ChessPiece> allChesses;
	/*
	 * 
	 * Creates and returns an instance of the Chinese Chess class.
	 * 
	 */
	public ChineseChess() throws CloneNotSupportedException {
		ChessGamePlayer P1 = new ChessGamePlayer(Color.RED);
		ChessGamePlayer P2 = new ChessGamePlayer(Color.BLACK);
		//ChessGamePlayer v1 =new CCAI_v1(Color.BLACK,this);
		ChessGamePlayer v2 =new CCAI_v2(Color.BLACK,this);
		ChessGamePlayer v3 =new CCAI_v3(Color.BLACK,this);
		ChessGamePlayer v4 =new CCAI_v4(Color.BLACK,this);
		//ChessGamePlayer v42 =new CCAI_v4(Color.RED,this);
		playerList.add(P1);
		//playerList.add(P2);
		//playerList.add(v1);
		//playerList.add(v3);
		playerList.add(v4);
		allChesses = new HashMap<>();
		getChesses();
		console= new ChineseChessConsole(this);
		table= new ChineseChessTable (this);
	}

	public Map<Point, ChessPiece> getAllChesses() {
		return this.allChesses;
	}
	
	public void getChesses() {
		for (int i=0 ; i<playerList.size();i++) {
			for (int j=0; j<playerList.get(i).getChessInHand().size(); j++) {
				allChesses.put(playerList.get(i).getChessInHand().getChessPiece(j).getLocation(), playerList.get(i).getChessInHand().getChessPiece(j));
			}
		}
	}

	public static void main(String[] args) {
		try {
			ChineseChess CC = new ChineseChess();
			//CC.start();
			CC.starttable();
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void starttable() {
		this.table.setActivePlayer(0);
		this.table.repaint();
	}

	private void start() {
		this.console.setActivePlayer(0);
		this.console.repaint();
		while (true) {
			ChessGamePlayer activePlayer = this.getPlayerList().get(currentIdx);
			Point selected = console.getSelected();
			while (!allChesses.containsKey(selected) || !allChesses.get(selected).color.equals(activePlayer.getColor())) {
				System.out.println("Not vaild selection!");
				selected = console.getSelected();
			}
			ChessPiece selectChess =allChesses.get(selected);
			
			Point target = console.toTarget(); //makemove here

				System.out.println("Not vaild move!");
				target = console.toTarget();
			
			
			if (allChesses.containsKey(target)) {
				this.getPlayerList().get((currentIdx+1)%2).removeChess(allChesses.get(target));
				allChesses.put(target, allChesses.get(selected));
				allChesses.remove(selected);
			}else {
				allChesses.put(target, allChesses.get(selected));
				allChesses.remove(selected);
			}
			if (endOfGame())
				break;
			
			currentIdx = (currentIdx+1)%2;
			console.setActivePlayer(currentIdx);
			console.repaint();
					
		}
		
	}
	
	public ChessPieceList chessRange (ChessPiece cp, Point target) {
		ChessPieceList chessRange=new ChessPieceList();
		int x,y=0;
		switch(cp.order) {
			case 3:
			case 1:
				if (cp.getLocation().getX()==target.getX()) {
					y = (int) (target.getY()-cp.getLocation().getY());
					while (y!=0){
						if (allChesses.get(new Point(target.x ,cp.getLocation().y+y))!=null) {
							chessRange.addChessPiece(allChesses.get(new Point(target.x ,cp.getLocation().y+y)));
						}
						y=(y>0)?y-1:y+1;		
					}
				}else if ((cp.getLocation().getY()==target.getY())) {
					x = (int) (target.getX()-cp.getLocation().getX());
					while (x!=0){
						if (allChesses.get(new Point(cp.getLocation().x+x ,target.y))!=null) {
							chessRange.addChessPiece(allChesses.get(new Point(cp.getLocation().x+x ,target.y)));
						}
						x=(x>0)?x-1:x+1;		
					}
				}
				break;
			case 2:
				if (target.getY() - cp.getLocation().getY()==2){
					if (allChesses.get(new Point(cp.getLocation().x ,cp.getLocation().y+1))!=null) {
						chessRange.addChessPiece(allChesses.get(new Point(cp.getLocation().x ,cp.getLocation().y+1)));
					}
				}else if (target.getY() - cp.getLocation().getY()==-2) {
					if (allChesses.get(new Point(cp.getLocation().x ,cp.getLocation().y-1))!=null) {
						chessRange.addChessPiece(allChesses.get(new Point(cp.getLocation().x ,cp.getLocation().y-1)));
					}
				}else if (target.getX() - cp.getLocation().getX()==2) {
					if (allChesses.get(new Point(cp.getLocation().x+1 ,cp.getLocation().y))!=null) {
						chessRange.addChessPiece(allChesses.get(new Point(cp.getLocation().x+1 ,cp.getLocation().y)));
					}
				}else if (target.getX() - cp.getLocation().getX()==-2) {
					if (allChesses.get(new Point(cp.getLocation().x-1 ,cp.getLocation().y))!=null) {
						chessRange.addChessPiece(allChesses.get(new Point(cp.getLocation().x-1 ,cp.getLocation().y)));
					}
				}
				break;
			case 5:
				if (target.getY()- cp.getLocation().getY()==2 && target.getX() - cp.getLocation().getX()==2) {
					if (allChesses.get(new Point(cp.getLocation().x+1 ,cp.getLocation().y+1))!=null) {
						chessRange.addChessPiece(allChesses.get(new Point(cp.getLocation().x+1 ,cp.getLocation().y+1)));
					}
				}else if (target.getY()- cp.getLocation().getY()==2 && target.getX() - cp.getLocation().getX()==-2) {
					if (allChesses.get(new Point(cp.getLocation().x-1 ,cp.getLocation().y+1))!=null) {
						chessRange.addChessPiece(allChesses.get(new Point(cp.getLocation().x-1 ,cp.getLocation().y+1)));
					}
				}else if (target.getY()- cp.getLocation().getY()==-2 && target.getX() - cp.getLocation().getX()==2) {
					if (allChesses.get(new Point(cp.getLocation().x+1 ,cp.getLocation().y-1))!=null) {
						chessRange.addChessPiece(allChesses.get(new Point(cp.getLocation().x+1 ,cp.getLocation().y-1)));
					}
				}else if (target.getY()- cp.getLocation().getY()==-2 && target.getX() - cp.getLocation().getX()==-2) {
					if (allChesses.get(new Point(cp.getLocation().x-1 ,cp.getLocation().y-1))!=null) {
						chessRange.addChessPiece(allChesses.get(new Point(cp.getLocation().x-1 ,cp.getLocation().y-1)));
					}
				}
			case 0:
				if (cp.getLocation().getX()==target.getX()) {
					y = (int) (target.getY()-cp.getLocation().getY());
					while (y!=0){
						if (allChesses.get(new Point(target.x ,cp.getLocation().y+y))!=null) {
							chessRange.addChessPiece(allChesses.get(new Point(target.x ,cp.getLocation().y+y)));
						}
						y=(y>0)?y-1:y+1;		
					}
				}
					break;
		}
		
		return chessRange;
	}

	@Override
	public int getNumOfPlayers() {
		return playerList.size();
	}

	@Override
	public ArrayList<ChessGamePlayer> getPlayerList() {
		return playerList;
	}

	@Override
	public int getCurrentIdx() {
		return currentIdx;
	}

	
	public void makeMove (Map<Point, ChessPiece> allChesses) {
		this.allChesses=allChesses;
	}

	@Override
	public void checkMove(int playerID, Point selected, Point target) {
		ChessGamePlayer activePlayer = this.getPlayerList().get(currentIdx);
		ChessPiece selectChess = allChesses.get(selected);
		if(!selectChess.isValid(target, selectChess.chessRange(selectChess,target,allChesses))) {
			table.printMsg("Not vaild move!\n");
		}else {
		
			if (allChesses.containsKey(target)) {
				this.getPlayerList().get((currentIdx+1)%2).removeChess(allChesses.get(target));
				allChesses.get(selected).setLocation(target);
				allChesses.put(target, allChesses.get(selected));
				allChesses.remove(selected);
			}else {
				allChesses.get(selected).setLocation(target);
				allChesses.put(target, allChesses.get(selected));
				allChesses.remove(selected);
			}
			if (endOfGame())
				table.printMsg(activePlayer.getName()+"Wins\n");
		
			currentIdx = (currentIdx+1)%2;
			table.setActivePlayer(currentIdx);
			table.resetSelected();
		
		}
	}
	
	public void makeMove(int playerID, Point chessLoca, Point target) {
		checkMove(playerID,chessLoca,target);
	}

	@Override
	public boolean endOfGame() {
		if (playerList.get(0).getChessInHand().getChessPiece(0)==null || playerList.get(1).getChessInHand().getChessPiece(0)==null )
			return true;
		else 
			return false;
	}
	/*
	public ArrayList<Point> undo(Map<Point, ChessPiece> originallChesses, Map<Point, ChessPiece> afterallChesses) {
		
		
		afterallChesses.keySet().toArray(a);
		afterallChesses.values().toArray(a);
		
	}
	*/

}
