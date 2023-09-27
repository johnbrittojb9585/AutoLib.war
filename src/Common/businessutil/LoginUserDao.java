package Common.businessutil;

import java.util.List;
import java.util.Map;

import Lib.Auto.Author.AuthorBean;
import Login.User;

public interface LoginUserDao {
	public User findById(String userId,String pwd);

	
	/**
	 * Find TimeDate
	 * 
	 * @param userId
	 *            user id.
	 * @return the List
	 */
	public List findTimeDate(String userId);

	/**
	 * Save TimeDate
	 * 
	 * @param userId
	 *            the user id
	 */
	public void saveTimeDate(String userId);

	/**
	 * Update TimeDate
	 * 
	 * @param userId
	 *            the user id
	 * 
	 */
	public void updateTimeDate(String userId);
		
	public Map loadHomeEvent(String mcode);
	
	public Map loadUserTransactionHome(String code);
	
	public byte[] findUserImage(byte[] userImage,String mcode);
}
