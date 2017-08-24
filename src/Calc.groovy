import java.text.DecimalFormat

/**
 * Created by sodino on 2017/8/24.
 */
class Calc {
    def name
    Calc(def name){
        this.name = name
    }
    protected int count;
    protected int invalidCount;
    protected int max = 0;
    protected int min = Integer.MAX_VALUE;
    protected long sum = 0;


    protected double average = 0.0;


    def calc(def value) {
        if (!value) {
            print("-\t\t");
            invalidCount ++;
            return;
        }
        print(value + " \t\t")
        count ++;

        if (max < value) {
            max = value;
        }
        if (min > value) {
            min = value;
        }
        sum += value;
        average = sum * 1.0d / count;
    }


    def printInfo(){
        println(name);
        DecimalFormat df = new DecimalFormat("#.00");
        print("avg =" + df.format(average) + " sum/All =" + df.format(sum * 1.0d / (count + invalidCount)) + " \t max =" + max + " \t min =" + min + " \t sum =" + sum + " count =" + count + " invalidCount=" + invalidCount)
        println("\n")
    }
}
