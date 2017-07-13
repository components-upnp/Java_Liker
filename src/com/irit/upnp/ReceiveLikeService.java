package com.irit.upnp;

import org.fourthline.cling.binding.annotations.UpnpService;
import org.fourthline.cling.binding.annotations.UpnpServiceId;
import org.fourthline.cling.binding.annotations.UpnpServiceType;

import java.beans.PropertyChangeSupport;

/**
 * Created by mkostiuk on 13/07/2017.
 */

@UpnpService(
        serviceType = @UpnpServiceType(value = "ReceiveLikeService", version = 1),
        serviceId = @UpnpServiceId("ReceiveLikeService")
)
public class ReceiveLikeService {

    private final PropertyChangeSupport propertyChangeSupport;

    public ReceiveLikeService() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }


}
