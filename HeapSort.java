import java.util.*;
import java.util.concurrent.TimeUnit;

public class HeapSort {
    
    public static void sort(int[] arr) 
    {
        int n = arr.length;
        
        for (int i = n / 2 - 1; i >= 0; i--)
        {
            heapify(arr, n, i);
        }
        
        for (int i = n - 1; i > 0; i--) 
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }
    
    private static void heapify(int[] arr, int n, int i) 
    {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] > arr[largest])
        {
            largest = left;
        }
        
        if (right < n && arr[right] > arr[largest])
        {
            largest = right;
        }

        if (largest != i) 
        {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }
    
    public static void main(String[] args) {
        Random rand = new Random();
        
        int[] sizes = {100000, 2550000, 5000000};
        
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
                } else {
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