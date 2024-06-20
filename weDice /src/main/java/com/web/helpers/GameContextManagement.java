package com.web.helpers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.bo.User;

import jakarta.servlet.ServletContext;

public class GameContextManagement  {

	private ServletContext conext;

	private static GameContextManagement instance;

	private GameContextManagement(ServletContext conext) {
		this.conext = conext;
	}

	synchronized public static final GameContextManagement getInstance(ServletContext conext) {
		if (instance == null) {
			instance = new GameContextManagement(conext);
		}
		return instance;
	}

	public List<User> getAllUsers() {
		return (List<User>) conext.getAttribute("users");

	}

	public void updateScore(User user) {
	    List<User> users = getAllUsers();
	    for (User it : users) {
	        if (it.getLogin().equals(user.getLogin())) {
	            it.setBestScore(user.getBestScore());
	            it.setScore(user.getScore());
	            it.setCompteurLancer(user.getCompteurLancer());

	        }
	    }
	}

	public void insertUser(User user) {
		List<User> userList = (List<User>) conext.getAttribute("users");

		userList.add(user);

	}

	public User getUserByLogin(String login) {
		List<User> users = getAllUsers();
		for (User it : users) {
			System.out.println(it);
			if (it.getLogin().equals(login)) {
				return it;
			}
		}

		return null;
	}
    public List<User> getUsersWithBestScore() {
        List<User> users = getAllUsers();
        Collections.sort(users, Comparator.comparingDouble(User::getBestScore).reversed());
        return users;

}
}
