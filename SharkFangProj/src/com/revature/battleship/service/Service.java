/*
 * Description: Service Interface that will provide all the methods needed for any Service implementations 
 */

package com.revature.battleship.service;

public interface Service {
	boolean login(String usernameEntered, String passwordEntered);
}
