package com.bakery_shop.service;

import com.bakery_shop.model.Mapper;
import com.bakery_shop.model.dto.RoleDTO;
import com.bakery_shop.model.entity.RoleEntity;
import com.bakery_shop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // GET role by name
    public RoleDTO getRole(String name) {

        RoleEntity role = roleRepository.getRoleEntityByName(name);

        if (role == null) {
            throw new RuntimeException("Role not found");
        }

        return Mapper.toRoleDTO(role);
    }
    public RoleEntity getRoleEntity(String name) {
        return roleRepository.getRoleEntityByName(name);
    }
    // GET all roles
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(Mapper::toRoleDTO)
                .toList();
    }

    // GET role by id
    public RoleDTO getRoleById(Long id) {

        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        return Mapper.toRoleDTO(role);
    }

    // CREATE role
    public RoleDTO createRole(RoleDTO roleDTO) {

        RoleEntity exist = roleRepository.getRoleEntityByName(roleDTO.getName());

        if (exist != null) {
            throw new RuntimeException("Role already exists");
        }

        RoleEntity role = Mapper.toRoleEntity(roleDTO);

        return Mapper.toRoleDTO(roleRepository.save(role));
    }

    // UPDATE role
    public RoleDTO updateRole(Long id, RoleDTO roleRequest) {

        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        role.setName(roleRequest.getName());

        return Mapper.toRoleDTO(roleRepository.save(role));
    }

    // DELETE role
    public void deleteRole(Long id) {

        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        roleRepository.delete(role);
    }
}