package com.internousdev.template.util;

import java.text.SimpleDateFormat;//日時を読みやすく変化させるためのクラス
import java.util.Date;//日付情報を扱うときは必ず利用

//日付時間の表示設定///////////////////////////////////////////////////////////////////////////

//DateUtilクラス
public class DateUtil {

	//getDateメソッド（BuyItemCompleteDAOクラスから呼び出し）
	public String getDate(){

		Date date = new Date(); //現在の日時を取得 (日付を扱うときはDate型)
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//書式を指定

		return simpleDateFormat.format(date); //現在日時 (2018-12-25 16:39:39形式)のデータを返す
	}
}
