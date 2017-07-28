/*
 * Description: Service Interface that will provide all the methods needed for any Service implementations 
 */

package com.revature.battleship.service;

import com.revature.battleship.pojos.Player;

public interface Service {
	Player login(String usernameEntered, String passwordEntered);
}
