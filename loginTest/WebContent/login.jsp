<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
    <body>
sql test.sqlを実行して、test testでログイン
        <h4>ログイン</h4>
        <s:form action="LoginAction">
            <p>ログインID<s:textfield label="ID" name="id" /></p>
            <p>パスワード<s:password label="パスワード" name="poassword"/></p>
            <s:submit value="ログイン"/>
        </s:form>

    </body>
</html>