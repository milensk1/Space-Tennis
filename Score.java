import java.io.*;

public class Score {
    public static int HighestScoreRead() {
        int highestScore = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("ranks.txt"));
            String line = reader.readLine();
            while (line != null) {
                try {
                    int lineScore = Integer.parseInt(line.trim());
                    if (lineScore > highestScore) {
                        highestScore = lineScore;
                    }
                } catch (NumberFormatException e1) {
                }
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException ex) {
            System.err.println("ERROR reading scores from file");
        }
        return highestScore;
    }

    public static void HighestScoreWrite(int input) {


        if (input > HighestScoreRead()) {
            try {
                BufferedWriter output = new BufferedWriter(new FileWriter("ranks.txt", true));
                output.newLine();
                output.write(String.valueOf(input));
                output.close();
            } catch (IOException ex1) {
                System.out.printf("ERROR writing score to file: %s\n", ex1);
            }
        }
    }
}
