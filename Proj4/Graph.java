import java.util.Stack;
import java.util.ArrayList;
class Graph{
  private Vertex[] graph;
  int length;

  public Graph(Vertex[] graph){
    this.graph = graph;
    this.length = graph.length;
  }

  public boolean hasPath(int v, int w){
    Vertex cur = graph[v];
    Stack<Vertex> visited = new Stack<Vertex>();
    visited.push(cur);
    int i = 0;
    while (!visited.empty()){
      cur = visited.pop();
      Edge[] temp = cur.edges();
      for (Edge e: temp){
        if (e.getEnd() == w){
          return true;
        }
        if (visited.search(graph[e.getEnd()]) == -1){
          visited.push(graph[e.getEnd()]);
        }
      }
    }
    return false;
  }

  public boolean hasCopperPath(int v, int w){
    Vertex cur = graph[v];
    Stack<Vertex> visited = new Stack<Vertex>();
    visited.push(cur);
    int i = 0;
    while (!visited.empty()){
      cur = visited.pop();
      Edge[] temp = cur.edges();
      for (Edge e: temp){
        if (e.isCopper()){
          if (e.getEnd() == w){
            return true;
          }
          if (visited.search(graph[e.getEnd()]) == -1){
            visited.push(graph[e.getEnd()]);
          }
        }
      }
    }
    return false;
  }

  public int[] lowestLatency(int v, int w){
    ArrayList<Integer> path = new ArrayList<Integer>();
    path.add(v);
    Vertex cur = graph[v];
    //distance from v to that vertex
    double[] distTo = new double[graph.length];
    //the starting point of the last edge of the path to the specified vertex
    int[] edgeTo = new int[graph.length];
  }

  public boolean isCopperConnected(){
    for (int i = 0; i < graph.length; i++){
      for (int j = 1; j < graph.length; j++){
        if (!hasCopperPath(i, j)){
          return false;
        }
      }
    }
    return true;
  }

}
