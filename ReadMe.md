Lancer la base de données
```
make h2
```
Lancer l'API REST
```
make rest
```
Lancer un worker
```
make worker
```
Lancer le client de notifications
```
make clientNotif
```
Lancer le client d'affichage des messages
```
make clientMessage
```
Envoyer un message
```
curl -X POST http://localhost:8080/messages?content=...
```
Supprimer un message
```
curl -X POST http://localhost:8080/messages/delete?id=...
```
Modifier un message
```
curl -X POST 'http://localhost:8080/messages/update?id=...&content=...'
```
Récupérer tous les messages
```
curl -X GET http://localhost:8080/messages
```