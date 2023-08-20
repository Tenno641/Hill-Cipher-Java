public class Test {

    public static int[] sort(int[] arr) {

        int size = arr.length;

        for (int i = 0; i < size - 1; i++) {

            for (int j = 0; j < size - 1; j++) {

                if (arr[j] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j] = temp;
                }

            }

        }


    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        int[] sortedArr = sort(arr);

        for (int n : sortedArr) {
            System.out.print(n);
        }

    }

}
