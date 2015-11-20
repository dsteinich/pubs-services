package gov.usgs.cida.pubs.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

import gov.usgs.cida.pubs.busservice.intfc.IBusService;
import gov.usgs.cida.pubs.domain.Contributor;
import gov.usgs.cida.pubs.domain.CorporateContributor;
import gov.usgs.cida.pubs.domain.OutsideContributor;
import gov.usgs.cida.pubs.domain.PersonContributor;
import gov.usgs.cida.pubs.domain.UsgsContributor;
import gov.usgs.cida.pubs.json.View;
import gov.usgs.cida.pubs.utility.PubsUtilities;

@Controller
public class ContributorMvcService extends MvcService<Contributor<?>> {

    private static final Logger LOG = LoggerFactory.getLogger(ContributorMvcService.class);
	
	private IBusService<CorporateContributor> corporateContributorBusService;
	private IBusService<PersonContributor<?>> personContributorBusService;

	@Autowired
	public ContributorMvcService(@Qualifier("corporateContributorBusService")
			IBusService<CorporateContributor> corporateContributorBusService,
			@Qualifier("personContributorBusService")
			IBusService<PersonContributor<?>> personContributorBusService) {
		this.corporateContributorBusService = corporateContributorBusService;
		this.personContributorBusService = personContributorBusService;
	}
	
    @RequestMapping(value={"/contributor/{contributorId}"}, method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.PW.class)
    public @ResponseBody Contributor<?> getContributor(HttpServletRequest request, HttpServletResponse response,
                @PathVariable("contributorId") String contributorId) {
        LOG.debug("getContributor");
        setHeaders(response);
        Contributor<?> rtn = Contributor.getDao().getById(PubsUtilities.parseInteger(contributorId));
        if (null == rtn) {
        	response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return rtn;
    }
	
	@RequestMapping(value={"/person/{contributorId}"}, method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.PW.class)
	public @ResponseBody Contributor<?> getPerson(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("contributorId") String contributorId) {
		LOG.debug("getPerson");
        setHeaders(response);
        Contributor<?> rtn = (Contributor<?>) personContributorBusService.getObject(PubsUtilities.parseInteger(contributorId));
        if (null == rtn) {
        	response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return rtn;
	}
	
	@RequestMapping(value={"/usgscontributor"}, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.PW.class)
	@Transactional
	public @ResponseBody UsgsContributor createUsgsContributor(@RequestBody UsgsContributor person, HttpServletResponse response) {
		LOG.debug("createUsgsContributor");
        setHeaders(response);
        UsgsContributor result = (UsgsContributor) personContributorBusService.createObject(person);
		if (null != result && result.getValidationErrors().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return result;
	}
	
	@RequestMapping(value="/usgscontributor/{id}", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.PW.class)
    @Transactional
    public @ResponseBody UsgsContributor updateUsgsContributor(@RequestBody UsgsContributor person, @PathVariable String id, HttpServletResponse response) {
		LOG.debug("updateUsgsContributor");
        setHeaders(response);
        UsgsContributor castPerson = (UsgsContributor) personContributorBusService.updateObject(person);
        if (null != castPerson && (null == castPerson.getValidationErrors() || castPerson.getValidationErrors().isEmpty())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return castPerson;
    }
	
	@RequestMapping(value={"/outsidecontributor"}, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.PW.class)
	@Transactional
	public @ResponseBody OutsideContributor createOutsideContributor(@RequestBody OutsideContributor person,
			HttpServletResponse response) {
		LOG.debug("createOutsideContributor");
        setHeaders(response);
        OutsideContributor result = (OutsideContributor) personContributorBusService.createObject(person);
		if (null != result && result.getValidationErrors().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return result;
	}
	
	@RequestMapping(value="/outsidecontributor/{id}", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.PW.class)
    @Transactional
    public @ResponseBody OutsideContributor updateOutsideContributor(@RequestBody OutsideContributor person, @PathVariable String id, HttpServletResponse response) {
		LOG.debug("updateOutsideContributor");
        setHeaders(response);
        OutsideContributor castPerson = (OutsideContributor) personContributorBusService.updateObject(person);
        if (null != castPerson && (null == castPerson.getValidationErrors() || castPerson.getValidationErrors().isEmpty())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return castPerson;
    }
	
	@RequestMapping(value={"/corporation/{contributorId}"}, method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.PW.class)
	public @ResponseBody CorporateContributor getCorporation(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("contributorId") String contributorId) {
		LOG.debug("getCorporation");
        setHeaders(response);
        CorporateContributor rtn = corporateContributorBusService.getObject(PubsUtilities.parseInteger(contributorId));
        if (null == rtn) {
        	response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return rtn;
	}
	
	@RequestMapping(value={"/corporation"}, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.PW.class)
	@Transactional
	public @ResponseBody CorporateContributor createCorporation(@RequestBody CorporateContributor corporation, HttpServletResponse response) {
		LOG.debug("createCorporation");
        setHeaders(response);
        CorporateContributor result = corporateContributorBusService.createObject(corporation);
		if (null != result && result.getValidationErrors().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return result;
	}
	
	@RequestMapping(value="/corporation/{id}", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.PW.class)
    @Transactional
    public @ResponseBody CorporateContributor updateCorporation(@RequestBody CorporateContributor corporation, @PathVariable String id, HttpServletResponse response) {
		LOG.debug("updateCorporation");
        setHeaders(response);
        CorporateContributor result = corporateContributorBusService.updateObject(corporation);
        if (null != result && (null == result.getValidationErrors() || result.getValidationErrors().isEmpty())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }
}
