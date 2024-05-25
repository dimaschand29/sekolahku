package org.example.controller;

import org.example.dto.ActivateOrDeactivateUserReqDto;
import org.example.dto.CreateNonStudentUserReqDto;
import org.example.service.NonStudentService;
import org.example.service.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nonStudent")
public class NonStudentController {

    @Autowired
    private NonStudentService nonStudentService;

    @Autowired
    private ResponseEntityBuilder responseEntityBuilder;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody CreateNonStudentUserReqDto createNonStudentUserReqDto) {
        Boolean createNonStudent = nonStudentService.createNonStudent(createNonStudentUserReqDto);
        ResponseEntity<Object> response = responseEntityBuilder.buildNormalResponseEntity(createNonStudent);
        return response;
    }

    @PostMapping(path = "/activate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> activateUser(@RequestBody ActivateOrDeactivateUserReqDto activateOrDeactivateUserReqDto) {
        Boolean activateStudent = nonStudentService.activateNonStudent(activateOrDeactivateUserReqDto);
        ResponseEntity<Object> response = responseEntityBuilder.buildNormalResponseEntity(activateStudent);
        return response;
    }

    @PostMapping(path = "/deactivate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deactivateUser(@RequestBody ActivateOrDeactivateUserReqDto activateOrDeactivateUserReqDto) {
        Boolean deactivateStudent = nonStudentService.deactivateNonStudent(activateOrDeactivateUserReqDto);
        ResponseEntity<Object> response = responseEntityBuilder.buildNormalResponseEntity(deactivateStudent);
        return response;
    }
}
