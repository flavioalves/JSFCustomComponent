package example;


public class Page {
    private String ccNum = "1234-2345-3456-4567";
    
    public String getCcNum() {
        return ccNum;
    }

    public void setCcNum(String ccNum) {
        this.ccNum = ccNum;
    }
    
    public String saveNumber() {
      return "success";
    }
}
