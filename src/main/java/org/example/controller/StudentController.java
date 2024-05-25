package org.example.controller;

import org.example.dto.ActivateOrDeactivateUserReqDto;
import org.example.dto.CreateUserStudentReqDto;
import org.example.dto.GetPageResDto;
import org.example.dto.GetUserStudentPageReqDto;
import org.example.service.ResponseEntityBuilder;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ResponseEntityBuilder responseEntityBuilder;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody CreateUserStudentReqDto createUserStudentReqDto) {
        Boolean createStudent = studentService.createStudent(createUserStudentReqDto);
        ResponseEntity<Object> response = responseEntityBuilder.buildNormalResponseEntity(createStudent);
        return response;
    }

    @PostMapping(path = "/activate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> activateUser(@RequestBody ActivateOrDeactivateUserReqDto activateOrDeactivateUserReqDto) {
        Boolean activateStudent = studentService.activateStudent(activateOrDeactivateUserReqDto);
        ResponseEntity<Object> response = responseEntityBuilder.buildNormalResponseEntity(activateStudent);
        return response;
    }

    @PostMapping(path = "/deactivate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deactivateUser(@RequestBody ActivateOrDeactivateUserReqDto activateOrDeactivateUserReqDto) {
        Boolean deactivateStudent = studentService.deactivateStudent(activateOrDeactivateUserReqDto);
        ResponseEntity<Object> response = responseEntityBuilder.buildNormalResponseEntity(deactivateStudent);
        return response;
    }

    @PostMapping(path = "/getPage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPageUser(@RequestBody GetUserStudentPageReqDto getUserStudentPageReqDto) {
        GetPageResDto getPageUserStudent = studentService.getPageUser(getUserStudentPageReqDto);
        ResponseEntity<Object> response = responseEntityBuilder.buildNormalResponseEntity(getPageUserStudent);
        return response;
    }
}
