package Exercise8;
public abstract class Filter
{
    public abstract boolean accept(String x);

    public String[] filter(String[] a) { 
        
        String[] tempString = new String[a.length];
        int index = 0;
    
        for (String string : a) {
            if (accept(string)) {
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
