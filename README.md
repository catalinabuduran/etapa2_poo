# etapa2_poo

Am folosit ca schelet rezolvarea oficială a etapei I.

Am adaugat noi clase: 
- ARTIST - pentru userii de tip "artist". Am implementat cateva metode de care ma ajut in metodele din clasele User si Admin(metoda care sterge un anumit eveniment, metoda care verifica daca un artist are cantece duplicate, dar si una asemanatoare pentru evenimente).
- ANNOUNCEMENT - pentru anunturi
- EVENT
- MERCH
- HOST - pentru userii de tip "host". Ca in clasa album am implementat cateva metode de care ma ajut in alte metode.
- ALBUM
- INFOALBUM - clasa ce detine numele albumului si cantecele sale.
- InfoPODCAST - clasa ce retine episoadele podcastului.

In clasa User am modificat functia select, astfel incat sa setez pagina curenta a unui user in functie de tipul acestuia, dar si ownerul paginii curente. A implementat metodele:
- switchConnectionStatus - care verifica daca userul curent este online (in acest caz o sa si modifice starea in offline si se opreste timestampul) sau daca este offline (il fac online si setez timestampul pe resume).
- addUser - care verifica fiecare caz reprezentativ mesajului pe care il afisez si apoi adaug userul in lista de useri din Admin, setand pagina curenta si tipul acestuia.
- addPodcast - verific fiecare caz reprezentativ mesajului pe care il afisez. Adaug podcastul in lista generala de podcasturi, in lista hostului de podcasturi, dar si episoadele acestuia.
- addAlbum - asemanatoare metodei addPodcast, dar care adauga cantecele albumului si albumul in sine, fiecare in lista corespunzatoare.
- addEvenet - asemaantor metodelor de mai sus
- removeEvent - sterge un event din lista de events a artistului daca acesta se afla, de aceea parcurg lista de events si retin evenimentul pe care trebuie sa l sterg, apoi ma ajut de metoda implementata in clasa Event.
- isValidDate - aici am folosit ajutor din partea lui ChatGpt.
- addMerch - asemanatoare celorlalte metode addAlbum, addEvent, etc.
- printCurrentPage - afisez pagina curenta a userului curent. Am cautat pe GPT partea cu StringBuilder ca nu stiam sa afisez in acel format.
- removePodcast
- addAnnouncement
- deleteUser - sterg un user in functie de tipul acestuia, tratand diferite cazuri. Ma ajut de metoda "hasUserInteractions", care verifica daca:
        - pentru un artist: daca exista un alt user in lista care asculta vreun album al acestuia sau daca are in playlist vreun cantec ce apartine vreunui album al artistului
        - pentru un host: daca exista un alt user in lista care vizioneaza vreun podcast sau vreun episod din podcasturile hostului.
        - pentru un user normal: daca exista vreun alt user care asculta vreun playlist de al acestuia.
    In cazul in care metoda aceasta returneaza false, atunci artistul / hostul / userul poate fi sters. Am luat in considerare sa sterg din toate listele din care facea parte.
- changePage - care schimba pagina curenta a userului. Am verificat prin metoda "isValidPage" daca pagina pe care doream ca userul sa mearga, e valida(daca e Home sau LikedContent).
- removeAlbum - metoda ce verifica daca un album poate fi sau nu sters. M am ajutat de metoda "isSongInPlaylist" pentru a verifica daca vreun alt user are in playlistul sau vreo melodie din acest album.

In clasa Admin am implementat metodele:
- showPodcasts - care afiseaza podcasturile unui host cu un nume dorit.
- getArtists / getHosts - care afiseaza din lista de useri doar pe cei de tip artist / host.
- onlineUsers - care afiseaza tot utilizatorii online
- allUsers - care afiseaza tot utilizatorii
- getTop5Albums - care afiseaza top 5 cele mai apreciate albume
- getTop5Songs
- showAlbums
Am folosit gpt in commandRunner cand trebuia sa afisez pentru fiecare podcast numele si episoadele.
Am folosit ca design pattern: singleton.