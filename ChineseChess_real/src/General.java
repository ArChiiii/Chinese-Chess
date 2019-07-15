import java.awt.Color;
import java.awt.Point;
import java.util.Map;

import javax.swing.ImageIcon;

public class General extends ChessPiece {
	
	static public final int VALUE = 9999;
	
	public General(Color color) {
		super(0, color);
		if(color.equals(Color.RED)) {
			this.setChessimage(new ImageIcon("chess/red_general.png").getImage());
		}else {
			this.setChessimage(new ImageIcon("chess/black_general.png").getImage());
		}
		
	}
	
	public void update(Map<Point, ChessPiece> allChesses) {
		if (this.getColor().equals(Color.BLACK) && this.getLocation().getY()==9) {
			value=value+50;
		}else if (this.getColor().equals(Color.RED) && this.getLocation().getY()==0) {
			value=value+50;
		}
	}
	

	
	public boolean isValid(Point target, ChessPieceList chessRange) {
		if (target.getLocation()==this.getLocation())
			return false ;
		int y=9;
		if (color.equals(Color.RED)) {
			y=0;
		}
		
		if (target.distance(this.getLocation())==1) {
			if (-2<target.getX() && target.getX()<2 ) {
				if (color.equals(Color.RED) && target.getY()>=y && target.getY()<y+3) {
					return true;
				}else if (color.equals(Color.BLACK) && target.getY()<=y && target.getY()>y-3)
					return true;
			}
		}
		if (chessRange.size()==1 && chessRange.getChessPiece(0).order==0) {
			return true;
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
		}
		
		return chessRange;
	
	}

}
