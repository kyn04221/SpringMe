package com.busanit501.helloword2.jdbc.dto;

import lombok.*;

@Getter
//@Setter vo에서는 setter 안쓰고 getter위주로 사용함 (불변성 중시)
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    private String mid;
    private String mpw;
    private String mname;
    private String uuid;
}
