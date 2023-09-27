try{
				if(rs!=null){
					rs.close();
					rs=null;
				}
				if(Prest!=null){
					Prest.close();
					Prest=null;
				}

			}catch(Exception e){
			e.printStackTrace();
			}




			con=DBConnection.getInstance();

			import Common.DBConnection;

			synchronized