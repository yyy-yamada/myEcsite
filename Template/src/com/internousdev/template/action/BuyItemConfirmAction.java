package com.internousdev.template.action;

import java.sql.SQLException;//エラー処理で使用「SQLException」
import java.util.Map; //session(Map)を利用するため記述

import org.apache.struts2.interceptor.SessionAware;//「SessionAware」

import com.internousdev.template.dao.BuyItemCompleteDAO;
import com.opensymphony.xwork2.ActionSupport; //Struts2が持つActionSupportクラス

//Actionクラス (buyItemConfirm.jspから呼び出し→executeメソッドを実行)
public class BuyItemConfirmAction extends ActionSupport implements SessionAware{ //Actionクラスは基本的にActionSupportをextend(継承)
                                                                                 //Sessionを利用するためのルール① SessionAwareをimplement(実装)
  //session：消えない保管庫
	private Map<String, Object> session;   //Sessionを利用するためのルール② sessionフィールド作成（書き方固定）


//executeメソッド//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String execute() throws SQLException{
		                  //throws SQLException = 例外を投げる（try/catch文書かなくてOK）

		BuyItemCompleteDAO buyItemCompleteDAO = new BuyItemCompleteDAO(); // BuyItemCompleteDAOクラスをインスタンス化・・・クラス名 変数名 =new クラス名();

		//DAOクラスのbuyItemInfoメソッドを呼び出し(実引数は各sessionからgetした値）
		buyItemCompleteDAO.buyItemInfo(
			session.get("id").toString(),             //session key : id(商品のId番号)
			session.get("login_user_id").toString(),  //session key : login_user_id(ログインしたユーザーのId番号)
			session.get("buyItem_price").toString(),  //session key : buyItem_price(購入合計金額)
			session.get("stock").toString(),          //session key : stock(購入個数 [在庫])
			session.get("pay").toString()             //session key : pay(支払い方法 = 現金orカード)
			);

		//メソッドの戻り値フィールド「result」の初期値に"success"を代入
		String result = SUCCESS; //定数フィールドSUCCESSの中身は"success"

		return result; //result = SUCCES("success")→buyItemComplete.jspへ
		}

	//Sessionを利用するためのルール③ sessionフィールドをgetter/setter
	public Map<String, Object> getSession(){
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}

}
