public class Exercise5 {
    private double[][] elements;
    private int rows;
    private int columns;

    public Exercise5(int r, int c){
        this.elements = new double[r][c];
        this.rows = r;
        this.columns = c;
    } 

    public Exercise5 add(Exercise5 temp){
        if(this.rows != temp.rows || this.columns != temp.columns){
            throw new IllegalArgumentException("Matrices not compatible");
        }
        
        Exercise5 temp_matrix = new Exercise5(this.rows, this.columns);
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<columns; j++){
                temp_matrix.set(i, j, this.get(i, j)+temp.get(i, j));
            }
        }
        return temp_matrix;
    }

    public Exercise5 mult(Exercise5 temp){
        if(this.columns != temp.rows){
            throw new IllegalArgumentException("Matrices not compatible");
        }

        int temp_row = 0;
        int temp_column = 0;
        if(this.rows<temp.rows){
            temp_row = this.rows;
        }
        else{
            temp_row = temp.rows;
        }
        if(this.columns<temp.columns){
            temp_column = this.columns;
        }
        else{
            temp_column = temp.columns;
        }
        Exercise5 temp_matrix = new Exercise5(temp_row, temp_column);
        for (int i = 0; i < temp_column; i++) {
            for (int j = 0; j < rows; j++) {
                double sum = 0;
                for (int j2 = 0; j2 < columns; j2++) {
                    sum+=(this.get(j, j2)*temp.get(j2, i));
                }
                temp_matrix.set(j, i, sum);
            }
        }
        return temp_matrix;
    }

    public void set(int r, int c, double val){
        this.elements[r][c] = val;
    }

    public double get(int r, int c){
        return this.elements[r][c];
    }

    public void print_matrix() {
        for (int i = 0; i < elements.length; i++) {

            System.out.print("( ");
            for (int j = 0; j < elements[i].length; j++) {
                System.out.print(elements[i][j] + " ");
            }
            System.out.println(")");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Exercise5 test_matrix_1 = new Exercise5(2, 2);
        test_matrix_1.elements[0][0] = 1;
        test_matrix_1.elements[1][0] = 3;
        test_matrix_1.elements[0][1] = 2;
        test_matrix_1.elements[1][1] = 4;

        Exercise5 test_matrix_2 = new Exercise5(2, 2);
        test_matrix_2.elements[0][0] = 5;
        test_matrix_2.elements[0][1] = 6;
        test_matrix_2.elements[1][0] = 7;
        test_matrix_2.elements[1][1] = 8;


        Exercise5 test_matrix_3 = new Exercise5(3, 3);
        test_matrix_3.elements[0][0] = 12;
        test_matrix_3.elements[0][1] = 31;
        test_matrix_3.elements[0][2] = 2;
        test_matrix_3.elements[1][0] = 6;
        test_matrix_3.elements[1][1] = 9;
        test_matrix_3.elements[1][2] = 1;
        test_matrix_3.elements[2][0] = 27;
        test_matrix_3.elements[2][1] = 54;
        test_matrix_3.elements[2][2] = 3;

        Exercise5 test_matrix_4 = new Exercise5(3, 2);
        test_matrix_4.elements[0][0] = 7;
        test_matrix_4.elements[0][1] = 19;
        test_matrix_4.elements[1][0] = 3;
        test_matrix_4.elements[1][1] = 10;
        test_matrix_4.elements[2][0] = 12;
        test_matrix_4.elements[2][1] = 4;

        System.out.println("Multiplying m1 and m2");
        test_matrix_1.print_matrix();
        test_matrix_2.print_matrix();
        System.out.println("gives");
        Exercise5 test1 = test_matrix_1.mult(test_matrix_2);
        test1.print_matrix();

        System.out.println("Multiplying m3 and m4");
        test_matrix_3.print_matrix();
        test_matrix_4.print_matrix();
        System.out.println("gives");
        Exercise5 test2 = test_matrix_3.mult(test_matrix_4);
        test2.print_matrix();

        System.out.println("Multiplying unmatching matrices");
        test_matrix_1.print_matrix();
        test_matrix_3.print_matrix();
        System.out.println("gives");
        Exercise5 test3 = test_matrix_1.mult(test_matrix_3);
        test3.print_matrix();
    }
}

