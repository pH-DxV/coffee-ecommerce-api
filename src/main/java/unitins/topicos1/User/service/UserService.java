package unitins.topicos1.User.service;

import unitins.topicos1.User.model.User;

public interface UserService {

    public User findByUsername(String username);

    public void update (User user);
    
}
