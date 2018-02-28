/**
    LZW Compression algorithm Modified for CS1501 Project 2
    Implements variable-width codewords and several techniques for
    when the codebook is filled up.
    @author Kevin Good
*/

/*************************************************************************
 *  Compilation:  javac MyLZW.java
    MODE OPTIONS: n do nothing
           r reset codebook
           m monitor compression ratio
 *  Execution:    java MyLZW - (mode) < input.txt > output.txt  (compress)
 *  Execution:    java MyLZW + < input.txt > output.txt  (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *
 *  Compress or expand binary input from standard input using LZW.
 *
 *  WARNING: STARTING WITH ORACLE JAVA 6, UPDATE 7 the SUBSTRING
 *  METHOD TAKES TIME AND SPACE LINEAR IN THE SIZE OF THE EXTRACTED
 *  SUBSTRING (INSTEAD OF CONSTANT SPACE AND TIME AS IN EARLIER
 *  IMPLEMENTATIONS).
 *
 *  See <a href = "http://java-performance.info/changes-to-string-java-1-7-0_06/">this article</a>
 *  for more details.
 *
 *************************************************************************/
import java.util.Arrays;

public class MyLZW {
    private static final int R = 256; // number of input chars
    private static int L = 512;       // initial number of codewords = 2^W
    private static int W = 9;         // codeword width

    public static void compress(char mode) {
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R+1;  // R is codeword for EOF
        BinaryStdOut.write(mode); //write mode for expansion
        while (input.length() > 0) {
            String s = st.longestPrefixOf(input);  // Find max prefix match s.
            BinaryStdOut.write(st.get(s), W);      // Print s's encoding.
            int t = s.length();
            //increase codeword length && number of codewords
            if (code == L && W < 16){
              W++;
              L *= 2;
            }else if (mode == 'r' && code == L && W == 16){
              W = 9;
              L = 512;
              st = new TST<Integer>();
              for (int i = 0; i < R; i++){
                st.put("" + (char) i, i);
              }
              code = R+1;
            }
            if (t < input.length() && code < L)    // Add s to symbol table.
                st.put(input.substring(0, t + 1), code++);
            //TODO: else if code == l for modes?
            input = input.substring(t);            // Scan past s in input.
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }


    public static void expand() {
        String[] st = new String[L];
        int i; // next available codeword value
        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";                        // (unused) lookahead for EOF
        char mode = BinaryStdIn.readChar(); //get mode
        int codeword = BinaryStdIn.readInt(W);
        if (codeword == R) return;           // expanded message is empty string
        String val = st[codeword];

        while (true) {
            BinaryStdOut.write(val);
            codeword = BinaryStdIn.readInt(W);;
            if (codeword == R) break;
            String s = st[codeword];
            if (i == codeword) s = val + val.charAt(0);   // special case hack
            if (i < L) st[i++] = val + s.charAt(0);
            if (i == L && W < 16){
              W++;
              L *= 2;
              st = Arrays.copyOf(st, L);
            }else if (mode == 'r' && i == L &&  W == 16){
              W = 9;
              L = 512;
              st = new String[L];
              for (i = 0; i < R; i++){
                  st[i] = "" + (char) i;
              st[i++] = "";
              }
            }
            //TODO: else if W == 16 for modes?
            val = s;
        }
        BinaryStdOut.close();
    }



    public static void main(String[] args) {

        if (args[0].equals("-")){
          char mode = args[1].charAt(0);
          if (mode != 'n' && mode != 'r' && mode != 'm'){
            throw new IllegalArgumentException("Invalid mode selection");
          }
          compress(mode);
        }
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }

}
