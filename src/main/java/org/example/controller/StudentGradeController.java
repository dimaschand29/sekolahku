package org.example.controller;

import org.example.dto.CreateRoleReqDto;
import org.example.dto.CreateStudentGradeReqDto;
import org.example.service.ResponseEntityBuilder;
import org.example.service.RoleService;
import org.example.service.StudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentGrade")
public class StudentGradeController {

    @Autowired
    private StudentGradeService studentGradeService;

    @Autowired
    private ResponseEntityBuilder responseEntityBuilder;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createStudentGrade(@RequestBody CreateStudentGradeReqDto createStudentGradeReqDto) {
        Boolean createStudentGrade = studentGradeService.createStudentGrade(createStudentGradeReqDto);
        ResponseEntity<Object> response = responseEntityBuilder.buildNormalResponseEntity(createStudentGrade);
        return response;
    }

    @GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllStudentGrade() {
        List<?> createStudentGrade = studentGradeService.getAllStudentGrade();
        ResponseEntity<Object> response = responseEntityBuilder.buildNormalResponseEntity(createStudentGrade);
        return response;
    }
}
