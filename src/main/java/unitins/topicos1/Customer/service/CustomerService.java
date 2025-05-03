package unitins.topicos1.Customer.service;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;
import unitins.topicos1.Customer.dto.CustomerAddAddressDTO;
import unitins.topicos1.Customer.dto.CustomerAllAddressResponseDTO;
import unitins.topicos1.Customer.dto.CustomerDTO;
import unitins.topicos1.Customer.dto.CustomerPasswordUpdateDTO;
import unitins.topicos1.Customer.dto.CustomerResponseDTO;
import unitins.topicos1.Customer.dto.CustomerUpdateDTO;
import unitins.topicos1.Customer.dto.CustomerUsernameUpdateDTO;
import unitins.topicos1.User.dto.UserResponseDTO;

public interface CustomerService {

    public CustomerResponseDTO create(@Valid CustomerDTO dto);

    public void update(Long id, CustomerUpdateDTO dto) throws ValidationException;

    public void updateUserPassword(CustomerPasswordUpdateDTO passwordUpdateDTO);

    public void updateUserUsername(CustomerUsernameUpdateDTO usernameUpdateDTO);

    public boolean delete(Long id);

    public List<CustomerResponseDTO> findAll();

    public CustomerResponseDTO findById(Long id);

    public List<CustomerResponseDTO> findByName(String name);

    public CustomerResponseDTO findByUsername(String username);

    public CustomerResponseDTO findByCpf(String cpf);
    
    public UserResponseDTO login (String username, String HashPassword);

    void addAddress(Long id, CustomerAddAddressDTO dto);

    public CustomerAllAddressResponseDTO findAllAddressesById(Long id);

    public CustomerResponseDTO getMyAccount();

    public void updateMyAccount(CustomerUpdateDTO dto);
    


    
}
