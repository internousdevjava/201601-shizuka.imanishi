/**
 *
 */
package com.internousdev.loginTest.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.loginTest.util.DBConnector;
import com.mysql.jdbc.Connection;

/***
 * IDとパスワードがデータベースにあるかを確認するクラス
 * @author SHIZUKA IMANISHI
 * @version 1.0
 * @since 1.0
 **/
public class LoginDAO {

    /**
     * ログイン結果
     */
    private boolean isLoginResult;

    /**
     * データベース接続
     */
    private Connection con;

    /**
     * sql文
     */
    String sql;

    /**
     * IDとパスワードがデータベースにあるかを確認するメソッド
     * @param id ID
     * @param password パスワード
     * @return boolean ログイン成功ならtrue、失敗ならfalse
     */
    public boolean select(String id, String password){

        isLoginResult=false;

        con = (Connection)DBConnector.getConnection();

        try{
            sql="SELECT id FROM test WHERE id = ? AND password = ?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
            	isLoginResult = true;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return isLoginResult;
    }

}
