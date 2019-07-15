import java.awt.Color;
import java.awt.Point;
import java.util.Map;

import javax.swing.ImageIcon;

public class Chariot extends ChessPiece {
	
	static public final int VALUE = 500;
	
	public Chariot(Color color) {
		super(1, color);
		if(color.equals(Color.RED)) {
			this.setChessimage(new ImageIcon("chess/red_rook.png").getImage());
		}else {
			this.setChessimage(new ImageIcon("chess/black_rook.png").getImage());
		}
	}
	

	public boolean isValid(Point target, ChessPieceList chessRange) {
		if (target.getLocation()==this.getLocation())
			return false ;
		if (target.getX()==this.getLocation().getX()) {
			if (chessRange.isEmpty()) {
				return true;
			}else if (chessRange.size()==1 && !chessRange.getChessPiece(0).getColor().equals(getColor())) {
				return true;
			}
		}else if (target.getY()==this.getLocation().getY()) {
			if (chessRange.isEmpty()) {
				return true;
			}else if (chessRange.size()==1 && !chessRange.getChessPiece(0).getColor().equals(getColor())) {
				return true;
			}
		}
		return false ;
	}
	
	public ChessPieceList chessRange (ChessPiece cp, Point target, Map<Point, ChessPiece> allChesses) {
		ChessPieceList chessRange=new ChessPieceList();
		int x,y=0;
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
		
		return chessRange;
	
	}

}
