class Edge implements Comparable<Edge>{
  private boolean isCopper;
  private int bandwidth;
  private int length;
  private int end;
  private double latency;

  public Edge(boolean isCopper, int bandwidth, int length, int end){
    this.isCopper = isCopper;
    this.bandwidth = bandwidth;
    this.length = length;
    this.end = end;
    if (isCopper){
      this.latency = (double)this.length / 230000000;
    }else{
      this.latency = (double)this.length / 200000000;
    }
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

  public int getEnd(){
    return this.end;
  }

  public double latency(){
    return this.latency;
  }
}
