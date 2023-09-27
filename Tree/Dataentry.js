// You can find instructions for this file here:
// http://www.treeview.net

// Decide if the names are links or just the icons
USETEXTLINKS = 1  //replace 0 with 1 for hyperlinks

// Decide if the tree is to start all open or just showing the root folders
STARTALLOPEN = 0 //replace 0 with 1 to show the whole tree

ICONPATH = '' //change if the gif's folder is a subfolder, for example: 'images/'


foldersTree = gFld("<i>Autolib Software Systems</i>", "")
  aux1 = insFld(foldersTree, gFld("Cataloging", "javascript:parent.op()"))
  	  insDoc(aux1, gLnk("R", "Author", "http://localhost:8080/AutoLib/Author"))
	  insDoc(aux1, gLnk("R", "Subject", "http://localhost:8080/AutoLib/Subject"))
          insDoc(aux1, gLnk("R", "Department", "http://localhost:8080/AutoLib/Department"))
	  insDoc(aux1, gLnk("R", "Member", "http://localhost:8080/AutoLib/Member"))
          insDoc(aux1, gLnk("R", "Book", "http://localhost:8080/AutoLib/Book"))
	  insDoc(aux1, gLnk("R", "Course", "http://localhost:8080/AutoLib/Course"))
          insDoc(aux1, gLnk("R", "Designation", "http://localhost:8080/AutoLib/Desig"))
	  insDoc(aux1, gLnk("R", "Binding", "http://localhost:8080/AutoLib/Binding"))
	  insDoc(aux1, gLnk("R", "Publisher/supplier", "http://localhost:8080/AutoLib/PubSup"))
          insDoc(aux1, gLnk("R", "Branch", "http://localhost:8080/AutoLib/Branch"))
	  insDoc(aux1, gLnk("R", "Currency", "http://localhost:8080/AutoLib/Currency"))

  /* aux1 = insFld(foldersTree, gFld("Circulation", "javascript:parent.op()"))
  	  insDoc(aux1, gLnk("R", "Counter", "http://localhost:8080/AutoLib/Counter"))
 
  aux1 = insFld(foldersTree, gFld("Serial Control", "javascript:parent.op()"))
          insDoc(aux1, gLnk("R", "Journal Master", "http://localhost:8080/AutoLib/Journal"))
     // insDoc(aux1, gLnk("B", "Journal Issues", "http://localhost:8080/AutoLib/Journal_Issues"))
	  insDoc(aux1, gLnk("R", "Journal Issues", "http://localhost:8080/AutoLib/Journal_Issues"))*/

aux1 = insFld(foldersTree, gFld("Reports", "javascript:parent.op()"))
          insDoc(aux1, gLnk("R", "Counter Report", "http://localhost:8080/AutoLib/CounterReport/ReportIndex"))
    	  insDoc(aux1, gLnk("R", "AccessionRegister", "http://localhost:8080/AutoLib/AccessionRegister"))
	  insDoc(aux1, gLnk("R", "Statistics Report", "http://localhost:8080/AutoLib/Statistics"))
	  insDoc(aux1, gLnk("R", "Member", "http://localhost:8080/AutoLib/MemberReport"))
	  insDoc(aux1, gLnk("R", "Journal List", "http://localhost:8080/AutoLib/JournalList"))
	  insDoc(aux1, gLnk("R", "Journal Issues", "http://localhost:8080/AutoLib/JnlIssueReport"))
	  insDoc(aux1, gLnk("R", "Frequently Used Resource", "http://localhost:8080/AutoLib/FrequentlyUsedResource/FreqUsedIndex"))/*

  aux1 = insFld(foldersTree, gFld("Acquisition", "javascript:parent.op()"))
          insDoc(aux1, gLnk("R", "Order Invoice Process", "http://localhost:8080/AutoLib/OrdInvProcessing"))

  aux1 = insFld(foldersTree, gFld("Admin", "javascript:parent.op()"))
          insDoc(aux1, gLnk("R", "Fine", "http://localhost:8080/AutoLib/Fine"))
    	  insDoc(aux1, gLnk("R", "Holiday", "http://localhost:8080/AutoLib/Holiday"))
	  insDoc(aux1, gLnk("R", "Budget", "http://localhost:8080/AutoLib/Budget"))
	  insDoc(aux1, gLnk("R", "Group", "http://localhost:8080/AutoLib/Group"))
	  insDoc(aux1, gLnk("R", "Login", "http://localhost:8080/AutoLib/Login/index.jsp"))
	  insDoc(aux1, gLnk("R", "Stock Verification", "http://localhost:8080/AutoLib/Stock"))*/

  aux1 = insFld(foldersTree, gFld("Book Search", "javascript:parent.op()"))
          insDoc(aux1, gLnk("R", "Simple Search", "http://localhost:8080/AutoLib/Simples"))
    	  insDoc(aux1, gLnk("R", "Advanced Search", "http://localhost:8080/AutoLib/Advanced"))
	  //insDoc(aux1, gLnk("B", "Basic Search", "http://localhost:8080/AutoLib/Browse"))
	//  insDoc(aux1, gLnk("B", "Browse", "http://localhost:8080/AutoLib/Browse"))

	   aux1 = insFld(foldersTree, gFld("Logout", "javascript:parent.cl()"))






 /* aux1 = insFld(foldersTree, gFld("Other icons", "javascript:parent.op()"))
  aux1.iconSrc = ICONPATH + "diffFolder.gif"
  aux1.iconSrcClosed = ICONPATH + "diffFolder.gif"
    docAux = insDoc(aux1, gLnk("B", "D/L Treeview", "http://www.treeview.net/treemenu/download.asp"))
    docAux.iconSrc = ICONPATH + "diffDoc.gif"


  aux1 = insFld(foldersTree, gFld("<font color=red>F</font><font color=blue>o</font><font color=pink>r</font><font color=green>m</font><font color=red>a</font><font color=blue>t</font><font color=brown>s</font>", "javascript:parent.op()"))
    docAux = insDoc(aux1, gLnk("R", "<div class=specialClass>CSS Class</div>", "http://www.treeview.net/treemenu/demopics/beenthere_newyork.jpg"))*/

