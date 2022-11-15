package Backgammon_Sprint12;
public class Points {

    public static void pushPoints(int i, String peekPoints) {

    }

    public static void popPoints(int i) {

    }

    public String peekPoints(int i) {
        if (i > 0 && i < 25){
            return "Black";
        }
        else{
            return "Red";
        }
    }

    public int getSize(int i) {
        return 0;
    }
}
