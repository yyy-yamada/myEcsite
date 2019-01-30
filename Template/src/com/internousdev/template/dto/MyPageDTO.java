package com.internousdev.template.dto;

//DTOクラス
public class MyPageDTO{

	private String itemName;
	private String totalPrice;
	private String totalCount;
	private String payment;

	//getter : ② MyPageActionクラスから呼び出され、①でsetしたDTO各フィールド内の値を MyPageActionクラスへ渡す
	//setter : ① MyPageDAOクラスから呼び出され、DAOクラスより実引数として受け取ったテーブルの値をDTOの各フィールドに格納

	public String getItemName(){  //itemNameフィールドのデータ (中身はDBで取得した"item_name =商品名"）をMyPageActionクラスでもgetできるように設定
		return this.itemName;
	}
	public void setItemName(String itemName){   //MyPageDAOクラス内でもitemNameフィールドにデータを書き込めるよう設定 (実際にMyPageDAOクラスでは、DBで取得した"item_name =商品名"をset)
		this.itemName = itemName;
	}

	public String getTotalPrice(){  //totalPriceフィールドのデータ (中身はDBで取得した"total_price =ユーザーが購入した合計金額"）をMyPageActionクラスでもgetできるように設定
		return this.totalPrice;
	}
	public void setTotalPrice(String totalPrice){   //MyPageDAOクラス内でもtotalPriceフィールドにデータを書き込めるよう設定 (実際にMyPageDAOクラスでは、DBで取得した"total_price =ユーザーが購入した合計金額"をset)
		this.totalPrice = totalPrice;
	}

	public String getTotalCount(){  //totalCountフィールドのデータ (中身はDBで取得した"total_count =ユーザーが購入した商品個数"）をMyPageActionクラスでもgetできるように設定
		return this.totalCount;
	}
	public void setTotalCount(String totalCount){   //MyPageDAOクラス内でもtotalCountフィールドにデータを書き込めるよう設定 (実際にMyPageDAOクラスでは、DBで取得した"total_count =ユーザーが購入した商品個数"をset)
		this.totalCount = totalCount;
	}

	public String getPayment(){  //paymentフィールドのデータ (中身はDBで取得した"pay =ユーザーが購入した商品の支払い方法"）をMyPageActionクラスでもgetできるように設定
		return this.payment;
	}
	public void setPayment(String payment){   //MyPageDAOクラス内でもpaymentフィールドにデータを書き込めるよう設定 (実際にMyPageDAOクラスでは、DBで取得した"pay =ユーザーが購入した商品の支払い方法"をset)
		this.payment = payment;
	}
}