public class VariablesJava{
    public static int variableStatic;
	


	public void imprimir(){
		int variableLocal=12;

		System.out.println("Valor de variable estatica \t"+ variableStatic);		
		System.out.println("Valor de variable local \t" + variableLocal);
	}
	public static void main(String args[]){
		VariablesJava variablesJava=new VariablesJava();
		variablesJava.imprimir();
	}
}

