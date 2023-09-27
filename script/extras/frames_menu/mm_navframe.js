/*
   Milonic DHTML Menu Frames Based Navigation Module  mm_navframe.js version 1.9 - September 29 2007
   Written by Kevin Clements
   This module is only compatible with the Milonic DHTML Menu version 5.62 or higher

   Copyright 2007 (c) Milonic Solutions Limited. All Rights Reserved.
   This is a commercial software product, please visit http://www.milonic.com/ for more information.
   
   This code should be loaded into the "nav" or "navigation" frame, where the Main Menu will be displayed.
*/

var mm_si;
var mm_tf;

function openSubmenu(){
	mm_si = _itemRef;
	if(_mi[mm_si][34]=='disabled')return;
	mm_tf = parent.frames[_mi[mm_si][35]];
	var subTop, subLeft;
	var selectedPos = $D($c("el" + mm_si));
	var mNum=mm_tf.$h(_mi[mm_si][3])
	var menuObj = mm_tf.$c("menu" + mNum);
	if(menuObj){
		subTop=selectedPos[0]+_subOffsetTop+mm_tf._sT;
		subLeft=selectedPos[1]+_subOffsetLeft+mm_tf._sL;
  		var Fm=mm_tf._m[mNum];
		if(Fm[2])subTop+=$x(Fm[2]);
  		if(Fm[3])subLeft+=$x(Fm[3]);
		_ofMT=0;
		if(ns7&&mm_tf.fixMozillaZIndex){
			subTop-=mm_tf._sT;
			subLeft-=mm_tf._sL;
		}
		mm_tf.popup(_mi[mm_si][3],2,subTop,subLeft);
	}
}

function closeSubmenu(){
	if(mm_tf&&mm_tf._itemRef==-1)mm_tf.popdown();
}

