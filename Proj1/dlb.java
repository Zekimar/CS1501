//KLG92 Feb. 2018 CS1501 spring 2018


public class dlb{
  private Node root;

  public dlb(){
    root = new Node('0');
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

		private boolean setChild(char c){
    	child = new Node(c);
			return true;
		}
	  private static boolean hasChild(Node n){
	    if (n.child == null){
				return false;
		  }
			return true;
	  }
		private boolean setNext(char c){
	    next = new Node(c);
	    return true;
		}
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
          n.setChild('0');
        }
        n = n.child;
      }else{
        if (n.value == '0'){
          n.setValue(c);
        }else if (!Node.hasNext(n)){
          n.setNext('0');
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
  public String[] predict(String s){
    String[] prediction = new String[5];
    int numWords = 0;
    String buffer = s;
    Node n = findInput(s);
    n = n.child;

    while (numWords < 5){
      if (n.value == '$'){
        prediction[numWords] = buffer;
        numWords++;
        n = n.next;
      }else{
        n = n.child;
        if (n != null){
          buffer += n.value;
        }
      }
      if (n.next == null){

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
  }

}
