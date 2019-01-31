package jp.co.netprotections.dto;

import java.util.ArrayList;

/*
 * ResponseBodyを受けるClass
 * 
 * */

public class MemberInfo {
	
	//0  隊員たちのリスト
	private ArrayList<MemberJudgeRequestDto> memberCandidatesList;

	public ArrayList<MemberJudgeRequestDto> getMemberCandidatesList() {
		return memberCandidatesList;
	}

	public void setMemberCandidatesList(ArrayList<MemberJudgeRequestDto> memberCandidatesList) {
		this.memberCandidatesList = memberCandidatesList;
	}


}
