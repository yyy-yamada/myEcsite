package com.internousdev.template.dao;

import java.sql.Connection; //DBMSへの接続や切断で使用「Connection」
import java.sql.PreparedStatement;//SQL文を送信する際に使用「PreparedStatement」
import java.sql.ResultSet;//DBMSから検索結果を受け取る際に使用「ResultSet」

import com.internousdev.template.dto.BuyItemDTO;
import com.internousdev.template.util.DBConnector;

//DAOクラス
public class BuyItemDAO {

 //getBuyItemInfoメソッド (LoginActionから呼び出し) ///////////////////////////////////////////////////////////////////////////////////

	//Actionクラス（呼び出し元）へはDTOの入れ物で返すため戻り値の型はDTO
	public BuyItemDTO getBuyItemInfo(){

		DBConnector dbConnector = new DBConnector(); //DBConnectorクラスをインスタンス化・・・クラス名 変数名 =new クラス名();
		Connection connection = dbConnector.getConnection(); //DBConnectorクラスのgetConnectionクラスを呼び出し（MySQLサーバに接続）

		BuyItemDTO buyItemDTO = new BuyItemDTO(); // BuyItemDTOクラスをインスタンス化・・・クラス名 変数名 =new クラス名();

		//SQL文を定義
		String sql = "SELECT id,item_name,item_price FROM item_info_transaction";
	              	 //item_info_transactionテーブルからid(商品のId番号),item_name(商品名),item_price (単価) をselect

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

		 //SQL文実行メソッド（select文→executeQueryメソッド＋戻り値は「ResultSet」）
			ResultSet resultSet = preparedStatement.executeQuery();

			//該当する検索結果があるかないか
			if(resultSet.next()){

				//select文でDBから取得したデータをDTOクラスへ
				buyItemDTO.setId(resultSet.getInt("id"));                  //DTOクラスのidフィールドにDBで取得した"id"(商品のId番号)をset
				buyItemDTO.setItemName(resultSet.getString("item_name"));  //DTOクラスのitemNameフィールドにDBで取得した"item_name(商品名)"をset
				buyItemDTO.setItemPrice(resultSet.getString("item_price"));//DTOクラスのitemPriceフィールドにDBで取得した"item_price"(単価)をset
			}

		//例外処理
		}catch(Exception e){
			e.printStackTrace();
		}

		//DTOの各フィールドに入れたデータを呼び出し元のLoginActionクラスへ返す
		return buyItemDTO;
		       //private int id =（商品のid番号);
		       //private String itemName =（商品名);
	           //private String itemPrice =（単価);
	}
}
