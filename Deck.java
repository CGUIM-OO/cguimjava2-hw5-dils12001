import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;
	private ArrayList<Card> usedCard;
	private ArrayList openCard;
	public int nUsed;

	public Deck(int nDeck) {
		cards = new ArrayList<Card>();
		usedCard = new ArrayList<Card>();
		openCard = new ArrayList<Card>();
		for (int i = 1; i <= nDeck; i++) {
			for (Card.Suit s : Card.Suit.values()) {
				for (int y = 1; y <= 13; y++) {
					Card card = new Card(s, y);
					cards.add(card);
				}
			}
		}
		shuffle();
	}

	public void printDeck() {

		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).printCard();
		}
	}

	public ArrayList<Card> getAllCards() {
		return cards;
	}

	public void shuffle() {
		Random rnd = new Random();
		for (int i = 0; i < cards.size(); i++) {
			int j = rnd.nextInt(cards.size());
			Card a = cards.get(i);
			Card b = cards.get(j);
			Card temp = a;
			a = b;
			b = temp;
			cards.set(i,a);
			cards.set(j,b);
		}
		nUsed = 0;
		//for (int i = 0; i < openCard.size(); i++) {
			//openCard.remove(i);
		//}
		openCard.clear();
		for (int i = 0; i < usedCard.size(); i++) {
			usedCard.remove(i);
		}
	}

	public Card getOneCard(boolean isOpened) {
		Card onecard = null;
		if(usedCard.size() == cards.size())
			{shuffle();
		getOneCard(isOpened);}
		else
		{onecard = cards.get(nUsed);
			if (isOpened)
				openCard.add(onecard);
		nUsed++;
		usedCard.add(onecard);}
		return onecard;
	}
	public ArrayList getOpenedCard(){
		return openCard;
	}
	
	
	
	
	
}
