package server.server.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
    private String userId;
    private String name;
    private String email;
    private String passward;
    private String checkPassward;
}
