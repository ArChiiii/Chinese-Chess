import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.Map;

public class ChessPiece implements Cloneable {
	private static final char[] RED = {'帥','俥','傌','炮','仕','相','兵'};
	private static final char[] BLACK = {'將','車','馬','砲','士','象','卒'};
	protected final int order; // 0 - 6
	protected final Color color;
	private Point location;
	protected Image chessImage;
	protected int value;
	
	
	public ChessPiece (int order, Color color) {
		this.order=order;
		if (color.equals(Color.RED) || color.equals(Color.BLACK)) {
			this.color=color;
		}else {
			this.color = null;
		}
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public int getOrder() {
		return order ;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void reset() {
		value=0;
	}
	
	public void update(Map<Point, ChessPiece> allChesses) {
		
		if (Math.abs(location.getX())>0) {
			value-=(5*Math.abs(location.getX()));
		}
		
		if (Math.abs(location.getY()-4)<4) {
			value+=(5*(4-Math.abs(location.getY()-4)));
		}
		
	}
	
	public int getValue() {
		return value ;
	}
	
	public void setValue(int value) {
		this.value=value;
	}
	
	public String toString() {
		if (color.equals(Color.RED)) {
			return ""+RED[order];
		}else {
			return ""+BLACK[order];
		}
	}
	
	public void setLocation (Point target) {
		this.location=target;
	}
	
	public Point getLocation () {
		return this.location;
	}
	
	public Image getChessimage() {
		return chessImage;
	}
	
	public void setChessimage(Image image) {
		this.chessImage=image;
	}
	
	public boolean isValid(Point target, ChessPieceList chessRange) {
			return false ;//to be override
		
	}
	
	public ChessPieceList chessRange (ChessPiece cp, Point target, Map<Point, ChessPiece> allChesses) {
		return null;
	}



}
