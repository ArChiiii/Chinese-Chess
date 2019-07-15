import java.awt.Color;
import java.awt.Point;
import java.util.Map;

public class ChessTroops extends ChessPieceList {
	
	private ChessGamePlayer player;
	private Color color;
	
	public ChessTroops(ChessGamePlayer player) throws CloneNotSupportedException {
		this.player =player;
		this.color =player.getColor();
		initialize(color);
		inilocation();
	}
	
	public ChessTroops(Color color) throws CloneNotSupportedException {
		this.color =color;
	}
	
	
	public void initialize(Color color) throws CloneNotSupportedException {
		removeAllChesses();
		ChessPiece general = new General(color);
		addChessPiece(general);
		ChessPiece advisor1 = new Advisor(color);
		addChessPiece(advisor1);
		ChessPiece advisor2 = (Advisor) advisor1.clone();
		addChessPiece(advisor2);
		ChessPiece elephant1 = new Elephant(color);
		addChessPiece(elephant1);
		ChessPiece elephant2 = (Elephant) elephant1.clone();
		addChessPiece(elephant2);
		ChessPiece horse1 = new Horse(color);
		addChessPiece(horse1);
		ChessPiece horse2 = (Horse) horse1.clone();
		addChessPiece(horse2);
		ChessPiece chariot1 = new Chariot(color);
		addChessPiece(chariot1);
		ChessPiece chariot2 = (Chariot) chariot1.clone();
		addChessPiece(chariot2);
		ChessPiece cannon1 = new Cannon(color);
		addChessPiece(cannon1);
		ChessPiece cannon2 = (Cannon) cannon1.clone();
		addChessPiece(cannon2);
		
		ChessPiece soildier1 = new Soildier(color);
		addChessPiece(soildier1);
		ChessPiece soildier2= (Soildier) soildier1.clone();
		addChessPiece(soildier2);
		ChessPiece solidier3 = (Soildier) soildier1.clone();
		addChessPiece(solidier3);
		ChessPiece solidier4 = (Soildier) soildier1.clone();
		addChessPiece(solidier4);
		ChessPiece solidier5 = (Soildier) soildier1.clone();
		addChessPiece(solidier5);
	}
	
	public void inilocation() {
		int y=9;
		if (this.color.equals(Color.RED)) {
			y=0;
		}
		int [] xlocal = {0,1,-1,2,-2,3,-3,4,-4};
		for (int i=0 ; i<9;i++) {
			this.getChessPiece(i).setLocation(new Point(xlocal[i],y));
		}
		y=(y==9)?7:2;
		this.getChessPiece(9).setLocation(new Point(3,y));
		this.getChessPiece(10).setLocation(new Point(-3,y));
		y=(y==7)?6:3;
		int [] xlocalsoldier = {0,2,-2,4,-4};
		for (int i=11 ; i<16;i++) {
			this.getChessPiece(i).setLocation(new Point(xlocalsoldier[i-11],y));
		}
	}
	
	public void resetValue() {
		for (int i=0;i<this.size();i++) {
			if(this.getChessPiece(i)!=null) {
			this.getChessPiece(i).reset();
			}
		}
	}
	
	public void updateValue(Map<Point, ChessPiece> allChesses) {
		for (int i=0;i<this.size();i++) {
			if(this.getChessPiece(i)!=null) {
			this.getChessPiece(i).update(allChesses);
			}
		}
	}


}
