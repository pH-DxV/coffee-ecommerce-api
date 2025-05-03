package unitins.topicos1.Employee.service;

import java.time.LocalDateTime;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import unitins.topicos1.Employee.dto.EmployeeDTO;
import unitins.topicos1.Employee.dto.EmployeePasswordUpdatetDTO;
import unitins.topicos1.Employee.dto.EmployeeResponseDTO;
import unitins.topicos1.Employee.dto.EmployeeUsernameUpdateDTO;
import unitins.topicos1.Employee.model.Employee;
import unitins.topicos1.Employee.repository.EmployeeRepository;
import unitins.topicos1.Hash.service.HashService;
import unitins.topicos1.Telephone.model.Telephone;
import unitins.topicos1.User.dto.UserResponseDTO;
import unitins.topicos1.User.model.User;
import unitins.topicos1.User.repository.UserRepository;
import unitins.topicos1.User.service.UserService;

@ApplicationScoped
public class EmployeeServiceImpl implements EmployeeService {
    
    @Inject
    EmployeeRepository repository;

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
    public EmployeeResponseDTO create(@Valid EmployeeDTO dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(hashService.getHashPassword(dto.password()));
        user.setType("Employee");
        userRepository.persist(user);    

        Employee employee = new Employee();
        employee.setAdmissionCode(dto.admissionCode());
        employee.setCpf(dto.cpf());
        employee.setName(dto.name());
        
        Telephone telephone = new Telephone();
        telephone.setDdd(dto.ddd());
        telephone.setPhoneNumber(dto.phoneNumber());
        employee.setTelephone(telephone);        
        
        employee.setDateOfBirth(dto.dateOfBirth());
        employee.setAdmissionDate(dto.admissionDate());
        employee.setUser(user);

        repository.persist(employee);

        return EmployeeResponseDTO.valueof(employee);

    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, EmployeeDTO dto) throws ValidationException {

        User user = repository.findById(id).getUser();

        if(user != null) {

            user.setUsername(dto.username());
            // user.setPassword(hashService.getHashPassword(dto.password()));

        } else {

            throw new ValidationException("Employee not found");

        }

        Employee employee = repository.findById(id);

        if(employee != null) {

            employee.setAdmissionCode(dto.admissionCode());
            employee.setCpf(dto.cpf());
            employee.setName(dto.name());

            Telephone telephone = new Telephone();
            telephone.setDdd(dto.ddd());
            telephone.setPhoneNumber(dto.phoneNumber());
            
            employee.setTelephone(telephone);        
            employee.setDateOfBirth(dto.dateOfBirth());
            employee.setAdmissionDate(dto.admissionDate());
            employee.setUser(user);

        } else {

            throw new ValidationException("Employee not found");

        }
    }

    @Override
    @Transactional
    public void updateUserPassword(EmployeePasswordUpdatetDTO passwordUpdateDTO) {

        User user = userRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));

        Employee employee = repository.findByIdUser(user.getId());
        
        if (user == null || employee == null) {

            throw new InternalError("User or employee not found");

        }

        if(user.getPassword().equals(hashService.getHashPassword(passwordUpdateDTO.oldPassword()))) {

            user.setDateChange(LocalDateTime.now());
            user.setPassword(hashService.getHashPassword(passwordUpdateDTO.newPassword()));

            userService.update(user);

        }
    }

    @Override
    @Transactional
    public void updateUserUsername(EmployeeUsernameUpdateDTO usernameUpdateDTO) {

        User user = userRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));

        Employee employee = repository.findByIdUser(user.getId());
        
        if (user == null || employee == null) {

            throw new InternalError("User or employee not found");

        }
        
        employee.getUser().setUsername(usernameUpdateDTO.newUsername());
        
        userService.update(employee.getUser());

        repository.persist(employee);

    }

    @Override
    public List<EmployeeResponseDTO> findAll()
    {

        return repository.findAll()
                            .stream()
                            .map(EmployeeResponseDTO::valueof)
                            .toList();


    }

    @Override
    public EmployeeResponseDTO findById(Long id) {

        Employee employee = repository.findById(id);

        return EmployeeResponseDTO.valueof(repository.findById(id));

    }

    @Override
    public List<EmployeeResponseDTO> findByName(String name) {

        return repository.findByName(name)
                            .stream()
                            .map(EmployeeResponseDTO::valueof)
                            .toList();

    }

    @Override
    public EmployeeResponseDTO findByUsername(String username) {
        
        Employee employee = repository.findByUsername(username);

        return employee != null ? EmployeeResponseDTO.valueof(employee) : null;

    }

    @Override
    public EmployeeResponseDTO findByCpf(String cpf) {

        Employee employee = repository.findByCpf(cpf);

        return employee != null ? EmployeeResponseDTO.valueof(employee) : null;

    }

    @Override
    public UserResponseDTO login(String username, String password) {

        Employee employee = repository.findByUsernameAndPassword(username, password);

        return employee != null ? UserResponseDTO.valueOf(employee.getUser()) : null;

    }
}