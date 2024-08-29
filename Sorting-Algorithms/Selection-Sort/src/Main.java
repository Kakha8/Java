//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static void selectionSort(int[] arr){

        for (int i=0; i < arr.length; i++){

            int min = i;

            for (int j=i+1; j< arr.length; j++){
                if (arr[j] < arr[min])
                    min = j;
            }

            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }

    }

    static void printArr(int[] arr){

        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }

    }

    public static void main(String[] args) {

        int arr[] = {34,23,42,18,51};
        selectionSort(arr);

        System.out.print("Sorted array: ");
        printArr(arr);

    }
}