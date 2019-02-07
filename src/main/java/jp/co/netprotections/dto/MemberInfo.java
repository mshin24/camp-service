package jp.co.netprotections.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author m.shin
 *  ReqeustBobyをrequestです
 * @param List<MemberJudgeRequestDto> memberCandidatesList
 * @return List<MemberJudgeRequestDto> memberCandidatesList
 * */

public class MemberInfo {
	
	//隊員たちのリスト
	private List<MemberJudgeRequestDto> memberCandidatesList;

	public List<MemberJudgeRequestDto> getMemberCandidatesList() {
		return memberCandidatesList;
	}

	public void setMemberCandidatesList(ArrayList<MemberJudgeRequestDto> memberCandidatesList) {
		this.memberCandidatesList = memberCandidatesList;
	}


}
