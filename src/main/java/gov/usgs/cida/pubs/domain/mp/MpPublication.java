package gov.usgs.cida.pubs.domain.mp;

import gov.usgs.cida.pubs.dao.intfc.IMpPublicationDao;
import gov.usgs.cida.pubs.domain.Publication;
import gov.usgs.cida.pubs.json.view.intfc.IMpView;
import gov.usgs.cida.pubs.validation.ValidatorResult;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author drsteini
 *
 */
//@JsonTypeName ("mpPublication")
@JsonPropertyOrder({"id", "type", "genre", "collection-title", "number", "subseries-title", "chapter-number",
    "sub-chapter-number", "title", "abstract", "language", "publisher", "publisher-place", "DOI", "ISSN", "ISBN", "number-of-pages",
    "page-first", "page-last", "author", "editor", "display-to-public-date", "indexID", "collaboration", 
    "usgs-citation", "cost-center", "links", "product-description", "online-only", "additional-online-files",
    "temporal-start", "temporal-end", "notes", "contact", "ipds-id", "ipds-review-process-state", "ipds-internal-id",
    "validationErrors"})
public class MpPublication extends Publication<MpPublication> {

    private static final long serialVersionUID = 8072814759958143994L;

    private static IMpPublicationDao mpPublicationDao;

    //TODO the following might be a hack - check on refactoring ValidationErrors so this is not needed.
    @JsonProperty("validation-errors")
    @JsonView(IMpView.class)
    public List<ValidatorResult> getValErrors() {
        if (null != validationErrors) {
            return validationErrors.getValidatorResults();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * @return the mpPublicationDao
     */
    public static IMpPublicationDao getDao() {
        return mpPublicationDao;
    }

    /**
     * The setter for mpPublicationDao.
     * @param inMpPublicationDao the MpPublicationDao to set
     */
    public void setMpPublicationDao(final IMpPublicationDao inMpPublicationDao) {
        mpPublicationDao = inMpPublicationDao;
    }

}
