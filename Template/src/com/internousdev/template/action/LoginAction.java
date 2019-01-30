package com.internousdev.template.action;

import java.util.Map;//Map（session）で使用

import org.apache.struts2.interceptor.SessionAware;//「SessionAware」

import com.internousdev.template.dao.BuyItemDAO;
import com.internousdev.template.dao.LoginDAO;
import com.internousdev.template.dto.BuyItemDTO;
import com.internousdev.template.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport; //Struts2が持つActionSupportクラス

//Actionクラス(login.jspから呼び出し→executeメソッドを実行)
public class LoginAction extends ActionSupport implements SessionAware{   //Actionクラスは基本的にActionSupportをextend(継承)
                                                                          //Sessionを利用するためのルール① SessionAwareをimplement(実装)
	//JSPから受け取る(入力データ)フィールド
	private String loginUserId;     //login.jspで入力したloginUserId (ログインId) をValueStack経由で自動的にset
	private String loginPassword;   //login.jspで入力したloginPassword (パスワード) をValueStack経由で自動的にをset


 //◆ValueStackとは（jinkore.work参照）/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	    //Struts2（フレームワーク）の便利機能の一つ
	    //一時的な保管庫(箱)

	//①ユーザーがフォームから入力し送信された値は、一旦ValueStackへ保存される
	//②Struts2がActionファイル内のsetterメソッドによって、ValueStackで一旦保存した値をActionクラスの該当フィールドにset
	//③JSPファイルに <s:property～>と記載することで、JSPファイルでもActionクラスのフィールド内の値を取得できる (Actionクラス内のgetterメソッドを通して)


	    //データの受け渡しを自動的に裏側でやってくれる (コードを書かなくてOK)

	    //A地点から入力され送信された値は、ValueStackという箱に一旦吸い込まれるイメージ →その後B地点へ受け渡し
        //役割①「ValueStackの近くをデータがうろつくと、指示されなくとも勝手に箱の中へ仕舞い込む」
	    //役割②「仕舞い込んだデータを目的地のファイルへ受け渡す」
       	        //→目的地ファイルで該当フィールドのsetterメソッド定義が必要

//	 login.jsp ---------→ ( ValueStack ) ----------------------------------------------------→ loginAction.java

   	//①データ入力          //①送信ボタンクリック→ValueStackの箱が生まれる                 	 //条件①同名のフィールド変数を宣言
	//②送信ボタン          //②入力データを一旦自動的に保管（フィールドも自動的に生成）     	 //条件②該当フィールド変数に対応するsetterメソッドを記述
							//③Actionクラスへお届け                                            	→JSPからの入力データを自動的に受け取れるように

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//メソッドの戻り値フィールド「result」
	private String result;

	//session：消えない保管庫
	private Map<String, Object> session;  //Sessionを利用するためのルール② sessionフィールド作成（書き方固定）

//executeメソッド///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String execute(){

		//各クラスをインスタンス化・・・クラス名 変数名 =new クラス名();

		LoginDAO loginDAO = new LoginDAO();      //LoginDAOクラス
		LoginDTO loginDTO = new LoginDTO();      //LoginDTOクラス
		BuyItemDAO buyItemDAO = new BuyItemDAO();//BuyItemDAOクラス

		//メソッドの戻り値「result」の初期値にERRORを代入
	    result=ERROR; //定数フィールドERRORの中身は"error"

	    //DAOクラスのgetLoginUserInfoメソッドを呼び出し →JSPから送られてきた入力データ(ログインId,パスワード)を実引数として送る
	    //DTOクラスにDAOで取得した結果を代入

		loginDTO =loginDAO.getLoginUserInfo(loginUserId, loginPassword);

		//戻り値は loginDTO; (DAOクラスのgetLoginUserInfoメソッドより)

		  //DTOに入っているデータ
				//private String loginId = （login.jspで入力したデータと合致したログインId）;
				//private String loginPassword =（login.jspで入力したデータと合致したログインパスワード）;
				//private String userName = （login.jspで入力したデータ情報に該当するユーザーネーム）;
				//private boolean loginFlg = true; ( =DBで取得した"login_id"に値が入っているなら"true" )

		//sessionにDTOで代入したデータをput（ key："loginUser",値：loginDTO ）
		session.put("loginUser", loginDTO);

		//if (true)なら{ 処理実行 }へ・・・if (session[key:loginUser] の中のLoginFlgの中身) [loginFlg = true or false]////////////////////////////////////////////////////////////////////////////////////

		if(((LoginDTO) session.get("loginUser")).getLoginFlg()){ //(LoginDTO) :sessionはObject型なためDTO型へ変更

			//if条件を満たした場合 →戻り値「result」の中身をSUCCESSに書き換え
			result=SUCCESS; //定数フィールドSUCCESSの中身は"success"

			//result=SUCCESSの場合、次の画面で「商品情報」が必要なため、商品情報を取得

			//buyItemDAOクラスのgetBuyItemInfoメソッドを呼び出し
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();

			//return buyItemDTO;; (DAOクラスのgetBuyItemInfoメソッドより)

			//DTOに入っているデータ
				//private int id =（商品のid番号);
				//private String itemName =（商品名）;
				//private String itemPrice =（単価）;

			//sessionにDTOで代入したデータをput
			session.put("login_user_id",loginDTO.getLoginId());     // key："login_user_id" ,値：loginDTOでgetしたLoginId (ログイン時に入力したデータと合致したログインId)
			session.put("id",buyItemDTO.getId());                   // key："id" ,値：buyItemDTOでgetしたId（商品のid番号)
			session.put("buyItem_name", buyItemDTO.getItemName());  // key："buyItem_name" ,値：buyItemDTOでgetしたItemName（商品名)
			session.put("buyItem_price", buyItemDTO.getItemPrice());// key："buyItem_price" ,値：buyItemDTOでgetしたItemPrice（単価)

			return result;//= SUCCES("success")→buyItem.jspへ
			}

		//if(false)の場合
		return result;  // = ERROR("error")→home.jspへ
		}

		//getter : buyItem.jspでsession (商品名・単価)のみ使用
		//setter : login.jspでユーザーが入力したデータをDTOの各フィールドに格納

		public String getLoginUserId(){
		return loginUserId;
		}
		public void setLoginUserId(String loginUserId){ //login.jspで入力したloginUserId (ログインId) のデータをloginUserIdフィールドへValueStack経由で自動的にset
			this.loginUserId = loginUserId;
		}
		public String getLoginPassword(){
			return loginPassword;
		}
		public void setLoginPassword(String loginPassword){  //login.jspで入力したloginPassword (パスワード) のデータをloginPasswordフィールドへValueStack経由で自動的にset
			this.loginPassword = loginPassword;
		}

	  //Sessionを利用するためのルール③ sessionフィールドをgetter/setter

		public Map<String, Object> getSession(){  //buyItem.jspでsession (商品名・単価)の中身をget
			return session;
		}
		@Override
		public void setSession(Map<String, Object> session){
			this.session = session;
		}
	}
