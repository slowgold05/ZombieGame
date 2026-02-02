import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main {

    public static Scanner scnr = new Scanner(System.in);

    public static Zombie zom;
    public static Weapon wpn;
    public static Player ply;

    public static void main(String[] args) {
        gameStart();
    }

    public static void gameStart() {
        JOptionPane.showMessageDialog(
                null,
                "Welcome to the Zombie Game!\n\nPlease sign in to start.",
                "Zombie Game",
                JOptionPane.INFORMATION_MESSAGE
        );

        new SignInScreen();
    }

    public static void startFightDemo() {
        if (ply == null || zom == null || wpn == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Game objects not initialized (Player/Zombie/Weapon).",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        for (int i = 0; i < 5; i++) {
            zom.zombieHealth = zom.zombieHealth - wpn.weaponDMG;
            ply.playerHealth = ply.playerHealth - zom.zombieDMG;

            System.out.println("Player Health: " + ply.playerHealth);
            System.out.println("Zombie Health: " + zom.zombieHealth);

            if (zom.zombieHealth <= 0) {
                System.out.println("You killed this zombie!");
                System.out.println("New zombie is attacking.");
                zom.zombieSelect();
            } else {
                System.out.println("...keep attacking...");
            }

            if (ply.playerHealth <= 0) {
                System.out.println("You Died! ... GAME OVER!");
                break;
            }
        }

        System.out.println("*********** END MAP level 1 ************");
    }
}
