Lancer la base de données
```
java -jar h2-1.4.200.jar
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