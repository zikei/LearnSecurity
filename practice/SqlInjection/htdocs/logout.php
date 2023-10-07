<?php
session_start();
$_SESSION = array();
if (isset($_COOKIE[session_name()])) {
    setcookie(session_name(), "", time() - 42000, "/");
}
session_destroy();
?>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>掲示板　ログアウト</title>
</head>

<body>
    <h1>ログアウト</h1>
    <p>ログアウトしました</p>
    <p><a href="./login.php">ログインページに戻る</a></p>
</body>

</html>