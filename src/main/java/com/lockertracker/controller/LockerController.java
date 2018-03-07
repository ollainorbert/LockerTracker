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
import com.lockertracker.resources.PageMessageIdConsts;
import com.lockertracker.resources.RoutingConsts;
import com.lockertracker.resources.ViewConsts;
import com.lockertracker.service.LockerService;
import com.lockertracker.service.MessageByLocaleService;
import com.lockertracker.service.exception.locker.BaseLockerException;

//#{__${attributumneve}__}
//#{__${attributumneve}__}
//#{__${attributumneve}__}
//#{__${attributumneve}__}
@Controller
public class LockerController {
	private LockerService lockerService;
	private MessageByLocaleService messageByLocaleService;

	@Autowired
	public LockerController(LockerService lockerService, MessageByLocaleService messageByLocaleService) {
		this.lockerService = lockerService;
		this.messageByLocaleService = messageByLocaleService;
	}

	@RequestMapping(RoutingConsts.LOCKERS)
	public ModelAndView lockers(Principal principal) throws BaseLockerException {
		List<LockerGUIModel> lockersForGUI = lockerService.getAllLockerWithUserBelongs(principal.getName());

		ModelAndView modelAndView = new ModelAndView(ViewConsts.LOCKERS);
		modelAndView.addObject(PageAttributeConsts.Locker.LOCKERLIST, lockersForGUI);

		return modelAndView;
	}

	@RequestMapping(value = RoutingConsts.RENT_LOCKER, method = RequestMethod.POST)
	public ModelAndView rentLocker(@RequestParam(value = PageAttributeConsts.Locker.RENT_PARAM_ID) final String id,
			Principal principal) throws BaseLockerException {
		lockerService.rentLocker(id, principal.getName());

		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));
		// modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG,
		// messageByLocaleService.getMessage(PageMessageIdConsts.RENT_SUCESS));

		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG, PageMessageIdConsts.RENT_SUCESS);

		return modelAndView;
	}

	@RequestMapping(value = RoutingConsts.RELEASE_LOCKER, method = RequestMethod.POST)
	public ModelAndView releaseLocker(@RequestParam(value = PageAttributeConsts.Locker.RENT_PARAM_ID) final String id,
			Principal principal, Model model) throws BaseLockerException {
		lockerService.releaseLockerById(id);

		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));
		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG,
				messageByLocaleService.getMessage(PageMessageIdConsts.RELEASE_SUCESS));

		return modelAndView;
	}

	@RequestMapping(RoutingConsts.RELEASE_ALL_LOCKER)
	public ModelAndView releaseAllLocker() throws BaseLockerException {
		lockerService.releaseAllLocker();
		return new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));
	}

}
