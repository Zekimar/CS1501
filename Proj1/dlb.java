//KLG92 Feb. 2018 CS1501 spring 2018
import java.util.ArrayList;

public class dlb{
  private Node root;

  public dlb(){
    root = new Node('!');
  }

  private static class Node{
    private Node next; //the next alternate letter
    private Node child; //the next letter in the word
    private char value;

    private Node(char c){
      next = null;
      child = null;
      value = c;
    }
  private void setValue(char v){
    	value = v;
  }

  // private boolean setChild(char c){
  //   	child = new Node(c);
  // return true;
  // }
    private static boolean hasChild(Node n){
      if (n.child == null){
  return false;
    }
  return true;
    }
  // private boolean setNext(char c){
  //     next = new Node(c);
  //     return true;
  // }
  private static boolean hasNext(Node n){
      if (n.next == null){
        return false;
  }
  return true;
  }
  }
  public boolean add(String s){
    s = s + "$";
    Node n = root;

    for (int i = 0; i < s.length(); i++){
      char c = s.charAt(i);
      if (n.value == c){
        if (!Node.hasChild(n) && c != '$'){
          n.child = new Node('0');
        }
        n = n.child;
      }else{
        if (n.value == '0' || n.value == '!'){
          n.setValue(c);
        }else if (!Node.hasNext(n)){
          n.next = new Node(c);
          n = n.next;
        }else{
          n = n.next;
        }
        i--;
      }
    }
    //System.out.println(root.child.value);
    return true;
  }

  public boolean contains(String s){
    if (s.charAt(s.length() - 1 ) != '$'){
      s = s + "$";
    }
    Node n = root;
    for (int i = 0; i < s.length(); i++){
      char c = s.charAt(i);
      while (n != null && n.value != c){
        n = n.next;
      }
      if (n == null){
        return false;
      }
      n = n.child;
    }
    return true;
  }

  public String[] predict(String[] prediction, String s, int numWords){
    Node n = findInput(s);
    ArrayList<Node> prevLevel = new ArrayList<Node>();
    Boolean reversed = false;
    prevLevel.add(n);
    n = n.child;
    String buf = s;
    buf += n.value;
    while (numWords < 5){
      if (prevLevel.size() == 0){
        return prediction;
      }
      //System.out.println(buf);
      if (n.value != '$'){
        if (n.child != null && !reversed){
          prevLevel.add(n);
          n = n.child;
          buf += n.value;
        }else if (n.next != null){
          n = n.next;
          buf = buf.substring(0, (buf.length() - 1));
          buf += n.value;
          reversed = false;
        }else{
          buf = buf.substring(0, (buf.length() - 1));
          n = prevLevel.get((prevLevel.size() - 1));
          prevLevel.remove(prevLevel.size() - 1);
          reversed = true;
        }
      }else if (n.value == '$'){
        buf = buf.replace("$", "");
        boolean already = false;
        if (contains(buf)){
          for (String word: prediction){
            if (word != null && word.equals(buf)){
              already = true;
            }
          }
          if (!already){
            prediction[numWords] = buf;
            numWords++;
          }
        }
        if (n.next != null){
          n = n.next;
          buf += n.value;
          reversed = false;
        }else{
          n = prevLevel.get((prevLevel.size() - 1));
          prevLevel.remove(prevLevel.size() - 1);
          reversed = true;
        }

      }
    }
    return prediction;
  }
  public Node findInput(String s){
    Node n = root;
    for(char c: s.toCharArray()){
      while (n.value != c){
        n = n.next;
        if (n == null){
          return n;
        }
      }
      if (c != s.charAt(s.length() - 1)){
        n = n.child;
      }
    }
    return n;
  }

  public static void main(String[] args){
    dlb DLB = new dlb();
    DLB.add("HELLO");
    DLB.add("goodbye");
    System.out.println(DLB.contains("goodbye"));
    System.out.println(DLB.contains("HELLO"));
    String s = "t";
    String g = "tab";
    System.out.println(g.startsWith(s));
  }

}
