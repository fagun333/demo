FROM eclipse-temurin:17-jre-focal

ENV JAR=demo-0.0.1-SNAPSHOT.jar

RUN apt-get update && apt-get install -y --no-install-recommends \
    curl=7.* \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /
COPY build/libs/${JAR} .
COPY entrypoint.sh .

RUN ["chmod", "+x", "entrypoint.sh"]

EXPOSE 8080

CMD ["./entrypoint.sh"]