package com.irit.upnp;

import org.fourthline.cling.binding.annotations.UpnpService;
import org.fourthline.cling.binding.annotations.UpnpServiceId;
import org.fourthline.cling.binding.annotations.UpnpServiceType;

import java.beans.PropertyChangeSupport;

/**
 * Created by mkostiuk on 13/07/2017.
 */

@UpnpService(
        serviceId = @UpnpServiceId("SendLikeService"),
        serviceType = @UpnpServiceType(value = "SendLikeService", version = 1)
)
public class SendLikeService {

    private final PropertyChangeSupport propertyChangeSupport;

    public SendLikeService() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }


}
