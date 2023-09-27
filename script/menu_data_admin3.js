fixMozillaZIndex=true; //Fixes Z-Index problem  with Mozilla browsers but causes odd scrolling problem, toggle to see if it helps
_menuCloseDelay=500;
_menuOpenDelay=150;
_subOffsetTop=2;
_subOffsetLeft=-2;




with(menuStyle=new mm_style()){
bgimage="skyback.gif";
borderstyle="solid";
borderwidth=1;
fontfamily="Verdana, Tahoma, Arial";
fontsize="9pt";
fontstyle="normal";
fontweight="normal";
headerbgcolor="#ffffff";
headercolor="#000000";
onsubimage="black_7x7.gif";
padding=8;
subimage="white_7x7.gif";
subimagepadding=6;
}

with(submenuStyle=new mm_style()){
styleid=1;
bordercolor="#000000";
borderstyle="solid";
borderwidth=1;
fontfamily="Verdana, Tahoma, Arial";
fontsize="8pt";
fontstyle="bold";
headerbgcolor="#ffffff";
headercolor="#000000";
offbgcolor="#91a5fc";
offcolor="#000000";
onbgcolor="#91a5fc";
oncolor="#e8fbfa";
onsubimage="black_7x7.gif";
padding=5;
pagebgcolor="#82B6D7";
subimage="white_7x7.gif";
subimagepadding=5;
}

with(milonic=new menuname("Main Menu")){
alwaysvisible=1;
orientation="horizontal";
style=menuStyle;
aI("text=Home;url=http://localhost:8080/AutoLib;");
aI("showmenu=Cataloging;text=Cataloging;");
aI("showmenu=Circulation;text=Circulation;");
aI("showmenu=Serial Control;text=Serial Control;");
aI("showmenu=Reports;text=Reports;");
aI("showmenu=Acquisition;text=Acquisition;");
aI("showmenu=Book search;text=Book search;");

}

with(milonic=new menuname("Cataloging")){
orientation="horizontal";
style=menuStyle;
aI("text=Author;url=http://localhost:8080/AutoLib/Author;");
aI("text=Subject;url=http://localhost:8080/AutoLib/Subject;");
aI("text=Department;url=http://localhost:8080/AutoLib/Department;");
aI("text=Member;url=http://localhost:8080/AutoLib/Member;");
aI("text=Book;url=http://localhost:8080/AutoLib/Book;");
aI("text=Course;url=http://localhost:8080/AutoLib/Course;");
aI("text=Designation;url=http://localhost:8080/AutoLib/Desig;");
aI("text=Publisher/supplier;url=http://localhost:8080/AutoLib/PubSup;");
aI("text=Branch;url=http://localhost:8080/AutoLib/Branch;");
aI("text=Currency;url=http://localhost:8080/AutoLib/Currency;");
aI("text=Keywords;url=http://localhost:8080/AutoLib/Keywords;");
}

with(milonic=new menuname("Circulation")){
orientation="horizontal";
style=menuStyle;
aI("text=Counter;url=http://localhost:8080/AutoLib/Counter;");

}

with(milonic=new menuname("Serial Control")){
orientation="horizontal";
style=menuStyle;
aI("text=Journal Master;url=http://localhost:8080/AutoLib/Journal;");
aI("text=Journal Issues;url=http://localhost:8080/AutoLib/Journal_Issues;");
aI("text=Journal Articles;url=http://localhost:8080/AutoLib/Journal_Artical;");

}
with(milonic=new menuname("Reports")){
orientation="horizontal";
style=menuStyle;
aI("text=Counter Report;url=http://localhost:8080/AutoLib/CounterReport/ReportIndex;");


}
with(milonic=new menuname("Acquisition")){
orientation="horizontal";
style=menuStyle;
aI("text=Order Invoice Process;url=http://localhost:8080/AutoLib/OrdInvProcessing;");


}

with(milonic=new menuname("Book search")){
orientation="horizontal";
style=menuStyle;
aI("text=Simple Search;url=http://localhost:8080/AutoLib/Simples;");
aI("text=Advanced Search;url=http://localhost:8080/AutoLib/Advanced/Userdefinedreport.jsp;");


}
drawMenus();

