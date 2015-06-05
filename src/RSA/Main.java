package RSA;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    int n = 18209;//from assignment specs
    int b = 3001;
    int p,q;
    int totient;
    int mi;
    String message = "";
    DataReader dr;
	
	//set up for decryption
    public Main()throws Exception{
        int[] factors = factor(n);
        p = factors[0];
        q=factors[1];
        totient = (p-1)*(q-1);
        mi= MultiplicativeInverse(totient,b);
        dr = new DataReader("input.txt");
        decrypt();
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        pw.write(message);
        pw.close();
        System.out.println(message);
    }
	//decrypt the integers
    void decrypt(){
        int token;
        int decoded;
        while(dr.hasNext()){
            token = dr.getNext();
            decoded = SquareAndMultiply(token,toBinary(mi),n);
            message = message + toLetters(decoded);
        }
    }
	//convert decrypted integers to letters
    String toLetters(int in){
        String letters="";
        letters = (char)((in%26)+'a')+letters;
        in = in/26;
         letters = (char)((in%26)+'a')+letters;
        in = in/26;
        letters = (char)((in%26)+'a')+letters;

        return letters;
    }
	//change an integer into its binary string
    int[] toBinary(int n){
        String bin=Integer.toBinaryString(n);
        int[] binary = new int[bin.length()];

        for (int i = 0; i <binary.length ; i++) {
            binary[binary.length-i-1]=bin.charAt(i)-'0';
        }
        return binary;
    }

	//find factors of the n value
    int[] factor(int n){
        int index = 0;
        int[] factors=new int[2];
        for (int i = n-1; i > 1; i--) {
            if(n%i==0) {
                factors[index] = i;
                index++;
            }
            if(index==2) break;
        }
        return factors;
    }

	//Square and multiply, taken from lecture slides
    int SquareAndMultiply(int x,int[] c,int n)
    {
        int z = 1;
        int l=c.length;
        for(int i = l-1; i >= 0; i--)
        {
            z = z*z % n;
            if(c[i] == 1)
                z = z*x % n;
        }
        return z;
    }

	//Multiplicative inverse from lecture slides
    int MultiplicativeInverse(int a,int b)	// Find b-1 mod a
    {
        int a0 = a;  int b0 = b;  int t0 = 0;  int t = 1;
        int q = a0/b0;		// quotient
        int r = a0- q * b0;		// remainder
        while(r > 0)
        {
           int temp = (t0 - q * t) % a;
            t0 = t;
            t = temp;
            a0 = b0;
            b0 = r;
            q = a0/b0;
            r = a0 - q * b0;
        }
        if (b0 != 1)
            return -1;
        else {
            b = t;
        }
        return b;
    }

    public static void main(String[] args) throws Exception {
	Main m = new Main();
    }
}
