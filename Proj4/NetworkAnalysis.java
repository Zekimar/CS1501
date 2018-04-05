import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Stack;

class NetworkAnalysis{
  private static Graph g;
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
        System.out.println(speed + " " + length + " " + end1 + " " + end2 + " " + isCopper);
        Edge e = new Edge(isCopper, speed, length, end1, end2);
        graph[end1].add(e);
        graph[end2].add(e);
      }
      g = new Graph(graph);
    }catch(FileNotFoundException e){
      e.printStackTrace();
      System.err.println("Could not find file");
      System.exit(1);
    }
    System.out.println("Welcome to network analysis by KLG92");
    System.out.println(g.hasPath(0, 4));
    System.out.println(g.hasPath(1, 4));
    System.out.println(g.hasPath(2, 4));
    Scanner input = new Scanner(System.in);
    int choice = -1;
    while (choice != 6){
      System.out.println("Select an option: \n1: Lowest latency path\n2: determine copper-only connection\n" +
      "3: Find maximum amount of data from vertex pair\n4: minimum average latency spanning tree\n" +
      "5: determine connectedness\n6: exit");
      choice = input.nextInt();
      switch(choice){
        case 1:
          System.out.println("Input vertex 1:");
          int vertex1 = input.nextInt();
          System.out.println("Input vertex 2:");
          int vertex2 = input.nextInt();
        break;
      }
    }
  }
  // public static boolean hasPath(int v, int w){
  //   Vertex cur = graph[v];
  //   Stack<Vertex> visited = new Stack<Vertex>();
  //   visited.push(cur);
  //   int i = 0;
  //   while (!visited.empty()){
  //     cur = visited.pop();
  //     LinkedList<Edge> temp = cur.edges();
  //     for (Edge e: temp){
  //       if (e.getEnd2() == w){
  //         return true;
  //       }
  //       visited.push(graph[e.getEnd2()]);
  //     }
  //   }
  //   return false;
  // }
  //
  // public static boolean hasCopperPath(){
  //
  //   return false;
  // }
}
