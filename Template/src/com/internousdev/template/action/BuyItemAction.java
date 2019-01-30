package com.internousdev.template.action;

import java.util.Map; //Map（session）使用するため

import org.apache.struts2.interceptor.SessionAware;//「SessionAware」

import com.opensymphony.xwork2.ActionSupport; //Struts2が持つActionSupportクラス

//Actionクラス (buyItem.jspから呼び出し→executeメソッドを実行)
public class BuyItemAction extends ActionSupport implements SessionAware{  //Actionクラスは基本的にActionSupportをextend(継承)
	                                                                       //Sessionを利用するためのルール① SessionAwareをimplement(実装)
	//JSPから受け取る(入力データ)フィールド
	private int stock;   //buyItem.jspで入力したstock (購入予定個数[在庫(1～5)]) をValueStack経由で自動的にset
	private String pay;  //buyItem.jspで入力したpay (支払い方法予定(現金[1]orカード[2]) をValueStack経由で自動的にset

	//session：消えない保管庫
	public Map<String, Object> session;  //Sessionを利用するためのルール② sessionフィールド作成（書き方固定）

	//メソッドの戻り値フィールド「result」
	private String result;

//executeメソッド/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String execute(){

		//メソッドの戻り値「result」の初期値にSUCCESSを代入
		result = SUCCESS; //定数フィールドSUCCESSの中身は"success"

		//sessionにput
		session.put("stock", stock); // key："stock",値：stock(購入予定個数[在庫(1～5)] : buyItem.jspで受け取った入力データ

		//①Object型からString型 toString();
		//②String型からint型へ  int ○○ = Integer.parseInt (int型にしたい文字列[String型のみ])

		int intStock = Integer.parseInt(session.get("stock").toString());         //stock=購入予定個数[在庫]
		int intPrice = Integer.parseInt(session.get("buyItem_price").toString()); //buyItem_price=単価

		//int型にしたため計算ができるように
		session.put("buyItem_price",intStock*intPrice);  //sessionに購入予定合計金額をput（ key："buyItem_price",値：intStock*intPrice ）
		           //buyItem_priceは作成済のためデータが上書きされる（単価→購入予定合計金額へ）

		//paymentフィールド
		String payment;

		//buyItem.jspでpay=1(現金)を選択した場合 →paymentフィールドに"現金払い"を代入
		if(pay.equals("1")){
			payment = "現金払い";
			session.put("pay", payment); //sessionにput（ key："pay",値：payment"現金払い" ）

			//buyItem.jspでpay=2(クレジットカード)を選択した場合 →paymentフィールドに"クレジットカード"を代入
			}else{
				payment = "クレジットカード";
				session.put("pay", payment); //sessionにput（ key："pay",値：payment"クレジットカード" ）
				}

		return result;  //result = SUCCES("success")→buyItemConfirm.jspへ
	}

	//getter : 今回は使用なし
	//setter : buyItemConfirm.jspでユーザーが入力したデータをDTOの各フィールドに格納

	public int getStock(){
		return stock;
	}
	public void setStock(int stock){  //buyItemConfirm.jspで入力したstock(購入予定個数(1～5)) のデータをstockフィールドにset
		this.stock = stock;
	}
	public String getPay(){
		return pay;
	}
	public void setPay(String pay){   //buyItemConfirm.jspで入力したpay(支払い方法予定(現金[1]orカード[2])) のデータをpayフィールドにset
		this.pay = pay;
	}

	//Sessionを利用するためのルール③ sessionフィールドをgetter/setter
	public Map<String, Object> getSession(){
		return session;
	}
	@Override
	public void setSession(Map<String,Object> session){
		this.session = session;
	}
}
