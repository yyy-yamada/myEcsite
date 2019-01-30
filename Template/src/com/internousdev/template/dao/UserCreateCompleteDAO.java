package com.internousdev.template.dao;

import java.sql.Connection; //DBMSへの接続や切断で使用「Connection」
import java.sql.PreparedStatement;//SQL文を送信する際に使用「PreparedStatement」
import java.sql.SQLException;//エラー処理で使用「SQLException」

import com.internousdev.template.util.DBConnector;
import com.internousdev.template.util.DateUtil;

//DAOクラス
public class UserCreateCompleteDAO {

	private DateUtil dateUtil = new DateUtil();

	//SQL文を定義
	private String sql = "INSERT INTO login_user_transaction (login_id,login_pass,user_name,insert_date)VALUES(?,?,?,?)";
	                                //login_user_transactionテーブルから①login_id, ②login_pass, ③user_name, ④insert_dateをinsert (登録)

	//createUserメソッド（UserCreateCompleteActionクラスから呼び出し）//////////////////////////////////////////////////////
	public void createUser(String loginUserId, String loginUserPassword, String userName)throws SQLException{
                           //仮引数に実引数を代入（loginUserId:ログインIDで入力したデータ, loginUserPassword:ログインPASSで入力したデータ, userName:ユーザー名で入力したデータ）

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginUserId);        //1番目の?にloginUserId(ログインIDで入力したデータ)
			preparedStatement.setString(2, loginUserPassword);  //2番目の?にloginUserPassword(ログインPASSで入力したデータ)
			preparedStatement.setString(3, userName);           //3番目の?にuserName(ユーザー名で入力したデータ)
			preparedStatement.setString(4, dateUtil.getDate()); //4番目の?にdateUtilクラスのgetDateメソッドの戻り値

			//SQL文実行
			preparedStatement.execute();

		//例外処理
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			connection.close(); //DB接続切断
		}
	}
}
