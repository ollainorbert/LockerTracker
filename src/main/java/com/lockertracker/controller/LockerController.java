package com.lockertracker.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lockertracker.model.LockerGUIModel;
import com.lockertracker.resources.PageAttributeConsts;
import com.lockertracker.resources.RoutingConsts;
import com.lockertracker.resources.ViewConsts;
import com.lockertracker.service.LockerService;
import com.lockertracker.service.exception.locker.BaseLockerException;

@Controller
public class LockerController {
	private LockerService lockerService;

	@Autowired
	public void setLockerService(LockerService lockerService) {
		this.lockerService = lockerService;
	}

	@RequestMapping(RoutingConsts.LOCKERS)
	public String lockers(Model model, Principal principal) throws BaseLockerException {
		List<LockerGUIModel> lockersForGUI = lockerService.getAllLockerWithUserBelongs(principal.getName());

		model.addAttribute(PageAttributeConsts.Locker.LOCKERLIST, lockersForGUI);

		return ViewConsts.LOCKERS;
	}

	@RequestMapping(value = RoutingConsts.RENT_LOCKER, method = RequestMethod.POST)
	public ModelAndView rentLocker(@RequestParam(value = PageAttributeConsts.Locker.RENT_PARAM_ID) final String id,
			Principal principal) throws BaseLockerException {
		lockerService.rentLocker(id, principal.getName());

		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));
		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG, "Renting success!");

		return modelAndView;
	}

	@RequestMapping(value = RoutingConsts.RELEASE_LOCKER, method = RequestMethod.POST)
	public ModelAndView releaseLocker(@RequestParam(value = PageAttributeConsts.Locker.RENT_PARAM_ID) final String id,
			Principal principal) throws BaseLockerException {
		lockerService.releaseLockerById(id);

		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));
		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG, "Release success!");

		return modelAndView;
	}

	@RequestMapping(RoutingConsts.RELEASE_ALL_LOCKER)
	public String releaseAllLocker() throws BaseLockerException {
		lockerService.releaseAllLocker();
		return ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS);
	}

}
