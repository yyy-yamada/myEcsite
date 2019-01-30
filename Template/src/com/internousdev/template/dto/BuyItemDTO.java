package com.internousdev.template.dto;

//DTOクラス
public class BuyItemDTO {

	private int id;
	private String itemName;
	private String itemPrice;

	//getter : ② LoginActionクラスから呼び出され、①でsetしたDTO各フィールド内の値を LoginActionクラスへ渡す
	//setter : ① BuyItemDAOクラスから呼び出され、DAOクラスより実引数として受け取ったテーブルの値をDTOの各フィールドに格納

	public int getId(){ //Idフィールドのデータ (中身はDBで取得した"id =商品のId番号"）をLoginActionクラスでもgetできるように設定
		return id;
	}
	public void setId(int id){ //BuyItemDAOクラス内でもIdフィールドにデータを書き込めるよう設定 (実際にBuyItemDAOクラスでは、DBで取得した"id =商品のId番号"をset)
		this.id = id;
	}

	public String getItemName(){ //ItemNameフィールドのデータ (中身はDBで取得した"item_name =商品名"）をLoginActionクラスでもgetできるように設定
		return itemName;
	}
	public void setItemName(String itemName){//BuyItemDAOクラス内でもItemNameフィールドにデータを書き込めるよう設定 (実際にBuyItemDAOクラスでは、DBで取得した"item_name =商品名"をset)
		this.itemName = itemName;
	}

	public String getItemPrice(){ //ItemPriceフィールドのデータ (中身はDBで取得した"item_price =単価"）をLoginActionクラスでもgetできるように設定
		return itemPrice;
	}
	public void setItemPrice(String itemPrice){//BuyItemDAOクラス内でもItemPriceフィールドにデータを書き込めるよう設定 (実際にBuyItemDAOクラスでは、DBで取得した"item_price =単価"をset)
		this.itemPrice = itemPrice;
	}
}
