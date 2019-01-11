import java.io.*;
import java.util.*;

public class Ping{
    public static void main(String[] args) {

        System.out.println("Enter the host ip");
        Scanner sc = new Scanner(System.in);
        String ip = sc.next();
        System.out.println("Enter the number of times the server to be pinged");
        int count = sc.nextInt();
        String timeString;
        float[] time = new float[count];

        String pingCmd = "ping " + ip + " -c" + count;

        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inputLine=in.readLine();
            int size=count;
            for (int i = 0; (inputLine = in.readLine()) != null && count >0; i++) {
                timeString = inputLine.substring(inputLine.lastIndexOf('=') + 1, inputLine.lastIndexOf(' '));
                //extracting the substring containing time//
                time[i] = Float.parseFloat(timeString);
                count--;
            }
            in.close();
            sc.close();

            Arrays.sort(time);//since median can be calculated only on sorted data
            if(size%2 == 0){
                System.out.println("\n" + "The median is " + (time[size/2]+time[(size/2)-1])/2 + " ms");
            }
            else{
                System.out.println("\n" + "The median is " + time[size/2] + " ms");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
