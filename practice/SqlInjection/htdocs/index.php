<?php
session_start();
if (!isset($_SESSION["username"])) {
    session_regenerate_id(true);
    header("Location: ./login.php");
    exit;
}
ini_set('display_errors', 'On');
$db = "mysql:host=SqlInjection-Mysql; dbname=sqlInjection; charset=utf8mb4";
$username = "sqlInjection";
$password = "sqlInjection";

$pdo = new PDO($db, $username, $password);

if (isset($_POST["newcontent"])) {
    $stmt = $pdo->query("INSERT INTO bbs (content, updated_at, UserName)
                              VALUES ('" . $_POST["newcontent"] . "', NOW(), '" . $_SESSION["username"] . "');");
}

if (isset($_POST["delete_id"])) {
    $stmt = $pdo->query("DELETE FROM bbs WHERE id = " . $_POST["delete_id"] . ";");
}

$stmt = $pdo->query('SELECT * FROM bbs;');
?>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>掲示板</title>
</head>

<body>
    <h1>掲示板</h1>
    <?php
    //ini_set('display_errors', "On");
    ?>
    <p>
        ようこそ！　
        <span style="font-size:200%">
            <?php echo htmlspecialchars($_SESSION["username"], ENT_QUOTES, "UTF-8"); ?>
        </span>
        さん
    </p>
    <h2>投稿</h2>
    <form action="" method="POST">
        <p>
            投稿内容：<input type="text" name="newcontent">
            <input type="submit" value="送信">
        </p>
    </form>
    <h2>発言一覧</h2>
    <table class="bbs">
        <tr>
            <th>発言者</th>
            <th>日付</th>
            <th>投稿内容</th>
            <th>削除</th>
        </tr>
        <?php
        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        ?>
            <tr>
                <td><?php echo htmlspecialchars($row["UserName"], ENT_QUOTES, "UTF-8"); ?></td>
                <td><?php echo htmlspecialchars($row["updated_at"], ENT_QUOTES, "UTF-8"); ?></td>
                <td><?php echo htmlspecialchars($row["content"], ENT_QUOTES, "UTF-8"); ?></td>
                <td>
                    <form action="" method="POST">
                        <input type="hidden" name="delete_id" value=<?php echo $row["id"]; ?>>
                        <input type="submit" class="delete" value="削除">
                    </form>
                </td>
            </tr>
        <?php
        }
        ?>
    </table>
    <p><a href="./logout.php">ログアウトはこちら</a></p>
</body>

</html>