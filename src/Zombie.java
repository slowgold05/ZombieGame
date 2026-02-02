public class Zombie {
	public static String zombieName = "";
	public static int zombieType = 0;
	public static double zombieHealth = 0.0;
	public static double zombieDMG = 0.0;

	public static void zombieSelect() {
		zombieType = (int) (Math.random() * 5);

		if (zombieType == 1) {
			System.out.println("A child zombie is after you!");
			zombieHealth = 25;
			zombieDMG = 15;
		} else if (zombieType == 2) {
			System.out.println("An adult zombie is after you!");
			zombieHealth = 50;
			zombieDMG = 45;
		} else if (zombieType == 3) {
			System.out.println("A poison zombie is after you!");
			zombieHealth = 30;
			zombieDMG = 55;
		} else if (zombieType == 4) {
			System.out.println("A boss zombie is after you!");
			zombieHealth = 100;
			zombieDMG = 70;
		} else {
			System.out.println("A weak zombie is after you!");
			zombieHealth = 15;
			zombieDMG = 10;
		}
	}
}
