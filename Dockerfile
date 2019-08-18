FROM java:8
VOLUME /tmp
ADD build/libs/Fremdsprache.jar Fremdsprache.jar
RUN bash -c 'touch /Fremdsprache.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/Fremdsprache.jar"]



