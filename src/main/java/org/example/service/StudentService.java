package org.example.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.dto.*;
import org.example.exception.AlreadyExistException;
import org.example.exception.DataNotFoundException;
import org.example.model.StudentGrade;
import org.example.model.User;
import org.example.model.UserDetail;
import org.example.model.UserStudent;
import org.example.repository.StudentGradeRepository;
import org.example.repository.UserDetailRepository;
import org.example.repository.UserRepository;
import org.example.repository.UserStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentGradeRepository studentGradeRepository;

    @Autowired
    private UserStudentRepository userStudentRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Transactional
    public Boolean createStudent(CreateUserStudentReqDto createUserStudentReqDto) {
        Optional<StudentGrade> studentGradeOpt = Optional.ofNullable(studentGradeRepository.findById(createUserStudentReqDto.getStudentGradeId())
                .orElseThrow(() -> new DataNotFoundException("Student Grade")));

        Optional<User> userOpt = userRepository.findByUsername(createUserStudentReqDto.getUser().getUsername());
        if (!userOpt.isEmpty()){
            throw new AlreadyExistException("User");
        }

        String passwordHashed = DigestUtils.md5Hex(createUserStudentReqDto.getUser().getPassword());
        createUserStudentReqDto.getUser().setPassword(passwordHashed);
        User user = userRepository.saveAndFlush(createUserStudentReqDto.getUser());
        UserStudent createdUserStudent = new UserStudent();
        createdUserStudent.setStudentGradeId(studentGradeOpt.get().getId());
        createdUserStudent.setUserId(user.getId());
        createdUserStudent = userStudentRepository.saveAndFlush(createdUserStudent);

        UserDetail createdUserDetail = new UserDetail();
        createdUserDetail.setAddress(createUserStudentReqDto.getUserDetail().getAddress());
        createdUserDetail.setCity(createUserStudentReqDto.getUserDetail().getCity());
        createdUserDetail.setEmail(createUserStudentReqDto.getUserDetail().getEmail());
        createdUserDetail.setProvince(createUserStudentReqDto.getUserDetail().getProvince());
        createdUserDetail.setUserId(user.getId());
        createdUserDetail.setFullname(createUserStudentReqDto.getUserDetail().getFullname());
        createdUserDetail = userDetailRepository.saveAndFlush(createdUserDetail);
        return true;
    }

    @Transactional
    public Boolean activateStudent(ActivateOrDeactivateUserReqDto activateOrDeactivateUserReqDto) {
        Optional <User> userOpt = Optional.ofNullable(userRepository.findById(activateOrDeactivateUserReqDto.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User")));

        Optional <UserStudent> studentOpt = Optional.ofNullable(userStudentRepository.findByUserId(activateOrDeactivateUserReqDto.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User Student")));

        User createdUser = userOpt.get();
        userRepository.activateOrDeactivateUser(false, createdUser.getId());
        return true;
    }

    @Transactional
    public Boolean deactivateStudent(ActivateOrDeactivateUserReqDto activateOrDeactivateUserReqDto) {
        Optional <User> userOpt = Optional.ofNullable(userRepository.findById(activateOrDeactivateUserReqDto.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User")));

        Optional <UserStudent> studentOpt = Optional.ofNullable(userStudentRepository.findByUserId(activateOrDeactivateUserReqDto.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User Student")));

        User createdUser = userOpt.get();
        userRepository.activateOrDeactivateUser(true, createdUser.getId());
        return true;
    }

    public GetPageResDto getPageUser(GetUserStudentPageReqDto getUserStudentPageReqDto) {
        String username = getUserStudentPageReqDto.getUsername();
        String city = getUserStudentPageReqDto.getCity();
        String province = getUserStudentPageReqDto.getProvince();
        String fullname = getUserStudentPageReqDto.getFullname();
        LocalDateTime startDate = getUserStudentPageReqDto.getFromCreateDate();
        LocalDateTime endDate = getUserStudentPageReqDto.getToCreateDate();
        Boolean isDel = false;
        Integer grade = getUserStudentPageReqDto.getGrade();
        PageDto pageDto = getUserStudentPageReqDto.getPage();
        PageRequest pageable = PageRequest.of(pageDto.getPageNo(), pageDto.getPageSize());
        Page<UserDetailPageResDto> pageUser = userDetailRepository
                .findAllStudentFilteredPage(username, city, province, fullname, startDate, endDate, isDel, grade, pageable);
        PageDto resultPage = PageDto.builder()
                .pageNo(pageUser.getNumber())
                .pageSize(pageUser.getSize())
                .totalPage(pageUser.getTotalPages())
                .totalRecord(pageUser.getTotalElements())
                .build();
        return GetPageResDto.builder()
                .page(resultPage)
                .contentList(pageUser.getContent())
                .build();
    }
}
