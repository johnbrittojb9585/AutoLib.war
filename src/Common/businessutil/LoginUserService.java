package Common.businessutil;

import java.util.List;
import java.util.Map;

import Login.User;

public interface LoginUserService {
	
	
	public User getUser(String userId,String pwd);
	
	/**
	 * Gets TimeDateS
	 * 
	 * @param userId
	 *            the user id
	 * @return list
	 * 
	 */
	public List getTimeDate(String userId);

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

	/**
	 * Init UpdateChangePassWord
	 * 
	 * @param user
	 *            the user
	 * @throws CampusBaseException
	 */
	
	public Map loadHomeEvent(String mcode);
	
	public Map loadUserTransactionHome(String Code);
	
	public byte[] getUserImage(byte[] userPhoto,String mcode);
	
}
