
-- DB(データベース)=SQL

set names utf8; -- 文字コード名
set foreign_key_checks = 0;   -- 外部キー制約を無効 (0)にする設定 ⇔（1）有効に戻す

    -- →作成する３つのテーブルには重複するカラムがあるが、どのデータの値も制限されなくなる

 -- ◆外部キーとは------------------------------------------------------------------------------------------------------
     -- 設定したテーブルに登録できるデータを、他テーブルで指定したカラム内のデータ以外は登録できないように制約

          -- ( 子テーブルのカラム「a」には親テーブルの同名カラム「a」に格納されているデータ値しか格納できない )
 -- --------------------------------------------------------------------------------------------------------------------

-- データベース作成
	drop database if exists ecsite;
	create database if not exists ecsite; -- ecsiteデータベース
	use ecsite;

-- (1)login_user_transactionテーブル-----------------------------------------------------------------------------------------------

	drop table if exists login_user_transaction;
	create table login_user_transaction(

	-- ①「id」カラム
		id int not null primary key auto_increment,
	    -- not null :必須入力(null値は禁止)
	    -- primary key :主キー (1つのテーブルに1つ) →①重複禁止＋②null登録できなくなる）
	    -- auto_increment :自動連番(整数:1,2,3～)

	-- ②「login_id」カラム
		login_id varchar(16) unique,
	    -- unique :ユニークキー（1つのテーブルにおける回数制限なし） →①重複禁止＋②null登録はできる＋③indexが自動設定
	    -- index :索引付与 =目的のデータを抽出する際、索引を使って検索を効率化

	-- ③「login_pass」カラム
 		login_pass varchar(16), -- varchar型(最大16文字まで)

	-- ④「user_name」カラム
		user_name varchar(50),  -- varchar型(最大50文字まで)

	-- ⑤「insert_date」カラム
		insert_date datetime,  -- datetime型 :日付型（時間単位）2018-12-01 12:01:00形式

	-- ⑥「updated_date」カラム
		updated_date datetime
	);

-- (2)item_info_transactionテーブル-----------------------------------------------------------------------------------------------

	 drop table if exists item_info_transaction;
	 create table item_info_transaction(

	-- ①「id」カラム
		id int not null primary key auto_increment,

	-- ②「item_name」カラム
		item_name varchar(30),  -- varchar型(最大30文字まで)

	-- ③「item_price」カラム
		item_price int,

	-- ④「item_stock」カラム
		item_stock int,

	-- ⑤「insert_date」カラム
		insert_date datetime,

	-- ⑥「updated_date」カラム
		update_date datetime

	 );

 -- (3)user_buy_item_transactionテーブル-----------------------------------------------------------------------------------------------

 	drop table if exists user_buy_item_transaction;
 	create table user_buy_item_transaction(

	-- ①「id」カラム
  		id int not null primary key auto_increment,

	-- ②「item_transaction_id」カラム
 		item_transaction_id int,

	-- ③「total_price」カラム
		total_price int,

	-- ④「total_count」カラム
		total_count int,

	-- ⑤「user_master_id」カラム
		user_master_id varchar(16),

	-- ⑥「pay」カラム
		pay varchar(30),

	-- ⑦「insert_date」カラム
		insert_date datetime,

	-- ⑧「delete_date」カラム
		delete_date datetime

 	);

	INSERT INTO item_info_transaction(item_name,item_price,item_stock) VALUES ("ノートBook",100,50);
   -- (2)item_info_transaction_transactionテーブルにinsert
                                                   -- ②「item_name」=ノートBook, ③「item_price」=100, ④「item_stock」=50

	INSERT INTO login_user_transaction(login_id,login_pass,user_name) VALUES  ("internous","internous01","test");
   -- (1)login_user_transactionテーブルにinsert
                                        -- ②「login_id」=internous, ③「login_pass」=internous01, ④「user_name」=test
