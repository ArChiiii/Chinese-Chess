import java.util.ArrayList;

/*
 * Class for represent a list of ChessPiece
 */
public class ChessPieceList  {
	private ArrayList<ChessPiece> chesses = new ArrayList<ChessPiece>();
	
	public void addChessPiece(ChessPiece chessPiece) {
		if (chessPiece !=null) {
			chesses.add(chessPiece);
		}
	}
	
	public ChessPiece getChessPiece (int i) {
		if (i>=0 &&i< chesses.size()) {
			return chesses.get(i);
		}else {
			return null;
		}
	}
	
	public ChessPiece removeChessPiece (int i) {
		if (i >= 0 && i < chesses.size()) {
			return chesses.set(i, null);
		} else {
			return null;
		}
	}
	
	public boolean removeChessPiece (ChessPiece chessPiece) {
		if (chesses.contains(chessPiece)) {
			removeChessPiece (chesses.indexOf(chessPiece));
			return true; //successful removal;
		}
		return false;
	}
	
	public void removeAllChesses() {
		chesses = new ArrayList<ChessPiece>();
	}
	
	public boolean contains(ChessPiece chessPiece) {
		return chesses.contains(chessPiece);
	}
	
	public boolean isEmpty() {
		return chesses.isEmpty();
	}
	
	public int size() {
		return chesses.size();
	}
	
	public void sort () {
		chesses.sort(new sortByorder());
	}
	
	

}
