package org.example.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.dto.ActivateOrDeactivateUserReqDto;
import org.example.dto.CreateNonStudentUserReqDto;
import org.example.exception.AlreadyExistException;
import org.example.exception.DataNotFoundException;
import org.example.model.*;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NonStudentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserNonStudentRepository userNonStudentRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Boolean createNonStudent(CreateNonStudentUserReqDto createNonStudentUserReqDto) {
        Optional<User> userOpt = userRepository.findByUsername(createNonStudentUserReqDto.getUser().getUsername());
        if (!userOpt.isEmpty()){
            throw new AlreadyExistException("User");
        }
        String passwordHashed = DigestUtils.md5Hex(createNonStudentUserReqDto.getUser().getPassword());
        createNonStudentUserReqDto.getUser().setPassword(passwordHashed);
        User user = userRepository.saveAndFlush(createNonStudentUserReqDto.getUser());
        Optional<Role> roleOpt = Optional.ofNullable(roleRepository.findByIdAndIsStudent(createNonStudentUserReqDto.getRoleId(), false)
                .orElseThrow(() -> new DataNotFoundException("Role")));

        UserNonStudent userNonStudent = new UserNonStudent();
        userNonStudent.setUserId(user.getId());
        userNonStudent.setRoleId(createNonStudentUserReqDto.getRoleId());
        userNonStudent = userNonStudentRepository.saveAndFlush(userNonStudent);

        UserDetail createdUserDetail = new UserDetail();
        createdUserDetail.setAddress(createNonStudentUserReqDto.getUserDetail().getAddress());
        createdUserDetail.setCity(createNonStudentUserReqDto.getUserDetail().getCity());
        createdUserDetail.setEmail(createNonStudentUserReqDto.getUserDetail().getEmail());
        createdUserDetail.setProvince(createNonStudentUserReqDto.getUserDetail().getProvince());
        createdUserDetail.setUserId(user.getId());
        createdUserDetail.setFullname(createNonStudentUserReqDto.getUserDetail().getFullname());
        createdUserDetail = userDetailRepository.saveAndFlush(createdUserDetail);
        return true;
    }

    @Transactional
    public Boolean activateNonStudent(ActivateOrDeactivateUserReqDto activateOrDeactivateUserReqDto) {
        Optional <User> userOpt = Optional.ofNullable(userRepository.findById(activateOrDeactivateUserReqDto.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User")));

        Optional <UserNonStudent> userNonStudentOpt = Optional.ofNullable(userNonStudentRepository.findByUserId(activateOrDeactivateUserReqDto.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User Non Student")));

        User createdUser = userOpt.get();
        userRepository.activateOrDeactivateUser(false, createdUser.getId());
        return true;
    }

    @Transactional
    public Boolean deactivateNonStudent(ActivateOrDeactivateUserReqDto activateOrDeactivateUserReqDto) {
        Optional <User> userOpt = Optional.ofNullable(userRepository.findById(activateOrDeactivateUserReqDto.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User")));

        Optional <UserNonStudent> userNonStudentOpt = Optional.ofNullable(userNonStudentRepository.findByUserId(activateOrDeactivateUserReqDto.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User Non Student")));

        User createdUser = userOpt.get();
        userRepository.activateOrDeactivateUser(true, createdUser.getId());
        return true;
    }
}
