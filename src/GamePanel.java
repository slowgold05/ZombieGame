import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class GamePanel extends JPanel {

    // Simple player/zombie positions (top-left)
    private int playerX = 280, playerY = 240;
    private int zombieX = 50, zombieY = 50;

    private int playerSize = 30;
    private int zombieSize = 30;

    private double zombieHealth = 60;
    private double playerHealth = 100;

    private final Timer gameTimer;

    public GamePanel() {
        setFocusable(true);
        setBackground(Color.BLACK);

        // Key Bindings (better than KeyListener)
        bindKeys();

        // Game loop: update 30x/sec
        gameTimer = new Timer(33, e -> {
            updateGame();
            repaint();
        });
        gameTimer.start();
    }

    private void bindKeys() {
        // Movement
        addKey("W", () -> movePlayer(0, -10));
        addKey("S", () -> movePlayer(0, 10));
        addKey("A", () -> movePlayer(-10, 0));
        addKey("D", () -> movePlayer(10, 0));

        addKey("UP", () -> movePlayer(0, -10));
        addKey("DOWN", () -> movePlayer(0, 10));
        addKey("LEFT", () -> movePlayer(-10, 0));
        addKey("RIGHT", () -> movePlayer(10, 0));

        // Attack
        addKey("SPACE", this::attack);

        // Restart
        addKey("R", this::reset);
    }

    private void addKey(String key, Runnable action) {
    InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = getActionMap();

    KeyStroke ks = KeyStroke.getKeyStroke("pressed " + key); // ✅ reliable
    im.put(ks, key);

    am.put(key, new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            action.run();
        }
    });
}

    

    private void movePlayer(int dx, int dy) {
        playerX += dx;
        playerY += dy;

        // keep inside panel bounds
        playerX = Math.max(0, Math.min(playerX, getWidth() - playerSize));
        playerY = Math.max(0, Math.min(playerY, getHeight() - playerSize));
    }

    private void attack() {
        // simple hit check: if close enough, damage zombie
        int dist = Math.abs(playerX - zombieX) + Math.abs(playerY - zombieY);
        if (dist < 80) {
            double dmg = (Main.wpn != null) ? Weapon.weaponDMG : 15;
            zombieHealth -= dmg;
            if (zombieHealth <= 0) {
                // respawn zombie
                zombieHealth = 60;
                zombieX = 20;
                zombieY = 20;
            }
        }
    }

    private void updateGame() {
        // Zombie chases player (very simple)
        if (zombieX < playerX) zombieX += 2;
        if (zombieX > playerX) zombieX -= 2;
        if (zombieY < playerY) zombieY += 2;
        if (zombieY > playerY) zombieY -= 2;

        // Collision damage
        if (rectsOverlap(playerX, playerY, playerSize, playerSize, zombieX, zombieY, zombieSize, zombieSize)) {
            playerHealth -= 0.6; // damage per tick
            if (playerHealth <= 0) {
                playerHealth = 0;
                gameTimer.stop();
            }
        }

        // keep Main.ply health synced (so your bottom label can display it)
        if (Main.ply != null) {
            Main.ply.playerHealth = playerHealth;
        }
    }

    private boolean rectsOverlap(int ax, int ay, int aw, int ah, int bx, int by, int bw, int bh) {
        return ax < bx + bw && ax + aw > bx && ay < by + bh && ay + ah > by;
    }

    private void reset() {
        playerX = 280; playerY = 240;
        zombieX = 50; zombieY = 50;
        zombieHealth = 60;
        playerHealth = 100;
        if (!gameTimer.isRunning()) gameTimer.start();
        if (Main.ply != null) Main.ply.playerHealth = playerHealth;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Player
        g.setColor(Color.GREEN);
        g.fillRect(playerX, playerY, playerSize, playerSize);

        // Zombie
        g.setColor(Color.RED);
        g.fillRect(zombieX, zombieY, zombieSize, zombieSize);

        // HUD text
        g.setColor(Color.WHITE);
        g.drawString("Move: WASD / Arrows | Attack: SPACE | Restart: R", 15, 20);
        g.drawString("Player HP: " + String.format("%.1f", playerHealth), 15, 40);
        g.drawString("Zombie HP: " + String.format("%.1f", zombieHealth), 15, 60);

        if (playerHealth <= 0) {
            g.drawString("GAME OVER (press R)", getWidth() / 2 - 60, getHeight() / 2);
        }
    }
}
