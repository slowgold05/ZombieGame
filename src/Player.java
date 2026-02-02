public class Player {

	public String playerName;
	public double playerHealth;
	public int lives;

	public Player() {
		this("Player");
	}

	public Player(String name) {
		if (name == null || name.isBlank()) {
			this.playerName = "Player";
		} else {
			this.playerName = name;
		}
		this.playerHealth = 100.0;
		this.lives = 3;
	}

	public void healToFull() {
		this.playerHealth = 100.0;
	}

	public void addHealth(double amount) {
		this.playerHealth = Math.min(100.0, this.playerHealth + amount);
	}

	public void takeDamage(double amount) {
		this.playerHealth = Math.max(0.0, this.playerHealth - amount);
	}
}
