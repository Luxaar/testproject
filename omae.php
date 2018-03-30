<html>
<head>
<style>
body {
	background-color: grey;
	background-image: url("./grey.png");
	background-repeat: repeat-x;
	background-position: top;
	background-attachment: fixed;
}

h1 {
	//background-color: white;
	color: blue;
	text-align: center;
}

p {
	color: lightblue;
	font-family: calibri;
	font-size: 50px;
}
div {
	background-image: url("./grey.png");
	background-repeat: repeat-x;
	background-position: bottom;
	background-attachment: fixed;
}
</style>
<title>NAAAANNNNNIIIIIII</title>
</head>
<body>
<div>
</div>
<h1>
This is a good meme
<br>
<br>
<br>
</h1>

<p>
<?php
echo "NEW MODIF v.2";
$feed = "<strong> Omae wa mou shindeiru ! </strong>";
define("NANI", "<strong> NANI !? </strong>");
//echo('Hello World!');
function getName() {
global $feed;
//global NANI;
echo $feed.NANI;
}
getName();
//echo NANI;
$str = "42.2";
$int = 42;
$sum = $str + $int;
echo $sum;
echo "<br>";
$tableau = array($feed, " watashi wa ningen ", 4562);
echo $tableau[1];
$tableau[1] = "qfsdghgf";
echo $tableau[1];
echo "<br>";
$bim = array("Wow"=>"42", "BOI"=>"47", "BOOM"=>"24");
echo $bim["Wow"];
?>
</p>
</body>
</html>
