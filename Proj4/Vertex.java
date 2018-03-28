
import java.util.LinkedList;
class Vertex{
  private int name;
  private LinkedList<Edge> edges = new LinkedList<Edge>();

  public Vertex(int name){
    this.name = name;
  }

  public boolean add(Edge e){
    return edges.add(e);
  }
}
