package com.example.servicemodule.s.services.person;

import com.example.commonmodule.exception.LocalException;
import com.example.datamodule.dto.person.PersonInfoDTO;
import com.example.datamodule.dto.person.RegistrationUserDTO;
import com.example.datamodule.models.person.Person;
import com.example.datamodule.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;
    private final RoleService roleService;
    private final  PasswordEncoder passwordEncoder;

    /*@Autowired
    public PersonService(PersonRepository personRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;

    }*/
    public List<Person> getAll() {
        return this.personRepository.findAll();
    }
    public Person findById(long personId){
        return personRepository.findById(personId).orElseThrow(()-> new UsernameNotFoundException(("User with this id not found")));//todo
    }

    public Person findByPhoneNumber(String phoneNumber) {
        return personRepository.findByPhoneNumber(phoneNumber).orElseThrow(()-> new UsernameNotFoundException(("User with this id not found")));
    }
    public boolean isPersonAlive(String phoneNumber){
        return personRepository.findByPhoneNumber(phoneNumber).isPresent();
    }
    public PersonInfoDTO getPersonInfo(String phoneNumber){
        Person person= findByPhoneNumber(phoneNumber);
        return new PersonInfoDTO(person.getPhoneNumber(),person.getName(),person.getLastname());
    }
    public void updatePersonInfo(PersonInfoDTO personInfoDTO,String phone){
        //TODO  если сменить номер, то jwt - недействителен?
        Person person = findByPhoneNumber(phone);
        /*if(!personInfoDTO.getPhoneNumber().equals(phone)){
            person.setPhoneNumber(personInfoDTO.getPhoneNumber());
        }*/
        person.setName(personInfoDTO.getName());
        person.setLastname(personInfoDTO.getLastname());
        personRepository.save(person);
        //todo Password
    }


    public  Optional<List<Person>> findFollowersById(Long id) {
        return null;
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Person person = findByPhoneNumber(phoneNumber);
        return new User(
                person.getPhoneNumber(),
                person.getPassword(),
                person.getRoles().stream().map(role ->  new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public Person createPerson( RegistrationUserDTO registrationUserDTO){
        //Проверку сделать
        //TODO modelMapper
        Person person = new Person();
        person.setPhoneNumber(registrationUserDTO.getPhoneNumber());
        person.setName(registrationUserDTO.getName());
        person.setLastname(registrationUserDTO.getLastname());
        person.setPassword(passwordEncoder.encode(registrationUserDTO.getPassword()));
        person.setRoles(List.of(roleService.getUserRole(),roleService.getAdminRole(),roleService.getManagerRole()));

        try {
            return savePerson(person);
        }
        catch (DataIntegrityViolationException e){
            throw new LocalException(HttpStatus.CONFLICT,"Такой email уже зарегистрирован");
        }

    }
    public Person savePerson(Person person) {
       return personRepository.save(person);
    }


}

