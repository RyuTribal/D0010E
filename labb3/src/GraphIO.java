import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GraphIO{
	static public void readFile(Graph g, String filename){
		File file = new File(filename);
		Scanner scan = null;
		try{
			scan = new Scanner(file);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}

		int nodes = scan.nextInt();

		for(int i = 0; i < nodes; i++){
            g.addNode(scan.nextInt(), scan.nextInt(), scan.nextInt());
        }


        while(scan.hasNextInt()){
            int a = scan.nextInt();
            int b = scan.nextInt();
            int c = scan.nextInt();
            System.out.println("" + a + "" + b + "" + c);

            g.addEdge(a, b, c);
        }
		
	}
}