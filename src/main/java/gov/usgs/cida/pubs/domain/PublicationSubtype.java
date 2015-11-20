package gov.usgs.cida.pubs.domain;

import gov.usgs.cida.pubs.dao.intfc.IDao;
import gov.usgs.cida.pubs.domain.intfc.ILookup;
import gov.usgs.cida.pubs.json.View;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author drsteini
 *
 */
public class PublicationSubtype extends BaseDomain<PublicationSubtype> implements ILookup, Serializable {

	private static final long serialVersionUID = 122305624975493957L;

	private static IDao<PublicationSubtype> publicationSubtypeDao;

    public static final Integer USGS_NUMBERED_SERIES = 5;

    public static final Integer USGS_UNNUMBERED_SERIES = 6;

    public static final Integer USGS_DATA_WEBSITE = 7;

    private PublicationType publicationType;

    private String text;

    private Collection<PublicationSeries> publicationSeries;

    /**
     * @return the publicationType
     */
    public PublicationType getPublicationType() {
        return publicationType;
    }

    /**
     * @param inPublicationType the publicationType to set
     */
    public void setPublicationType(final PublicationType inPublicationType) {
        publicationType = inPublicationType;
    }

    @Override
    @JsonView(View.Lookup.class)
    public String getText() {
        return text;
    }
    
	public void setText(String text) {
		this.text = text;
	}

	/**
     * @return the publicationSeries
     */
    public Collection<PublicationSeries> getpublicationSeries() {
        return publicationSeries;
    }

    /**
     * @param inPublicationSeries the publicationSeries to set
     */
    public void setPublicationSeries(final Collection<PublicationSeries> inPublicationSeries) {
        publicationSeries = inPublicationSeries;
    }

    /**
     * @return the publicationTypeDao
     */
    public static IDao<PublicationSubtype> getDao() {
        return publicationSubtypeDao;
    }

    /**
     * The setter for publicationSubtypeDao.
     * @param inPublicationSubtypeDao the publicationSubtypeDao to set
     */
    public void setPublicationSubtypeDao(final IDao<PublicationSubtype> inPublicationSubtypeDao) {
        publicationSubtypeDao = inPublicationSubtypeDao;
    }

}
