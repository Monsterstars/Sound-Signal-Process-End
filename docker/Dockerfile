FROM openjdk:16
RUN mkdir ./ssp
COPY ./ssp-1.0-SNAPSHOT.jar ./ssp/
ARG SPRING_PROFILES_ACTIVE
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
CMD java -jar ./ssp/ssp-1.0-SNAPSHOT.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE}