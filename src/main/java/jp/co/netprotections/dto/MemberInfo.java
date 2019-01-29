package jp.co.netprotections.dto;

import java.util.ArrayList;

public class MemberInfo {
	
	private ArrayList<MemberJudgeRequestDto> memberCandidatesList;

	public ArrayList<MemberJudgeRequestDto> getMemberCandidatesList() {
		return memberCandidatesList;
	}

	public void setMemberCandidatesList(ArrayList<MemberJudgeRequestDto> memberCandidatesList) {
		this.memberCandidatesList = memberCandidatesList;
	}


}
