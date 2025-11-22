import java.util.*;
import java.util.concurrent.TimeUnit;

public class InsertionSort {
    
    public static void sort(int[] arr) {
        System.out.println("  Iniciando algoritmo de ordenamiento...");
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        System.out.println("Algoritmo terminado");
    }
    
    public static void main(String[] args) {
        System.out.println("INICIANDO PRUEBAS DE INSERTION SORT");
        Random rand = new Random();
        
        int[] sizes = {100000, 750000, 3000000};
        
        for (int size : sizes) {
            try {
                System.out.println("\n=== Probando con " + size + " elementos ===");
                
                int[] arr = new int[size];
                for (int i = 0; i < size; i++) {
                    arr[i] = rand.nextInt(size * 10);
                }
                System.out.println("Arreglo preparado - " + size + " elementos");
                
                System.out.print("Muestra original: [");
                for (int i = 0; i < Math.min(5, size); i++) {
                    System.out.print(arr[i] + (i < Math.min(4, size-1) ? ", " : ""));
                }
                System.out.println("...]");
                
                System.out.println("Iniciando ordenamiento...");
                long start = System.nanoTime();
                sort(arr);
                long end = System.nanoTime();
                long tiempo = TimeUnit.NANOSECONDS.toMillis(end - start);
                System.out.println("Ordenamiento completado");
                
                System.out.println("Tiempo: " + tiempo + " ms");
                
                System.out.print("Muestra ordenada: [");
                for (int i = 0; i < Math.min(5, size); i++) {
                    System.out.print(arr[i] + (i < Math.min(4, size-1) ? ", " : ""));
                }
                System.out.println("...]");
                
                boolean ordenado = true;
                for (int i = 0; i < size - 1; i++) {
                    if (arr[i] > arr[i + 1]) {
                        ordenado = false;
                        break;
                    }
                }
                System.out.println("Verificación: " + (ordenado ? "CORRECTO" : "INCORRECTO"));
                
            } catch (OutOfMemoryError e) {
                System.out.println("ERROR MEMORIA: " + size + " elementos - " + e.getMessage());
                break;
            } catch (Exception e) {
                System.out.println("ERROR GENERAL: " + size + " elementos - " + e.getMessage());
                e.printStackTrace();
                break;
            }
        }
        
        System.out.println("\\n TODAS LAS PRUEBAS COMPLETADAS");
    }
}