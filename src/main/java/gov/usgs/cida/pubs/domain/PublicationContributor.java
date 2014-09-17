package gov.usgs.cida.pubs.domain;

import gov.usgs.cida.pubs.json.view.intfc.IMpView;
import gov.usgs.cida.pubs.validation.constraint.ParentExists;
import gov.usgs.cida.pubs.validation.constraint.UniqueKey;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;

@UniqueKey(message = "{publication.indexid.duplicate}")
@ParentExists
public class PublicationContributor<D> extends BaseDomain<D> implements Serializable {

	private static final long serialVersionUID = 5911778679824879199L;

	private Integer publicationId;

    @JsonProperty("contributorType")
    @JsonView(IMpView.class)
    @NotNull
    private ContributorType contributorType;

    @JsonProperty("rank")
    @JsonView(IMpView.class)
    @Pattern(regexp="^\\d+$")
    private Integer rank;

    @JsonView(IMpView.class)
    @JsonUnwrapped
    @NotNull
    @Valid
    private Contributor<?> contributor;

    public Integer getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(final Integer inPublicationId) {
        publicationId = inPublicationId;
    }

    public ContributorType getContributorType() {
        return contributorType;
    }

    public void setContributorType(final ContributorType inContributorType) {
        contributorType = inContributorType;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(final Integer inRank) {
        rank = inRank;
    }

    public Contributor<?> getContributor() {
        return contributor;
    }

    public void setContributor(final Contributor<?> inContributor) {
        contributor = inContributor;
    }

}
