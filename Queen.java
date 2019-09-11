import java.util.ArrayList;
import java.util.List;

public class Queen {

    int dimension;
    List<int[]> resultList = new ArrayList<>();

    public Queen(int dimension) {
        this.dimension = dimension;
    }

    public List<int[]> getResultList() {
        calculate(generateMatrix());
        return this.resultList;
    }

    private void calculate(int[] matrix) {
        int currentRow = 0;
        int currentColumn = 0;
        while (currentRow >= 0) {
            for (; currentColumn < this.dimension; currentColumn++) {
                if(isValid(matrix,currentRow,currentColumn)){
                    putChess(matrix,currentRow,currentColumn);
                    currentRow++;
                    if(currentRow >= this.dimension){
                        this.resultList.add(matrix);
                        currentRow --;
                        continue;
                    }else{
                        currentColumn = 0;
                    }
                    break;
                }
            }
            if(currentColumn >= this.dimension){
                currentRow--;
                if(currentRow >= 0){
                    currentColumn = matrix[currentRow]+1;
                }
            }
        }
    }

    private int[] generateMatrix() {
        return new int[this.dimension];
    }

    private boolean isValid(int[] matrix, int row, int column) {
        for (int i = 0; i < row; i++) {
            if (matrix[i] == column) return false;// columns equal
            if (i == row) return false;// rows equal
            if ((matrix[i] - column == i - row) || (matrix[i] - column == row - i)) return false;
        }
        return true;
    }

    private void putChess(int[] matrix, int row, int column){
        matrix[row] = column;
    }

}
