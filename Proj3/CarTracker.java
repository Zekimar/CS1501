/**
  Project 3 CarTracker
  @author Kevin Good KLG92
*/
import java.util.Scanner;
import java.io.*;

class CarTracker{
  public static void main(String[] args){
    System.out.println("Welcome to CarTracker by KLG92");
    int input = 0;
    PQ pq = new PQ();
    try{
      File f = new File("cars.txt");
      Scanner asdf = new Scanner(f);
      String s = asdf.nextLine();
      while (asdf.hasNextLine()){
        s = asdf.nextLine();
        String[] parts = s.split(":");
        String vin = parts[0];
        String make = parts[1];
        String model = parts[2];
        int price = Integer.parseInt(parts[3]);
        int mileage = Integer.parseInt(parts[4]);
        String color = parts[5];
        pq.add(new Car(vin, make, model, price, mileage, color));
      }
    }catch(Exception e){
      e.printStackTrace();
      System.err.println("Error: file not found");
    }

    //Car c = new Car("hello", "car", "model", 13000, 12, "red");
    Car c;
    while (input != 8){
      System.out.println("\n1: Add a Car \n2: Update a Car \n3: Remove a car \n" +
      "4: retrieve the lowest price car \n5: retrieve the lowest mileage car \n" +
      "6: retrive the lowest price car by make/model \n7: retrieve the lowest mileage car by make/model \n" +
      "8: exit");
      System.out.println("Choose an action:");
      Scanner s = new Scanner(System.in);
      input = s.nextInt();
      switch(input){
        case 1:
          System.out.println("Enter vin number: ");
          String vin = s.next();
          System.out.println("Enter a make: ");
          String make = s.next();
          System.out.println("Enter a model: ");
          String model = s.next();
          System.out.println("Enter a price: ");
          int price = s.nextInt();
          System.out.println("Enter a mileage: ");
          int mileage = s.nextInt();
          System.out.println("Enter a color: ");
          String color = s.next();
          pq.add(new Car(vin, make, model, price, mileage, color));
          break;
        case 2:
          System.out.println("Enter a vin number: ");
          String vin1 = s.next();
          System.out.println("Do you wish to update: \n1)price \n2)mileage \n3)color");
          int color1 = s.nextInt();
          if (color1 == 1){
            System.out.println("Enter a price");
            int price1 = s.nextInt();
            pq.updatePrice(vin1, price1);
          }else if (color1 == 2){
            System.out.println("Enter a mileage");
            int price1 = s.nextInt();
            pq.updateMileage(vin1, price1);
          }else{
            System.out.println("Enter a color");
            String color2 = s.next();
            pq.updateColor(vin1, color2);
          }
          break;
        case 3:
          System.out.println("Enter a vin");
          String vin2 = s.next();
          pq.remove(vin2);
          break;
        case 4:
          c = pq.getPrice();
          System.out.println(c.getColor() + " " + c.getMake() + " " + c.getModel() +" for: $"+ c.getPrice());
          break;
        case 5:
          c = pq.getMileage();
          System.out.println(c.getColor() + " " + c.getMake() +" "+ c.getModel() +" for: $" + c.getMileage());
          break;
        case 6:
          System.err.println("Enter a make: ");
          make = s.next();
          System.err.println("Enter a model: ");
          model = s.next();
          c = pq.getPrice(make, model);
          System.out.println(c.getColor() + " " + c.getMake() +" "+ c.getModel() +" for: $"+ c.getPrice());
          break;
        case 7:
          System.err.println("Enter a make: ");
          make = s.next();
          System.err.println("Enter a model: ");
          model = s.next();
          c = pq.getMileage(make, model);
          System.out.println(c.getColor() + " " + c.getMake() +" "+ c.getModel() +" for: $"+ c.getMileage());
          break;
      }
    }
  }
}
