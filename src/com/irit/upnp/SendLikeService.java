package com.irit.upnp;

import org.fourthline.cling.binding.annotations.*;

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

    @UpnpStateVariable(name = "Like")
    private String like = "";

    @UpnpStateVariable(name = "Likes")
    private String likes = "";

    //Envoie un unique like pour une slide donnée
    @UpnpAction(name = "SendLike")
    public void sendLike(@UpnpInputArgument(name = "Like") String l) {
        String oldValue = like;
        like = l;
        getPropertyChangeSupport().firePropertyChange("Like", oldValue, like);
    }

    //Envoie l'ensemble des likes
    @UpnpAction(name = "SendLikes")
    public void sendLikes(@UpnpInputArgument(name = "Likes") String l) {
        String oldValue = likes;
        likes = l;
        getPropertyChangeSupport().firePropertyChange("Likes", oldValue, likes);
    }
}
