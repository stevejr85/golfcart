<?php
require_once "debug.php";
require_once "interface.php";

function writeFile($cmd)
{
     $comFile = "data/comFile.txt";
     $file = fopen($comFile,'w') or die("can't make file");
     fwrite($file,$cmd);
     fclose($file);
}

function writeModeFile($cmd)
{
//     echo "in here";
     $comFile = "data/controlFile.txt";
     $file = fopen($comFile,'w') or die("can't make file");
     fwrite($file,$cmd);
     fclose($file);
}

function get_file($filename)
{
     $new_filename = "data/script.txt";
     if(!move_uploaded_file($_FILES['THE_SCRIPT']['tmp_name'], $new_filename))
     {
          echo "The file did not upload!";
     }
     else
     {
          echo $new_filename ." was uploaded";
     }
}

if(isset($_POST['mode']) && $_POST['mode']=="script")//change back to remote if not working
{
     writeFile("script");//change back to remote if not working
}
else  //this is for script
{
    // writeModeFile("script");
    writeFile("remote");
}

if (isset($_POST['speed']))
{
//     echo "we are supppsed to be here";
     writeFile("speed ". $_POST['speed']);
}
//print_array($_POST);
if (isset($_POST['clickme']))
{
     switch($_POST['clickme'])
     {
          case "fwd":
               writeFile("fwd");
          break;
          case "rev":
               writeFile("rev");
          break;
          case "left":
               writeFile("left");
          break;
          case "right":
               writeFile("right");
          break;
          case "go":
               writeFile("up");
          break;
          case "stop":
               writeFile("stop");
          break;
          case "center":
               writeFile("svt");
          break;
          case "refresh":
               writeFile("refresh");
          break;
          
          case "Upload":
		//print_array($_FILES);
		$new_filename = "data/script.txt";
		if(!move_uploaded_file($_FILES['THE_SCRIPT']['tmp_name'], $new_filename))
		{
			echo "The file did not upload!";
		}
		else
		{
	          echo $new_filename ." was uploaded";
		}


               //if(isset($_FILES) && $_FILES['THE_SCRIPT']['name'] != "" )
               //{
                    //get_file($_FILES['THE_SCRIPT']['name']);
	//	    $my_folder="data/";
	//	    copy($_FILES["THE_SCRIPT"]["name"], $my_folder.$_FILES["THE_SCRIPT"]["script.txt"]);
               //}
          break;
          default:
     }
}

begin_page();
display_form($_POST);
end_page();

?>
