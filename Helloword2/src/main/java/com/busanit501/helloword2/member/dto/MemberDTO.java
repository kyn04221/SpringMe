package com.busanit501.helloword2.member.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long mno;
    private String mid;
    private String mpw;
    private String mname;

}
