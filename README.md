TP4 – Chat client/serveur avec sockets UDP

etape2: chat unidirectionnel (ServeurUDP, ClientUDP)
images/client11.png
images/client12.png
images/resultat1.png

Synthèse : (le client envoie des DatagramPacket préfixés par son nom ; le serveur affiche IP:port et contenu.)

Étape 3 : Chat Bidirectionnel et Diffusion à Plusieurs Clients
images/client21.png
images/client22.png
images/client23.png
images/resulata2.png
Synthèse : (le serveur stocke les SocketAddress dans un Set, retransmet à tous sauf l'expéditeur ; le client utilise un thread dédié à la réception.)