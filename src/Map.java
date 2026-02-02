public class Map {

    // Variables related to MAP
    private int level = 0;
    private boolean completionStatus;

    // Locations for each level
    public String[] map;

    public Map() {

        // New Game - initiate the map
        this.map = new String[] {"house", "street", "store", "building1", "building2"};
        completionStatus = false;

        // Opening narrative (ASCII art)
        System.out.println();
        System.out.println("                       _       _      ");
        System.out.println("                      | |     (_)     ");
        System.out.println(" ____  ___   _  _  _  | |      _   ____");
        System.out.println("|_  / ( _ ) | ||_|| | |_|--   | | ( __/");
        System.out.println(" / / ( (_) )| ||_|| | | |_) ) | |( __/");
        System.out.println("/___| (___) |_|   |_| |_.__/  |_| (___/");
        System.out.println("    ******************************     ");
        System.out.println();
    }

    public int getLevel() {
        return level;
    }

    public void nextLevel() {
        if (level < map.length - 1) {
            level++;
        } else {
            completionStatus = true;
        }
    }

    public boolean isComplete() {
        return completionStatus;
    }
}
