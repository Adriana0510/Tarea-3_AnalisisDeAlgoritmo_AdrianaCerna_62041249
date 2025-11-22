import java.util.*;
import java.util.concurrent.TimeUnit;

public class QuickSort {
    
    public static void sort(int[] arr) 
    {
        sort(arr, 0, arr.length - 1);
    }
    
    private static void sort(int[] arr, int low, int high) 
    {
        if (low < high) 
        {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) 
    {
        int pivot = arr[high];
        int i = (low - 1);
        
        for (int j = low; j < high; j++) 
        {
            if (arr[j] <= pivot) 
            {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }
    
    public static void main(String[] args) {
        Random rand = new Random();
        
        int[] sizes = {100000, 750000, 3000000};
        
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
            } catch (StackOverflowError e) {
                System.out.println("" + size + " elementos: ERROR - Stack overflow");
                break;
            } catch (Exception e) {
                System.out.println("" + size + " elementos: ERROR - " + e.getMessage());
                break;
            }
        }
        System.out.println("\n PRUEBA COMPLETADA");
    }
}