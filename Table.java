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
		pos_betArray = new int[get_player().length];
		int i = 0;
		for (Player p : get_player()) {
			p.sayHello();
			pos_betArray[i] = p.makeBet();
			i++;
		}
	}

	private void distribute_cards_to_dealer_and_players() {
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
		System.out.print("Dealer's face up card is ");
		dealer.getOneRoundCard().get(1).printCard();
		;
	}

	private void ask_each_player_about_hits() {

		for (Player p : get_player()) {
			ArrayList<Card> temp2 = p.getOneRoundCard();
			boolean hit = false;
			do {
				if (p.getTotalValue() > 21)
					break;
				hit = p.hit_me(this); // this
				if (hit) {

					System.out
							.println("Hit! " + p.getName() + "・s cards now: ");
					temp2.add(allcards.getOneCard(true));
					p.setOneRoundCard(temp2);
					for (Card c : p.getOneRoundCard()) {
						c.printCard();
					}
				}

			} while (hit);
			System.out.println(p.getName() + ", Pass hit!");

		}
	}

	private void ask_dealer_about_hits() {
		boolean hit = false;
		ArrayList<Card> temp3 = dealer.getOneRoundCard();
		do {
			hit = dealer.hit_me(this);
			if (hit) {
				temp3.add(allcards.getOneCard(true));
				dealer.setOneRoundCard(temp3);
			}
		} while (hit);
		System.out.println("Dealer's hit is over!");
	}

	private void calculate_chips() {
		int d = dealer.getTotalValue();
		System.out.println("Dealer's card value is " + d + " ,Cards:");
		for (Card c : dealer.getOneRoundCard()) {
			c.printCard();
		}
		for (Player player : get_player()) {
			int win = 0;// キも0 怪長1 �営�2
			int p = player.getTotalValue();
			System.out.print(player.getName() + " card value is " + p);
			if (p > 21)
				win = 1;
			else if (p <= 21 & d > 21)
				win = 2;
			else if (p <= 21 & d <= 21) {
				if (p > d)
					win = 2;
				if (p < d)
					win = 1;
				if (p == d)
					win = 0;
			}
			if (win == 1) {
				int b = player.makeBet();
				player.increaseChips(-player.makeBet());
				System.out.println(", Loss " + b + " Chips, the Chips now is: "
						+ player.getCurrentChips());
			}
			if (win == 2) {
				int b = player.makeBet();
				player.increaseChips(player.makeBet());
				System.out.println(",Get " + b + " Chips, the Chips now is: "
						+ player.getCurrentChips());
			}
			if (win == 0) {
				System.out.println(",chips have no change! The Chips now is: "
						+ player.getCurrentChips());
			}
		}
	}

	public int[] get_palyers_bet() {

		return pos_betArray;
	}

	public void play() {
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}

}
