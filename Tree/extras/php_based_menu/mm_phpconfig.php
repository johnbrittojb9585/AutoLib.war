<?php

/*
   This is the config file for setting up database driven menus
   The settings here are pretty much self explanitory and should be straight forward
   for somebody who has a little knowldge of PHP and MySQL
*/



$pathToCodeFiles="../../";  // The www root to where the menu code files are located
// pathToCodeFiles is normally a directory from root, i.e. /menu/ for http://www.domain.com/menu/
// You can use relative or absolute paths


/// The following is only changed if the name of the menu code files have been changed.
$menuIncFiles=array();
$menuIncFiles[]="milonic_menu_code.js";

$menuVars=array();
$menuVars["menuCloseDelay"]=500;
$menuVars["menuOpenDelay"]=150;
$menuVars["subOffsetTop"]=0;
$menuVars["subOffsetLeft"]=0;



?>