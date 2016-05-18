package net.cavitos.ros.pm;

/**
 * Created by fvcg on 16/05/2016.
 */
public class DivisionByTwo {

    public static void main(String [] args) {
        double value = 1000000;

        while(value > 0) {
            System.out.printf("Value: %f\n", value);
            value = value / 2;
        }
    }
}
