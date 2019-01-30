
<!-- ◆useCreate.jsp (UserCreateActionクラスで「SUCCESS」または UserCreateConfirmActionクラスで「ERROR」が返された場合 -->


<!-- JSPファイルを宣言（毎回固定）=============================================== -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
			 <!--  s:●●～"を記述するとstrurs-tagsが使えるように
						（strutsタグ：Strutsフレームワークで使える専用のHTMLタグ) -->

<!-- HTML5を宣言 ===================================================================================================================================== -->
<!DOCTYPE html>

<html>
<head>

<!-- meta要素 --><!-- http-equiv属性 --><!-- Content-Type:文字コード指定 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> <!--HTML(HTML5の場合 <meta charset="UTF-8">のみの記述でOK -->

	<meta http-equiv="Content-Style-Type" content="text/css"/>           <!--CSS (HTML5の場合省略可) -->
	<meta http-equiv="Content-Script-Type" content="text/javascript"/>   <!--JavaScript (HTML5の場合省略可) -->
                                     <!--多くの場合これらの指定をしなくてもブラウザが自動的に判断するが、誤作動予防で記述しておく方がよい-->

	<meta http-equiv="imagetoolbar" content="no"/><!-- イメージツールバー非表示設定 -->

	<meta name="description" content=""/><!--検索結果画面に説明文として表示される  →HTML文書(ページ)の内容をあらわす概要を記述 -->
	<meta name="keywords" content=""/>   <!-- →HTML文書(ページ)のキーワードを指定する -->


	<title>UserCreate画面</title>

<style type="text/css">
 /* =============================TAG LAYOUT=======================================*/
	body{
	margin:0;
		padding:0;
		line-height:1.6;
		letter-spacing:1px; /* letter-spacing：文字の間隔を指定 */
		font-family:Verdana,Helvetica,sans-serif;
		font-size:12px;
		color:#333;
		background:#fff;
	 }

	table{
		text-align:center;
		margin:0 auto;
	}
 /* ==============================ID LAYOUT=======================================*/
	#top{
		width:780px;
		margin:30px auto;
		border:1px solid #333;
	}

	#header{
		width:100%;
		height:80px;
		background-color:black;
	}

	#main{
		width:100%;
		height:500px;
		text-align:center;
	}

	#footer{
		width:100%;
		height:80px;
		background-color:black;
		clear:both;
	}
/* ===============================================================================*/
	</style>
</head>

<body>

	<div id="header">
	</div>
	<div id="main">
		<div id="top">
			<p>UserCreate</p>
		</div>

		<!-- result=ERRORでuserCreate.jspに戻された場合、
 			 ErrorMessageフィールドに("未入力の項目があります。") が入っているはず -->

   <!-- もしerrorMessageフィールドが空欄でなければ =================================================================== -->

		<s:if test= "errorMessage != ''">
			<s:property value="errorMessage" escape="false"/> <!-- errorMessageフィールドの中身を表示 -->
		</s:if>

            <!-- UserCreateConfirmActionクラスへ ==================================================

			①入力したログインID = ②loginUserId
			①入力したログインPASS = ②loginPassword
			①入力したユーザー名 = ②userName

			→ ①入力データと②名前がセットでUserCreateConfirmActionクラスへ送信される

					(Actionクラスに同名のフィールドを用意＋上書きするためにsetterを定義)  -->

		<table>
			<s:form action="UserCreateConfirmAction">
				<tr>
					<td><label>ログインID：</label></td>
					<td><input type="text" name="loginUserId" value=""/></td> <!-- ＝<input type="text" name="loginUserId"> -->
				</tr>
				<tr>
					<td><label>ログインPASS：</label></td>
					<td><input type="text" name="loginPassword" value=""/></td> <!-- ＝<input type="text" name="loginPassword"> -->
				</tr>
				<tr>
					<td><label>ユーザー名：</label></td>
					<td><input type="text" name="userName" value=""/></td> <!-- ＝<input type="text" name="userName"> -->
				</tr>
				<s:submit value="登録"/><!-- ＝<input type="submit" name="登録"> -->
			</s:form>
		</table>

		<span>前画面に戻る場合は</span>

		<!-- HomeActionクラスへ =============================  -->
			<a href='<s:url action="HomeAction"/>'>こちら</a> <!-- a href =リンクの挿入（「こちら」をクリックするとリンクからActionクラスを呼び出す -->
	</div>

	<div id="footer">
	</div>

</body>
</html>