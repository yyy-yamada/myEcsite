
<!--◆buyItem.jsp (LoginActionクラスで「SUCCESS」が返された場合) -->


<!--JSPファイルを宣言（毎回固定）=============================================== -->

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

	<title>buyItem画面</title>

<style type="text/css">
/* =============================TAG LAYOUT=======================================*/
	body{
		margin:0; /* margin  :対象テキストの外側の余白 */
		padding:0;/* padding :対象テキストの内側の余白 */
		line-height:1.6;
		letter-spacing:1px; /* letter-spacing：文字の間隔を指定 */
		font-family:Verdana,Helvetica,sans-serif;
		font-size:12px;
		color:#333;
		background:#fff;
	}

	table{
		text-align:center;/* text-align :テキスト寄せ方向 */
		margin:0 auto;
	}
/* ==============================ID LAYOUT=======================================*/
	#top{
		width:780px;
		margin:30px auto;
		border:1px solid #333; /* border :対象テキストを囲んでいる線の①太さ②スタイル③色 */
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

<!-- <div>タグでブロックに分ける ≒  <span> :要素間でも改行されない -->

	<div id="header"> <!-- <div id ="●●"></div> :IDセレクタ  =スタイル(CSS)を適用するHTMLの対象範囲を示す -->
	</div>

	<div id="main">
		<div id="top">
			<p>BuyItem</p>
	</div>

  <!-- BuyItemActionクラスへ ==============================================================================

	①購入予定個数(1～5) = ②name: stock
	①支払い方法予定(現金[1]orカード[2]) = ②name: pay

 → ①入力データと②名前がセットでBuyItemActionクラスへ送信される

		(Actionクラスに同名のフィールドを用意＋上書きするためにsetterを定義)  -->

	<s:form action="BuyItemAction">

		<table>
			<tr>
				<td>
					<span>商品名</span>
				</td>
				<td><s:property value="session.buyItem_name"/></td> <!-- session key : buyItem_name(商品名) -->
			</tr>
			<tr>
				<td><span>値段</span></td>
				<td><s:property value="session.buyItem_price"/><span>円</span></td> <!-- session key : buyItem_price(単価) -->
			</tr>
			<tr>
				<td><span>在庫</span></td>
				<td>
					<select name="stock">
					<!-- selected=selected；選択状態で表示 (「1」がすでに選択されている状態) -->
						<option value="1" selected="selected">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><span>支払い方法</span></td>
				<td>
					<!-- checked=checked；選択状態で表示 (「現金払い」がすでにチェックされている状態) -->
						<input type="radio" name="pay" value="1" checked="checked">現金払い
						<input type="radio" name="pay" value="2">クレジットカード
				</td>
			</tr>
			<tr>
				<td><s:submit value="購入"/></td> <!-- ＝<input type="submit" name="購入"> -->
			</tr>
		</table>
	</s:form>

	<!--s:propertyタグとは ==============================================================================

		Struts2便利機能の一つ

			～JSPファイルでフィールドの中身を取得(表示)させたいものがある場合～

				JSP側にs:property～/を記載することで、
				Actionクラスファイル内のgetterメソッドを経由しフィールドの中身を引っ張り出してくれる

 											→Actionクラスにgetterメソッドを必ず設定すること

    ================================================================================================== -->

		<span>前画面に戻る場合は</span>

	<!-- HomeActionクラスへ ================================================  -->
		<a href='<s:url action="HomeAction"/>'>こちら</a>
	<!-- a href = リンクの挿入（「こちら」をクリックするとリンクからActionクラスを呼び出す -->

	</div>

	<div id="footer">
	</div>

</body>
</html>
