package org.example.service;

import org.example.dto.CreateStudentGradeReqDto;
import org.example.exception.AlreadyExistException;
import org.example.model.StudentGrade;
import org.example.repository.StudentGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentGradeService {

    @Autowired
    private StudentGradeRepository studentGradeRepository;

    @Transactional
    public Boolean createStudentGrade(CreateStudentGradeReqDto createStudentGradeReqDto) {
        Optional<StudentGrade> studentGradeOpt = studentGradeRepository.findByGrade(createStudentGradeReqDto.getGrade());
        if (!studentGradeOpt.isEmpty()){
            throw new AlreadyExistException("Grade");
        }
        StudentGrade createdStudentGrade = new StudentGrade();
        createdStudentGrade.setGrade(createStudentGradeReqDto.getGrade());
        createdStudentGrade.setRoleId(createStudentGradeReqDto.getRoleId());
        createdStudentGrade = studentGradeRepository.saveAndFlush(createdStudentGrade);
        return true;
    }

    public List<?> getAllStudentGrade() {
        List<StudentGrade> studentGradeList = studentGradeRepository.findAllStudentGrade();
        return studentGradeList;
    }
}
