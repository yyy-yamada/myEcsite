package com.internousdev.template.action;

import com.opensymphony.xwork2.ActionSupport;  //Struts2が持つActionSupportクラス

//Actionクラス(home.jspから呼び出し→executeメソッドを実行)
public class HomeAction extends ActionSupport{ //Actionクラスは基本的にActionSupportをextend(継承)


//executeメソッド//////////////////////////////////////////////////

	public String execute(){
		return SUCCESS; //result =SUCCES("success")→login.jspへ
	}
}
//◆SUCCESSが定数（大文字）な理由 //////////////////////////////////////////////////////////////////////////////////////////////////
//
// →Actionクラスで継承(extend)しているActionSupportクラスが実装(implement)しているActionインタフェースで宣言・初期化されているため
//
//		public abstract interface Action {
//			public static final String SUCCESS = "success";
//			public static final String ERROR = "error";
//		}
//					//定数の宣言方法 = final型 定数名(大文字) = 初期値 ;
