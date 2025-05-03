package unitins.topicos1.Customer.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;
import unitins.topicos1.Address.dto.AddressDTO;
import unitins.topicos1.Address.model.Address;
import unitins.topicos1.Customer.dto.CustomerAddAddressDTO;
import unitins.topicos1.Customer.dto.CustomerAllAddressResponseDTO;
import unitins.topicos1.Customer.dto.CustomerDTO;
import unitins.topicos1.Customer.dto.CustomerPasswordUpdateDTO;
import unitins.topicos1.Customer.dto.CustomerResponseDTO;
import unitins.topicos1.Customer.dto.CustomerUpdateDTO;
import unitins.topicos1.Customer.dto.CustomerUsernameUpdateDTO;
import unitins.topicos1.Customer.model.Customer;
import unitins.topicos1.Customer.repository.CustomerRepository;
import unitins.topicos1.Hash.service.HashService;
import unitins.topicos1.Telephone.model.Telephone;
import unitins.topicos1.User.dto.UserResponseDTO;
import unitins.topicos1.User.model.User;
import unitins.topicos1.User.repository.UserRepository;
import unitins.topicos1.User.service.UserService;

@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

    @Inject
    CustomerRepository repository;

    @Inject
    UserRepository userRepository;

    @Inject
    UserService userService;

    @Inject
    HashService hashService;

    @Inject
    JsonWebToken jwt;


    @Override
    @Transactional
    public CustomerResponseDTO create(@Valid CustomerDTO dto){

        // === USER ===
        User user = new User();

        user.setUsername(dto.username());
        user.setPassword(hashService.getHashPassword(dto.password()));
        user.setType("Customer");

        userRepository.persist(user);

        // === CUSTOMER ===
        Customer customer = new Customer(); 
        customer.setCpf(dto.cpf());
        customer.setName(dto.name());

        // === TELEPHONE ===
        Telephone telephone = new Telephone();
        telephone.setDdd(dto.ddd());
        telephone.setPhoneNumber(dto.phoneNumber());
        customer.setTelephone(telephone);

        customer.setDateOfBirth(dto.dateOfBirth());

        customer.setAddressList(new ArrayList<Address>());
        
        for(AddressDTO address : dto.addressList()){

            Address addr = new Address();

            addr.setCep(address.cep());
            addr.setStreet(address.street());
            addr.setComplement(address.complement());

            customer.getAddressList().add(addr);

        }

        customer.setUser(user);

        repository.persist(customer);

        return CustomerResponseDTO.valueOf(customer);

    }
    
    @Override
    @Transactional
    public boolean delete(Long id){

        return repository.deleteById(id);
            
    }

    
    @Override
    @Transactional
    public void update(Long id, CustomerUpdateDTO dto) throws ValidationException{

        Customer customer = repository.findById(id);

        if(customer != null){

            customer.setCpf(dto.cpf());
            customer.setName(dto.name());

            Telephone telephone = new Telephone();
            telephone.setDdd(dto.ddd());
            telephone.setPhoneNumber(dto.phoneNumber());
            customer.setTelephone(telephone);  

            customer.setDateOfBirth(dto.dateOfBirth());

        }

        else {

            throw new ValidationException("Non-Existent Customer");

        }

    }

    @Transactional
    @Override
    public void addAddress(Long id, CustomerAddAddressDTO dto){

        Customer customer = repository.findById(id);

        if (customer != null){

            List<Address> addresses = customer.getAddressList();

            Address addr = new Address();
            addr.setCep(dto.newAddress().cep());
            addr.setStreet(dto.newAddress().street());
            addr.setComplement(dto.newAddress().complement());

            addresses.add(addr);

        }
    }
    @Transactional
    @Override
    public List<CustomerResponseDTO> findAll(){

        return repository.findAll()
                            .stream()
                            .map(CustomerResponseDTO::valueOf)
                            .toList();

    }

    @Transactional
    @Override
    public CustomerAllAddressResponseDTO findAllAddressesById(Long id){

        Customer customer = repository.findById(id);

        return CustomerAllAddressResponseDTO.valueof(customer);

    }

    @Override
    public CustomerResponseDTO findById(Long id){

        Customer customer = repository.findById(id);

        if (customer != null){

            return CustomerResponseDTO.valueOf(repository.findById(id));

        }

        return null;

    }

    @Override
    public List<CustomerResponseDTO> findByName(String name){

        return repository.findByName(name)
                            .stream()
                            .map(CustomerResponseDTO::valueOf)
                            .toList();

    }

    @Override
    public CustomerResponseDTO findByUsername(String username){

        Customer customer = repository.findByUsername(username);

        if(customer != null){

            return CustomerResponseDTO.valueOf(customer);

        }

        return null;

    }
    
    @Override
    public CustomerResponseDTO findByCpf(String cpf){

        Customer customer = repository.findByCpf(cpf);

        if (customer != null){

            return CustomerResponseDTO.valueOf(customer);

        }

        return null;

    }

    @Override
    public UserResponseDTO login (String username, String password){

        Customer customer = repository.findByUsernameAndPassword(username, password);

        if (customer != null){

            return UserResponseDTO.valueOf(customer.getUser());

        }

        return null;

    }

    @Override
    @Transactional
    public void updateUserPassword(CustomerPasswordUpdateDTO passwordUpdateDTO){

        if (jwt.getClaim("userId") == null){

            throw new IllegalArgumentException("Invalid JWT Token or Claim absent.");

        }

        String claimValue = jwt.getClaim("userId").toString();

        if (claimValue == null){

            throw new IllegalArgumentException("JWT Token invalid or Claim absent");
        }

        User user = userRepository.findById(Long.valueOf(claimValue));
        Customer custotmer = repository.findById(user.getId());

        if (user == null || custotmer == null){

            throw new EntityNotFoundException("user not logged in");

        }

        if (user.getPassword().equals(hashService.getHashPassword(passwordUpdateDTO.oldPassword()))){

            user.setDateChange(LocalDateTime.now());
            user.setPassword(hashService.getHashPassword(passwordUpdateDTO.newPassword()));
            userService.update(user);

        }

    }

    @Override
    @Transactional
    public void updateUserUsername(CustomerUsernameUpdateDTO usernameUpdateDTO){

        User user = userRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));
        Customer customer = repository.findByIdUser(user.getId());

        if (user == null || customer == null){

            throw new EntityNotFoundException("user not logged in");

        }

        customer.getUser().setUsername(usernameUpdateDTO.newUsername());
        userService.update(customer.getUser());
        
        repository.persist(customer);

    }

    @Override
    @Transactional
    public CustomerResponseDTO getMyAccount(){

        Long userId = Long.valueOf(jwt.getClaim("userId").toString());

        Customer customer = repository.findByIdUser(userId);

        if (customer == null){

            throw new EntityNotFoundException("Customer not found.");

        }

        return CustomerResponseDTO.valueOf(customer);

    }

    @Override
    @Transactional
    public void updateMyAccount(CustomerUpdateDTO dto){

        // JWT Verification (Token with Claim)

        if (jwt.getClaim("userId") == null){

            throw new IllegalArgumentException("JWT Token invalid or Claim absent.");

        }

        Long userId = Long.valueOf(jwt.getClaim("userId").toString());
        Customer customer = repository.findByIdUser(userId);

        if (customer == null){

            throw new EntityNotFoundException("Customer not found.");

        }

        customer.setName(dto.name());
        customer.setCpf(dto.cpf());
        customer.setDateOfBirth(dto.dateOfBirth());

        Telephone telephone = new Telephone();
        telephone.setDdd(dto.ddd());
        telephone.setPhoneNumber(dto.phoneNumber());

        customer.setTelephone(telephone);

        repository.persist(customer);
    }

}

