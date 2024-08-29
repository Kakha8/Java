public class Main {

    static void bubbleSort(int[] arr){

        int temp = 0;
        int length = arr.length;
        boolean swapped;

        for(int i=0; i< length; i++){
            swapped = false;
            for (int j=0; j< length-1; j++) {

                    if (arr[j] > arr[j + 1]) {
                        temp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = temp;
                        swapped = true;
                    }

            }

            if (!swapped)
                break;
        }

    }

    static void printArr(int[] arr){
        System.out.println("Sorted array:");
        for (int i=0; i<arr.length; i++)
            System.out.print(arr[i] + " ");
    }
    public static void main(String[] args) {

        int[] arr = {4,6,1,76,65};

        bubbleSort(arr);
        printArr(arr);
    }

}
