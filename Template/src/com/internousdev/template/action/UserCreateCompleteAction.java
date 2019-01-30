package com.internousdev.template.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.template.dao.UserCreateCompleteDAO;
import com.opensymphony.xwork2.ActionSupport; //Struts2が持つActionSupportクラス

//Actionクラス (userCreateConfirm.jspから呼び出し→executeメソッドを実行)
public class UserCreateCompleteAction extends ActionSupport implements SessionAware{ //Actionクラスは基本的にActionSupportをextend(継承)
                                                                                     //Sessionを利用するためのルール① SessionAwareをimplement(実装)
	//JSPから受け取る(入力データ)フィールド
	private String loginUserId;
	private String loginPassword;
	private String userName;

	//session：消えない保管庫
	private Map<String, Object> session; //Sessionを利用するためのルール② sessionフィールド作成（書き方固定）

//executeメソッド///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String execute() throws SQLException{

		UserCreateCompleteDAO userCreateCompleteDAO = new UserCreateCompleteDAO();

		//DAOクラスのcreateUserメソッドを呼び出し（実引数は各sessionでgetした値）
		userCreateCompleteDAO.createUser(
				                         session.get("loginUserId").toString(),   // key: loginUserId(ログインIDで入力したデータ)
				                         session.get("loginPassword").toString(), // key: loginPassword(ログインPASSで入力したデータ)
				                         session.get("userName").toString()       // key: userName(ユーザー名で入力したデータ)
				                         );

		//メソッドの戻り値フィールド「result」の初期値に"success"を代入
		String result=SUCCESS; //定数フィールドSUCCESSの中身は"success"

		return result;   //return=SUCCESS("success") → userCreateComplete.jspへ
	}

	//getter : 今回は使用なし
	//setter : userCreateConfirm.jspでユーザーが入力したデータをDTOの各フィールドに格納

	public String getLoginUserId(){
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId){  //userCreateConfirm.jspで入力したログインidのデータをloginUserIdフィールドにset
		this.loginUserId = loginUserId;
	}

	public String getLoginPassword(){
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword){ //userCreateConfirm.jspで入力したログインパスワードのデータをloginPasswordフィールドにset
		this.loginPassword = loginPassword;
	}

	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){ //userCreateConfirm.jspで入力したユーザーネームのデータをuserNameフィールドにset
		this.userName = userName;
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
