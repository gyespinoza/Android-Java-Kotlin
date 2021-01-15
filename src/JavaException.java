public class JavaException {
  
  
    public static void main(String args[]){
        try{
           int d = 0;
           int n =20;
           int resultado = n/d;
        }
      catch(ArithmeticException e){
        System.out.println("Excepcion: " + e);
      }
      finally{
        System.out.println("Dentro del bloque Finally");
      }
    }
}











    /*public static void main(String args[]) {
        int d = 0;
        int n = 20;

        try {
            int resultado = n / d;
            System.out.println("Esta linea no sera ejecutada");
        } catch (ArithmeticException e) {
         System.out.println("Exception = " + e);
        }
        System.out.println("Finaliza main");
       }
*/


