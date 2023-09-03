package board.member.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
	
	//식별자
	private Long memberId;
	
	//아이디 비밀번호
	private String memberEmail;
	private String memberPassword;
	
	//회원정보
	private String memberName;
	private int memberAge;
	private String memberAddress;
	
	//회원가입 시간
	private LocalDateTime memberCreatedTime;
	
	//회원 탈퇴
	private String memberDeleted;
	
}
