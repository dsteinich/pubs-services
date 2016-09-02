package gov.usgs.cida.pubs.domain;

import gov.usgs.cida.pubs.dao.intfc.IPersonContributorDao;
import gov.usgs.cida.pubs.domain.intfc.ILookup;
import gov.usgs.cida.pubs.json.View;
import gov.usgs.cida.pubs.validation.constraint.ParentExists;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

@Component
@ParentExists
public class PersonContributor<D> extends Contributor<PersonContributor<D>> implements ILookup {

	private static IPersonContributorDao personContributorDao;

	@JsonProperty("family")
	@JsonView(View.PW.class)
	@NotNull
	@Length(min=1, max=40)
	private String family;

	@JsonProperty("given")
	@JsonView(View.PW.class)
	@Length(min=0, max=40)
	private String given;

	@JsonProperty("suffix")
	@JsonView(View.PW.class)
	@Length(min=0, max=40)
	private String suffix;

	@JsonProperty("email")
	@JsonView(View.PW.class)
	@Length(min=0, max=400)
	@Email
	private String email;

	@JsonProperty("orcid")
	@JsonView(View.PW.class)
	@Length(min=19, max=19)
	private String orcid;

	@JsonProperty("affiliation")
	@JsonView(View.PW.class)
	private Affiliation<? extends Affiliation<?>> affiliation;

	@JsonProperty("affiliations")
	@JsonView(View.PW.class)
	private Collection<Affiliation<? extends Affiliation<?>>> affiliations;

	@JsonIgnore
	private Integer ipdsContributorId;

	public String getFamily() {
		return family;
	}

	public void setFamily(final String inFamily) {
		family = inFamily;
	}

	public String getGiven() {
		return given;
	}

	public void setGiven(final String inGiven) {
		given = inGiven;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(final String inSuffix) {
		suffix = inSuffix;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String inEmail) {
		email = inEmail;
	}

	public String getOrcid() {
		return orcid;
	}

	public void setOrcid(String orcid) {
		this.orcid = orcid;
	}

	public Affiliation<? extends Affiliation<?>> getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(final Affiliation<? extends Affiliation<?>> inAffiliation) {
		affiliation = inAffiliation;
	}

	public Collection<Affiliation<? extends Affiliation<?>>> getAffiliations() {
		return affiliations;
	}

	public void setAffiliations(
			Collection<Affiliation<? extends Affiliation<?>>> affiliations) {
		this.affiliations = affiliations;
	}

	public Integer getIpdsContributorId() {
		return ipdsContributorId;
	}

	public void setIpdsContributorId(final Integer inIpdsContributorId) {
		ipdsContributorId = inIpdsContributorId;
	}

	@Override
	public String getText() {
		StringBuilder text = new StringBuilder();
		if (StringUtils.isNotBlank(family)) {
			text.append(family);
			if (StringUtils.isNotBlank(given) || StringUtils.isNotBlank(suffix)) {
				text.append(",");
			}
		}
		if (StringUtils.isNotBlank(given)) {
			text.append(" ").append(given);
		}
		if (StringUtils.isNotBlank(suffix)) {
			text.append(" ").append(suffix);
		}
		if (StringUtils.isNotBlank(email)) {
			text.append(" ").append(email);
		}
		return text.toString();
	}

	public static IPersonContributorDao getDao() {
		return personContributorDao;
	}

	@Autowired
	@Qualifier("personContributorDao")
	public void setPersonContributorDao(final IPersonContributorDao inPersonContributorDao) {
		personContributorDao = inPersonContributorDao;
	}
}