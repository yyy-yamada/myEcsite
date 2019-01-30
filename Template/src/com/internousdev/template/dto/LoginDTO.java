package com.internousdev.template.dto;

//DTOクラス
public class LoginDTO {

	private String loginId;
	private String loginPassword;
	private String userName;

	private boolean loginFlg = false;  // boolean型 (真偽型)：true or false
	                                   // loginFlgフィールドに初期値「ERROR」を代入


	//getter : ② LoginActionクラスから呼び出され、①でsetしたDTO各フィールド内の値を LoginActionクラスへ渡す
	//setter : ① LoginDAOクラスから呼び出され、DAOクラスより実引数として受け取ったテーブルの値をDTOの各フィールドに格納

	public String getLoginId(){ //loginIdフィールドのデータ (中身はDBで取得した"login_id =ログイン時に入力したデータと合致したログインId"）をLoginActionクラスでもgetできるように設定
		return loginId;
	}
	public void setLoginId(String loginId){  //LoginDAOクラス内でもloginIdフィールドにデータを書き込めるよう設定 (実際にLoginDAOクラスでは、DBで取得した"login_id =ログイン時に入力したデータと合致したログインId"をset)
		this.loginId = loginId;
	}

	public String getLoginPassword(){//loginPasswordフィールドのデータ (中身はDBで取得した"login_pass =ログイン時に入力したデータと合致したログインパスワード"）をLoginActionクラスでもgetできるように設定
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword){ //LoginDAOクラス内でもloginPasswordフィールドにデータを書き込めるよう設定 (実際にLoginDAOクラスでは、DBで取得した"login_pass =ログイン時に入力したデータと合致したログインパスワード"をset)
		this.loginPassword = loginPassword;
	}

	public String getUserName(){  //userNameフィールドのデータ (中身はDBで取得した"user_name =ログイン時に入力したデータ情報に該当するユーザーネーム"）をLoginActionクラスでもgetできるように設定
		return userName;
	}
	public void setUserName(String userName){ //LoginDAOクラス内でもuserNameフィールドにデータを書き込めるよう設定 (実際にLoginDAOクラスでは、DBで取得した"user_name =ログイン時に入力したデータ情報に該当するユーザーネーム"をset)
		this.userName = userName;
	}

	public boolean getLoginFlg(){//loginFlgフィールドのデータ (中身は"true" or "false"）をLoginActionクラスでもgetできるように設定
		return loginFlg;
	}
	public void setLoginFlg(boolean loginFlg){ //LoginDAOクラス内でもloginFlgフィールドにデータを書き込めるよう設定 (実際LoginDAOクラスでは、ログイン成功の条件が満たされた場合"true"に上書きをする)
		this.loginFlg = loginFlg;
	}

}
