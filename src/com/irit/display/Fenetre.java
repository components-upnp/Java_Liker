/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irit.display;

import com.irit.main.Liker;
import com.irit.upnp.PageService;
import com.irit.upnp.ReceiveLikeService;
import com.irit.upnp.SendLikeService;
import com.irit.xml.GenerateurXml;
import org.fourthline.cling.model.meta.LocalService;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author mkostiuk
 */
public class Fenetre extends javax.swing.JFrame {

    private LocalService<ReceiveLikeService> receiveLikeService;
    private LocalService<SendLikeService> sendLikeService;
    private LocalService<PageService> pageService;
    private String numPageCourante;
    private Liker liker;
    private GenerateurXml gen;
    private String udn;

    /**
     * Creates new form Fenetre
     */
    public Fenetre(String u, LocalService<ReceiveLikeService> r, LocalService<SendLikeService> s, LocalService<PageService> p) {
        initComponents();
        udn = u;
        receiveLikeService = r;
        sendLikeService = s;
        pageService = p;

        liker = new Liker();
        gen = new GenerateurXml();

        numPageCourante = "0";

        receiveLikeService.getManager().getImplementation().getPropertyChangeSupport()
                .addPropertyChangeListener(
                        evt -> {
                            if (evt.getPropertyName().equals("likeReveived")) {
                                liker.addLike(evt.getPropertyName());
                            }
                        }
                );

        pageService.getManager().getImplementation().getPropertyChangeSupport()
                .addPropertyChangeListener(
                        evt -> {
                            if (evt.getPropertyName().equals("numPage")) {
                                numPageCourante = (String) evt.getNewValue();
                            }
                        }
                );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        likeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        likeButton.setText("Like");
        likeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    likeButtonActionPerformed(evt);
                } catch (TransformerException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(likeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(likeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void likeButtonActionPerformed(java.awt.event.ActionEvent evt) throws TransformerException, ParserConfigurationException {//GEN-FIRST:event_likeButtonActionPerformed
        sendLikeService.getManager().getImplementation().sendLikes(
                gen.getDocXml(udn, numPageCourante)
        );
        sendLikeService.getManager().getImplementation().sendLikes(
                ""
        );
    }//GEN-LAST:event_likeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton likeButton;
    // End of variables declaration//GEN-END:variables
}
