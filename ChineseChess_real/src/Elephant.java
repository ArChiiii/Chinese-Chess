import java.awt.Color;
import java.awt.Point;
import java.util.Map;

import javax.swing.ImageIcon;

public class Elephant extends ChessPiece {
	
	static public final int VALUE = 50;
	
	public Elephant(Color color) {
		super(5, color);
		if(color.equals(Color.RED)) {
			this.setChessimage(new ImageIcon("chess/red_elephant.png").getImage());
		}else {
			this.setChessimage(new ImageIcon("chess/black_elephant.png").getImage());
		}
	
	}
	

	public boolean isValid(Point target, ChessPieceList chessRange) {
		if (target.getLocation()==this.getLocation())
			return false ;
		int y=9;
		if (color.equals(Color.RED)) {
			y=0;
		}
		if (target.distance(this.getLocation())==Math.sqrt(8)) {
			if (chessRange.isEmpty()) {
				if (color.equals(Color.RED) && target.getY()>=y && target.getY()<y+5) {
					return true;
				}else if (color.equals(Color.BLACK) && target.getY()<=y && target.getY()>y-5)
					return true;
			}
		}
		return false ;
	}
	
	public ChessPieceList chessRange (ChessPiece cp, Point target, Map<Point, ChessPiece> allChesses) {
		ChessPieceList chessRange=new ChessPieceList();
		int x,y=0;
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
		
		return chessRange;
	
	}

}
