import java.util.Hashtable;

class PQ{
  private static int size;
  private static Car[] cars;
  private Heap price;
  private Heap mileage;

  public PQ(){
    size = 0;
    cars = new Car[7];
    price = new Heap();
    mileage = new Heap();
  }

  public void resize(){
    int len = cars.length;
    Car newcars[] = new Car[len * 2 + 1];
    Node newPrice[] = new Node[len * 2 + 1];
    Node newMileage[] = new Node[len * 2 + 1];
    for (int i = 0; i < len; i++){
      newcars[i] = cars[i];
      newPrice[i] = price.vals[i];
      newMileage[i] = mileage.vals[i];
    }
    cars = newcars;
    mileage.vals = newMileage;
    price.vals = newPrice;
  }

  public boolean add(Car c){
    if (size == cars.length){
      resize();
    }
    cars[size] = c;
    price.add(c.getVin(), c.getPrice());
    mileage.add(c.getVin(), c.getMileage());
    size++;
    return true;
  }

  public void remove(String vin){
    cars[price.get(vin)] = null;
    price.remove(vin);
    mileage.remove(vin);
  }

  public Car getMileage(){
    return cars[mileage.get()];
  }

  public Car getMileage(String make, String model){
    int as = mileage.get(make, model);
    if (as == -1){
      System.out.println("Could not find car of make/model");
      return null;
    }
    Car ans = cars[as];
    return ans;
  }

  public Car getPrice(){
    return cars[price.get()];
  }
  public Car getPrice(String make, String model){
    int as = price.get(make, model);
    if (as == -1){
      System.out.println("could not find car of make/model");
      return null;
    }
    Car ans = cars[as];
    return ans;
  }

  public Car get(String s){
    return cars[price.get(s)];
  }

  public boolean updatePrice(String s, int i){
    cars[price.get(s)].setPrice(i);
    price.update(s, i);
    return true;
  }

  public boolean updateMileage(String s, int i){
    cars[price.get(s)].setMileage(i);
    mileage.update(s, i);
    return true;
  }

  public boolean updateColor(String s, String color){
    cars[price.get(s)].setColor(color);
    return true;
  }

  private static class Node{
    //private Node left;
    //private Node right;
    private String vin;
    private int value;
    private int pos;
    private int carNum;
    public Node(int v, int p, String s){
      value = v;
      pos = p;
      vin = s;
      carNum = size;
    }

    public void setValue(int i ){
      value = i;
    }
  }
  private static class Heap{
    //maps vin string to place in vals
    private Hashtable<String, Integer> ind;
    private Node[] vals;

    public Heap(){
      ind = new Hashtable<String, Integer>();
      vals = new Node[7];
    }

    private void balance(int p, Node n){
      int i = n.value;
      while (p > 0 && i < vals[(p - 1) / 2].value){
        Node holder = vals[(p - 1) / 2];
        vals[(p - 1) / 2] = n;
        vals[p] = holder;
        ind.put(vals[p].vin, p);
        p = (p - 1) / 2;
      }
      ind.put(vals[p].vin, p);
    }

    private void add(String s, int i){
      vals[size] = new Node(i, size, s);
      balance(size, vals[size]);
    }

    private int get(String make, String model){
      for (Node n: vals){
        Car c = cars[n.carNum];
        if (c.getMake().equals(make) && c.getModel().equals(model)){
          return n.carNum;
        }
      }
      return -1;
    }

    private int get(String s){
      return vals[ind.get(s)].carNum;
    }

    private int get(){
      return vals[0].carNum;
    }

    private void update(String s, int i){
      vals[ind.get(s)].value = i;
      balance(ind.get(s), vals[ind.get(s)]);
    }

    private void remove(String s){
      int p = ind.get(s);
      int left;
      int right;
      vals[p] = null;
      while (2 * p + 1 < cars.length && (vals[2*p + 1] != null || vals[2 * p + 2] != null)){
        left = 2 * p + 1;
        right = 2 * p + 2;
        if (vals[left] != null && vals[right] != null){
          if (vals[left].value < vals[right].value){
            vals[p] = vals[left];
            vals[left] = null;
            p = left;
          }else{
            vals[p] = vals[right];
            vals[right] = null;
            p = right;
          }
        }else if (vals[left] != null){
          vals[p] = vals[left];
          vals[left] = null;
          p = left;
        }else if (vals[right] != null){
          vals[p] = vals[right];
          vals[right] = null;
          p = right;
        }
        if (vals[left] == null && vals[right] == null){
          break;
        }
      }
    }
  }

  public static void main(String[] args){
    PQ pq = new PQ();
    Car a = new Car("1", 9, 9);
    Car b = new Car("2", 10, 10);
    Car c = new Car("3", 8, 8);
    Car d = new Car("4", 11, 11);
    Car e = new Car("5", 12, 12);
    pq.add(a);
    pq.add(b);
    pq.add(c);
    pq.add(d);
    pq.add(e);
    pq.remove("3");
    System.out.println(pq.getMileage().getMileage());
  }
}
