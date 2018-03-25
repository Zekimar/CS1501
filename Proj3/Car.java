class Car{
  private String vin;
  private String make;
  private String model;
  private int price;
  private int mileage;
  private String color;
  public Car(String vin, String make, String model, int price, int mileage, String color){
    this.vin = vin;
    this.make = make;
    this.model = model;
    this.color = color;
    this.price = price;
    this.mileage = mileage;
  }

  public Car(String vin, int price, int mileage){
    this.vin = vin;
    this.make = "make";
    this.model = "model";
    this.color = "color";
    this.price = price;
    this.mileage = mileage;
  }

  public String getVin(){
    return vin;
  }
  public boolean setVin(String s){
    vin = s;
    return true;
  }

  public String getMake(){
    return make;
  }
  public boolean setMake(String s){
    make = s;
    return true;
  }

  public String getModel(){
    return model;
  }
  public boolean setModel(String s){
    model = s;
    return true;
  }

  public int getPrice(){
    return price;
  }
  public boolean setPrice(int i){
    price = i;
    return true;
  }

  public int getMileage(){
    return mileage;
  }
  public boolean setMileage(int i){
    mileage = i;
    return true;
  }

  public String getColor(){
    return color;
  }
  public boolean setColor(String s){
    color = s;
    return true;
  }
}
