package calculator.anova.elena.analysis;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class Anova {

    public static ArrayList<Double> getArithmeticMean(ArrayList<ArrayList<Double>> matrix){
        ArrayList<Double> mean = new ArrayList<>();
        matrix.forEach(k -> mean.add(k.stream().mapToDouble(Double::doubleValue).average().getAsDouble()));
        return mean;
    }
    public static double getTotalArithmeticMean(ArrayList<ArrayList<Double>> matrix){
        return matrix.stream().mapToDouble(k -> k.stream().mapToDouble(Double::doubleValue).average().getAsDouble()).average().getAsDouble();
    }
    public static ArrayList<Double> getEffects(ArrayList<ArrayList<Double>> matrix){

        ArrayList<Double> effects = new ArrayList<>();
        ArrayList<Double> means = getArithmeticMean(matrix);
        double totalMean = getTotalArithmeticMean(matrix);

        for(Double mean : means){
            effects.add(mean - totalMean);
        }
        return effects;
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
    public static int getDegFreedomAlternatives(ArrayList<ArrayList<Double>> matrix){
        return matrix.size() - 1;
    }
    public static int getDegFreedomError(ArrayList<ArrayList<Double>> matrix){
        return matrix.size()*(matrix.get(0).size() - 1);
    }
    public static double meanSquareAlternatives(ArrayList<ArrayList<Double>> matrix){
        return getSSA(matrix) / getDegFreedomAlternatives(matrix);
    }
    public static double meanSquareError(ArrayList<ArrayList<Double>> matrix){
        return getSSE(matrix) / getDegFreedomError(matrix);
    }
    public static double getComputedF(ArrayList<ArrayList<Double>> matrix){
        return meanSquareAlternatives(matrix) / meanSquareError(matrix);
    }
    public static double getTabulatedF(ArrayList<ArrayList<Double>> matrix){
        double confidenceLevel = 0.95;
        return new FDistribution(getDegFreedomAlternatives(matrix), getDegFreedomError(matrix)).inverseCumulativeProbability(confidenceLevel);
    }
    private static double[] convert(ArrayList<Double> arrayList) {
        double[] array = new double[arrayList.size()];
        int index = 0;
        for (Double value : arrayList) {
            array[index++] = value;
        }
        return array;
    }
    public static double getStudentDistribution(ArrayList<ArrayList<Double>> matrix){
        double k = matrix.size();
        double n = matrix.get(0).size();
        double alpha = 0.95;
        return new TDistribution(k*(n-1)).inverseCumulativeProbability(alpha);
    }
    public static double getSc(ArrayList<ArrayList<Double>> matrix){
        double k = matrix.size();
        double n = matrix.get(0).size();
        return sqrt((2*meanSquareError(matrix))/(k*n));
    }
    public static String getResults(ArrayList<ArrayList<Double>> matrix){

        String result = "Deg freedom(SSA): " + getDegFreedomAlternatives(matrix) + "\n";
        result += "Deg freedom(SSE): " + getDegFreedomError(matrix) + "\n";
        result += "Mean square(SSA): " + meanSquareAlternatives(matrix) + "\n";
        result += "Mean square(SSE): " + meanSquareError(matrix) + "\n";

        double computed = getComputedF(matrix);
        double tabulated = getTabulatedF(matrix);
        result += "Computed F: " + computed + "\n";
        result += "Tabulated F: " + tabulated + "\n";
        result += computed > tabulated ? "ComputedF > TabulatedF -> 95% confidence that the differences between the alternatives are statistically significant\n" : "ComputedF < TabulatedF -> 95% confidence that the differences between the alternatives are not statistically significant\n\n";

        double sc = getSc(matrix);
        double studentDistribution = getStudentDistribution(matrix);
        ArrayList<Double> effects = getEffects(matrix);
        for(int i = 0; i < effects.size() - 1; ++i){
            for(int j = i + 1; j < effects.size(); ++j){

                double c = effects.get(i) - effects.get(j);
                double c1 = c - studentDistribution*sc;
                double c2 = c + studentDistribution*sc;
                int first = i+1;
                int second = j+1;
                result += "95% confidence interval for comparing the " + first + ". and " + second + ". alternatives: (" + c1 + "," + c2 + ")\n";

            }
        }
        return result;
    }
}
