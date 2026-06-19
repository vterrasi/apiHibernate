package com.cursoSpring.apiHibernate.domain.models;

import com.cursoSpring.apiHibernate.domain.enums.UserRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserModel {

    private final String id;
    private final String username;
    private final String password;
    private final UserRole role;
}