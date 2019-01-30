
<!-- ◆初期画面(home.jsp) -->


<!-- JSPファイルを宣言（毎回固定）=============================================== -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
			 <!--  s:●●～"を記述するとstrurs-tagsが使えるように
						（strutsタグ：Strutsフレームワークで使える専用のHTMLタグ) -->


<!-- HTML5を宣言 =================================================================================================================================== -->
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

	<title>Home画面</title>

<style type="text/css">
/* =============================TAG LAYOUT=======================================*/
	body{
		margin:0;   /* margin  :対象テキストの外側の余白 */
		padding:0;  /* padding :対象テキストの内側の余白 */
		line-height:1.6;
		letter-spacing:1px; /* letter-spacing :文字の間隔を指定 */
		font-family:Verdana,Helvetica,sans-serif; /* フォントの種類 */
		font-size:12px;
		color:#333;
		background:#fff;
	}

	table{
		text-align:center; /* text-align :テキスト寄せ方向 */
		margin:0 auto;
	}
/* ==============================ID LAYOUT=======================================*/
	#top{
		width:780px;
		margin:30px auto;
		border:1px solid #333;  /* border :対象テキストを囲んでいる線の①太さ②スタイル③色 */
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

<!-- <div>タグでブロックに分ける -->

	<div id="header">   <!-- <div id ="●●"></div> :IDセレクタ =スタイル(CSS)を適用するHTMLの対象範囲を示す -->
	</div>

	<div id="main">
		<div id="top">
			<p>Home</p>
		</div>

	<!-- HomeActionクラスへ ================================================================= -->
		<div>
			<s:form action="HomeAction"><!-- 入力データの送信先はHomeActionファイル -->
				<s:submit value="商品購入"/> <!-- ＝< input type="submit" name="商品購入" > -->
			</s:form>
		</div>
	</div>
	<!-- ==================================================================================== -->

	<div id="footer">
	</div>

</body>
</html>

