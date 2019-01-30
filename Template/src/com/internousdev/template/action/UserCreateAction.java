package com.internousdev.template.action;

import com.opensymphony.xwork2.ActionSupport; //Struts2が持つActionSupportクラス

public class UserCreateAction extends ActionSupport{ //Actionクラスは基本的にActionSupportをextend(継承)

//executeメソッド//////////////////////////////////////////////////////////////
	public String execute(){
		return SUCCESS; //result = SUCCES("success")→userCreate.jspへ
	}
}
