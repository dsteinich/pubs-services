package gov.usgs.cida.pubs.domain;

import gov.usgs.cida.pubs.json.view.intfc.IMpView;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;

public class PublicationCostCenter<D> extends BaseDomain<D> {

    @JsonIgnore
    private Integer publicationId;

    @JsonView(IMpView.class)
    @JsonUnwrapped
    private CostCenter costCenter;

    public Integer getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(final Integer inPublicationId) {
        publicationId = inPublicationId;
    }

    public CostCenter getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(final CostCenter inCostCenter) {
        costCenter = inCostCenter;
    }

    //We don't want the publicationCostCenter.id in the json view
    @JsonIgnore
    @Override
    public Integer getId() {
        return id;
    }
}