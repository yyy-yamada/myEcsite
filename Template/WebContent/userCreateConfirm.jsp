
<!-- ◆useCreateConfirm.jsp (UserCreateConfirmActionクラスで「SUCCESS」が返された場合) -->


<!-- JSPファイルを宣言（毎回固定）=============================================== -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
			 <!--  s:●●～"を記述するとstrurs-tagsが使えるように
						（strutsタグ：Strutsフレームワークで使える専用のHTMLタグ) -->

<!-- HTML5を宣言 =================================================================================================================================================== -->
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


	<title>UserCreateConfirm画面</title>

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
			<p>UserCreateConfirm</p>
		</div>
		<h3>登録する内容は以下でよろしいですか。</h3>

     <!-- UserCreateCompleteActionクラスへ ========================================================= -->

		<table>
			<s:form action="UserCreateCompleteAction">
				<tr id="box">
					<td><label>ログインID：</label></td>
					<td><s:property value="loginUserId" escape="false"/></td> <!-- loginUserId(ログインIDで入力したデータ) -->
				</tr>
				<tr id="box">
					<td><label>ログインPASS：</label></td>
					<td><s:property value="loginPassword" escape="false"/></td> <!-- loginPassword(ログインPASSで入力したデータ) -->
				</tr>
				<tr id="box">
					<td><label>ユーザー名</label></td>
					<td><s:property value="userName" escape="false"/></td> <!-- userName(ユーザー名で入力したデータ) -->
				</tr>
				<tr>
					<td><s:submit value="完了"/></td> <!-- ＝<input type="submit" name="完了"> -->
				</tr>
			</s:form>
		</table>
	</div>

  <!-- escape属性とは ====================================================================================================== -->

		<!-- ①escape="true"（普段省略されている場合も"true"に設定済）
			 ②escape="false"

			 ～ ログインId：<body>AAA</body> と入力した場合 ～

				実行後のフロント画面
				① "true" → <body>AAA<body>と表示 (<body>が文字列として変換される)
				② "false"→       AAA      と表示 (<body>が認識されている)

													→これを利用して悪用する人がいるため、escape="true"のままが望ましい
  <!-- ===================================================================================================================== -->

	<div id="footer">
	</div>

</body>
</html>