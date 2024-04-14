public class Exercise14Filter {

    public static String[] filter(String[] a, Filter f) { 
        
        String[] tempString = new String[a.length];
        int index = 0;
    
        for (String string : a) {
            if (f.accept(string)) {
                tempString[index] = string;
                index++;
            }
        } 

        String[] returnNewArray = new String[index];
        for (int i = 0; i < index; i++) {
            returnNewArray[i] = tempString[i];
        }
        return returnNewArray;
    }
    
    
}
