import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;
/**
  takes in input that respresents a graph and performs analysis on it.
  this graph represents internet servers with edges having bandwidth and
  length. Edges can be copper or optical
  latency = length / 230,000,000 for copper, 200,000,000 for optical.

  @author Kevin Good KLG92

*/

class NetworkAnalysis{
  private static Graph g;
  public static void main(String[] args){
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
        graph[end1].add(new Edge(isCopper, speed, length, end2));
        graph[end2].add(new Edge(isCopper, speed, length, end1));
      }
      g = new Graph(graph);
    }catch(FileNotFoundException e){
      e.printStackTrace();
      System.err.println("Could not find file");
      System.exit(1);
    }
    System.out.println("Welcome to network analysis by KLG92");
    Scanner input = new Scanner(System.in);
    int choice = -1;
    while (choice != 6){
      System.out.print("1: Lowest latency path\n2: determine copper-only connection\n" +
      "3: Find maximum bandwidth from vertex pair\n4: minimum average latency spanning tree\n" +
      "5: determine if graph will be connected if any two points fail\n6: exit\nSelect an option:");
      choice = input.nextInt();
      switch(choice){
        case 1:
          System.out.println("Input vertex 1:");
          int vertex1 = input.nextInt();
          System.out.println("Input vertex 2:");
          int vertex2 = input.nextInt();
          int bandwidth = g.lowestLatency(vertex1, vertex2);
          System.out.println("\nBandwidth: "+ bandwidth + "\n");
          break;
        case 2:
          if (g.isCopperConnected()){
            System.out.println("\nThis graph is copper only connected\n");
          }else{
            System.out.println("\nThis graph is NOT copper only connected\n");
          }
          break;
        case 3:
          System.out.println("Input vertex 1:");
          vertex1 = input.nextInt();
          System.out.println("Input vertex 2:");
          vertex2 = input.nextInt();
          System.out.println("\nMax Bandwidth is: " + g.maxData(vertex1, vertex2) + "\n");
          break;
        case 4:
          g.mst();
          break;
        case 5:
          System.out.println("\n" + g.isBiconnected() + "\n");
          break;
      }
    }
  }
}
