import java.util.Comparator;

public class sortByorder implements Comparator<ChessPiece>{

	@Override
	public int compare(ChessPiece arg0, ChessPiece arg1) {
		int [] point = {General.VALUE,Chariot.VALUE,Horse.VALUE,Cannon.VALUE,Advisor.VALUE,Elephant.VALUE,Soildier.VALUE};
		if (arg0.getOrder()==0 ) {
			return 1;
		}
		if (arg1.getOrder()==0 ) {
			return -1;
		}
		
		
		if(point[arg0.getOrder()] <point[arg1.getOrder()]){
			return 1;
		} else {
			return -1;
		}
	}
	
}
