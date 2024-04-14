// 8. (*)In assignment 1 you programmed an interface Filter and a method filter. In this exercise,
// use the Template method pattern instead to define an abstract class Filter with a public method
// filter (the template method) that calls the method accept (the hook method) that can be
// implemented in different ways in the different concrete classes. Write a test program by extending
// the class Filter and defining accept so that only strings of at most three characters are accepted.


public abstract class Filter {
    
    public abstract boolean accept(String x);

    public String[] filter(String[] a) { 
        
        String[] f_string = new String[a.length];
        int index = 0;
    
        for (String string : a) {
            if (accept(string)) {
                f_string[index] = string;
                index++;
            }
        }
        
        // shortened array
        String[] reduced_f_string = new String[index];
        for (int i = 0; i < index; i++) {
            reduced_f_string[i] = f_string[i];
        }
        return reduced_f_string;
    }
}