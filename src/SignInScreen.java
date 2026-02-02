import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class SignInScreen extends JFrame {

	public String encPass = "";
	public GameFile gf = new GameFile(); // reads user's data file

	public SignInScreen() {
		Container c = getContentPane();

		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));

		// No logo image required (since you don't have pictures)
		JLabel lblLogo = new JLabel("ZOMBIE GAME", SwingConstants.CENTER);
		lblLogo.setFont(new Font("Bahnschrift", Font.BOLD, 26));
		lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		p1.add(lblLogo);

		// Username / password inputs
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(2, 2));

		JTextField uname = new JTextField();
		JPasswordField passwd = new JPasswordField(10);

		uname.setFocusable(true);
		uname.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		uname.setPreferredSize(new Dimension(50, 30));

		passwd.setBackground(Color.LIGHT_GRAY);
		passwd.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		passwd.setEchoChar('*');
		passwd.setPreferredSize(new Dimension(50, 30));

		p2.add(new JLabel("Username: ", SwingConstants.CENTER));
		p2.add(uname);
		p2.add(new JLabel("Password: ", SwingConstants.CENTER));
		p2.add(passwd);

		// Buttons / links panel
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));

		JButton sgn = new JButton("Sign In");
		sgn.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel forgot = new JLabel("Forgot Password?");
		forgot.setAlignmentX(Component.CENTER_ALIGNMENT);

		p3.add(Box.createVerticalStrut(5));
		p3.add(sgn);
		p3.add(Box.createVerticalStrut(5));
		p3.add(forgot);

		// Sign in action
		sgn.addActionListener(evt -> {
			String username = uname.getText().trim();
			String pass = new String(passwd.getPassword()); // better than getText()

			if (username.isEmpty() || pass.isEmpty()) {
				JOptionPane.showMessageDialog(
						this,
						"Please enter both username and password.",
						"Missing Fields",
						JOptionPane.WARNING_MESSAGE
				);
				return;
			}

			String hash = "";
			int shift = generateShift();
			encPass = encrypt(pass, shift);

			if (shift < 10) {
				hash = "0" + shift + encPass;   // two-digit shift
			} else {
				hash = "" + shift + encPass;
			}

			try {
				// Only close the sign-in window if login succeeds
				boolean success = gf.readFile(username, hash);

				if (success) {
					dispose(); // close only this window
				} else {
					JOptionPane.showMessageDialog(
							this,
							"Invalid username or password.",
							"Login Failed",
							JOptionPane.ERROR_MESSAGE
					);
				}

			} catch (IOException e) {
				JOptionPane.showMessageDialog(
						this,
						"Could not read userdata file.\n" + e.getMessage(),
						"File Error",
						JOptionPane.ERROR_MESSAGE
				);
			}
		});

		c.setLayout(new BorderLayout());
		c.add(p1, BorderLayout.NORTH);
		c.add(p2, BorderLayout.CENTER);
		c.add(p3, BorderLayout.SOUTH);

		pack();
		setTitle("Log In to Your Account");
		setLocationRelativeTo(null);

		//Do NOT exit the whole game if user closes sign-in window
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setVisible(true);
	}

	public int generateShift() {
		int x = (int) (Math.random() * 27);
		while (x == 0 || x == 26) {
			x = (int) (Math.random() * 27);
		}
		return x;
	}

	public String encrypt(String word, int shift) {
		StringBuilder encryptedWord = new StringBuilder();
		for (char c : word.toCharArray()) {
			if (Character.isLetter(c)) {
				char base = Character.isLowerCase(c) ? 'a' : 'A';
				int originalAlphabetPosition = c - base;
				int newAlphabetPosition = (originalAlphabetPosition + shift) % 26;
				if (newAlphabetPosition < 0) newAlphabetPosition += 26;
				char encryptedChar = (char) (base + newAlphabetPosition);
				encryptedWord.append(encryptedChar);
			} else {
				encryptedWord.append(c);
			}
		}
		return encryptedWord.toString();
	}
}
