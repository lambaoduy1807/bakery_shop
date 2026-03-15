package com.bakery_shop.service;

import com.bakery_shop.model.dto.RoleDTO;
import com.bakery_shop.model.entity.RoleEntity;
import com.bakery_shop.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    private RoleEntity roleEntity;
    private RoleDTO roleDTO;

    @BeforeEach
    void setUp() {
        roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setName("User");

        roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        roleDTO.setName("User");
    }

    @Test
    void getRole_Success() {
        when(roleRepository.getRoleEntityByName("User")).thenReturn(roleEntity);

        RoleDTO result = roleService.getRole("User");

        assertNotNull(result);
        assertEquals("User", result.getName());
        verify(roleRepository, times(1)).getRoleEntityByName("User");
    }

    @Test
    void getRole_NotFound() {
        when(roleRepository.getRoleEntityByName("Admin")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> roleService.getRole("Admin"));

        assertEquals("Role not found", exception.getMessage());
        verify(roleRepository, times(1)).getRoleEntityByName("Admin");
    }

    @Test
    void getRoleEntity_Success() {
        when(roleRepository.getRoleEntityByName("User")).thenReturn(roleEntity);

        RoleEntity result = roleService.getRoleEntity("User");

        assertNotNull(result);
        assertEquals("User", result.getName());
        verify(roleRepository, times(1)).getRoleEntityByName("User");
    }

    @Test
    void getAllRoles_Success() {
        when(roleRepository.findAll()).thenReturn(Arrays.asList(roleEntity));

        List<RoleDTO> result = roleService.getAllRoles();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("User", result.get(0).getName());
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    void getRoleById_Success() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleEntity));

        RoleDTO result = roleService.getRoleById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("User", result.getName());
        verify(roleRepository, times(1)).findById(1L);
    }

    @Test
    void getRoleById_NotFound() {
        when(roleRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> roleService.getRoleById(2L));

        assertEquals("Role not found", exception.getMessage());
        verify(roleRepository, times(1)).findById(2L);
    }

    @Test
    void createRole_Success() {
        when(roleRepository.getRoleEntityByName("User")).thenReturn(null);
        when(roleRepository.save(any(RoleEntity.class))).thenReturn(roleEntity);

        RoleDTO result = roleService.createRole(roleDTO);

        assertNotNull(result);
        assertEquals("User", result.getName());
        verify(roleRepository, times(1)).getRoleEntityByName("User");
        verify(roleRepository, times(1)).save(any(RoleEntity.class));
    }

    @Test
    void createRole_AlreadyExists() {
        when(roleRepository.getRoleEntityByName("User")).thenReturn(roleEntity);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> roleService.createRole(roleDTO));

        assertEquals("Role already exists", exception.getMessage());
        verify(roleRepository, times(1)).getRoleEntityByName("User");
        verify(roleRepository, never()).save(any(RoleEntity.class));
    }

    @Test
    void updateRole_Success() {
        RoleDTO updateRequest = new RoleDTO();
        updateRequest.setName("Admin");

        RoleEntity updatedEntity = new RoleEntity();
        updatedEntity.setId(1L);
        updatedEntity.setName("Admin");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleEntity));
        when(roleRepository.save(any(RoleEntity.class))).thenReturn(updatedEntity);

        RoleDTO result = roleService.updateRole(1L, updateRequest);

        assertNotNull(result);
        assertEquals("Admin", result.getName());
        verify(roleRepository, times(1)).findById(1L);
        verify(roleRepository, times(1)).save(any(RoleEntity.class));
    }

    @Test
    void updateRole_NotFound() {
        when(roleRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> roleService.updateRole(2L, roleDTO));

        assertEquals("Role not found", exception.getMessage());
        verify(roleRepository, times(1)).findById(2L);
        verify(roleRepository, never()).save(any(RoleEntity.class));
    }

    @Test
    void deleteRole_Success() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleEntity));
        doNothing().when(roleRepository).delete(roleEntity);

        assertDoesNotThrow(() -> roleService.deleteRole(1L));

        verify(roleRepository, times(1)).findById(1L);
        verify(roleRepository, times(1)).delete(roleEntity);
    }

    @Test
    void deleteRole_NotFound() {
        when(roleRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> roleService.deleteRole(2L));

        assertEquals("Role not found", exception.getMessage());
        verify(roleRepository, times(1)).findById(2L);
        verify(roleRepository, never()).delete(any(RoleEntity.class));
    }
}
