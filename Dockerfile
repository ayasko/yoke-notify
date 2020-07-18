FROM adoptopenjdk/openjdk11:alpine
RUN addgroup -S yoke && adduser -S yokeadmin -G yoke
USER yokeadmin
EXPOSE 8582
WORKDIR /app
COPY build/distribution/BOOT-INF/lib /app/lib
COPY build/distribution/META-INF /app/META-INF
COPY build/distribution/BOOT-INF/classes /app
ENTRYPOINT [ "java", "-cp", "/app:/app/lib/*", "com.epam.yoke.notify.YokeNotifyApplication" ]