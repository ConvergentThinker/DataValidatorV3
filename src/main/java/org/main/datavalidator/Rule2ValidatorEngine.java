package org.main.datavalidator;
import org.main.jTable.Rule1.Rule1Model;
import org.main.jTable.Rule2.Rule2Model;
import org.main.model.ErrorModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule2ValidatorEngine {


    private List<ErrorModel> errors;

    public Rule2ValidatorEngine() {
        System.out.println("Rule2ValidatorEngine Obj created...");
        errors = new ArrayList<>();
    }

    public String getErrorsList(){
        System.out.println("errors size in Rule 2:: "+ errors.size());

        String data="";

        if(errors.size()>0){
            for (int i = 0; i < errors.size(); i++) {
                ErrorModel rule = errors.get(i);
                String x = "";
                x = x.concat(rule.getRule()).concat(",").concat(rule.getSheetName()).concat(",").concat(String.valueOf(rule.getRowNo())).concat(",")
                        .concat(rule.getColumnHeader()).concat(",").concat( rule.getInfo());
                data = data.concat(x).concat("&");
            }
        }else{
            data = "Rule 2 Processed successfully. No issues found. ";
        }


        return data;
    }



    public void validateRule2(Map<String, Map<String, Map<Integer, String>>> inputExcelData, List<Rule2Model> rule2ModelList ) {

        List<Rule2Model> lstRule2 = rule2ModelList;

        for(int i=0;i<lstRule2.size();i++){
            Rule2Model rule = lstRule2.get(i);
             System.out.println("rule 2 "+ rule);

        }

        for(int i=0;i<lstRule2.size();i++){

            Rule2Model rule = lstRule2.get(i);

            // this IF is to decide which rule to be applied
            if(rule.getIsToRun().equals("Yes")) {

                switch (rule.getRuleExecutionType()) {

                    case "All Rows":
                        System.out.println("ALL");

                        Map<Integer, String> map = getSpecificColumnAllValues(inputExcelData, rule.getSheet(), rule.getTargetHeader().trim());

                        for (Map.Entry<Integer, String> entry : map.entrySet()) {
                            System.out.println(entry.getKey() + " : " + entry.getValue());
                            String cellData = entry.getValue().trim();
                            System.out.println("cellData "+ cellData);
                            System.out.println("Format: "+ rule.getFormat());
                            String format = rule.getFormat().trim();

                            // start

                            if (format.equals("Text")) {

                                // todo:- implement


                            } else if (format.equals("Number")) {

                                // todo:- implement



                            } else {
                                System.out.println("isValidDate(cellData) "+ isValidDate(cellData));

                                int dataLength = entry.getValue().trim().length();

                                if(dataLength==0){
                                    errors.add(new ErrorModel("Rule2",rule.getSheet(),entry.getKey(),rule.getTargetHeader(),"Expected Date format is: "+ format+ " but Actual is Empty Cell"));
                                }else{
                                    if(isValidDate(cellData) == false){
                                        errors.add(new ErrorModel("Rule2",rule.getSheet(),entry.getKey(),rule.getTargetHeader(),"Expected Date format is: "+ format+" but Actual : "+ cellData));
                                    }
                                }
                            }

                            // end
                        }
                        break;

                    case "Custom":
                        System.out.println("CUSTOM");

                        Map<Integer, String> mapCustom = getSpecificColumnAllValues(inputExcelData, rule.getSheet(), rule.getTargetHeader().trim());

                        boolean isToEnd = false;

                        for (Map.Entry<Integer, String> entry : mapCustom.entrySet()) {

                            int fromNo = Integer.parseInt(rule.getFromRow());
                            int toNo = Integer.parseInt(rule.getToRow());

                            if(entry.getKey() >= fromNo ){
                                System.out.println(entry.getKey() + " : " + entry.getValue());
                                String cellData = entry.getValue().trim();
                                System.out.println("cellData "+ cellData);
                                System.out.println("Format: "+ rule.getFormat());
                                String format = rule.getFormat().trim();

                                if (format.equals("Text")) {

                                } else if (format.equals("Number")) {

                                } else {
                                    System.out.println("isValidDate(cellData) "+ isValidDate(cellData));

                                    int dataLength = entry.getValue().trim().length();

                                    if(dataLength==0){
                                        errors.add(new ErrorModel("Rule2",rule.getSheet(),entry.getKey(),rule.getTargetHeader(),"Expected Date format is: "+ format+" but Actual is Empty Cell"));
                                    }else{
                                        if(isValidDate(cellData) == false){
                                            errors.add(new ErrorModel("Rule2",rule.getSheet(),entry.getKey(),rule.getTargetHeader(),"Expected Date format is: "+ format+" but Actual : "+ cellData));
                                        }
                                    }
                                }

                                // end

                                if(entry.getKey() == toNo){
                                    isToEnd = true;
                                    break;
                                }
                            }
                            if(isToEnd)
                                break;
                        }

                        break;

                    default:

                }
            }
            else{
                System.out.println(" Skipping Rule :  "+ rule);
            }

        }




    }

    // Function to validate the username
    public static boolean isValidDate(String inputStr)
    {
       // String regex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)(?:0?2|(?:Feb))\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        String regex = "(\\b(0?[1-9]|[12]\\d|30|31)[^\\w\\d\\r\\n:](0?[1-9]|1[0-2])[^\\w\\d\\r\\n:](\\d{4}|\\d{2})\\b)|(\\b(0?[1-9]|1[0-2])[^\\w\\d\\r\\n:](0?[1-9]|[12]\\d|30|31)[^\\w\\d\\r\\n:](\\d{4}|\\d{2})\\b)";

        Pattern p = Pattern.compile(regex);
        if (inputStr == null) {
            return false;
        }
        Matcher m = p.matcher(inputStr);
        return m.matches();
    }


    public Map<Integer, String> getSpecificColumnAllValues(Map<String, Map<String, Map<Integer, String>>> fullExcel,String sheetName,String headerName) {
        Map<String, Map<Integer, String>> mapOfHeaders = fullExcel.get(sheetName);
        Map<Integer, String> map = mapOfHeaders.get(headerName);
        return map;
    }



}
