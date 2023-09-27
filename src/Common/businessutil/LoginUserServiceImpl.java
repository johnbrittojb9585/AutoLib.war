package Common.businessutil;

import java.util.List;
import java.util.Map;

import Lib.Auto.Author.AuthorBean;
import Login.User;

public class LoginUserServiceImpl implements LoginUserService {
	private LoginUserDao userDao;

	public LoginUserServiceImpl() {
	}
	
	public LoginUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(LoginUserDao userDao) {
		this.userDao = userDao;
	}
	
	public User getUser(String userId,String pwd) {
		return userDao.findById(userId,pwd);
	}

	
	/**
	 * Gets TimeDate
	 * 
	 * @param userId
	 *            the user id
	 * @return list
	 * 
	 */
	public List getTimeDate(String userId)
	{
		return userDao.findTimeDate(userId);
	}

	/**
	 * Save TimeDate
	 * 
	 * @param userId
	 *            the user id
	 */
	public void saveTimeDate(String userId)
	{
		userDao.saveTimeDate(userId);
	}

	/**
	 * Update TimeDate
	 * 
	 * @param userId
	 *            the user id
	 * 
	 */
	public void updateTimeDate(String userId)
	{
		userDao.updateTimeDate(userId);
	}
		
	public Map loadHomeEvent(String mcode)
	{
		return userDao.loadHomeEvent(mcode);
	}
	
	public Map loadUserTransactionHome(String code)
	{
		return userDao.loadUserTransactionHome(code);
	}
	
	
	public byte[] getUserImage(byte[] getUserImage,String mcode){
		
		return userDao.findUserImage(getUserImage,mcode);
		
	}
	
	
}
