package com.internousdev.template.action;

import java.sql.SQLException;//エラー処理で使用「SQLException」
import java.util.Map;  //session(Map)を利用するため記述

import org.apache.struts2.interceptor.SessionAware; //「SessionAware」

import com.internousdev.template.dao.MyPageDAO;
import com.internousdev.template.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport; //Struts2が持つActionSupportクラス

//Actionクラス（buyItemComplete.jspから呼び出される→executeメソッドを実行）
public class MyPageAction extends ActionSupport implements SessionAware{ //Actionクラスは基本的にActionSupportをextend(継承)
                                                                         //Sessionを利用するためのルール① SessionAwareをimplement(実装)

	//session：消えない保管庫
	public Map<String, Object> session; //Sessionを利用するためのルール② sessionフィールド作成（書き方固定）

	public String deleteFlg;//deleteFlgフィールド
	private String result; //メソッドの戻り値フィールド「result」

//executeメソッド////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String execute() throws SQLException{

		MyPageDAO myPageDAO = new MyPageDAO();
		MyPageDTO myPageDTO = new MyPageDTO();

		//商品履歴を削除しない場合（deleteFlgフィールドに何も代入されていない場合）
		if (deleteFlg ==null){

			//item_transaction_idフィールド = sessionからget（ key:"id"（商品のId番号））
			String item_transaction_id = session.get("id").toString();

			//user_master_idフィールド = sessionからget（ key:"login_user_id"（ログインしたユーザーのId））
			String user_master_id = session.get("login_user_id").toString();

			//DAOクラスのgetMyPageUserInfoメソッドを呼び出し（実引数は商品のId番号とユーザーのId）
			myPageDTO = myPageDAO.getMyPageUserInfo(item_transaction_id, user_master_id);

			//sessionにput
			session.put("buyItem_name", myPageDTO.getItemName()); // key："buyItem_name",値：DTOクラスで作成したitemName(商品名)
			session.put("total_price",myPageDTO.getTotalPrice()); // key："total_price",値：DTOクラスで作成したTotalPrice(ユーザー購入合計金額)
			session.put("total_count",myPageDTO.getTotalCount()); // key："total_count",値：DTOクラスで作成したTotalCount(ユーザー購入商品個数)
			session.put("total_payment",myPageDTO.getPayment());  // key："total_payment",値：DTOクラスで作成したPayment(ユーザー購入商品の支払い方法)
			session.put("message","");                            // key："message",値：""

			//商品履歴を削除する場合（deleteFlgフィールドに「1」が入っている場合）
			}else if (deleteFlg.equals("1")){

				delete();//deleteメソッドを呼び出す →このプログラムでは購入回数が最新の１回のみの表示となる
				}

		//メソッドの戻り値「result」に"success"を代入
		result = SUCCESS; //定数フィールドSUCCESSの中身は"success"

	return result;// SUCCESS("success") → myPage.jspへ
	}

//deleteメソッド//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void delete() throws SQLException{

		MyPageDAO myPageDAO = new MyPageDAO();

		//item_transaction_idフィールド = sessionからget（ key:"id"（商品のId番号））
		String item_transaction_id = session.get("id").toString();

		//user_master_idフィールド = sessionからget（ key:"login_user_id"（ログインしたユーザーのId））
		String user_master_id = session.get("login_user_id").toString();

		//DAOクラスのbuyItemHistoryDeleteメソッド呼び出し
		int res = myPageDAO.buyItemHistoryDelete(item_transaction_id, user_master_id);
		                               //実引数はitem_transaction_id（商品のId番号）, user_master_id（ログインしたユーザーのId番号）

		//int res = returnされた数字（deleteされた件数）
		if(res>0){
			session.put("message", "商品情報を正しく削除しました。");//sessionにput（key："messsage" 値： "商品情報を正しく削除しました。"）

		}else if (res==0){
			session.put("message", "商品情報の削除に失敗しました。");//sessionにput（key："messsage" 値： "商品情報の削除に失敗しました。"）
			}
	}

	public String getDeleteFlg(){
		return deleteFlg;
	}
	public void setDeleteFlg(String deleteFlg){
		this.deleteFlg = deleteFlg;
	}

//Sessionを利用するためのルール③ sessionフィールドをgetter/setter
	public Map<String, Object> getSession(){
		return session;
	}
	@Override
	public void setSession(Map<String, Object> loginSessionMap){
		this.session = loginSessionMap;
	}

}