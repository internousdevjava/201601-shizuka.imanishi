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
	 * ログイン結果
	 */
    private boolean isLoginResult;
    
    /**
     * ID
     */
    private String id;
    
    /**
     * パスワード
     */
    private String password;
    
    /**
     * ログインエラーメッセージ
     */
    private String loginErrorMessage;
    
    /**
     * セッション
     */
    private Map<String,Object> session;

    /**
     * ログイン認証を行うメソッド
     * @param id ログインID
     * @param password パスワード
     * @return boolean ログイン成功ならtrue、失敗ならfalse
     */
    public String execute() {

        LoginDAO dao = new LoginDAO();

        isLoginResult = dao.select(id,password);
        
        if(!isLoginResult){
        	loginErrorMessage="IDまたはpasswordが誤っています。";
            return ERROR;

        }else{
            session.put("id",id);
        }
        return SUCCESS;
    }


    /**IDを格納する為のメソッド
     * @param id ID
     */
    public void setId(String id) {
        this.id = id;
    }


    /**パスワードを格納する為のメソッド
     * @param password パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**ログインエラーメッセージを取得する為のメソッド
	 * @return loginErrorMessage ログインエラーメッセージ 
	 */
	public String getLoginErrorMessage() {
		return loginErrorMessage;
	}


	@Override
    public void setSession(Map<String, Object> session) {
        this.session = session;

    }


}
