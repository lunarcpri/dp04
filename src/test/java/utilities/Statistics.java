package utilities;


import java.util.Arrays;

public class Statistics
{
    private double[] data;
    private int size;

    public Statistics(double[] data)
    {
        this.data = data;
        size = data.length;
    }

    private double getMean()
    {
        double sum = 0.0;
        for(double a : data)
            sum += a;
        return sum/size;
    }

    private double getVariance()
    {
        double mean = getMean();
        double temp = 0;
        for(double a :data)
            temp += (a-mean)*(a-mean);
        return temp/size;
    }

    double getStdDev()
    {
        return Math.sqrt(getVariance());
    }

    public double median()
    {
        Arrays.sort(data);

        if (data.length % 2 == 0)
        {
            return (data[(data.length / 2) - 1] + data[data.length / 2]) / 2.0;
        }
        else
        {
            return data[data.length / 2];
        }
    }
}