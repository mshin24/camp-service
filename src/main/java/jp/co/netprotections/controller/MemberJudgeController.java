package jp.co.netprotections.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import jp.co.netprotections.dto.MemberInfo;
import jp.co.netprotections.dto.MemberJudgeRequestDto;
import jp.co.netprotections.dto.MemberJudgeResponse;
import jp.co.netprotections.dto.MemberJudgeResponseDto;

/**
 * 隊員判定コントローラクラス
 */
@RestController
public class MemberJudgeController {
	
	/**
	 * Controller
	 * @param MemberInfo info
	 * @return MemberJudgeResponse response
	 * */
	
	@RequestMapping(value="/execute", method=RequestMethod.POST, consumes=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MemberJudgeResponse list(@RequestBody MemberInfo info) throws Exception {
	
		// responseするレスポンスを初期化
		MemberJudgeResponse response = new MemberJudgeResponse();
		
		//responseBoye(info)を保存 
		//getMemberCandidatesList() - responseBody
		ArrayList<MemberJudgeRequestDto> memList = info.getMemberCandidatesList();
		ArrayList<MemberJudgeResponseDto> responseDtoList = new ArrayList<MemberJudgeResponseDto>();
		
		// 
		int total =0;
		
		/**
		 * @param MemberJudgeResponseDto responseDto
		 * @return ArrayList<MemberJudgeResponseDto> responseDtoList
		 * */
		
		/*
		 * 処理の前提チェック
		 * 不正が以下の2つある場合は処理を中断　→　空のレスポンスを返却
		 * 
		 * 不正どは？
		 * 名前がない場合
		 * 隊員の属性が0以上5以下以外の場合
		 * */
		
		MemberJudgeRequestDto requestDto = new MemberJudgeRequestDto();
		//0 全体隊員を反復検査のためのfor()
		for(int i=0;i<memList.size();i++) {

			//0 隊員一人ずつ検査します。
			requestDto = memList.get(i);
			
			//O 隊員の属性が0以上5以下以外検査します。
			if(requestDto.getCogitation()<0&&requestDto.getCogitation()>5) {
				//0~5以外の場合、空を入れます。　 (空の意味でNULL値を入れたですか。。)
				response.setJudgedCandidatesResultList(null);
				//0   リスポンスを空に返却する。　
				return response;
			}else if(requestDto.getCoodination()<0&&requestDto.getCoodination()>5) {
				response.setJudgedCandidatesResultList(null);
				return response;
			}else if(requestDto.getEventPlanning()<0&&requestDto.getEventPlanning()>5) {
				response.setJudgedCandidatesResultList(null);
				return response;
			}else if(requestDto.getInfrastructureKnowledge()<0&&requestDto.getInfrastructureKnowledge()>5) {
				response.setJudgedCandidatesResultList(null);
				return response;
			}else if(requestDto.getProgrammingAbility()<0&&requestDto.getProgrammingAbility()>5) {
				response.setJudgedCandidatesResultList(null);
				return response;
			}
			
			//nameがない隊員判断 
			if(memList.get(i).getMemberName()==null) {

				//0  名前をNULLの変更
				for(int j=0;j<memList.size();j++) {
				memList.get(j).setMemberName(null);				
				memList.get(j).setEnlistedPropriety(false);
					}
				}
		}	
		
	
		//0  リスポンスする隊員リスト保存　＆　 適正判断の計算ロジック
		
		for(int i=0;i<memList.size();i++) {
			
			//0  臨時隊員class 初期化
			MemberJudgeResponseDto responseDto = new MemberJudgeResponseDto();
			
			//0 処理の前提チェックが終わった隊員の名前をリスポンスする客体に保存
			responseDto.setMemberName(memList.get(i).getMemberName());
			
			//0   属性合計計算
			total= memList.get(i).getCogitation()+memList.get(i).getCoodination()+memList.get(i).getEventPlanning()+memList.get(i).getInfrastructureKnowledge()
				+memList.get(i).getProgrammingAbility();
			
			//0  適正判断の計算ロジック　-  イベント企画力が1以下は入隊不可能
			if(memList.get(i).getEventPlanning()<=1){
				//0  条件と合った場合は入隊不可能
				responseDto.setEnlistedPropriety(false);
			}else responseDto.setEnlistedPropriety(true);	//0  入隊可能
			
			
			/**
			 * 
			 *  適正判断の計算ロジック　- total score 処理
			 *  隊員の属性の合計が１０以下場合入隊不可能
			 * */
			if(total<=10) {
				responseDto.setEnlistedPropriety(false);
			}else responseDto.setEnlistedPropriety(true);
			
			//0  検査が終わった隊員のリストを作りなす。
			responseDtoList.add(responseDto);
		}
		//0  最後に処理が全部終わったリストをリスポンスする客体に保存
		response.setJudgedCandidatesResultList(responseDtoList);
			
		
		//0 処理をしたレスポンスインスタンスを返却 
		return response;
	}
	

	
}
