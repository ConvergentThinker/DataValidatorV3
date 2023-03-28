package org.main.jTable.Rule1;

public class Rule1Model {

    public void setIsToRun(String isToRun) {
        this.isToRun = isToRun;
    }

    public void setRuleExecutionType(String ruleExecutionType) {
        this.ruleExecutionType = ruleExecutionType;
    }

    public void setFromRow(String fromRow) {
        this.fromRow = fromRow;
    }

    public void setToRow(String toRow) {
        this.toRow = toRow;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }

    public void setTargetHeader(String targetHeader) {
        this.targetHeader = targetHeader;
    }

    public String getIsToRun() {
        return isToRun;
    }

    public String getRuleExecutionType() {
        return ruleExecutionType;
    }

    public String getFromRow() {
        return fromRow;
    }

    public String getToRow() {
        return toRow;
    }

    public String getSheet() {
        return sheet;
    }

    public String getTargetHeader() {
        return targetHeader;
    }
    private String isToRun = "";

    @Override
    public String toString() {
        return "Rule1Model{" +
                "isToRun='" + isToRun + '\'' +
                ", ruleExecutionType='" + ruleExecutionType + '\'' +
                ", fromRow='" + fromRow + '\'' +
                ", toRow='" + toRow + '\'' +
                ", sheet='" + sheet + '\'' +
                ", targetHeader='" + targetHeader + '\'' +
                '}';
    }

    private String  ruleExecutionType = ""; // Row[All/Custom]
    private String  fromRow = "";
    private String  toRow = "";
    private String  sheet = "";
    private String  targetHeader = "";



    public Rule1Model(String isToRun, String ruleExecutionType, String fromRow, String toRow, String sheet, String targetHeader) {
        this.isToRun = isToRun;
        this.ruleExecutionType = ruleExecutionType;
        this.fromRow = fromRow;
        this.toRow = toRow;
        this.sheet = sheet;
        this.targetHeader = targetHeader;
    }






}
