import java.util.Arrays;

public class Summation {
    public static void main(String[] args) {
        int [][] matrix_1 = new int[3][3] ,matrix_2 = new int[3][3] ,sum = new int[3][3];
        int value = 0;
        Thread [] threads = new Thread[3];

        for (int row=0;row<matrix_1.length;++row)
            for (int col = 0 ;col<matrix_1[row].length;++col){
                matrix_2[row][col] = value++;
                matrix_1[row][col] = value++;
            }
        for (int row=0;row<matrix_1.length;++row) {
            int finalRow = row;
            threads[row]=(new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (threads){
                        for (int col = 0; col<matrix_1[finalRow].length; ++col)
                            sum[finalRow][col] = matrix_1[finalRow][col] + matrix_2[finalRow][col];
                    }
                }
            }));
        }

        for (Thread thread:threads) {
            thread.start();
        }
        for (Thread thread:threads) {
            try {
                thread.join();
            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        }
        System.out.println(Arrays.deepToString(matrix_1));
        System.out.println(Arrays.deepToString(matrix_2));
        System.out.println(Arrays.deepToString(sum));
    }
}
