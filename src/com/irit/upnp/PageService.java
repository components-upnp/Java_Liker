package com.irit.upnp;

import com.irit.xml.LecteurXml;
import org.fourthline.cling.binding.annotations.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by mkostiuk on 17/07/2017.
 */
@UpnpService(
        serviceType = @UpnpServiceType(value = "PageService", version = 1),
        serviceId = @UpnpServiceId("PageService")
)
public class PageService {

    private final PropertyChangeSupport propertyChangeSupport;

    public PageService() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    @UpnpStateVariable(name = "NumPage")
    private String numPage = "";

    @UpnpAction(name = "SetNumPage")
    public void setNumPage(@UpnpInputArgument(name = "NumPage") String n) throws IOException, SAXException, ParserConfigurationException {
        numPage = n;

        LecteurXml lec = new LecteurXml(n);

        getPropertyChangeSupport().firePropertyChange("numPage", "", lec.getPageCourante());
    }
}
