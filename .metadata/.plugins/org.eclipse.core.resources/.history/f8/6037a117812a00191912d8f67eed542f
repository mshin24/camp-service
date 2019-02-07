package jp.co.netprotections.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import jp.co.netprotections.dto.MemberInfo;
import jp.co.netprotections.dto.MemberJudgeRequestDto;
import jp.co.netprotections.dto.MemberJudgeResponse;
import jp.co.netprotections.service.MemberjudgeService;


@RestController
public class MemberJudgeController {
	
	/**
	 * @author m.shin
	 * Controller 隊員判定コントローラクラス
	 * @param MemberInfo info
	 * @return MemberJudgeResponse response
	 * */
	
	@RequestMapping(value="/execute", method=RequestMethod.POST, consumes=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MemberJudgeResponse list(@RequestBody MemberInfo info) throws Exception {
	
		// responseするレスポンスを初期化
		MemberJudgeResponse response = new MemberJudgeResponse();
		
		// responseBobyをListへ保存
		List<MemberJudgeRequestDto> memList =  info.getMemberCandidatesList();
		
		/**@service 初期化*/
		MemberjudgeService service = new MemberjudgeService();
		
		//0 service処理が完了出来た情報response保存
		response = service.judgeQualification(memList);		

		//0 処理をしたレスポンスインスタンスを返却 
		return response;
	}
	

	
}
