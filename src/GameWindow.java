import java.awt.*;
import javax.swing.*;

public class GameWindow extends JFrame {

    private JLabel lblHealth;

    public GameWindow() {

        final int FRAME_WIDTH = 650;
        final int FRAME_HEIGHT = 800;

        // Initialize shared game objects (prevents null crashes later)
        if (Main.ply == null) Main.ply = new Player();
        if (Main.wpn == null) Main.wpn = new Weapon();
        if (Main.zom == null) Main.zom = new Zombie();

        // === Top panel with vertical layout ===
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // No images (you said you don't have pictures)
        JLabel title = new JLabel("ZOMBIE GAME", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // -- Game stats row --
        JPanel statsPanel = new JPanel(new GridLayout(1, 3));
        statsPanel.add(new JLabel("KILL COUNT: 0", SwingConstants.CENTER));
        statsPanel.add(title);
        statsPanel.add(new JLabel("ROUND: 1 | TIMER: --", SwingConstants.CENTER));
        topPanel.add(statsPanel);
        statsPanel.setPreferredSize(new Dimension(100, 70));

        // === Gameplay screen panel (center) ===
        JPanel gameScreen = new GamePanel();
        gameScreen.setBackground(Color.BLACK);
        gameScreen.setPreferredSize(new Dimension(FRAME_WIDTH, 550));

        // === Bottom panel ===
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));

        // Health label (will update)
        lblHealth = new JLabel("HEALTH: " + Main.ply.playerHealth, SwingConstants.CENTER);
        bottomPanel.add(lblHealth);

        // Weapon button
        JButton btnWeapon = new JButton("WEAPON");
        btnWeapon.addActionListener(evt -> {
            Weapon.weaponSelect();
            refreshStats();
        });
        bottomPanel.add(btnWeapon);

        // Items button (now passes player)
        JButton btnItems = new JButton("ITEMS");
        btnItems.addActionListener(evt -> {
            new Items(Main.ply);
            refreshStats();
        });
        bottomPanel.add(btnItems);

        // Fight demo button
        JButton btnFight = new JButton("FIGHT DEMO");
        btnFight.addActionListener(evt -> {
            Main.startFightDemo();
            refreshStats();
        });
        bottomPanel.add(btnFight);

        bottomPanel.setPreferredSize(new Dimension(100, 70));

        // === Layout setup ===
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(gameScreen, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // === Frame setup ===
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle("Zombie Game Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        SwingUtilities.invokeLater(() -> gameScreen.requestFocusInWindow());
    }

    private void refreshStats() {
        if (lblHealth != null && Main.ply != null) {
            lblHealth.setText("HEALTH: " + Main.ply.playerHealth);
        }
    }

    private class GameScreen extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.drawString("GAMEPLAY SCREEN", getWidth() / 2 - 50, getHeight() / 2);
        }
    }
}
