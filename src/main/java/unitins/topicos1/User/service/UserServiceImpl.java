package unitins.topicos1.User.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import unitins.topicos1.User.model.User;

@ApplicationScoped
public class UserServiceImpl implements UserService{
    
    @Inject
    EntityManager em;

    @Override
    public User findByUsername(String username){

        try{

            return em
                    .createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();

        } catch (Exception e){

            return null;

        }

    }

    @Override
    @Transactional
    public void update (User user){

        em.merge(user);

    }

}
