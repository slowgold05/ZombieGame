import java.awt.*;
import javax.swing.*;

public class Items extends JFrame {

	private static final double healthItem = 10;
	private static final int ammoItem = 25;
	private static final int oneUp = 1;

	private final Player player;

	public Items(Player player) {
		this.player = player;

		final int FRAME_WIDTH = 360;
		final int FRAME_HEIGHT = 140;

		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));

		JButton btnHealth = new JButton("+HEALTH");
		JButton btnAmmo = new JButton("+AMMO");
		JButton btnLives = new JButton("+LIVES");

		btnHealth.addActionListener(evt -> {
			if (this.player == null) return;
			this.player.healToFull();
			JOptionPane.showMessageDialog(this, "Health updated.");
		});

		btnAmmo.addActionListener(evt -> {
			JOptionPane.showMessageDialog(this, "Ammo system not implemented yet.");
		});

		btnLives.addActionListener(evt -> {
			JOptionPane.showMessageDialog(this, "Lives system not implemented yet.");
		});

		c.add(btnHealth);
		c.add(btnAmmo);
		c.add(btnLives);

		setTitle("Item Menu");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
