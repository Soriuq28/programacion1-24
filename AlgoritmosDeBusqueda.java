import java.time.Instant;
import java.time.Duration;

public class AlgoritmosDeBusqueda{
	

	// retorne  -1 si el elemento no se enuentra
	// retorna la celda donde está ubicado si el elemento se encuentra
	public int busquedaSecuencial(int [] arreglo, int valor){
		int posicion = -1;
		int contador = 0;
		while(contador < arreglo.length && posicion == -1){
			if(arreglo[contador] == valor){
				posicion = contador;
			}
			contador++;
		}
		return posicion;
	}

	public int busquedaBinaria(int[]arreglo, int valor){
		return busquedaBinaria(arreglo, valor, 0, arreglo.length);
	}

	private int busquedaBinaria(int[] arreglo, int valor, int limInf, int limSup){
		int resultado = -1;
		int celda = (limInf + limSup)/2;
		
		if(limInf <= limSup  && celda >= 0 && celda < arreglo.length){
			// Caso trivial y casos recursivos
			if(arreglo[celda] == valor){
				// Caso trivial, encontramos el valor que buscamos
				resultado = celda;
			}
			else if (arreglo[celda]  > valor){
				// Caso recursivo donde el valor se encuentra parte inicial del arreglo
				resultado = busquedaBinaria(arreglo, valor, limInf, celda-1);
			}
			else{
				// Caso recursivo donde el valor se encuentra en la mitad + 1 hasta el final
				resultado = busquedaBinaria(arreglo,valor, celda+1, limSup);
			}
		}
		return resultado;
	}

	public static void main (String [] args){
		int [] arreglo1 = {1,2,4,6,8,9,10,15,18,22,34,45,47,60,90,100};
		AlgoritmosDeBusqueda ab = new AlgoritmosDeBusqueda();
		System.out.println(ab.busquedaSecuencial(arreglo1, 22));
		System.out.println(ab.busquedaSecuencial(arreglo1, 0));
		System.out.println(ab.busquedaBinaria(arreglo1, 22));
		System.out.println(ab.busquedaBinaria(arreglo1, 0));

		System.out.println("Ejecutando busquedas para calcular tiempos....");
		int [] arreglo2 = new int [1000000000];
		for(int  i = 0 ; i < arreglo2.length; i++){
			arreglo2[i] = 2*i;
		}

		Instant tiempoInicial = Instant.now();
		int indice = ab.busquedaSecuencial(arreglo2,1);
		Instant tiempoFinal = Instant.now();
		long tiempoTranscurrido = Duration.between(tiempoInicial, tiempoFinal).toMillis();
		System.out.println("Tiempo de la busqueda secuencial es: " + tiempoTranscurrido/1000.0);
	
		tiempoInicial = Instant.now();
		indice = ab.busquedaBinaria(arreglo2,1);
		tiempoFinal = Instant.now();
		tiempoTranscurrido = Duration.between(tiempoInicial, tiempoFinal).toMillis();
		System.out.println("Tiempo de la busqueda binaria es: " + tiempoTranscurrido/1000.0);


	}
}