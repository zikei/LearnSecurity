# 概要
SQLインジェクションの脆弱性のあるやられ掲示板アプリです。
# 初期ユーザ
| ユーザー名 | パスワード |
| ---- | ---- |
| Admin | adminpass |
| user1 | user1pass |
# 起動方法
1. コマンドラインツールでプロジェクトファイルの中に移動
2. `docker compose up -d`を実行
3. http://localhost:8080 にアクセスする
# データベースについて
[phpMyAdmin](localhost:8081)
## DBユーザ
| ユーザー名 | パスワード | 説明 |
| ---- | ---- | ---- |
| root | root | rootユーザ |
| sqlInjection | sqlInjection | アプリ接続用ユーザ |
