/**
 * Thanks to http://martypitt.wordpress.com/2012/11/05/custom-json-views-with-spring-mvc-and-jackson/ for this pattern. 
 * @author drsteini
 *
 */

package gov.usgs.cida.pubs.domain.intfc;

public interface DataView {

    boolean hasView();

    Class<? extends BaseView> getView();

    Object getData();
}
