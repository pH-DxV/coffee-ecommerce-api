package unitins.topicos1.Employee.service;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import unitins.topicos1.Employee.dto.EmployeeDTO;
import unitins.topicos1.Employee.dto.EmployeePasswordUpdatetDTO;
import unitins.topicos1.Employee.dto.EmployeeResponseDTO;
import unitins.topicos1.Employee.dto.EmployeeUsernameUpdateDTO;
import unitins.topicos1.User.dto.UserResponseDTO;

public interface EmployeeService {

    public EmployeeResponseDTO create(@Valid EmployeeDTO dto);

    public void update(Long id, EmployeeDTO dto) throws ValidationException;

    public void updateUserPassword(EmployeePasswordUpdatetDTO passwordUpdateDTO);

    public void updateUserUsername(EmployeeUsernameUpdateDTO usernameUpdateDTO);

    public boolean delete(Long id);

    public List<EmployeeResponseDTO> findAll();

    public EmployeeResponseDTO findById(Long id);

    public List<EmployeeResponseDTO> findByName(String name);

    public EmployeeResponseDTO findByUsername(String username);

    public EmployeeResponseDTO findByCpf(String cpf);

    public UserResponseDTO login(String username, String hashPassword);


}