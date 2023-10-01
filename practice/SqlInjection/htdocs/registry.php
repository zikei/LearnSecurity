<?php
if (!empty($_POST["newuname"]) && !empty($_POST["password"]) && !empty($_POST["email"])) {
    if ($_POST["password"] == $_POST["passwordConfirm"]) {
        $db = "mysql:host=SqlInjection-Mysql; dbname=sqlInjection; charset=utf8mb4";
        $username = "sqlInjection";
        $password = "sqlInjection";

        $pdo = new PDO($db, $username, $password);

        $stmt = $pdo->query("SELECT * FROM Account WHERE UserName='" . $_POST["newuname"] . "';");
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        if (!$row) {
            $stmt = $pdo->query("INSERT INTO Account (UserName, Password, email) VALUES ("
                . $_POST["newuname"] . ", "
                . $_POST["password"] . ", "
                . $_POST["email"] . ");");

            header("Location: ./login.php");
            exit;
        } else {
            $msg = "そのユーザ名は使われています";
        }
    } else {
        $msg = "パスワードが一致しません";
    }
} else {
    $msg = "入力されていない項目があります";
}
?>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>掲示板　新規登録</title>
</head>

<body>
    <h1>掲示板 新規会員登録</h1>
    <?php
    ini_set('display_errors', "On");
    if (isset($msg)) {
        echo $msg;
    }
    ?>
    <form action="" method="POST">
        <table class="registry">
            <tr>
                <td>ユーザー名</td>
                <td><input type="text" name="newuname"></td>
            </tr>
            <tr>
                <td>パスワード</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>パスワード(確認用)</td>
                <td><input type="password" name="passwordConfirm"></td>
            </tr>
            <tr>
                <td>メールアドレス</td>
                <td><input type="email" name="email"></td>
            </tr>
        </table>
        <div class="registry">
            <input type="submit" class="registry" value="登録">
        </div>
    </form>
    <p><a href="./login.php">ログインページに戻る</a></p>
</body>

</html>