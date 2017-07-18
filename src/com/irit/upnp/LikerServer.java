package com.irit.upnp;

import com.irit.display.Fenetre;
import com.irit.main.Liker;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.UpnpServiceImpl;
import org.fourthline.cling.binding.annotations.AnnotationLocalServiceBinder;
import org.fourthline.cling.model.DefaultServiceManager;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.meta.*;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.UDADeviceType;
import org.fourthline.cling.model.types.UDN;


/**
 * Created by mkostiuk on 13/07/2017.
 */
public class LikerServer implements Runnable {

    private LocalService<ReceiveLikeService> receiveLikeService;
    private LocalService<SendLikeService> sendLikeService;
    private LocalService<PageService> pageService;

    @Override
    public void run() {
        final UpnpService upnpService = new UpnpServiceImpl();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                upnpService.shutdown();
            }
        });

        // Add the bound local device to the registry
        try {
            upnpService.getRegistry().addDevice(
                    createDevice()
            );
        } catch (ValidationException e) {
            e.printStackTrace();
        }

    }

    private LocalDevice createDevice() throws ValidationException {
        DeviceIdentity identity =
                new DeviceIdentity(
                        UDN.uniqueSystemIdentifier("Liker")
                );

        DeviceType type =
                new UDADeviceType("Diapo", 1);

        DeviceDetails details =
                new DeviceDetails(
                        "Liker",					// Friendly Name
                        new ManufacturerDetails(
                                "UPS-IRIT",								// Manufacturer
                                ""),								// Manufacturer URL
                        new ModelDetails(
                                "Liker",						// Model Name
                                "Composant qui reçoit/envoie des likes de chaque slide",	// Model Description
                                "v1" 								// Model Number
                        )
                );
        receiveLikeService =
                new AnnotationLocalServiceBinder().read(ReceiveLikeService.class);
        receiveLikeService.setManager(
                new DefaultServiceManager(receiveLikeService, ReceiveLikeService.class)
        );
        sendLikeService =
                new AnnotationLocalServiceBinder().read(SendLikeService.class);
        sendLikeService.setManager(
                new DefaultServiceManager(sendLikeService,SendLikeService.class)
        );
        pageService =
                new AnnotationLocalServiceBinder().read(PageService.class);
        pageService.setManager(
                new DefaultServiceManager(pageService,PageService.class)
        );


        new Fenetre(identity.getUdn().toString(), receiveLikeService, sendLikeService, pageService).setVisible(true);

        return new LocalDevice(
                identity, type, details,
                new LocalService[] {sendLikeService, receiveLikeService, pageService}
        );
    }
}
