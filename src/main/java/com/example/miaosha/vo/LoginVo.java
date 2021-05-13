package com.example.miaosha.vo;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

/**
 * @author kelin
 */
@Data
public class LoginVo {
    @NonNull
    String mobile;

    @NonNull
    @Length(min = 32)
    String password;
}
