package fr.esisar.frigolo.portail;

import javax.enterprise.event.Observes;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

public class HttpSecurityConfiguration {

    /**
     * Method which permits to manage the access on html pages according to one
     * user's role
     *
     * @param event
     *            the event to observe
     */
    public void onInit(@Observes SecurityConfigurationEvent event) {

        /**
         * creation of the builder permitting to manage the differents xhtml/jsf
         * paths
         */
        SecurityConfigurationBuilder builder = event.getBuilder();

        builder.http().forPath("/*.xhtml").authenticateWith().form().authenticationUri("/loginTonic.xhtml")
                .loginPage("/loginTonic.xhtml").errorPage("/error.xhtml").restoreOriginalRequest()

                .forPath("/admin/*").authorizeWith().role("administrateur").redirectTo("/forbidden.xhtml")
                .whenForbidden()

                .forPath("/admin/*").authorizeWith().role("administrateur").redirectTo("/error.xhtml").whenError();

    }

}
