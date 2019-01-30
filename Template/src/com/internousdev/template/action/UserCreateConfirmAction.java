package com.internousdev.template.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;//Struts2が持つActionSupportクラス

public class UserCreateConfirmAction extends ActionSupport implements SessionAware{  //Actionクラスは基本的にActionSupportをextend(継承)
                                                                                     //Sessionを利用するためのルール① SessionAwareをimplement(実装)
	//JSPから受け取る(入力データ)フィールド
	private String loginUserId;   //loginUserId:ログインIDで入力したデータ
	private String loginPassword; //loginPassword:ログインPASSで入力したデータ
	private String userName;      //userName:ユーザー名で入力したデータ

	//session：消えない保管庫
	public Map<String,Object> session; //Sessionを利用するためのルール② sessionフィールド作成（書き方固定）

	private String errorMessage;

//executeメソッド/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String execute(){

		//メソッドの戻り値フィールド「result」の初期値に"success"を代入
		String result=SUCCESS;//定数フィールドSUCCESSの中身は"success"

		if( !(loginUserId.equals(""))        //各入力項目が空欄でなければ各sessonにput
			&& ! (loginPassword.equals(""))
			&& ! (userName.equals(""))){

				session.put("loginUserId", loginUserId);     // key："loginUserId",値：loginUserId(ログインIDで入力したデータ)
				session.put("loginPassword", loginPassword); // key："loginPassword",値：loginPassword(ログインPASSで入力したデータ)
				session.put("userName", userName);           // key："userName",値：userName(ユーザー名で入力したデータ)

		}else{
		//空欄の項目があるならば
				setErrorMessage("未入力の項目があります。"); //ErrorMessageフィールドに書き込み

			  //resultフィールドをERRORに上書き
				result=ERROR; //定数フィールドERRORの中身は"error"
			}

			return result; //result = SUCCES("success")→userCreateConfirm.jspへ
			               //result = ERROR("error") →userCreate.jspへ
	}

		public String getLoginUserId(){
			return loginUserId;
		}
		public void setLoginUserId(String loginUserId){
			this.loginUserId = loginUserId;
		}
		public String getLoginPassword(){
			return loginPassword;
		}
		public void setLoginPassword(String loginPassword){
			this.loginPassword = loginPassword;
		}
		public String getUserName(){
			return userName;
		}
		public void setUserName(String userName){
			this.userName = userName;
		}

	//Sessionを利用するためのルール③ sessionフィールドをgetter/setter
		public Map<String, Object> getSession(){
			return session;
		}
		@Override
		public void setSession(Map<String, Object> session){
			this.session = session;
		}

		public String getErrorMessage(){
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage){
			this.errorMessage = errorMessage;
		}

}
