public class Conversion {
    public static void main (String args []) {
        byte x;
        int a = 22;
        double b = 10.28;

        System.out.println ("int convertido a byte");
        x = (byte) a; //conversion de int a byte

        System.out.println ("a y x" + a + " " + x);

        System.out.println ("double convertido a int");
        a = (int) b; //conversion de double  a entero

        System.out.println ("b y a" + b + " " + a);

        System.out.println ("\n double convertido a byte");
        x = (byte) b; //conversion de double a byte

        System.out.println ("b y x" + b + " " + x);
       }
}
