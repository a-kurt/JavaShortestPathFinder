import java.util.List;

public class TestCases implements Runnable {

    private  String FileName;
    private int X_SIZE;
    private int Y_SIZE;

    public TestCases(String FileName, int X_SIZE, int Y_SIZE) {
        this.FileName = FileName;
        this.X_SIZE = X_SIZE;
    	this.Y_SIZE = Y_SIZE;
    }



  
    
    public void test(){
    	
    	System.out.println("\n\n*******************\nMap is " + this.FileName + " with X_SIZE " + this.X_SIZE + " and Y_SIZE " + this.Y_SIZE + "\n********************\n");

        CSE222Map m = new CSE222Map(this.FileName);
        CSE222Graph g = new CSE222Graph(m);
        CSE222Dijkstra dijkstra = new CSE222Dijkstra(g);
        CSE222BFS bfs = new CSE222BFS(g);

        long startTime = System.nanoTime();
        List<Coordinate> dijkstraPath = dijkstra.findPath();
        long endTime = System.nanoTime();
        long runningTime = (endTime - startTime) / 1000000;
        System.out.println("Running time dijkstra: " + runningTime + " ms");

        startTime = System.nanoTime();
        List<Coordinate> bfsPath = bfs.findPath();
        endTime = System.nanoTime();
        runningTime = (endTime - startTime) / 1000000;
        System.out.println("Running time bfs: " + runningTime + " ms");

        m.convertPNG(dijkstraPath, "dijkstra");
        m.convertPNG(bfsPath, "bfs");

        m.writePath(dijkstraPath, "dijkstra");
        m.writePath(bfsPath, "bfs");

        System.out.println("Dijkstra Path: " + (dijkstraPath.size()-1));
        System.out.println("BFS Path: " + (bfsPath.size()-1));
    }

    @Override
    public void run() {
        test();
    }
}

