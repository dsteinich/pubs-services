package gov.usgs.cida.pubs.domain;

import gov.usgs.cida.pubs.dao.intfc.IPublishingServiceCenterDao;
import gov.usgs.cida.pubs.domain.intfc.ILookup;

import java.io.Serializable;

public class PublishingServiceCenter extends BaseDomain<PublishingServiceCenter> implements ILookup, Serializable {

	private static final long serialVersionUID = -281712263572997816L;

	private static IPublishingServiceCenterDao pscDao;

    public static final Integer ARTICLE = 2;

    public static final Integer REPORT = 18;

    private String text;

	@Override
	public String getText() {
		return text;
	} 

	public void setText(final String inText) {
		text = inText;
	}

    public static IPublishingServiceCenterDao getDao() {
        return pscDao;
    }

    public void setPublishingServiceCenterDao(final IPublishingServiceCenterDao inPscDao) {
    	pscDao = inPscDao;
    }

}