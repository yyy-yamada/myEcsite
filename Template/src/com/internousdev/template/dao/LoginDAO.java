package com.internousdev.template.dao;

//Javaが用意してくれている便利クラスを利用できるようにimport

import java.sql.Connection;//DBMSへの接続や切断で使用「Connection」
import java.sql.PreparedStatement;//SQL文を送信する際に使用「PreparedStatement」
import java.sql.ResultSet;//DBMSから検索結果を受け取る際に使用「ResultSet」

import com.internousdev.template.dto.LoginDTO;
import com.internousdev.template.util.DBConnector;

//DAOクラス
public class LoginDAO {

	//getLoginUserInfoメソッド（loginActionクラスから呼び出し）////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//Actionクラス（呼び出し元）へはDTOの入れ物で返すため戻り値の型はDTO
	public LoginDTO getLoginUserInfo(String loginUserId, String loginPassword){
		                          //引数は①loginUserId =login.jspで入力したログインId ②loginPassword =login.jspで入力したパスワード

		DBConnector dbConnector = new DBConnector();  //DBConnectorクラスをインスタンス化・・・クラス名 変数名 =new クラス名();
		Connection connection = dbConnector.getConnection();//DBConnectorクラスのgetConnectionクラスを呼び出し（MySQLサーバに接続）

		LoginDTO loginDTO = new LoginDTO();   //LoginDTOクラスをインスタンス化

		//SQL文を定義
		String sql = "SELECT * FROM login_user_transaction where login_id = ? AND login_pass = ? ";
                    //login_user_transactionテーブルからlogin_id=? & login_passが? なものをselect

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, loginUserId);    //1番目の?に引数のloginUserId（ログイン時に入力したId)をset
			preparedStatement.setString(2, loginPassword);  //2番目の?に引数のloginPassword（ログイン時に入力したPassword)をset

		// ＝login_user_transactionテーブルからlogin_id=ログイン時に入力したId & login_pass=ログイン時に入力したPassword に該当するデータをselect

		  //SQL文実行メソッド（select文→executeQueryメソッド＋戻り値は「ResultSet」）
			ResultSet resultSet = preparedStatement.executeQuery();

			//該当する検索結果があるかないか
			if(resultSet.next()){

				//select文でDBから取得したデータをDTOクラスへ
				loginDTO.setLoginId(resultSet.getString("login_id"));         //DTOクラスのloginIdフィールドにDBで取得した"login_id" (login.jspで入力したデータと合致したログインId) をset
				loginDTO.setLoginPassword(resultSet.getString("login_pass")); //DTOクラスのloginPasswordフィールドにDBで取得した"login_pass" (login.jspで入力したデータと合致したログインパスワード)をset
				loginDTO.setUserName(resultSet.getString("user_name"));       //DTOクラスのuserNameフィールドにDBで取得した"user_name"(login.jspで入力したデータ情報に該当するユーザーネーム)をset

				//DBで取得した"login_id"に値が入っているならば
				if( !(resultSet.getString("login_id").equals(null))){

					//DTOクラスのLoginFlgフィールドを「true」に上書き
					loginDTO.setLoginFlg(true);
				}
			}

			//例外処理
			}catch (Exception e){
				e.printStackTrace();
				}

		//DTOの各フィールドに入れたデータを呼び出し元のLoginActionクラスへ返す
		return loginDTO;
		       //private String loginId = （ログイン時に入力したデータと合致したログインId）;
		       //private String loginPassword =（ログイン時に入力したデータと合致したログインパスワード）;
		       //private String userName = （ログイン時に入力したデータ情報に該当するユーザーネーム）;
               //private boolean loginFlg = true;
		}
}
