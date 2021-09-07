package server.server.memberDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String checkPassword;
}
