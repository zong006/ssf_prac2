# FROM maven:3.9.9-eclipse-temurin-23

# ARG APP_DIR=/APP

# # directory where either source code resides or i copy my project to 
# WORKDIR ${APP_DIR}

# # copy required files into the image
# COPY pom.xml .
# COPY mvnw .
# COPY mvnw.cmd .
# COPY src src
# COPY .mvn .mvn 

# # package application using RUN directive 
# # downloads dependencies defines in pom.xml. then compile and package as jar
# RUN mvn package -Dmaven.test.skip=true

# RUN apt update && apt install -y curl


# ENV SERVER_PORT=3000
# EXPOSE ${SERVER_PORT}

# HEALTHCHECK --interval=10s --timeout=5s --start-period=3s --retries=3 \
#    CMD curl http://localhost:${SERVER_PORT}/health || exit 1

# ENTRYPOINT SERVER_PORT=${SERVER_PORT} java -jar target/ssf_prac2-0.0.1-SNAPSHOT.jar



# ---------------------------- STAGE 1 ----------------------------
FROM maven:3.9.9-eclipse-temurin-23 AS compiler

ARG COMPIILE_DIR=/code_folder

WORKDIR ${COMPIILE_DIR}

COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn 

RUN mvn package -Dmaven.test.skip=true

# ---------------------------- STAGE 1 ----------------------------

# ---------------------------- STAGE 2 ----------------------------

FROM maven:3.9.9-eclipse-temurin-23

ARG DEPLOY_DIR=/app

WORKDIR ${DEPLOY_DIR}
COPY --from=compiler /code_folder/target/ssf_prac2-0.0.1-SNAPSHOT.jar target/ssf_prac2.jar
COPY events.json .

RUN apt update && apt install -y curl

ENV SERVER_PORT=3000
EXPOSE ${SERVER_PORT}

HEALTHCHECK --interval=10s --timeout=5s --start-period=3s --retries=3 \
   CMD curl http://localhost:${SERVER_PORT}/health || exit 1

ENTRYPOINT SERVER_PORT=${SERVER_PORT} java -jar target/ssf_prac2.jar

# ---------------------------- STAGE 2 ----------------------------
    