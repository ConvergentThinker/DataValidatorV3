package regexClass;

public class DataTypeCheck {


    public static void main(String[] args) {

       // String input = "adfhdfhhgfm1";


        String namePattern = "[^\\p{P}|^\\d+]+";

//true if name contains only alphabets, false - otherwise
       // boolean result = input.matches(namePattern);

        String input = "26-04-2000";


        System.out.println("result "+ input.matches("^\\d+(\\.\\d+)?"));





    }

}
