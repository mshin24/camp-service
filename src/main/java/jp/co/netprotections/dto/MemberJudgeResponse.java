package jp.co.netprotections.dto;

import java.util.ArrayList;

/*
 * リスポンスClass
 * ArrayList<MemberJudgeResponseDto> judgedCandidatesResultList 
 * の中には処理ができた隊員リストが入っています。
 * */

public class MemberJudgeResponse {
	//private ArrayList<MemberJudgeRequestDto> memberCandidatesList;
	private ArrayList<MemberJudgeResponseDto> judgedCandidatesResultList;
	public ArrayList<MemberJudgeResponseDto> getJudgedCandidatesResultList() {
		return judgedCandidatesResultList;
	}
	public void setJudgedCandidatesResultList(ArrayList<MemberJudgeResponseDto> judgedCandidatesResultList) {
		this.judgedCandidatesResultList = judgedCandidatesResultList;
	}

	



	

}
