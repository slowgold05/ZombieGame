import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads userdata.txt and validates login.
 *
 * userdata.txt format (repeats per user):
 *   line 1: username
 *   line 2: password (plaintext - for a school project only)
 *   line 3: first name
 *   line 4: last name
 *
 * Put userdata.txt in the SAME folder as your .java files.
 */
public class GameFile {

	private static final String USERDATA_FILENAME = "userdata.txt";

	/**
	 * passwordEntry format from SignInScreen:
	 *   "##" + <encryptedPassword>
	 * where ## is a 2-digit Caesar shift.
	 */
	public boolean readFile(String username, String passwordEntry) throws IOException {

		File file = new File(USERDATA_FILENAME);
		if (!file.exists()) {
			throw new FileNotFoundException("Missing " + USERDATA_FILENAME
					+ " (put it in the same folder as your .java files).");
		}

		if (username == null || username.isBlank() || passwordEntry == null || passwordEntry.length() < 2) {
			return false;
		}

		int key;
		try {
			key = Integer.parseInt(passwordEntry.substring(0, 2));
		} catch (NumberFormatException ex) {
			return false;
		}

		String incomingEncrypted = passwordEntry.substring(2);
		String decryptedPassword = decrypt(incomingEncrypted, key);

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			while (true) {
				String uname = reader.readLine();
				if (uname == null) break;

				String passwd = reader.readLine();
				String fname = reader.readLine();
				String lname = reader.readLine();

				if (passwd == null || fname == null || lname == null) break;

				if (uname.equals(username) && passwd.equals(decryptedPassword)) {

					// Initialize shared objects
					Main.ply = new Player(fname + " " + lname);
					Main.wpn = new Weapon();
					Main.zom = new Zombie();

					System.out.println("WELCOME!! " + fname + " " + lname);

					new GameWindow();
					return true;
				}
			}
		}

		return false;
	}

	// Decryption is encryption with the inverse shift
	public static String decrypt(String encword, int shift) {
		return encrypt(encword, 26 - (shift % 26));
	}

	// Caesar shift on letters only
	public static String encrypt(String word, int shift) {
		StringBuilder encryptedWord = new StringBuilder();

		for (char c : word.toCharArray()) {
			if (Character.isLetter(c)) {
				char base = Character.isLowerCase(c) ? 'a' : 'A';
				int originalAlphabetPosition = c - base;
				int newAlphabetPosition = (originalAlphabetPosition + shift) % 26;
				if (newAlphabetPosition < 0) newAlphabetPosition += 26;
				encryptedWord.append((char) (base + newAlphabetPosition));
			} else {
				encryptedWord.append(c);
			}
		}

		return encryptedWord.toString();
	}
}
