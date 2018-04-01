package Mytraining.DataBase;

import com.opensymphony.xwork2.ActionSupport;

import java.security.MessageDigest;

public class TestListAction extends ActionSupport {
    private String message;
    @Override
    public String execute() throws Exception{
        message = "Testing List";
        return SUCCESS;
    }
    public String getMessage(){
        return message;
    }
}
