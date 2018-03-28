class Edge{
  private boolean isCopper;
  private int bandwidth;
  private int length;
  private int end1;
  private int end2;

  public Edge(boolean isCopper, int bandwidth, int length, int end1, int end2){
    this.isCopper = isCopper;
    this.bandwidth = bandwidth;
    this.length = length;
    this.end1 = end1;
    this.end2 = end2;
  }

  public int bandwidth(){
    return this.bandwidth;
  }

  public boolean isCopper(){
    return this.isCopper;
  }

  public int length(){
    return this.length;
  }

  public int[] points(){
    int[] p = {end1, end2};
    return p;
  }
}
