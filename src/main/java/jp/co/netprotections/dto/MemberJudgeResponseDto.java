package jp.co.netprotections.dto;

/**
  *　@author m.shin
  *  @see MeberjudgeService 
  *  処理完了したResponse DTO
  *  
 */
public class MemberJudgeResponseDto {

	private String memberName;

	private boolean enlistedPropriety;
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public boolean isEnlistedPropriety() {
		return enlistedPropriety;
	}
	public void setEnlistedPropriety(boolean enlistedPropriety) {
		this.enlistedPropriety = enlistedPropriety;
	}

	
	
	
	
	
}
