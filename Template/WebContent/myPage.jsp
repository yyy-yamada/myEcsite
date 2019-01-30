
<!-- ◆myPage.jsp (MyPageActionクラスで「SUCCESS」が返された場合) -->


<!-- JSPファイルを宣言（毎回固定）=============================================== -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

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

	<title>MyPage画面</title>

<style type="text/css">
 /* =============================TAG LAYOUT=======================================*/
	body{
		margin:0;
		padding:0;
		line-height:1.6;
		letter-spacing:1px;
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
			<p>MyPage</p>
		</div>

	<!-- sessionのkey:"message"に何も入っていない場合 ========================================================== -->

		<s:if test="session.message == ''">
			<h3>ご購入情報は以下になります。</h3>

			<table>
	  			<tr>
	    			<td>商品名</td>
	    			<td><s:property value="session.buyItem_name"/></td>
				</tr>
				<tr>
				  <td>値段</td>
				  <td><s:property value="session.total_price"/><span>円</span></td>
				</tr>
				<tr>
					<td>購入個数</td>
					<td><s:property value="session.total_count" /><span>個</span></td>
				</tr>
				<tr>
					<td>支払い方法</td>
					<td><s:property value="session.total_payment"/></td>
				</tr>
			</table>

	<!-- MyPageActionクラスへ========================================================= -->

			<s:form action="MyPageAction">

			<!-- hidden=フロント画面に表示されない非表示データを送信 -->

				<input type = "hidden" name="deleteFlg" value="1">
			<!-- input type = "text"に変えた場合 →フロント画面には「1」が入ったテキスト入力画面が表示されているはず -->

				<s:submit value="削除" method="delete"/><!-- ＝< input type="submit" name="削除"
                                                                                   → MyPageActionのdeleteメソッド呼び出し> -->
			</s:form>
		</s:if>

     <!-- sessionのkey:"message"に何か入っている場合 ========================================================================= -->

		<s:if test="session.message != null">
			<h3><s:property value="session.message"/></h3><!-- messageの中身を表示 -->
		</s:if><br>

		<span>前画面に戻る場合は</span>
			<a href='<s:url action="HomeAction"/>'>ログアウト</a>
		<span>をお願いします</span>
	</div>

	<div id="footer">
	</div>

</body>
</html>