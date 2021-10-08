package server.server.memberDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserVo {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
}
