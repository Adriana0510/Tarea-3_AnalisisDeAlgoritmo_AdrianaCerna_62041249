import java.util.*;
import java.util.concurrent.TimeUnit;

public class InsertionSort {
    
    public static void sort(int[] arr)
    {
        for (int i = 1; i < arr.length; i++) 
        {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) 
            {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    public static void main(String[] args) {
        Random rand = new Random();
        
        int[] sizes = {50000, 100000, 1000000, 10000000, 100000000, 1000000000 };
        
        for (int size : sizes) 
        {
            try 
            {
                System.out.println("=== Probando con " + size + " elementos ===");
                
                int[] arr = new int[size];

                for (int i = 0; i < size; i++) 
                {
                    arr[i] = rand.nextInt(size * 10);
                }
                
                if (size <= 100) 
                {
                    System.out.println("ARREGLO ORIGINAL:");
                    System.out.println(Arrays.toString(arr));
                } 
                else 
                {
                    System.out.print("Original (primeros 50): [");
                    for (int i = 0; i < Math.min(50, size); i++) 
                    {
                        System.out.print(arr[i] + (i < Math.min(49, size-1) ? ", " : ""));
                    }
                    System.out.println("]");
                }
                
                long start = System.nanoTime();
                sort(arr);
                long end = System.nanoTime();
                long tiempo = TimeUnit.NANOSECONDS.toMillis(end - start);
                
                System.out.println("Tiempo: " + tiempo + " ms");
                
                if (size <= 100) 
                {
                    System.out.println("ARREGLO ORDENADO:");
                    System.out.println(Arrays.toString(arr));
                }
                else 
                {
                    System.out.print("Ordenado (primeros 50): [");
                    for (int i = 0; i < Math.min(50, size); i++) 
                    {
                        System.out.print(arr[i] + (i < Math.min(49, size-1) ? ", " : ""));
                    }
                    System.out.println("]");
                    
                    System.out.print("Ordenado (ultimos 10): [");
                    for (int i = Math.max(0, size-10); i < size; i++) 
                    {
                        System.out.print(arr[i] + (i < size-1 ? ", " : ""));
                    }
                    System.out.println("]");
                }
                
                System.out.println();
                
            } catch (OutOfMemoryError e) {
                System.out.println("" + size + " elementos: ERROR - Memoria insuficiente");
                break;
            } catch (Exception e) {
                System.out.println("" + size + " elementos: ERROR - " + e.getMessage());
                break;
            }
        }
        System.out.println("\n PRUEBA COMPLETADA");
    }
}