package com.internousdev.template.dao;

import java.sql.Connection;//DBMSへの接続や切断で使用「Connection」
import java.sql.PreparedStatement;//SQL文を送信する際に使用「PreparedStatement」
import java.sql.ResultSet;//DBMSから検索結果を受け取る際に使用「ResultSet」
import java.sql.SQLException;//エラー処理で使用「SQLException」

import com.internousdev.template.dto.MyPageDTO;
import com.internousdev.template.util.DBConnector;

//DAOクラス
public class MyPageDAO {

//getMyPageUserInfoメソッド（MyPageActionクラスから呼び出される）//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//Actionクラス（呼び出し元）へはDTOの入れ物で返すため戻り値の型はDTO
	public MyPageDTO getMyPageUserInfo (String item_transaction_id,String user_master_id) throws SQLException{
		                             //引数は①item_transaction_id = 商品のId番号 ②user_master_id = ログインユーザーのId

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();//DBConnectorクラスのgetConnectionクラスを呼び出し（MySQLサーバに接続）

		MyPageDTO myPageDTO = new MyPageDTO();

		//SQL文を定義
		String sql ="SELECT iit.item_name, ubit.total_price, ubit.total_count, ubit.pay "
			     	+ "FROM user_buy_item_transaction ubit "
				    + "LEFT JOIN item_info_transaction iit "
				    + "ON ubit.item_transaction_id = iit.id "
				    + "WHERE ubit.item_transaction_id=? AND ubit.user_master_id = ? "
				    + "ORDER BY ubit.insert_date DESC ";

		//「SELECT」①item_name ②total_price ③total_count ④pay

		             // item_info_transactionテーブルより①item_name
		             // user_buy_item_transactionテーブルより②total_price ③total_count ④pay をselect


        //「FROM」user_buy_item_transaction (as) ubit  = テーブル名①user_buy_item_transaction = ubitに省略

    	//「LEFT JOIN」item_info_transaction (as) iit  = テーブル名②item_info_transaction = iitに省略

		//「ON」ubit.item_transaction_id =iit.id

	    //「WHERE」ubit.item_transaction_id=引数のitem_transaction_id（商品のId番号）
		     //AND ubit.user_master_id = 引数のuser_master_id（ログインユーザーのId）

		//「ORDER BY」insert_date DESC →新しい日付順

		//LEFT JOIN : 外部結合
		//SELECT ○○ FROM テーブル名① LEFT JOIN テーブル名② ON 結合の条件 WHERE 抽出条件
		   //左側に指定されたテーブル名①を軸にして外部結合

		   //user_buy_item_transactionテーブルを軸にitem_info_transactionテーブルと外部結合

		 //⇔右側 ：RIGHT JOIN

		//ORDER BY : insert「昇順」, desc「降順」

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);     //1番目の?に引数のitem_transaction_id（商品のId番号）
			preparedStatement.setString(2, user_master_id);          //2番目の?に引数のuser_master_id（ログインユーザーのId）

		  //SQL文実行
			ResultSet resultSet = preparedStatement.executeQuery();

			//該当する検索結果があるかないか
			if(resultSet.next()){

				//DBから取得したデータをString型に変換→DTOクラスの各フィールドに上書き
				myPageDTO.setItemName(resultSet.getString("item_name"));       //DTOクラスのitemNameフィールドにDBで取得した"item_name"(商品名) をset
				myPageDTO.setTotalPrice(resultSet.getString("total_price"));   //DTOクラスのtotalPriceフィールドにDBで取得した"total_price"(ユーザーが購入した合計金額) をset
				myPageDTO.setTotalCount(resultSet.getString("total_count"));   //DTOクラスのtotalCountフィールドにDBで取得した"total_count"(ユーザーが購入した商品個数) をset
				myPageDTO.setPayment(resultSet.getString("pay"));              //DTOクラスのpayフィールドにDBで取得した"pay"(ユーザーが購入した商品個数) をset
			}

		//例外処理
		}catch (Exception e){
			e.printStackTrace();

		}finally{ //例外が発生するしないに関わらず実行

			connection.close();//DB接続切断
		}

		return myPageDTO;  //DTOクラスのフィールドに上書きされたデータを呼び出し元のActionクラスへ
	}

 //buyItemHistoryDeleteメソッド（MyPageActionクラスから呼び出し）//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int buyItemHistoryDelete(String item_transaction_id, String user_master_id) throws SQLException{
		                          //引数 ①item_transaction_id =商品のId番号  ②user_master_id =ログインしたユーザーのId番号

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		//SQL文を定義
		String sql = "DELETE FROM user_buy_item_transaction "
				   + "WHERE item_transaction_id = ? AND user_master_id =?";
		                       // user_buy_item_transactionテーブルからitem_transaction_id = ? & user_master_id =?のものををdelete (削除)

		PreparedStatement preparedStatement; //preparedStatementフィールド

		int result = 0;//戻り値フィールド「result」の初期値に0を代入

		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,item_transaction_id); //1番目の?に引数のitem_transaction_id(商品のId番号)
			preparedStatement.setString(2,user_master_id);      //2番目の?に引数のuser_master_id(ログインしたユーザーのId番号)

			//SQL文実行
			result = preparedStatement.executeUpdate(); //1件削除されれば result=1に上書きされる

	    //例外処理
		}catch (SQLException e){
			e.printStackTrace();

		}finally{//例外が発生するしないに関わらず実行

			connection.close(); //DB接続切断
		}

		return result; //result = (0以上の数字[int型]) →Actionクラスに返す
	}
}
