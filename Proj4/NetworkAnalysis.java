import java.io.*;
import java.util.Scanner;

class NetworkAnalysis{
  public static void main(String[] args){
    //Edge e = new Edge(false, 1, 1, 0, 1);
    //System.out.println(e.length());
    if (args.length != 1){
      System.err.println("invalid number of command line args");
      System.exit(1);
    }

    try{
      File f = new File(args[0]);
      Scanner input = new Scanner(f);
      int i = Integer.parseInt(input.nextLine());
      Vertex[] graph = new Vertex[i];
      for (int j = 0; j < i; j++){
        graph[j] = new Vertex(j);
      }
      //System.out.println(i);
      while(input.hasNextLine()){
        String[] parts = input.nextLine().split(" ");
        int end1 = Integer.parseInt(parts[0]);
        int end2 = Integer.parseInt(parts[1]);
        boolean isCopper = false;
        if (parts[2].equals("copper")){
          isCopper = true;
        }
        int speed = Integer.parseInt(parts[3]);
        int length = Integer.parseInt(parts[4]);
        System.out.println(speed + " " + length + " " + end1 + " " + end2);
        Edge e = new Edge(isCopper, speed, length, end1, end2);
        graph[end1].add(e);
        graph[end2].add(e);
      }
    }catch(FileNotFoundException e){
      e.printStackTrace();
      System.err.println("Could not find file");
      System.exit(1);
    }
    System.out.println("Welcome to network analysis by KLG92");
    Scanner input = new Scanner(System.in);
    


  }
}
