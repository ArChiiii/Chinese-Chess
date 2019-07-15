import java.awt.Color;
import java.awt.Point;
import java.util.Map;

import javax.swing.ImageIcon;

public class Advisor extends ChessPiece {

	static public final int VALUE = 30;
	
	public Advisor(Color color) {
		super(4, color);
		if(color.equals(Color.RED)) {
			this.setChessimage(new ImageIcon("chess/red_advisor.png").getImage());
		}else {
			this.setChessimage(new ImageIcon("chess/black_advisor.png").getImage());
		}
	}
	
	public void update(Map<Point, ChessPiece> allChesses) {
		if (this.getColor().equals(Color.BLACK) && this.getLocation().getY()==9) {
			value=value+70;
		}else if (this.getColor().equals(Color.RED) && this.getLocation().getY()==0) {
			value=value+70;
		}
	}
	
	public boolean isValid(Point target, ChessPieceList chessRange) {
		if (target.getLocation()==this.getLocation())
			return false ;
		int y=9;
		if (color.equals(Color.RED)) {
			y=0;
		}
		
		if (target.distance(this.getLocation())==Math.sqrt(2)) {
			if (-2<target.getX() && target.getX()<2 ) {
				if (color.equals(Color.RED) && target.getY()>=y && target.getY()<y+3) {
					return true;
				}else if (color.equals(Color.BLACK) && target.getY()<=y && target.getY()>y-3)
					return true;
			}
		}
		return false ;
	}
	
	public ChessPieceList chessRange (ChessPiece cp, Point target, Map<Point, ChessPiece> allChesses) {
		ChessPieceList chessRange=new ChessPieceList();		
		return chessRange;
	}
	
	
}
