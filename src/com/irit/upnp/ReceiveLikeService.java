package com.irit.upnp;

import com.irit.xml.LecteurXml;
import org.fourthline.cling.binding.annotations.*;

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

    private LecteurXml lec = new LecteurXml();

    @UpnpStateVariable(name = "LikedPAge")
    private String likedPage = "";

    @UpnpAction(name = "SetLikes")
    public void setPageLike(@UpnpInputArgument(name = "NewLikedPageValue") String l) {
        likedPage = l;
        getPropertyChangeSupport().firePropertyChange("likeReveived", "", lec.getLikes(likedPage));
    }
}
