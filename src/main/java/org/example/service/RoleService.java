package org.example.service;

import org.example.dto.CreateRoleReqDto;
import org.example.dto.DeleteRoleReqDto;
import org.example.exception.AlreadyExistException;
import org.example.exception.DataNotFoundException;
import org.example.model.Role;
import org.example.model.User;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Boolean createRole(CreateRoleReqDto createRoleReqDto) {
        Optional<Role> roleOpt = roleRepository.findByName(createRoleReqDto.getName());
        if (!roleOpt.isEmpty()){
            throw new AlreadyExistException("Role");
        }
        Role createdRole = new Role();
        createdRole.setName(createRoleReqDto.getName());
        createdRole.setIsStudent(createRoleReqDto.getIsStudent());
        createdRole = roleRepository.save(createdRole);
        return true;
    }

    @Transactional
    public Boolean deleteRole(DeleteRoleReqDto deleteRoleReqDto) {
        Optional<Role> roleOpt = roleRepository.findById(deleteRoleReqDto.getRoleId());
        if (roleOpt.isEmpty()){
            throw new DataNotFoundException("Role");
        }

        Role createdRole = roleOpt.get();
        createdRole.setIsDel(true);
        createdRole = roleRepository.save(createdRole);
        return true;
    }
}
