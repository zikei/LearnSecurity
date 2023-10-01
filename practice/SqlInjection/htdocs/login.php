<?php
session_start();
if (isset($_SESSION["username"])) {
    session_regenerate_id(true);
    header("Location: ./index.php");
    exit;
}

if (isset($_POST["username"], $_POST["password"])) {
    $post = $_POST;
    $db = "mysql:host=SqlInjection-Mysql; dbname=sqlInjection; charset=utf8mb4";
    $username = "sqlInjection";
    $password = "sqlInjection";

    $pdo = new PDO($db, $username, $password);
    $stmt = $pdo->query("SELECT * FROM Account
      WHERE username='" . $_POST["username"] . "' AND password='" . $_POST["password"] . "';");
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
    if ($row) {
        session_regenerate_id(true);
        $_SESSION["userid"] = $row["UserID"];
        $_SESSION["username"] = $row["UserName"];
        header("Location: ./index.php");
        exit;
    } else {
        $msg = "ユーザ名またはパスワードが間違っています";
    }
}
?>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>掲示板</title>
</head>

<body>
    <h1>掲示板ログイン</h1>
    <?php
    //ini_set('display_errors', "On");
    if (isset($msg)) {
        echo $msg;
    }
    ?>
    <form action="" method="POST">
        <table class="login">
            <tr>
                <td>ユーザー名</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>パスワード</td>
                <td><input type="password" name="password"></td>
            </tr>
        </table>
        <div class="login">
            <input type="submit" class="login" value="ログイン">
        </div>
    </form>
    <p><a href="./registry.php">新規登録はこちら</a></p>
</body>

</html>