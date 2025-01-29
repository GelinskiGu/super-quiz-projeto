package com.gelinski.superquiz.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "tb_permission")
@JsonIgnoreProperties({"userPermission"})
public class Permission implements GrantedAuthority, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermission;

    @Column(nullable = false, length = 20)
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private List<User> userPermission;

    @Override
    public String getAuthority() {
        return this.description;
    }
}
