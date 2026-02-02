# Zombie Game (Java)

## Project Overview
This project is a simple Java-based zombie game developed using Swing for the graphical user interface.  
Players can log in, select weapons, use items, and interact with a basic real-time game panel where a zombie chases the player.

The project was created as part of an academic assignment and focuses on:
- Java fundamentals
- Object-oriented programming
- Event-driven programming with Swing
- Basic game loop and keyboard input handling

---

## Features
- Login system using a local text file (`userdata.txt`)
- Graphical user interface built with Java Swing
- Player movement using keyboard controls (WASD / Arrow keys)
- Basic zombie AI that follows the player
- Weapon selection system
- Health system with items
- Simple combat interaction
- Restart functionality

---

## Controls
- **W / A / S / D** or **Arrow Keys**: Move player  
- **SPACE**: Attack zombie (must be close)  
- **R**: Restart game  

---

## Project Structure
ZombieGame/
│
├── src/
│ ├── Main.java
│ ├── GameWindow.java
│ ├── GamePanel.java
│ ├── GameFile.java
│ ├── Player.java
│ ├── Zombie.java
│ ├── Weapon.java
│ ├── Items.java
│ ├── Map.java
│ ├── SignInScreen.java
│ └── userdata.txt
│
├── .project
├── .classpath
├── .settings/
├── .gitignore
└── README.md


---

## How to Run
### Option 1: Eclipse (Recommended)
1. Clone or download the repository
2. Open Eclipse
3. File → Open Projects from File System
4. Select the project folder
5. Run `Main.java`

### Option 2: Command Line
Navigate to the `src` directory and run:
```bash```
javac *.java
java Main
Login Credentials (Demo Accounts)
Use one of the following accounts to log in:

Username: batman
Password: hello

Username: test
Password: password

Credentials are stored in userdata.txt for demonstration purposes.

Notes
This project is intended for educational use.

Passwords are stored in plaintext for simplicity and learning purposes.

The game logic is intentionally simple to focus on Java and Swing concepts rather than advanced game mechanics.

Author
Developed as part of an academic Java programming assignment.
