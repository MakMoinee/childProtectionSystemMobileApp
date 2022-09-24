package com.child.safe.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Users {
    int userID;
    String username;
    String password;
    int userType;
}
