package com.dizitiveit.bms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dizitiveit.bms.config.Status;
import com.dizitiveit.bms.model.DrillingDetails;
import com.dizitiveit.bms.model.Revisit;
import com.dizitiveit.bms.model.SurveyBasicDetails;
import com.dizitiveit.bms.service.DrillingDetailsService;
import com.dizitiveit.bms.service.RevisitService;
import com.dizitiveit.bms.service.SurveyBasicDetailsService;

@Controller
public class MapController {
	
	@Autowired
	private SurveyBasicDetailsService surveyBasicDetailsService;
	
	@Autowired
	private DrillingDetailsService drillingDetailsService;
	
	@Autowired
	private RevisitService revisitService;
	
	@RequestMapping(value="/getMap",method=RequestMethod.GET)
	public String getMap(Model model)
	{
		List<SurveyBasicDetails> completed=surveyBasicDetailsService.findByStatus();
		
		List<SurveyBasicDetails> list=surveyBasicDetailsService.findBySurveyStatusAndStatus();
		
		List<SurveyBasicDetails> reject=surveyBasicDetailsService.findBySurveyStatusAndStatusReject();
		
		List<DrillingDetails> failure=drillingDetailsService.findByStatusAndDrillingStatusFailure(Status.ACTIVE,Status.FAILURE);
		
		List<DrillingDetails> Success=drillingDetailsService.findByStatusAndDrillingStatus(Status.ACTIVE,Status.SUCCESS);
		
		List<Revisit> revisit= revisitService.findAllBystatus();
		
		model.addAttribute("surveycomplete", completed);
		
		model.addAttribute("surveyaccept", list);
		
		model.addAttribute("surveyreject", reject);
		
		model.addAttribute("drillingFail", failure);
		
		model.addAttribute("drillingsuccess", Success);
		
		model.addAttribute("revisit", revisit);
		
		
		return "map";
	}

}
