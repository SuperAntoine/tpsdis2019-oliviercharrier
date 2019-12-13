.DEFAULT_GOAL := default

rest:
	mvn spring-boot:run -P rest

worker:
	mvn spring-boot:run -P worker

clientNotif:
	mvn spring-boot:run -P clientNotif

clientMessage:
	mvn spring-voot:run -p clientMessage

clean:
	mvn clean

h2:
	java -jar h2-1.4.200.jar

default:
	mvn help:active-profiles -P rest,worker,clientNotif,clientMessage