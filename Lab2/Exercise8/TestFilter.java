package Exercise8;

import java.util.Arrays;

public class TestFilter extends Filter{
    @Override
    public boolean accept(String x) {
        if (x.length() <= 3) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        String[] string = {"Ludde", "Aleks", "AOP", "1337", "Lud", "-_-"};
        String[] filtered_string1 = new TestFilter().filter(string);

        System.out.println(Arrays.toString(filtered_string1));
    }
}
