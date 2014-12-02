<?php

function begin_page()
{
     echo <<<EOF
<!doctype html>
<html>
<head>
<title>testing</title>
EOF;
}

function display_form()
{

     echo <<<EOF
<script type="text/javascript">

          //submits the speed form
        function submitform()
        {
            //document.getElementById("range").innerHTML=newValue;
            controls.submit();
        }

        //submits the radio buttons
          function submitbuttons()
        {
            //document.getElementById("range").innerHTML=newValue;
            buttons.submit();
        }


</script>

</head>
<body >
<fieldset>
<legend>status</legend>
<p>the text goes here</p>
</fieldset>
<h3>Please select the mode before controlling the golf cart.</h3>
<form name="buttons" method="POST" action="index.php">
<table border="1">
<tr>
EOF;
     if(isset($_POST['mode']) && $_POST['mode'] == "remote")
          echo "<td><input type=\"radio\" name=\"mode\" value=\"remote\" checked onclick=\"submitbuttons();\">remote</td>";
     else
          echo "<td><input type=\"radio\" name=\"mode\" value=\"remote\" onclick=\"submitbuttons();\">remote</td>";
     echo "</tr>\n<tr>";
     if(isset($_POST['mode']) && $_POST['mode'] == "script")
          echo "<td><input type=\"radio\" name=\"mode\" value=\"script\" checked onclick=\"submitbuttons();\">script</td>";
     else
          echo "<td><input type=\"radio\" name=\"mode\" value=\"script\" onclick=\"submitbuttons();\">script</td>";
     echo "</tr>\n";
     echo "</table>\n";
     echo "</form>\n";
     
     
     echo "<form name=\"controls\" method=\"POST\" action=\"index.php\">     \n";
     echo "<table border=\"1\">\n";
     echo "<tr>";
     echo "<td><input type=\"submit\" name=\"clickme\" value=\"fwd\" onclick=\"submitform();\"></td>";

     
     //echo "</tr>";     

echo <<<EOF

<td><input type="submit" name="clickme" value="go"></td>
<td rowspan="2">
EOF;
     if(isset($_POST['speed']))
     {
          echo "<input type=\"range\" name=speed min=\"1000\" max=\"2000\" value=\"".$_POST['speed']."\" step=\"100\" orient=\"vertical\"   onchange=\"showValue(speed.value)\" />";
          echo "<span id=\"range\">".$_POST['speed']."</span>";
     }
     else
     {
          echo "<input type=\"range\" name=speed min=\"1000\" max=\"2000\" value=\"1000\" step=\"100\" orient=\"vertical\"   onchange=\"showValue(speed.value)\" />";
          echo "<span id=\"range\">1000</span>";
     }
     echo <<<EOF
        
        <script type="text/javascript">

        function showValue(newValue)
        {
            document.getElementById("range").innerHTML=newValue;
            controls.submit();
        }

        </script>
</td>
<td><input type="submit" name="clickme" value="left"></td>
<td><input type="submit" name="clickme" value="center"></td>
<td><input type="submit" name="clickme" value="right"></td>
</tr>
<tr>
<td>

EOF;

     echo "<input type=\"submit\" name=\"clickme\" value=\"rev\" onclick=\"submitform();\">";

echo <<<EOF

</td>
<td><input type="submit" name="clickme" value="stop"></td>
<td><input type="submit" name="clickme" value="refresh"></td>
</tr>
</table>
</form>
<form name="uploadfile" enctype="multipart/form-data" method="POST" action="index.php" >
<table >
<tr>
<td><input type="hidden" name="MAXFILESIZE" value="1000000">
<input name="THE_SCRIPT" type="file" size="50">
</td>
<td><input type="submit" name="clickme" value="Upload" ></td>
</tr>
</table>
</form>
EOF;
}

function end_page()
{
     echo <<<EOF
</body>
</html>
EOF;
}

?>
