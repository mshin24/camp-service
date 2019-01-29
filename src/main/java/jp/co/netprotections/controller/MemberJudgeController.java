package jp.co.netprotections.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import jp.co.netprotections.dto.MemberInfo;
import jp.co.netprotections.dto.MemberJudgeRequestDto;
import jp.co.netprotections.dto.MemberJudgeResponse;
import jp.co.netprotections.dto.MemberJudgeResponseDto;


@RestController
public class MemberJudgeController {
	
	
	
	@RequestMapping(value="/execute", method=RequestMethod.POST, consumes=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MemberJudgeResponse list(@RequestBody MemberInfo info) throws Exception {
		//JSON -> JAVA -> JSON
	
		
		ArrayList<MemberJudgeRequestDto> memList = new ArrayList<MemberJudgeRequestDto>();
		
		//All data print-test
		//System.out.println(new ObjectMapper().writeValueAsString(info));
		//json String data -> JAVA Beans data save(ArrayList)
		MemberJudgeResponse response = new MemberJudgeResponse();
		ArrayList<MemberJudgeResponseDto> responseDtoList = new ArrayList<MemberJudgeResponseDto>();
		
		//json -> javaBean 完了
		for(int i=0;i<info.getMemberCandidatesList().size();i++) {
			MemberJudgeRequestDto dto = new MemberJudgeRequestDto();
			//data -> ArrayList
			dto.setCogitation(info.getMemberCandidatesList().get(i).getCogitation());	
			dto.setCoodination(info.getMemberCandidatesList().get(i).getCoodination());
			dto.setEventPlanning(info.getMemberCandidatesList().get(i).getEventPlanning());
			dto.setInfrastructureKnowledge(info.getMemberCandidatesList().get(i).getInfrastructureKnowledge());
			dto.setMemberName(info.getMemberCandidatesList().get(i).getMemberName());
			dto.setProgrammingAbility(info.getMemberCandidatesList().get(i).getProgrammingAbility());
			
			memList.add(i, dto);
			//JSON(info) -> ArrayList 変換完了 （memberList-dto）
		}
		
		int count = 0;
		int total =0;
		
		for(int i=0;i<memList.size();i++) {
			MemberJudgeResponseDto responseDto = new MemberJudgeResponseDto();
			 
			responseDto.setMemberName(memList.get(i).getMemberName());
			total= memList.get(i).getCogitation()+memList.get(i).getCoodination()+memList.get(i).getEventPlanning()+memList.get(i).getInfrastructureKnowledge()
				+memList.get(i).getProgrammingAbility();
			//属性0~5判断 && (2)適正判断の計算ロジック
			if(memList.get(i).getCogitation()>=0 && memList.get(i).getCogitation()<=5) {
				responseDto.setCogitation(memList.get(i).getCogitation());
				responseDto.setEnlistedPropriety(true);
			}else {
				responseDto.setEnlistedPropriety(true);
			
				memList.get(i).setMemberName(null);
				count++;
			}
			if(memList.get(i).getCoodination()>=0 && memList.get(i).getCoodination()<=5) {
				responseDto.setCoodination(memList.get(i).getCoodination());
				responseDto.setEnlistedPropriety(true);
			}else {	//調整力処理
				responseDto.setEnlistedPropriety(false);
				memList.get(i).setMemberName(null);
				count++;
			}
			if(memList.get(i).getEventPlanning()>=0 && memList.get(i).getEventPlanning()<=5) {
				responseDto.setEventPlanning(memList.get(i).getEventPlanning());
				responseDto.setEnlistedPropriety(true);
			}else if(memList.get(i).getEventPlanning()<=1){	//イベント企画力処理
				responseDto.setEnlistedPropriety(false);
			}else {
				responseDto.setEnlistedPropriety(false);
				memList.get(i).setMemberName(null);
				count++;
				}
			
			if(memList.get(i).getInfrastructureKnowledge()>=0 && memList.get(i).getInfrastructureKnowledge()<=5) {
				responseDto.setInfrastructureKnowledge(memList.get(i).getInfrastructureKnowledge());
				responseDto.setEnlistedPropriety(true);	
			}else {
				responseDto.setEnlistedPropriety(false);
				memList.get(i).setMemberName(null);
				count++;
			}
			

         
			if(memList.get(i).getProgrammingAbility()>=0 && memList.get(i).getProgrammingAbility()<=5) {
				responseDto.setProgrammingAbility(memList.get(i).getProgrammingAbility());
				responseDto.setEnlistedPropriety(true);	
			}else {
				responseDto.setEnlistedPropriety(false);
				memList.get(i).setMemberName(null);
				count++;
			}
			
			//total score 処理
			if(total<=10) {
				responseDto.setEnlistedPropriety(false);
			}
			if(count>=2) {
				//空のリスポンス返却
				//response = null;
				break;
			}
			responseDtoList.add(i,responseDto);
			
//			if(responseDto.isEnlistedPropriety()==false) {
//				responseDtoList.remove(i);
//			}
			
		}
		
		
		
		
		//Gson gson = new GsonBuilder().create();	
		//ArrayList -> Json
		
		//for(int i=0;i<responseDtoList.size()-1;i++) {
			//name, enlistedPropriety
			// resultString = gson.toJson(gson.toJson(responseDtoList.get(i)));
			// System.out.println(gson.toJson(tempList.get(i)));
		//}
		response.setJudgedCandidatesResultList(responseDtoList);
		//System.out.println(resultString);
		return response;
	}
	

	
}
