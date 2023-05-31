import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CSE222Map represents a map with coordinates, start and end points, and various operations on the map.
 */
public class CSE222Map {
    private int[][] map;
    private Coordinate start;
    private Coordinate end;
    private String fileName;

    /**
     * Constructs a CSE222Map object with the specified file name.
     * @param fileName the name of the file to read the map from
     * @throws IllegalArgumentException if the start or end point is marked as 1 on the map or filename is null.
     */
    public CSE222Map(String fileName) throws IllegalArgumentException {
        if (fileName == null) {
            throw new IllegalArgumentException("Invalid filename.");
        }
        this.fileName = fileName;
        this.readMapFromFile();
        if (map[start.getY()][start.getX()] == 1 || map[end.getY()][end.getX()] == 1) {
            throw new IllegalArgumentException("Start or end point can't be 1.");
        }
    }

    /**
     * Reads the map data from a file and initializes the map, start, and end points.
     */
    private void readMapFromFile() {
        try {
            File myFile = new File("./TextFiles/" + fileName);
            Scanner myReader = new Scanner(myFile);
            List<int[]> lines = new ArrayList<>();
            int lineCount = 0;


            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] paths = data.split(",");
                int[] row = new int[paths.length];

                for (int i = 0; i < paths.length; i++) {
                    if (paths[i].equals("-1")) {
                        row[i] = 1;
                    } else {
                        row[i] = Integer.parseInt(paths[i]);
                    }
                }

                if (lineCount == 0) {
                    this.start = new Coordinate(row[0], row[1]);
                } else if (lineCount == 1) {
                    this.end = new Coordinate(row[0], row[1]);
                } else {
                    lines.add(row);
                }
                lineCount++;
            }

            this.map = new int[lines.size()][lines.get(0).length];
            for (int i = 0; i < lines.size(); i++)
                this.map[i] = lines.get(i);


        } catch (FileNotFoundException e) {
            System.out.println("ERROR! An error occurred in file.");
            e.printStackTrace();
        }
    }

    /**
     * Writes the path coordinates to a file.
     * @param path the list of coordinates representing the path.
     * @param algorithmName from which algorithm is used to generate the path. We need this to put it in proper txt file.
     * @throws IllegalArgumentException if path is invalid.
     */
    public void writePath(List<Coordinate> path, String algorithmName) throws IllegalArgumentException {
        if (path == null) {
            throw new IllegalArgumentException("Path can't be null.");
        }
        try {
            String[] mt = this.fileName.split("\\.");
            FileWriter myWriter = new FileWriter("./Paths/" + algorithmName + "/" + mt[0] + "_path.txt");

            for (Coordinate coordinate : path) {
                myWriter.write(coordinate.getY() + "," + coordinate.getX() + System.lineSeparator());
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    /**
     * Converts the map and path to a PNG image file. And draws path on it based on the given path.
     * @param path the list of coordinates representing the path
     * @param algorithmName from which algorithm is used to generate the path. We need this to put it in proper txt file.
     * @throws IllegalArgumentException if path is null.
     */
    public void convertPNG(List<Coordinate> path, String algorithmName) throws IllegalArgumentException {
        if (path == null) {
            throw new IllegalArgumentException("Path can't be null.");
        }
        int width = this.map[0].length, height = this.map.length;
        int imageSize = 1;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int x = col * imageSize;
                int y = row * imageSize;
                int value = this.map[row][col];

                Color color = (value == 1) ? Color.BLACK : Color.WHITE;
                graphics.setColor(color);
                graphics.fillRect(x, y, imageSize, imageSize);
            }
        }

        graphics.setColor(Color.RED);
        for (Coordinate coordinate : path) {
            int y = coordinate.getY() * imageSize;
            int x = coordinate.getX() * imageSize;
            graphics.fillRect(x, y, imageSize, imageSize);
        }

        graphics.dispose();

        try {
            String[] mn = this.fileName.split("\\.");
            ImageIO.write(image, "png", new File("./Outputs/" + algorithmName + "/"  + mn[0] +"_output.png"));
            System.out.println("Image saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving image: " + e.getMessage());
        }
    }

    /**
     * Returns the map array.
     * @return the map array
     */
    public int[][] getMap() {
        return map;
    }

    /**
     * Returns the start coordinate
     * @return the start coordinate
     */
    public Coordinate getStart() {
        return start;
    }

    /**
     * Returns the end coordinate.
     * @return the end coordinate.
     */
    public Coordinate getEnd() {
        return end;
    }
}
