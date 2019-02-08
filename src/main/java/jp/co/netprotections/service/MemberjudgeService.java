package jp.co.netprotections.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import jp.co.netprotections.dto.MemberJudgeRequestDto;
import jp.co.netprotections.dto.MemberJudgeResponse;
import jp.co.netprotections.dto.MemberJudgeResponseDto;


@Service
public class MemberjudgeService {

	/**
	 * @author m.shin
	 * 処理Logic　Service class
	 * @param List<MemberJudgeRequestDto> memList
	 * @return MemberJudgeResponse res
	 * */
	public MemberJudgeResponse judgeQualification(List<MemberJudgeRequestDto> memList) {
		
		//Controllerへresponseするresult初期化
		MemberJudgeResponse res = new MemberJudgeResponse();
		
		//隊員全体を含めるList
		List<MemberJudgeResponseDto> judgedList = new ArrayList<MemberJudgeResponseDto>();
		
		//response
		for(int i=0;i<memList.size();i++) {
			//一人ずつ検査するため臨時客体初期化
			MemberJudgeResponseDto resDto = new MemberJudgeResponseDto();
			
			//処理の前提チェックを実行する
			if(this.hasValidinput(memList.get(i))) {
				//適正判断の計算ロジックを実行する　→　入隊判断
				resDto.setEnlistedPropriety(this.calculation(memList.get(i)));
			}
			else resDto.setEnlistedPropriety(false);
			
			//処理が終わった隊員の名前保存
			resDto.setMemberName(memList.get(i).getMemberName());	
			//リストに追加
			judgedList.add(resDto);
		}
		//responseにリスト保存
		res.setJudgedCandidatesResultList(judgedList);
		
		//controllerへresponse
		return res;
	}
	

	
	/**
	 * 処理の前提チェック(名前がない隊員 、各属性0以上5以下　判断)
	 * @author m.shin
	 * @param ArrayList<MemberJudgeRequestDto> member
	 * @return true || false
	 * */
	private boolean hasValidinput(MemberJudgeRequestDto member) {
		
		//名前にString 以外数字が入った場合、名前をnullにする。
		try {
			Integer.parseInt(member.getMemberName());
			member.setMemberName(null);
			return false;
		}catch(Exception e) {
			
		}
		
		//名前がない隊員判断 (名前が無いとはnullまたは""です)
		if(member.getMemberName()==null||member.getMemberName()=="") {
			//名前をNULLの変更
			member.setMemberName(null);				
			return false;
		}
		//隊員の各属性前提チェック　
		if(member.getCogitation()<1 || member.getCogitation()>5 ) {
			return false;
		}
		if(member.getCoodination()<1 || member.getCoodination()>5) {
			return false;
		}
		if(member.getEventPlanning()<1 || member.getEventPlanning()>5) {
			return false;
		}
		if(member.getInfrastructureKnowledge()<1|| member.getInfrastructureKnowledge()>5) {
			return false;
		}
		if(member.getProgrammingAbility()<1||member.getProgrammingAbility()>5) {
			return false;
		}
		
		return true;
	}
	

	/**
	 * 適正判断の計算ロジック
	 * @author m.shin
	 * @param MemberJudgeRequestDto member
	 * @return true || false
	 * */
	private boolean calculation(MemberJudgeRequestDto member) {
		//隊員の属性の合計が１０以下場合入隊不可能
		int total = member.getCogitation()+member.getCoodination()+member.getEventPlanning()
		+member.getInfrastructureKnowledge()+member.getProgrammingAbility();
		if(total<=10) return false;
		
		//イベント企画力が1以下のか判断（以下の場合、入隊不可能）
		if(member.getEventPlanning()<=1) return false;
		
		//調整力が１以下のか判断（以下の場合、入隊不可能）
		if(member.getCoodination()<=1) return false;
		
		return true;
	}

}
