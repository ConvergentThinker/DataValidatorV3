import org.main.engine.ReaderEngine;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class RunnerClass {


    public static void main(String[] args) throws IOException {



        final ReaderEngine readerEngine = new ReaderEngine();
        Map<String, Map<String, Map<Integer, String>>> inputExcelData = readerEngine.readCompleteExcel("/Users/innovative/Desktop/TestDataSheet.xlsx");

        System.out.println("inputExcelData "+ inputExcelData);

        System.out.println("all keys: " + inputExcelData.keySet());



        Map<String, Map<Integer, String>> mapOfHeaders = inputExcelData.get("Sheet1");

        System.out.println("mapOfHeaders keys "+ mapOfHeaders.keySet());





    }


    // Function to convert Set<String> to String[]
    public static String[] convert(Set<String> setOfString)
    {

        // Create String[] of size of setOfString
        String[] arrayOfString = new String[setOfString.size()];

        // Copy elements from set to string array
        // using advanced for loop
        int index = 0;
        for (String str : setOfString)
            arrayOfString[index++] = str;

        // return the formed String[]
        return arrayOfString;
    }



}
