import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        ArrayList<Object> queue = new ArrayList<Object>();
        ArrayList<Object> queue1 = new ArrayList<Object>();
        queue.add(null);
        queue1.add(null);
        FIFOmain.main(args);
        BFTmain.main(args);
    }
}