FROM openjdk:17
#ADD ./firebase_service_account_key.json firebase_service_account_key.json
ADD ./*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]