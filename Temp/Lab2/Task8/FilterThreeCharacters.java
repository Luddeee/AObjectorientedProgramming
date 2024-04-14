import java.util.Arrays;

public class FilterThreeCharacters extends Filter {

    @Override
    public boolean accept(String x) {
        if (x.length() <= 3) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        String[] string1 = { "hej", "hello", "lol", "fgfggf", "123" };
        String[] filtered_string1 = new FilterThreeCharacters().filter(string1);

        System.out.println(Arrays.toString(filtered_string1));
    }
}
