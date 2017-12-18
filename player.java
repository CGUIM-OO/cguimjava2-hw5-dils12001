import java.util.ArrayList;

public class Player extends Person {
	private String name;
	private int chips;
	private int bet;


	public Player(String name, int chips) {
		this.name = name;
		this.chips = chips;
	}

	public String getName() {
		return name;
	}

	public int makeBet() {
		bet = 10000;
		if (chips < 1)
			return 0;
		else
			return bet;
	}

	public int getCurrentChips() {
		return chips;
	}

	public void increaseChips(int diff) {
		chips += diff;
	}

	public void sayHello() {
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}

	@Override
	public boolean hit_me(Table table) {
		int sum = 0;
		for (int i = 0; i < getOneRoundCard().size(); i++) {
			sum +=  getOneRoundCard().get(i).getRank();
		}
		if (sum < 21)
			return true;
		else
			return false;
	}

}