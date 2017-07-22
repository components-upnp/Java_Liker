# Java_Liker
Composant qui reçoit/envoie un like pour une slide (dans le cadre d'une présentation)

<strong>Description : </strong>

Ce composant permet à un élève suivant un cours de faire un retour au professeur en likant la slide qui est affiché a son écran, 
en effet, dans le cas où il apprécie de le contenu de celle-ci il va appuyé sur le bouton de l'applcation présente, ce 
qui aura pour effet d'avertir les autres Likers présents dont celui du professeur. Ce dernier pourra finalement voir 
quelle slide a été le plus appréciée.

Ce composant offre une interface graphique qui est seulement composée d'un bouton permettant de liker la slide courante.

<strong>Lancement de l'application : </strong>

Pour lancer l'application, il suffit de lancer le .jar présent dans le dossier target. Pour générer celui-ci, lancer la phase 
maven install de projet.

<strong>Spécifications UPnP : </strong>

Ce composant offre les services UPnP suivant dont voici les descriptions :

  1) SendLikeService :
    a) public void SendLike(String like) : permet d'envoyer un évènement UPnP "Like" aux autres Likers lorsqu'une slide a 
    été likée.
    b) public void SendLikes(String likes) : permet d'envoyer un évènement UPnP "Likes" à l'afficheur de likes connecté lorsqu'il 
    y a changement de slide ou un nouveau like reçu.
  
  2) PageService :
    a) public void SetNumPage(String numPage) : reçoit un évènement UPnP contenant le numéro de la slide courante.
    
  3) ReceiveLikeService :
    a) public void SetLike(String likedPage) : reçoit un évènement UPnP contenant le numéro de slide lorsqu'une slide est likée
    par un autre Liker.
    
    
Voici le schéma représentant le composant:

![alt tag](https://github.com/components-upnp/Java_Liker/blob/master/Liker.png)

<strong>Maintenance : </strong>

Ce projet est un projet Maven dont la phase d'exécution install créé l'exécutable de l'application.
Aucun test unitaire n'a été produit pour l'instant.



