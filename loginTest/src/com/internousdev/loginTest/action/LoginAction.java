/**
 *
 */
package com.internousdev.loginTest.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.loginTest.DAO.LoginDAO;
import com.opensymphony.xwork2.ActionSupport;

/***
 * ログイン認証を行うクラス
 * @author SHIZUKA IMANISHI
 * @version 1.0
 * @since 1.0
 **/
public class LoginAction extends ActionSupport implements SessionAware{

    /**
     * ログイン認証を行うメソッド
     * @param id ログインID
     * @param password パスワード
     * @return boolean ログイン成功ならtrue、失敗ならfalse
     */
    private boolean loginResult;
    private String id;
    private String password;
    private Map<String,Object> session;


    public String execute() {

        LoginDAO dao = new LoginDAO();

        loginResult = dao.select(id,password);
        if(!loginResult){
            return ERROR;

        }else{
            session.put("id",id);
        }
        return SUCCESS;
    }


    /**を格納する為のメソッド
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**を格納する為のメソッド
     * @param id
     */
    public String getId(String id) {
        return id;
    }


    /**を格納する為のメソッド
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /* (非 Javadoc)
     * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
     */
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;

    }


}
