package jp.co.netprotections.dto;

import java.util.List;

/**
 * @author m.shin
 *  Controllerのresponseです.
 *@param ArrayList<MemberJudgeResponseDto> judgedCandidatesResultList 
 *@return ArrayList<MemberJudgeResponseDto> judgedCandidatesResultList
 * */

public class MemberJudgeResponse {
	private List<MemberJudgeResponseDto> judgedCandidatesResultList;
	public List<MemberJudgeResponseDto> getJudgedCandidatesResultList() {
		return judgedCandidatesResultList;
	}
	public void setJudgedCandidatesResultList(List<MemberJudgeResponseDto> judgedCandidatesResultList) {
		this.judgedCandidatesResultList = judgedCandidatesResultList;
	}

	



	

}
