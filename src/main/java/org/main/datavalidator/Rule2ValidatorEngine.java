package org.main.datavalidator;



import org.main.jTable.Rule1.Rule1Model;
import org.main.jTable.Rule2.Rule2Model;
import org.main.model.ErrorModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rule2ValidatorEngine {


    private List<ErrorModel> errors;

    public Rule2ValidatorEngine() {
        System.out.println("Rule2ValidatorEngine Obj created...");
        errors = new ArrayList<>();
    }

    public String getErrorsList(){
        System.out.println("errors size in Rule 2:: "+ errors.size());
        String data="";
        for (int i = 0; i < errors.size(); i++) {
            ErrorModel rule = errors.get(i);
            String x = "";
            x = x.concat(rule.getRule()).concat(",").concat(rule.getSheetName()).concat(",").concat(String.valueOf(rule.getRowNo())).concat(",")
                    .concat(rule.getColumnHeader()).concat(",").concat( rule.getInfo());
            data = data.concat(x).concat("&");
        }
        return data;
    }



    public void validateRule2(Map<String, Map<String, Map<Integer, String>>> inputExcelData, List<Rule2Model> rule2ModelList ) {

        List<Rule2Model> lstRule2 = rule2ModelList;

        for(int i=0;i<lstRule2.size();i++){
            Rule2Model rule = lstRule2.get(i);
             System.out.println("rule "+ rule);

        }






    }




}
