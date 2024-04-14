import java.util.Arrays;

public class Exercise14 implements Filter {

    @Override
    public boolean accept(String x) {
        if (x.length() <= 3) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String[] tempArray = {"test","yes","works","get","set","pull","push"};
        String[] filteredArray = Exercise14Filter.filter(tempArray, new Exercise14());
        System.out.println("these input was provided: ");
        System.out.println(Arrays.toString(tempArray));
        System.out.println("After filtering: ");
        System.out.println(Arrays.toString(filteredArray));
    }
}