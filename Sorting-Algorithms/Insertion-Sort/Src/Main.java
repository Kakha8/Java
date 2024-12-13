package Src;

public class Main {

    static int[] insertionSort(int[] arr){

        int length = arr.length;


        for(int i=1; i < length; i++){

            int current = arr[i];
            int j = i-1;

            while (j >= 0 && arr[j] > current){
                arr[j+1] = arr[j];
                j = j -1;
            }

            arr[j+1] = current;

        }

        return arr;
    }

    static void printArr(int[] arr){
        int length = arr.length;

        for(int i=0; i<length; i++){
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {

        int[] arr = {3, 1, 65, 32, 76, 8};

        printArr(insertionSort(arr));

    }
}
