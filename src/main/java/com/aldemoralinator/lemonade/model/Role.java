package com.aldemoralinator.lemonade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "role")
@ToString
@EqualsAndHashCode
public class Role implements GrantedAuthority {
	
	private static final long serialVersionUID = 6899812091677169861L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @Getter
    private ERole name;
    
    public Role() {
    }

    public Role(ERole eRole) {
        this.name = eRole;
    }

	@Override
	public String getAuthority() {
		return name.toString();
	}
}