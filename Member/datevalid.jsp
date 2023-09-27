if((bir).equals(enr)){
		 	 birth=getdate(Member_savebdate);
			 enroll=getdate(Member_saveedate);
			 expiry=getdate(Member_saveexdate);

			 ob.setCode(Member_savecode);
			 ob.setName(Member_savename);
			 ob.setBdate(birth);
			 ob.setEdate(enroll);
			 ob.setExdate(expiry);
			 ob.setDamount(Member_savedamount);
			 ob.setDecode(Member_savedcode);
			 ob.setSex(Member_savesex);
			 ob.setMadd1(Member_savemadd1);
			 ob.setMadd2(Member_savemadd2);
			 ob.setMcity(Member_savemcity);
			 ob.setMstate(Member_savemstate);
			 ob.setMpincode(Member_savempin);
			 ob.setMphone(Member_savemphone);
			 ob.setMemail(Member_savememail);
            		 ob.setDeptcode(Member_savedecode);
			 ob.setCoursecode(Member_savecoursecode);
			 ob.setGroupcode(Member_savegroupcode);
			 ob.setRemarks(Member_saverem);
			 ob.setProfile(Member_savepro);
			 ob.setPhoto(Member_savephoto);
			 ob.setCyear(Member_savecyear);
		getServletConfig().getServletContext().getRequestDispatcher("/Member/index.jsp?check=birth").forward(request, response);

		}
		else if((enr).equals(exp)){
		 	 birth=getdate(Member_savebdate);
			 enroll=getdate(Member_saveedate);
			 expiry=getdate(Member_saveexdate);

			 ob.setCode(Member_savecode);
			 ob.setName(Member_savename);
			 ob.setBdate(birth);
			 ob.setEdate(enroll);
			 ob.setExdate(expiry);
			 ob.setDamount(Member_savedamount);
			 ob.setDecode(Member_savedcode);
			 ob.setSex(Member_savesex);
			 ob.setMadd1(Member_savemadd1);
			 ob.setMadd2(Member_savemadd2);
			 ob.setMcity(Member_savemcity);
			 ob.setMstate(Member_savemstate);
			 ob.setMpincode(Member_savempin);
			 ob.setMphone(Member_savemphone);
			 ob.setMemail(Member_savememail);
            		 ob.setDeptcode(Member_savedecode);
			 ob.setCoursecode(Member_savecoursecode);
			 ob.setGroupcode(Member_savegroupcode);
			 ob.setRemarks(Member_saverem);
			 ob.setProfile(Member_savepro);
			 ob.setPhoto(Member_savephoto);
			 ob.setCyear(Member_savecyear);
		//getServletConfig().getServletContext().getRequestDispatcher("/Member/index.jsp?check=validity").forward(request, response);

		}