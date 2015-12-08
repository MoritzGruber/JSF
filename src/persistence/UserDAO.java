package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Utils;
import dto.User;

public class UserDAO {
	static private UserDAO instance;
	private Map<Integer, User> data = new HashMap<Integer, User>();
	private String userFile;
	
	private UserDAO() {
		userFile = Utils.getPersistencePath("users.ser");
	}
	
	static public UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}
		
	@SuppressWarnings("unchecked")
	public void load() {
		data = (Map<Integer, User>) Utils.loadFromFile(userFile);
		if (data == null) {
			data = new HashMap<Integer, User>();
			User admin = new User(1, "Admin", "admin", "nowhere", "-", "-", "-");
			admin.setAdmin(true);
			data.put(admin.getUserId(), admin);
		}
	}
	
	public void persist() {
		Utils.storeToFile(userFile, data);
	}
	
	synchronized public void create(User user) throws Exception {
		load();
		if (data.get(user.getUserId()) != null) {
			throw new Exception("User with id " + 
					user.getUserId() + " already exists!");
		}
		data.put(user.getUserId(), user);
		persist();
	}
	
	synchronized public void delete(User user) throws Exception {
		load();
		if (data.get(user.getUserId()) == null) {
			throw new Exception("User with id " + 
					user.getUserId() + " does not exist!");
		}
		data.remove(user.getUserId());
		persist();
	}
	
	synchronized public List<User> findAll() {
		load();
		List<User> result = new ArrayList<User>();
		
		for (Map.Entry<Integer, User> entry : data.entrySet()) {
			result.add(entry.getValue());
		}
		
		return result;
	}
	
	synchronized public User findForId(int id) {
		load();
		return data.get(id);
	}
	
	synchronized public void updateAllAndPersist(List<User> users) {
		load();
		for (User user : users) {
			data.put(user.getUserId(), user);
		}
		persist();
	}

	synchronized public void updateAndPersist(User user) {
		load();
		data.put(user.getUserId(), user);
		persist();
	}

	synchronized public int getMaxUserId() {
		int max = 0;
		
		load();
		for (User user : findAll()) {
			if (user.getUserId() > max) 
				max = user.getUserId();
		}
		
		return max;
	}
	
}
