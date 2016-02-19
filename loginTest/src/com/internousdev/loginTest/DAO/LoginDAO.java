/**
 *
 */
package com.internousdev.loginTest.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.loginTest.util.DBConnector;
import com.mysql.jdbc.Connection;

/**
 * @author internous
 *
 */
public class LoginDAO {

    /**
     * ログイン結果
     */
    private boolean loginResult;

    /**
     * データベース接続
     */
    private Connection con;

    /**
     * sql文
     */
    String sql;

    /**
     * idとパスワードがあるかを確認するメソッド
     * @param id ログインID
     * @param password パスワード
     * @return boolean ログイン成功ならtrue、失敗ならfalse
     */
    public boolean select(String id, String password){

        loginResult=false;

        con = (Connection)DBConnector.getConnection();

        try{
            sql="SELECT id FROM test WHERE id = ? AND password = ?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                loginResult = true;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return loginResult;
    }

}
