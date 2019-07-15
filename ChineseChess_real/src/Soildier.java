import java.awt.Color;
import java.awt.Point;
import java.util.Map;

import javax.swing.ImageIcon;

public class Soildier extends ChessPiece {
	
	static public final int VALUE = 100;
	
	public Soildier(Color color) {
		super(6, color);
		if(color.equals(Color.RED)) {
			this.setChessimage(new ImageIcon("chess/red_soildier.png").getImage());
		}else {
			this.setChessimage(new ImageIcon("chess/black_soildier.png").getImage());
		}
	}
	
	
	public boolean isValid(Point target, ChessPieceList chessRange) {
		if (target.getLocation()==this.getLocation())
			return false ;
		int y=4;
		if (color.equals(Color.RED)) {
			y=5;
		}
		if (target.getY()<this.getLocation().getY() &&this.getColor().equals(Color.RED)) {
			return false;
		}else if(target.getY()>this.getLocation().getY() &&this.getColor().equals(Color.BLACK)) {
			return false;
		}
		//System.out.println(target.distance(this.getLocation()));
		if (target.distance(this.getLocation())==1 ) {
			if (color.equals(Color.RED)) {
				if (this.getLocation().getY()<y) {
					if (target.getX()==this.getLocation().getX()) {
						return true;
					}
				}else if(this.getLocation().getY()>=y) {
					return true;
				}
			}else if (color.equals(Color.BLACK)) {
				if (this.getLocation().getY()>y) {
					if (target.getX()==this.getLocation().getX()) {
						return true;
					}
				}else if(this.getLocation().getY()<=y){
					return true;
				}
				
			}
				
		}
		return false ;
	}
	
	public ChessPieceList chessRange (ChessPiece cp, Point target, Map<Point, ChessPiece> allChesses) {
		ChessPieceList chessRange=new ChessPieceList();		
		return chessRange;
	
	}

}
