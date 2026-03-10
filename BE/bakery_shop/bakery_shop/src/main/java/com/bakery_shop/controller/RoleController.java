package com.bakery_shop.controller;

import com.bakery_shop.model.ApiResponse;
import com.bakery_shop.model.dto.RoleDTO;
import com.bakery_shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // GET all roles
    @GetMapping
    public ApiResponse<List<RoleDTO>> getAllRoles() {
        return ApiResponse.success(
                roleService.getAllRoles(),
                "Get all roles successfully"
        );
    }

    // GET role by id
    @GetMapping("/{id}")
    public ApiResponse<RoleDTO> getRoleById(@PathVariable Long id) {
        return ApiResponse.success(
                roleService.getRoleById(id),
                "Get role successfully"
        );
    }

    // CREATE role
    @PostMapping
    public ApiResponse<RoleDTO> createRole(@RequestBody RoleDTO role) {
        return ApiResponse.success(
                roleService.createRole(role),
                "Create role successfully"
        );
    }

    // UPDATE role
    @PutMapping("/{id}")
    public ApiResponse<RoleDTO> updateRole(
            @PathVariable Long id,
            @RequestBody RoleDTO role
    ) {
        return ApiResponse.success(
                roleService.updateRole(id, role),
                "Update role successfully"
        );
    }

    // DELETE role
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ApiResponse.success(
                null,
                "Delete role successfully"
        );
    }
}