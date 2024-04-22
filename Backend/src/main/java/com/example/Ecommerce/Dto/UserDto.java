package com.example.Ecommerce.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserDto {
    private String username;
    private String password;
    private String email;
}
