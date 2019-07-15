import java.awt.Color;
import java.awt.Point;
import java.util.Map;

import javax.swing.ImageIcon;

public class Horse extends ChessPiece {
	
	static public final int VALUE = 300;
	
	public Horse(Color color) {
		super(2, color);
		if(color.equals(Color.RED)) {
			this.setChessimage(new ImageIcon("chess/red_knight.png").getImage());
		}else {
			this.setChessimage(new ImageIcon("chess/black_knight.png").getImage());
		}
		
	}
	
	public void update(Map<Point, ChessPiece> allChesses) {
		super.update(allChesses);
		
	}
	
	public boolean isValid(Point target, ChessPieceList chessRange) {
		if (target.getLocation()==this.getLocation())
			return false ;
		if (target.distance(this.getLocation())==Math.sqrt(5)) {
			if (chessRange.isEmpty()) {
				return true;
			}
		}
		return false ;
	}
	
	public ChessPieceList chessRange (ChessPiece cp, Point target, Map<Point, ChessPiece> allChesses) {
		ChessPieceList chessRange=new ChessPieceList();
		int x,y=0;
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
		
		
		return chessRange;
	
	}

}
