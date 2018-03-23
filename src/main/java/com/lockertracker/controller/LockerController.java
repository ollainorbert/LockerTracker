package com.lockertracker.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.lockertracker.service.exception.locker.BaseLockerException;

@Controller
public class LockerController {
	private LockerService lockerService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public void setLockerService(LockerService lockerService) {
		this.lockerService = lockerService;
	}

	@RequestMapping(RoutingConsts.LOCKERS)
	public ModelAndView lockers(Principal principal) throws BaseLockerException {
		List<LockerGUIModel> lockersForGUI = lockerService.getAllLockerWithUserBelongs(principal.getName());
		lockersForGUI.forEach(x -> logger.info(x.toString()));

		ModelAndView modelAndView = new ModelAndView(ViewConsts.LOCKERS);
		modelAndView.addObject(PageAttributeConsts.Locker.LOCKERLIST, lockersForGUI);

		return modelAndView;
	}

	@RequestMapping(value = RoutingConsts.RENT_LOCKER, method = RequestMethod.POST)
	public ModelAndView rentLocker(@RequestParam(value = PageAttributeConsts.Locker.RENT_PARAM_ID) final String id,
			Principal principal) throws BaseLockerException {
		lockerService.rentLocker(id, principal.getName());

		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));
		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG, PageMessageIdConsts.RENT_SUCCESS);

		return modelAndView;
	}

	@RequestMapping(value = RoutingConsts.RELEASE_LOCKER, method = RequestMethod.POST)
	public ModelAndView releaseLocker(@RequestParam(value = PageAttributeConsts.Locker.RENT_PARAM_ID) final String id,
			Principal principal, Model model) throws BaseLockerException {
		lockerService.releaseLockerById(id);

		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));
		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG, PageMessageIdConsts.RELEASE_SUCCESS);

		return modelAndView;
	}

	@RequestMapping(RoutingConsts.RELEASE_ALL_LOCKER)
	public ModelAndView releaseAllLocker() throws BaseLockerException {
		lockerService.releaseAllLocker();
		return new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));
	}

	/**
	 * ----------------------------------------------------------------------------------------------------------------
	 * Ajax - jQuery tests
	 * ----------------------------------------------------------------------------------------------------------------
	 */

	@RequestMapping("lockers1")
	public ModelAndView lockers1(Principal principal) throws BaseLockerException {
		List<LockerGUIModel> lockersForGUI = lockerService.getAllLockerWithUserBelongs(principal.getName());

		ModelAndView modelAndView = new ModelAndView("lockers1");
		modelAndView.addObject(PageAttributeConsts.Locker.LOCKERLIST, lockersForGUI);

		return modelAndView;
	}

	@RequestMapping("lockers2")
	public ModelAndView lockers2(Principal principal) throws BaseLockerException {
		List<LockerGUIModel> lockersForGUI = lockerService.getAllLockerWithUserBelongs(principal.getName());

		ModelAndView modelAndView = new ModelAndView("lockers2");
		modelAndView.addObject(PageAttributeConsts.Locker.LOCKERLIST, lockersForGUI);

		return modelAndView;
	}

	// lekerem id-bol, es meghatarozom hogy renting vagy releaseing
	@RequestMapping(value = "/lockersAjax1/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<LockerGUIModel>> testAjaxCall1(Principal principal, @PathVariable("id") long id)
			throws BaseLockerException {
		lockerService.rentLocker(("" + id), principal.getName());

		List<LockerGUIModel> lockersForGUI = lockerService.getAllLockerWithUserBelongs(principal.getName());

		return new ResponseEntity<List<LockerGUIModel>>(lockersForGUI, HttpStatus.OK);
	}

	@RequestMapping(value = "/lockersAjax2/{id}", method = RequestMethod.POST)
	public ResponseEntity<List<LockerGUIModel>> testAjaxCall2(Principal principal, @PathVariable("id") long id)
			throws BaseLockerException {
		lockerService.rentLocker(("" + id), principal.getName());

		List<LockerGUIModel> lockersForGUI = lockerService.getAllLockerWithUserBelongs(principal.getName());

		return new ResponseEntity<List<LockerGUIModel>>(lockersForGUI, HttpStatus.OK);
	}

	/**
	 * ----------------------------------------------------------------------------------------------------------------
	 * Ajax - jQuery tests
	 * ----------------------------------------------------------------------------------------------------------------
	 */

}
