package unitins.topicos1.Coffee.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import unitins.topicos1.Coffee.repository.CoffeeRepository;

@ApplicationScoped
public class CoffeeServiceImpl implements CoffeeService {

    @Inject
    public CoffeeRepository repository;

    //@Inject
    //MarkRepository markRepository;

    //@Inject


    
}
