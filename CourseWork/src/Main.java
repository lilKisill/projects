import java.io.FileWriter;
import java.io.IOException;

public class Main {

    static double   lam = 1,  // значення констант
                    a = 5,
                    b = -5,
                    C = 5;

    public static double boundaryFunctionConditionsZero(double t) // x=0 -> w(0,t)
    {
        return Math.pow(C * Math.exp(-(lam/a) * (0 + lam*t)) - b/2*lam, -1);
    }

    public static double boundaryFunctionConditionsOne( double t) // x=1 -> w(1,t)
    {
        return Math.pow(C * Math.exp(-(lam/a) * (1 + lam*t)) - b/2*lam, -1);
    }

    public static double initialFunctionConditions(double x) // t = 0 -> w(x,0)
    {
        return Math.pow(C * Math.exp(-(lam/a) * (x)) - b/2*lam, -1);
    }

    public static double countFunction( double x, double t)  //
    {
        return Math.pow(C * Math.exp(-(lam/a) * (x + lam*t)) - b/2*lam, -1);
    }

    public static double countWgridFunction(double rightWi, double leftWi, double Wi, double tau, double hStep)
    {
        return Wi + tau * (2 * ((leftWi - 2 * Wi + rightWi)/(hStep * hStep)) + 6 * Wi * ((rightWi - leftWi)/(2*hStep)));
    }

    public static int NUNMBER_THREADS = 3;
    public static final int amountOfSteps_X = 30; // 10 // 20 // 30
    public static final int amountOfSteps_T = 900; // 100 // 400 // 900

    public static void main(String[] args) throws InterruptedException, IOException {
        double stepX = 0.1; // 0.1 // 0.05 // 0.03
        double stepT = 0.00001; // 0.00001 // 0.00000625 // 0.0000012346

        double[][]  MatrixPutTX = new double[amountOfSteps_T][amountOfSteps_X];
        double[][]  MatrixFormulaConsistently =  new double [amountOfSteps_T][amountOfSteps_X];
        double[][]  MatrixFormulaParallel =  new double [amountOfSteps_T][amountOfSteps_X];

        double Value_X;
        double Value_T = 0.0;

        for (int i = 0; i < amountOfSteps_T; i++)
        {
            Value_X = 0.0;
            for (int j = 0; j < amountOfSteps_X; j++)
            {
                MatrixPutTX[i][j] = countFunction(Value_X, Value_T);
                Value_X += stepX;
            }
            Value_T += stepT;
        }


        Value_X = 0.0;
        for (int i = 0; i < amountOfSteps_X; i++)
        {
            MatrixFormulaConsistently[0][i] = initialFunctionConditions(Value_X);
            MatrixFormulaParallel[0][i] = initialFunctionConditions(Value_X);
            Value_X += stepX;
        }

        Value_T = 0.0;
        for (int i = 0; i < amountOfSteps_T; i++)
        {
            MatrixFormulaConsistently[i][0] = boundaryFunctionConditionsZero(Value_T);
            MatrixFormulaConsistently[i][amountOfSteps_X - 1] = boundaryFunctionConditionsOne(Value_T);
            MatrixFormulaParallel[i][0] = boundaryFunctionConditionsZero(Value_T);
            MatrixFormulaParallel[i][amountOfSteps_X - 1] = boundaryFunctionConditionsOne(Value_T);
            Value_T += stepT;
        }

        long startConsistentlyTime = System.nanoTime();
        for (int i = 1; i < amountOfSteps_T; i++){
            for (int j = 1; j < amountOfSteps_X-1; j++){
                MatrixFormulaConsistently[i][j] = Main.countWgridFunction(MatrixFormulaConsistently[i - 1][j + 1],
                        MatrixFormulaConsistently[i - 1][j - 1], MatrixFormulaConsistently[i - 1][j], stepT, stepX);
            }
        }
        long endConsistentlyTime = System.nanoTime();
        long ConsistentlyTime = endConsistentlyTime - startConsistentlyTime;


        long startParallelTime = System.nanoTime();
        for (int i = 1; i < amountOfSteps_T; i++){
            ThreadApproximation TreadArray[] = new ThreadApproximation[NUNMBER_THREADS];
            for(int j = 0; j < NUNMBER_THREADS; j++){
                TreadArray[j] = new ThreadApproximation(MatrixFormulaParallel,
                        j==0?1:(amountOfSteps_X-1)/NUNMBER_THREADS * j,
                        j==(NUNMBER_THREADS - 1)?(amountOfSteps_X-1):(amountOfSteps_X-1)/NUNMBER_THREADS * (j + 1),stepT, stepX, i);
                TreadArray[j].start();
            }
            for(int j = 0; j < NUNMBER_THREADS; j++){
                TreadArray[j].join();
            }
        }

        long endParallelTime = System.nanoTime();
        long ParallelTime = endParallelTime - startParallelTime;

        for (int i = 0; i < amountOfSteps_T; i++){
            for (int j = 0; j < amountOfSteps_X; j++){
                System.out.print(MatrixFormulaParallel[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();


        for (int i = 0; i < amountOfSteps_T; i++){
            for (int j = 0; j < amountOfSteps_X; j++){
                System.out.print(MatrixFormulaConsistently[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        int iMax = 0, jMax = 0;
        double maxInaccuracy = 0;
        for (int i = 0; i < amountOfSteps_T; i++)
        {
            for (int j = 0; j < amountOfSteps_X; j++)
            {
                if (Math.abs(MatrixPutTX[i][j] - MatrixFormulaParallel[i][j]) > maxInaccuracy)
                {
                    iMax = i;
                    jMax = j;
                    maxInaccuracy = Math.abs(MatrixPutTX[i][j] - MatrixFormulaParallel[i][j]);
                }
            }
        }
        System.out.println("Абсолютна - " + maxInaccuracy + " та відносна похибки - " + maxInaccuracy / MatrixPutTX[iMax][jMax]);
        System.out.println("Время работы параллельно - " + ParallelTime);
        System.out.println("Время работы последовательно - " + ConsistentlyTime);

        FileWriter filePutTX = new FileWriter("MatrixPutTX.txt");
        FileWriter foutConsistently = new FileWriter("MatrixFormulaConsistently.txt");
        FileWriter foutParallel = new FileWriter("MatrixFormulaParallel.txt");
        filePutTX.write( " MatrixPutTX = { ");
        foutConsistently.write( " MatrixFormulaConsistently = { ");
        foutParallel.write( " MatrixFormulaParallel = { ");
        for (int i = 0; i < amountOfSteps_T; i++)
        {
            filePutTX.write(" { ");
            foutConsistently.write(" { ");
            foutParallel.write(" { ");
            for (int j = 0; j < amountOfSteps_X; j++)
            {
                if (j < amountOfSteps_X - 1) {
                    filePutTX.write((MatrixPutTX[i][j] + ", "));
                    foutConsistently.write((MatrixFormulaConsistently[i][j] + ", "));
                    foutParallel.write((MatrixFormulaParallel[i][j] + ", "));
                }
                else {
                    filePutTX.write("" + MatrixPutTX[i][j]);
                    foutConsistently.write("" + MatrixFormulaConsistently[i][j]);
                    foutParallel.write("" + MatrixFormulaParallel[i][j]);
                }
            }
            if (i < amountOfSteps_T - 1) {
                filePutTX.write("}, ");
                foutConsistently.write("}, ");
                foutParallel.write("}, ");
            }
            else {
                filePutTX.write(" } ");
                foutConsistently.write(" } ");
                foutParallel.write(" } ");
            }
        }
        filePutTX.write(" };");
        foutConsistently.write(" };");
        foutParallel.write(" };");
        filePutTX.close();
        foutConsistently.close();
        foutParallel.close();

    }
}
