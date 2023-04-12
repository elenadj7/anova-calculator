package calculator.anova.elena.analysis;

import java.util.ArrayList;

public class Anova {

    public static ArrayList<Double> getArithmeticMean(ArrayList<ArrayList<Double>> matrix){
        ArrayList<Double> mean = new ArrayList<>();
        matrix.forEach(k -> mean.add(k.stream().mapToDouble(Double::doubleValue).average().getAsDouble()));
        return mean;
    }
    public static double getTotalArithmeticMean(ArrayList<ArrayList<Double>> matrix){
        return matrix.stream().mapToDouble(k -> k.stream().mapToDouble(Double::doubleValue).average().getAsDouble()).average().getAsDouble();
    }

    public static double getSSA(ArrayList<ArrayList<Double>> matrix){

        ArrayList<Double> means = getArithmeticMean(matrix);
        double totalMean = getTotalArithmeticMean(matrix);
        int n = matrix.get(0).size();

        double ssa = 0.0;
        for(Double mean : means){
            ssa += Math.pow((mean - totalMean), 2.0);
        }

        return ssa * n;
    }
    public static double getSST(ArrayList<ArrayList<Double>> matrix){

        Double totalMean = getTotalArithmeticMean(matrix);

        double totalSum = 0.0;
        for(ArrayList<Double> array : matrix){

            double sum = 0.0;
            for(Double element : array){
                sum += Math.pow((element - totalMean), 2.0);
            }

            totalSum += sum;
        }

        return totalSum;
    }
    public static double getSSE(ArrayList<ArrayList<Double>> matrix){
        return getSST(matrix) - getSSA(matrix);
    }
}
