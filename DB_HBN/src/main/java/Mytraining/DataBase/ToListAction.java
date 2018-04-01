package Mytraining.DataBase;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionSupport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ToListAction extends ActionSupport {

    static{
        System.out.println("Loadding ToListAction");
    }

    //使用@SessionTarget标注得到Hibernate Session
    @SessionTarget
    private Session session = null;
    //使用@TransactionTarget标注得到Hibernate Transaction
    @TransactionTarget
    private Transaction transaction = null;
    private List<DemoEntity> users;
    public String list(){
        System.out.println("Starting method list");
        try{
            //得到user表中的所有记录
            users = session.createCriteria(DemoEntity.class).list();
//            transaction.commit();
//            session.close();
            return SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ERROR;
        }
    }
    public List<DemoEntity> getUsers(){
        return users;
    }
    public void setUsers(List<DemoEntity> users){
        this.users = users;
    }
}
