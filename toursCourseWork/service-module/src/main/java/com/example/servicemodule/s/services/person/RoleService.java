package com.example.servicemodule.s.services.person;


import com.example.datamodule.models.person.Role;
import com.example.datamodule.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role findRoleByName(String name){
        return roleRepository.findByName(name).get();
    }
    public Role getUserRole() {//TODO
       /* Role role = new Role();
        role.setName("ROLE_USER");
        roleRepository.save(role);
        Role role1 = new Role();
        role1.setName("ROLE_ADMIN");
        roleRepository.save(role1);
         Role role1 = new Role();
        role1.setName("ROLE_MANAGER");
        roleRepository.save(role1);*/
        return roleRepository.findByName("ROLE_USER").get();
    }
    public Role getManagerRole() {//TODO

        return roleRepository.findByName("ROLE_MANAGER").get();
    }
    public Role getAdminRole() {//TODO

        return roleRepository.findByName("ROLE_ADMIN").get();
    }
}