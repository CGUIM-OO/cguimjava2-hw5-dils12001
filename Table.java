import java.util.ArrayList;

public class Table {
	final int MAXPLAYER = 4;
	private Deck allcards;
	private Player[] players;
	private Dealer dealer;
	private int[] pos_betArray;

	public Table(int nDeck) {
		allcards = new Deck(nDeck);
		players = new Player[MAXPLAYER];
	}

	public void set_player(int pos, Player p) {
		players[pos] = p;
	}

	public Player[] get_player() {
		return players;
	}

	public void set_dealer(Dealer d) {
		dealer = d;
	}

	public Card get_face_up_card_of_dealer() {
		return dealer.getOneRoundCard().get(1);
	}

	private void ask_each_player_about_bets() {
		for (Player p : get_player()) {
			p.sayHello();
			p.makeBet();
		}
	}

	private void distribute_cards_to_dealer_and_players() {
		int a = 0;
		for (Player p : get_player()) {
			ArrayList<Card> temp = new ArrayList<Card>();
			temp.add(allcards.getOneCard(true));
			temp.add(allcards.getOneCard(true));
			p.setOneRoundCard(temp);
		}
		ArrayList<Card> temp1 = new ArrayList<Card>();
		temp1.add(allcards.getOneCard(true));
		temp1.add(allcards.getOneCard(false));
		dealer.setOneRoundCard(temp1);
	}

	private void ask_each_player_about_hits() {
		for (Player p : get_player()) {
			boolean hit = false;
			do {if(p.getTotalValue()>21)
				break;
				hit = p.hit_me(this); // this
				if (hit) {
					ArrayList<Card> temp2 = new ArrayList<Card>();
					System.out
							.println("Hit! " + p.getName() + "¡¦s cards now: ");
					temp2.add(allcards.getOneCard(true));
					p.setOneRoundCard(temp2);
					for (Card c : p.getOneRoundCard()) {
						c.printCard();
					}
				} else {
					System.out.println(p.getName() + ", Pass hit!");
					for (Card c : p.getOneRoundCard()) {
						c.printCard();
					}
				}

			} while (hit);
		}
	}

	private void ask_dealer_about_hits(){
		boolean hit = false;
		do {
			hit = dealer.hit_me(this); // this
			if (hit) {
				ArrayList<Card> temp3 = new ArrayList<Card>();
				temp3.add(allcards.getOneCard(true));
				dealer.setOneRoundCard(temp3);
				} else {
				System.out.println("Dealer's hit is over!");
			}
		} while (hit);
	}
	
	private void calculate_chips(){
		
		System.out.println("Dealer's card value is "+dealer.getTotalValue()+" ,Cards:");
		for (Card c : dealer.getOneRoundCard()) {
			c.printCard();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
