public class Switch {
    public static void main(String args[]){
        int valor=3;

        switch (valor){
            case 0:
                System.out.println("Cero");
                break;
            case 1:
                System.out.println("Uno");
                break;
            case 2:
                System.out.println("Dos");
                break;
            case 3:
                System.out.println("Tres");
                break;
            case 4:
                System.out.println("Cuatro");
                break;
            default:
                System.out.println("El valor no se encuentra en la lista");
                break;
        }

    }
    
}
