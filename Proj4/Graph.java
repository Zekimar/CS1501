import java.util.Stack;
import java.util.ArrayList;
import java.util.PriorityQueue;
class Graph{
  private Vertex[] graph;
  int length;

  public Graph(Vertex[] graph){
    this.graph = graph;
    this.length = graph.length;
  }

  public Vertex[] getGraph(){
    return graph;
  }

  public boolean hasPath(int v, int w){
    Vertex cur = graph[v];
    Stack<Vertex> visited = new Stack<Vertex>();
    boolean[] done = new boolean[graph.length];
    visited.push(cur);
    int i = 0;
    while (!visited.empty()){
      cur = visited.pop();
      done[cur.getName()] = true;
      Edge[] temp = cur.edges();
      for (Edge e: temp){
        if (e.getEnd() == w){
          return true;
        }
        if (!done[e.getEnd()]){
          visited.push(graph[e.getEnd()]);
        }
      }
    }
    return false;
  }

  public boolean hasCopperPath(int v, int w){
    Vertex cur = graph[v];
    Stack<Vertex> visited = new Stack<Vertex>();
    boolean[] done = new boolean[graph.length];
    visited.push(cur);
    int i = 0;
    while (!visited.empty()){
      cur = visited.pop();
      done[cur.getName()] = true;
      Edge[] temp = cur.edges();
      for (Edge e: temp){
        if (e.isCopper()){
          if (e.getEnd() == w){
            return true;
          }
          if (!done[e.getEnd()]){
            visited.push(graph[e.getEnd()]);
          }
        }
      }
    }
    return false;
  }

  public int lowestLatency(int v, int w){
    ArrayList<Integer> path = new ArrayList<Integer>();
    if (!hasPath(v, w)){
      return -1;
    }
    //path.add(v);
    Vertex cur = graph[v];
    //distance from v to that vertex
    double[] distTo = new double[graph.length];
    //the starting point of the last edge of the path to the specified vertex
    int[] edgeTo = new int[graph.length];
    boolean[] visited = new boolean[graph.length];
    visited[v] = true;
    for (int i = 0; i < distTo.length; i++){
      distTo[i] = 12341.3;
    }
    distTo[v] = 0.0;
    edgeTo[v] = -1;
    PriorityQueue<Edge> neighbors = new PriorityQueue<Edge>();
    while(!visited[w]){
      for (Edge e: cur.edges()){
        int end = e.getEnd();
        double dist = distTo[cur.getName()];
        dist += e.latency();
        if (dist < distTo[end]){
          distTo[end] = dist;
          edgeTo[end] = cur.getName();
        }
        if (!visited[end]){
          neighbors.add(e);
        }
      }
      visited[cur.getName()] = true;
      cur = graph[neighbors.poll().getEnd()];
    }

    cur = graph[w];
    while (cur != graph[v]){
      path.add(cur.getName());
      Vertex a = graph[edgeTo[cur.getName()]];
      cur = a;
    }
    path.add(cur.getName());

    System.out.print("\nPath: ");
    int bandwidth = Integer.MAX_VALUE;
    for (int i = path.size() - 1; i >= 0; i--){
      System.out.print(path.get(i) + "->");
    }
    for (int i = 0; i <= path.size() - 2; i++){
      Edge[] edges = graph[path.get(i)].edges();
      for (Edge e: edges){
        if (e.getEnd() == path.get(i + 1)){
          if (bandwidth > e.bandwidth()){
            bandwidth = e.bandwidth();
          }
        }
      }
    }
    return bandwidth;
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

  public boolean isConnected(){
    for (int i = 0; i < graph.length; i++){
      for (int j = 1; j < graph.length; j++){
        if (!hasPath(i, j)){
          return false;
        }
      }
    }
    return true;
  }

  public int maxData(int v, int w){
    ArrayList<Integer> p = new ArrayList<Integer>();
    int ans = 0;
    int[] bandwidth = new int[graph.length];
    int[] edgeTo = new int[graph.length];
    boolean[] visited = new boolean[graph.length];
    Vertex cur = graph[v];
    Stack<Edge> q = new Stack<Edge>();
    while (!visited[w]){
      for (Edge e: cur.edges()){
        int end = e.getEnd();
        if (e.bandwidth() > bandwidth[end]){
          bandwidth[end] = e.bandwidth();
          edgeTo[end] = cur.getName();
        }
        if (!visited[end]){
          q.push(e);
        }
      }
      visited[cur.getName()] = true;
      cur = graph[q.pop().getEnd()];
    }
    cur = graph[w];
    while (cur != graph[v]){
      p.add(cur.getName());
      Vertex a = graph[edgeTo[cur.getName()]];
      cur = a;
    }
    p.add(cur.getName());
    for (int i = 0; i <= p.size() - 2; i++){
      Edge[] edges = graph[p.get(i)].edges();
      for (Edge e: edges){
        if (e.getEnd() == p.get(i + 1)){
          if (ans < e.bandwidth()){
            ans = e.bandwidth();
          }
        }
      }
    }
    return ans;
  }

  public void mst(){
    int[] edgeTo = new int[graph.length];
    double[] distTo = new double[graph.length];
    for (int i = 0; i < distTo.length; i++){
      distTo[i] = 12341.3;
    }
    Vertex cur;
    for (int i = 0; i < graph.length; i++){
      cur = graph[i];
      for (Edge e: cur.edges()){
        int end = e.getEnd();
        double dist = distTo[cur.getName()];
        // if (dist == 12341.3){
        //   dist = e.latency();
        // }else{
        //   dist += e.latency();
        // }
        dist = e.latency();
        if (dist < distTo[end]){
          distTo[end] = dist;
          edgeTo[end] = cur.getName();
        }
      }
    }
    System.out.println("");
    for (int i = 0; i < graph.length; i++){
      System.out.println(i + "->" + edgeTo[i]);
    }
    System.out.println("");
  }

  public boolean isBiconnected(){
    Vertex cur = graph[0];
    if (!isConnected()){
      return false;
    }
    for (int i = 0; i < graph.length; i++){
      if (cur.edges().length < 3){
        return false;
      }
    }
    return true;
  }
}
