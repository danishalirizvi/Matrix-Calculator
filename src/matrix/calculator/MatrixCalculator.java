package matrix.calculator;
//import classes user
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MatrixCalculator {

    static Scanner in = new Scanner(System.in);//Scanner object for user Input

    static int r1, c1, r2, c2;// Integer type variable to store the order of the Matrices
    //r1 = No. of Rows of Matrix1
    //c1 = No. of Columns of Matrix1
    //r2 = No. of Rows of Matrix2
    //c2 = No. of Columns of Matrix2
    
    
    static double matrix1[][], matrix2[][], resultantMatrix[][];//Matrices
    
    //Menu Function containing main Menu
    public static void Menu() throws IOException {
        
        int choice = 7;//Fake Value Initialization to get into the while loop

        while (choice != 0) {
            System.out.print("Press "
                    + "\n  1 to Add Two Matrices "
                    + "\n  2 to Subtract Two Matrices"
                    + "\n  3 for Scalar Multiplication"
                    + "\n  4 to Multiply Two Matrices"
                    + "\n  5 for Scalar Division"
                    + "\n  6 to Calculate Reciprocal"
                    + "\n  0 to Exit."
                    + "\n   Your Choice = ");

            choice = getNextInt(in);//getting the user input (Numeric Only)

            if (choice >= 0 && choice <= 6) {//validation on the user input to restrict it within the given option
                switch (choice) {
                    case 0:
                        System.out.println("You choosed to Quit the Program"
                                + "\n Good Bye!!!");
                        break;
                    case 1:
                        if (inputDoubleMatrices()) {
                            resultantMatrix = new double[r1][c1];
                            addMatrices(matrix1, matrix2);
                        }
                        break;
                    case 2:
                        if (inputDoubleMatrices()) {
                            resultantMatrix = new double[r1][c1];
                            subtractMatrices(matrix1, matrix2);
                        }
                        break;
                    case 3:
                        if (inputSingleMatrix()) {
                            resultantMatrix = new double[r1][c1];
                            scalarMultiply(matrix1);
                        }
                        break;
                    case 4:
                        if (inputDoubleMatrices()) {
                            resultantMatrix = new double[r1][c2];
                            multiplyMatrices(matrix1, matrix2);
                        }
                        break;
                    case 5:
                        if (inputSingleMatrix()) {
                            resultantMatrix = new double[r1][c1];
                            scalarDivide(matrix1);
                        }
                        break;
                    case 6:
                        if (inputSingleMatrix()) {
                            resultantMatrix = new double[r1][c1];
                            reciprocal(matrix1);
                        }
                        break;
                    default:
                        break;
                }
            } else {
                System.out.println("Invalid Choice\nChoose a valid option from the above Menu");
            }
        }
    }
    //function to restrict the user to give numeric input only
    public static int getNextInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Please enter a number");
        }
        return scanner.nextInt();
    }

    //function to get a single matrix input for operations involving only one matrix
    public static boolean inputSingleMatrix() throws FileNotFoundException, IOException {

        System.out.print("Enter the inpur file name with extension(abc.txt)\nFile Name = ");
        String file = in.next();

        File tmpfile = new File(file);
        if (tmpfile.isFile()) {
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(filereader);

            String line = bufferedReader.readLine();
            String[] rzlt = line.split(" ");

            r1 = Integer.parseInt(rzlt[0]);
            c1 = Integer.parseInt(rzlt[1]);

            matrix1 = new double[r1][c1];

            for (int i = 0; i < r1; i++) {
                line = bufferedReader.readLine();
                rzlt = line.split(" ");
                for (int j = 0; j < rzlt.length; j++) {
                    matrix1[i][j] = Integer.parseInt(rzlt[j]);
                }
            }
            return true;
        } else {
            System.out.println("You entered incorrect file name or the file does not exists!");
            return false;
        }
    }

    //function to get double matrix input for operations involving two matrices
    public static boolean inputDoubleMatrices() throws FileNotFoundException, IOException {

        System.out.print("Enter the inpur file name with extension(abc.txt)\nFile Name = ");
        String file = in.next();

        File tmpfile = new File(file);
        if (tmpfile.isFile()) {

            FileReader filereader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(filereader);

            String line = bufferedReader.readLine();
            String[] rzlt = line.split(" ");

            r1 = Integer.parseInt(rzlt[0]);
            c1 = Integer.parseInt(rzlt[1]);

            matrix1 = new double[r1][c1];

            for (int i = 0; i < r1; i++) {
                line = bufferedReader.readLine();
                rzlt = line.split(" ");
                for (int j = 0; j < rzlt.length; j++) {
                    matrix1[i][j] = Integer.parseInt(rzlt[j]);
                }
            }

            bufferedReader.readLine();

            line = bufferedReader.readLine();
            rzlt = line.split(" ");

            r2 = Integer.parseInt(rzlt[0]);
            c2 = Integer.parseInt(rzlt[1]);

            matrix2 = new double[r2][c2];

            for (int i = 0; i < r2; i++) {
                line = bufferedReader.readLine();
                rzlt = line.split(" ");
                for (int j = 0; j < rzlt.length; j++) {
                    matrix2[i][j] = Integer.parseInt(rzlt[j]);
                }
            }
            return true;
        } else {
            System.out.println("You entered incorrect file name or the file does not exists!");
            return false;
        }
    }

    //function to store the result on the file 
    public static void output1() throws IOException {
        System.out.print("Enter the name of File(with .txt extension) where you want to store the Result"
                + "\n File Name = ");
        String filename = in.next();
        FileWriter writer = new FileWriter(filename);
        BufferedWriter buffer = new BufferedWriter(writer);

        buffer.write("Output Matrix");
        buffer.newLine();
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                buffer.write(Double.toString(resultantMatrix[i][j]) + " ");
            }
            buffer.newLine();
        }
        buffer.close();
        System.out.println("Results Successfully Stored to File Name = " + filename);
    }
    
    //function to store the result on the file(only for matix multiplication)
    public static void output2() throws IOException {
        System.out.print("Enter the name of File(with .txt extension) where you want to store the Result"
                + "\n File Name = ");
        String filename = in.next();
        FileWriter writer = new FileWriter(filename);
        BufferedWriter buffer = new BufferedWriter(writer);

        buffer.write("Output Matrix");
        buffer.newLine();
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                buffer.write(Double.toString(resultantMatrix[i][j]) + " ");
            }
            buffer.newLine();
        }
        buffer.close();
        System.out.println("Results Successfully Stored to File Name = " + filename);
    }
    
    //function to add two matrices 
    public static void addMatrices(double[][] matrix1, double[][] matrix2) throws IOException {

        if (r1 == r2 && c1 == c2) {
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c1; j++) {
                    resultantMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }

            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c1; j++) {
                    System.out.println(resultantMatrix[i][j]);
                }
            }

            output1();
        } else {
            System.out.println("Matrices are not of the Same order. Addition not Possible.");
        }
    }

    //function to subtract two matrices
    public static void subtractMatrices(double[][] matrix1, double[][] matrix2) throws IOException {
        if (r1 == r2 && c1 == c2) {
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c1; j++) {
                    resultantMatrix[i][j] = matrix1[i][j] - matrix2[i][j];
                }
            }

            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c1; j++) {
                    System.out.println(resultantMatrix[i][j]);
                }
            }

            output1();
        } else {
            System.out.println("Matrices are not of the Same order. Subtraction not Possible.");
        }
    }

    //function to multiply a scalar with a matrix
    public static void scalarMultiply(double[][] matrix1) throws IOException {
        System.out.print("Enter the Scalar Number to Multiply\nNumber = ");
        int k = getNextInt(in);
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                resultantMatrix[i][j] = k * matrix1[i][j];
            }
        }

        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                System.out.println(resultantMatrix[i][j]);
            }
        }

        output1();
    }

    //function to divide a scalar with a matrix
    public static void scalarDivide(double[][] matrix1) throws IOException {
        System.out.print("Enter the Scalar Number to Divide(Other then Zero)\nNumber = ");
        int k = getNextInt(in);
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                resultantMatrix[i][j] = matrix1[i][j] / k;
            }
        }

        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                System.out.println(resultantMatrix[i][j]);
            }
        }

        output1();
    }

    //function to multiply two matrices
    public static void multiplyMatrices(double[][] matrix1, double[][] matrix2) throws IOException {
        if (c1 == r2) {
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c2; j++) {
                    for (int k = 0; k < c1; k++) {
                        resultantMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
            System.out.println(c2);
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c2; j++) {
                    System.out.println(resultantMatrix[i][j]);
                }
            }

            output2();
        } else {
            System.out.println("Number of columns of 1st matrix is not equal to number of rows of 2nd matrix."
                    + "\nMultipkication not Possible.");
        }
    }

    //function to find reciprocal of a 2x2 matrix only
    public static void reciprocal(double[][] matrix1) throws IOException {
        if (r1 == 2 && c1 == 2) {
            double[][] adjoint = new double[r1][c1];

            adjoint[0][0] = matrix1[1][1];
            adjoint[0][1] = matrix1[0][1] * (-1);
            adjoint[1][0] = matrix1[1][0] * (-1);
            adjoint[1][1] = matrix1[0][0];

            double determinent = matrix1[0][0] * matrix1[1][1] - matrix1[0][1] * matrix1[1][0];

            resultantMatrix[0][0] = adjoint[0][0] / determinent;
            resultantMatrix[0][1] = adjoint[0][1] / determinent;
            resultantMatrix[1][0] = adjoint[1][0] / determinent;
            resultantMatrix[1][1] = adjoint[1][1] / determinent;

            System.out.println("Determinent = " + determinent);

            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c1; j++) {
                    System.out.println(adjoint[i][j]);
                }
            }

            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c1; j++) {
                    System.out.println(resultantMatrix[i][j]);
                }
            }

            output1();
        }
        else{
            System.out.println("Matric is not of order 2x2. ENTER 2x2 matrices only.");
        }
    }

    //main method
    public static void main(String[] args) throws IOException {
        Menu();
    }
}
