import javax.swing.*;
import java.awt.*;

public class Weapon {
    public static String weaponName = "fists";
    public static double weaponDMG = 10.0;

    public Weapon() {}

    public static void weaponSelect() {
        JFrame frame = new JFrame("Select Your Weapon");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 150);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout());

        JButton swordBtn = new JButton("Sword");
        JButton gunBtn = new JButton("Gun");
        JButton bowBtn = new JButton("Bow");
        JButton flameBtn = new JButton("Flame Thrower");

        swordBtn.addActionListener(e -> {
            weaponName = "sword";
            weaponDMG = 35;
            JOptionPane.showMessageDialog(frame, "You've selected a sword... ==[=====> !");
            frame.dispose();
        });

        gunBtn.addActionListener(e -> {
            weaponName = "gun";
            weaponDMG = 40;
            JOptionPane.showMessageDialog(frame, "You've selected a gun... pew pew!");
            frame.dispose();
        });

        bowBtn.addActionListener(e -> {
            weaponName = "bow & arrow";
            weaponDMG = 100;
            JOptionPane.showMessageDialog(frame, "You've selected a bow... aim true!");
            frame.dispose();
        });

        flameBtn.addActionListener(e -> {
            weaponName = "flame thrower";
            weaponDMG = 50;
            JOptionPane.showMessageDialog(frame, "You've selected a flame thrower... crispy!");
            frame.dispose();
        });

        panel.add(swordBtn);
        panel.add(gunBtn);
        panel.add(bowBtn);
        panel.add(flameBtn);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
