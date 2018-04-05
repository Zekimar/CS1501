import java.util.Stack;
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
        if (e.getEnd2() == w){
          return true;
        }
        if (visited.search(graph[e.getEnd2()]) == -1){
          visited.push(graph[e.getEnd2()]);
        }
      }
    }
    return false;
  }

  public boolean hasCopperPath(){

    return false;
  }
}
