package org.example.controller;

import org.example.dto.CreateRoleReqDto;
import org.example.dto.DeleteRoleReqDto;
import org.example.service.ResponseEntityBuilder;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResponseEntityBuilder responseEntityBuilder;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createRole(@RequestBody CreateRoleReqDto createRoleReqDto) {
        Boolean createRole = roleService.createRole(createRoleReqDto);
        ResponseEntity<Object> response = responseEntityBuilder.buildNormalResponseEntity(createRole);
        return response;
    }

    @PostMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteRole(@RequestBody DeleteRoleReqDto deleteRoleReqDto) {
        Boolean createRole = roleService.deleteRole(deleteRoleReqDto);
        ResponseEntity<Object> response = responseEntityBuilder.buildNormalResponseEntity(createRole);
        return response;
    }
}
