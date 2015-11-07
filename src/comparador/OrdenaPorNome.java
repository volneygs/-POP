package comparador;

import java.util.Comparator;

import users.User;

public class OrdenaPorNome implements Comparator<User> {
    @Override
    public int compare(User um, User dois) {
    	
        return um.getName().compareTo(dois.getName());
    }
}