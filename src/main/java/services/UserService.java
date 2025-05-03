package services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.User;

import java.util.List;

@Stateless
@LocalBean
public class UserService {
    @PersistenceContext
    EntityManager em;

    public User getUserByData(String login, String mdp){
        List<User> user=em.createNamedQuery("User.findByData", User.class)
                .setParameter("login",login).setParameter("mdp",mdp).getResultList();
        if(user.isEmpty()){
            return null;
        }
       return user.get(0);
    }
}
