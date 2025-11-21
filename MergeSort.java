import java.util.*;
import java.util.concurrent.TimeUnit;

public class MergeSort {
    
    public static void sort(int[] arr) 
    {
        sort(arr, 0, arr.length - 1);
    }
    
    private static void sort(int[] arr, int left, int right) 
    {
        if (left < right) 
        {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) 
    {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        for (int i = 0; i < n1; i++)
        {
            
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++)
        {
            rightArr[j] = arr[mid + 1 + j];
        }
        
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) 
        {
            if (leftArr[i] <= rightArr[j]) 
            {
                arr[k] = leftArr[i];
                i++;
            } 
            else 
            {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) 
        {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < n2) 
        {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    
    public static void main(String[] args) {
        Random rand = new Random();
        
        int[] sizes = {50000, 100000, 1000000, 10000000, 100000000, 1000000000};
        
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