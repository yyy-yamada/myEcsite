package com.internousdev.template.util;

//Javaが用意してくれている便利クラスを利用できるようにimport

import java.sql.Connection;    //DBMSへの接続や切断で使用「Connection」
import java.sql.DriverManager; //DBMSへの接続準備で使用  「DriverManager」
import java.sql.SQLException;  //エラー処理で使用        「SQLException」

//JavaとDBのconnectionを作るためのクラス
public class DBConnector {

	//①JDBCドライバー名
	private static String driverName = "com.mysql.jdbc.Driver";

	//②データベース接続URL
	private static String url ="jdbc:mysql://localhost/ecsite";    //サーバ名/DB名

	//③データベース接続ユーザ名
	private static String user ="root";

	//④データベース接続パスワード
	private static String password = "mysql";


//getConnectionメソッド(DAOクラスから呼び出される)/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Connection getConnection(){

		//conフィールド
		Connection con = null; //Connection型："java.sql.Connection"の略 (import文記載のためpackage名は省略可)

		// try/catch文

			//try{例外が発生しそうな処理}
			// catch(例外名){例外が発生した場合の処理}

		try{
			Class.forName(driverName);   //（ドライバーのクラス名=driverName）
			con = (Connection) DriverManager.getConnection(url,user,password);//データベース接続の確立 ( 設定したurl,user,passwordの情報を使用してMySQLサーバーへ接続 )

	   //例外処理
		}catch (ClassNotFoundException e){  //=「driverNameクラスが見つかりません」
			e.printStackTrace();

		}catch (SQLException e){
			e.printStackTrace();   //e.printStackTrace(); =例外の履歴をコンソール上に表示
		}

		//MySQLサーバに接続できたかの結果をメソッドの呼び出し元に返す
		return con;
	}

}