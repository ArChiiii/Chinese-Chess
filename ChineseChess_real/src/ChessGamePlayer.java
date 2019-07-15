import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 * This class is used to represent a player in general chess games.
 * 
 * @author Kenneth Wong n Archie Loong
 */
public class ChessGamePlayer {
	private static int playerId = 0;
	private Color color ;
	private String name = "";
	private ChessPieceList chessInHand ;

	/**
	 * Creates and returns an instance of the Player class.
	 * @throws CloneNotSupportedException 
	 */
	public ChessGamePlayer(Color color) throws CloneNotSupportedException {
		this.name = "Player " + playerId;
		this.color= color;
		playerId++;
		chessInHand = new ChessTroops(this);
		
	}

	/**
	 * Creates and returns an instance of the Player class.
	 * 
	 * @param name
	 *            the name of the player
	 * @throws CloneNotSupportedException 
	 */
	
	public ChessGamePlayer(String name,Color color) throws CloneNotSupportedException {
		this.name = name;
		this.color= color;
		chessInHand = new ChessTroops(this);
	}

	/**
	 * Returns the name of this player.
	 * 
	 * @return the name of this player
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of this player.
	 * 
	 * @param name
	 *            the name of this player
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Adds the specified chessPiece to this player.
	 * 
	 * @param chessPiece
	 *            the specified chessPiece to be added to this player
	 */
	public void addChessPiece(ChessPiece chessPiece) {
		if (chessPiece != null) {
			chessInHand.addChessPiece(chessPiece);
		}
	}

	/**
	 * Removes the list of chesses from this player, if they are held by this
	 * player.
	 * 
	 * @param cards
	 *            the list of chesses to be removed from this player
	 */
	public void removeChess(ChessPiece chessPiece) {
			chessInHand.removeChessPiece(chessPiece);
	}

	/**
	 * Removes all chesses from this player.
	 */
	public void removeAllChesses() {
		chessInHand = new ChessPieceList();
	}

	/**
	 * Returns the number of cards held by this player.
	 * 
	 * @return the number of cards held by this player
	 */
	public int getNumOfChess() {
		return chessInHand.size();
	}

	
	/**
	 * Sorts the list of cards held by this player.
	 
	public void sortCardsInHand() {
		cardsInHand.sort();
	}
	*/

	/**
	 * Returns the list of cards held by this player.
	 * 
	 * @return the list of cards held by this player
	 */
	public ChessPieceList getChessInHand() {
		return chessInHand;
	}
	
	public Color getColor () {
		return this.color;
	}
	
	public void makeMove() {
		
	}

	/**
	/**
	 * Returns the list of cards played by this player.
	 * 
	 * @param cardIdx
	 *            the list of the indices of the cards
	 * @return the list of cards played by this player, or null if the list of
	 *         cards is empty
	 
	public ChessPiece play(int chessIdx) {
		if (chessIdx == null) {
			return null;
		}

		CardList cards = new CardList();
		for (int idx : cardIdx) {
			if (idx >= 0 && idx < cardsInHand.size()) {
				cards.addCard(cardsInHand.getCard(idx));
			}
		}

		if (cards.isEmpty()) {
			return null;
		} else {
			return cards;
		}
	}
	**/
}
