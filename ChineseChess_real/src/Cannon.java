import java.awt.Color;
import java.awt.Point;
import java.util.Map;

import javax.swing.ImageIcon;

public class Cannon extends ChessPiece {
	
	static public final int VALUE = 325;
	
	public Cannon(Color color) {
		super(3, color);
		if(color.equals(Color.RED)) {
			this.setChessimage(new ImageIcon("chess/red_cannon.png").getImage());
		}else {
			this.setChessimage(new ImageIcon("chess/black_cannon.png").getImage());
		}
		
	}
	
	public void update(Map<Point, ChessPiece> allChesses) {
		super.update(allChesses);
		if (this.getLocation().getX()==0) {
			value+=15;
		}
		
	}
	
	public boolean isValid(Point target, ChessPieceList chessRange) {
		//System.out.println(chessRange.toString());
		//System.out.println(chessRange.getChessPiece(0));
		//System.out.println(target.getX());
		if (target.getLocation()==this.getLocation())
			return false ;
		
		if (target.getX()==this.getLocation().getX()) {
			if (chessRange.isEmpty()) {
				return true;
			}else if (chessRange.size()==2 && chessRange.getChessPiece(0).getLocation().equals(target.getLocation())) {
				return true;
			}
		}else if (target.getY()==this.getLocation().getY()) {
			if (chessRange.isEmpty()) {
				return true;
			}else if (chessRange.size()==2 && chessRange.getChessPiece(0).getLocation().equals(target.getLocation())) {
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
