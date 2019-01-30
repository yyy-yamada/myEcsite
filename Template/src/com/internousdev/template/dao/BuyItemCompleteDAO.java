package com.internousdev.template.dao;

import java.sql.Connection;//DBMSへの接続や切断で使用「Connection」
import java.sql.PreparedStatement;//SQL文を送信する際に使用「PreparedStatement」
import java.sql.SQLException;//エラー処理で使用 「SQLException」

import com.internousdev.template.util.DBConnector;//「DBConnector」
import com.internousdev.template.util.DateUtil;   //「DateUtil」

//DAOクラス（BuyItemConfirmActionクラスから呼び出し）
public class BuyItemCompleteDAO {

	private DateUtil dateUtil = new DateUtil();

	//SQL文を定義
	private String sql = "INSERT INTO user_buy_item_transaction"
			           + "(item_transaction_id, total_price, total_count, user_master_id, pay, insert_date)"
			           + " VALUES(?,?,?,?,?,?)";

	                   //user_buy_item_transactionテーブルから①item_transaction_id ②total_price ③total_count ④user_master_id ⑤pay ⑥insert_date をinsert (登録)

	// buyItemInfoメソッド（BuyItemConfirmActionから呼び出し）///////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void buyItemInfo(String item_transaction_id, String user_master_id, String total_price, String total_count, String pay) throws SQLException{
		                 //引数は①item_transaction_id (商品のId番号),
		                       //②user_master_id (ログインしたユーザーのId番号),
                               //③total_price (購入合計金額)
		                       //④total_count (購入個数[在庫])
		                       //⑤pay (支払い方法)

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();//DBConnectorクラスのgetConnectionクラスを呼び出し（MySQLサーバに接続）

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);  //1番目の?に引数のitem_transaction_id(商品のId番号)
			preparedStatement.setString(2,total_price);           //2番目の?に引数のuser_master_id(ログインしたユーザーのId番号)
			preparedStatement.setString(3, total_count);          //3番目の?に引数のtotal_price(購入合計金額)
			preparedStatement.setString(4, user_master_id);       //4番目の?に引数のtotal_count(購入個数 [在庫])
			preparedStatement.setString(5, pay);                  //5番目の?に引数のpay(支払い方法 = 現金orカード)
			preparedStatement.setString(6, dateUtil.getDate());   //6番目の?にdateUtilクラスのgetDateの戻り値

			//SQL文実行メソッド
			preparedStatement.execute();

		//例外処理
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			connection.close(); //DB接続切断
	    }
     }
}
