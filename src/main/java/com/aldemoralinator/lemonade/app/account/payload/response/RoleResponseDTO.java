package com.aldemoralinator.lemonade.app.account.payload.response;

import com.aldemoralinator.lemonade.model.constant.ERole;
import com.aldemoralinator.lemonade.model.entity.Role;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class RoleResponseDTO {

    @Getter
    private Long id;
    
    @Getter
    private String name;
    
    public RoleResponseDTO() { }
    
    public RoleResponseDTO(Role role) {
    	this.id = role.getId();
    	this.name = role.getName().toString();
    }
}
