class Edge implements Comparable<Edge>{
  private boolean isCopper;
  private int bandwidth;
  private int length;
  private int end1;
  private int end2;
  private double latency;

  public Edge(boolean isCopper, int bandwidth, int length, int end1, int end2){
    this.isCopper = isCopper;
    this.bandwidth = bandwidth;
    this.length = length;
    this.end1 = end1;
    this.end2 = end2;
    if (isCopper){
      this.latency = (double)this.length / 230000000;
    }else{
      this.latency = (double)this.length / 200000000;
    }
    System.out.println(this.latency);
    //System.out.println(this.latency / 100000000);
  }

  @Override
  public int compareTo(Edge that){
    if (this.latency < that.latency){
      return 0;
    }else{
      return 1;
    }
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

  public int other(int vertex) {
    if      (vertex == end1) return end2;
    else if (vertex == end2) return end1;
    else throw new IllegalArgumentException("Illegal endpoint");
  }

  public int getEnd2(){
    return this.end2;
  }

  public double latency(){
    return this.latency;
  }
}
