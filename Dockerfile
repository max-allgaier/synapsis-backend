FROM gradle:9-jdk21 AS build_image
WORKDIR /gradle/
COPY --chown=gradle:gradle gradle/ gradle/
COPY --chown=gradle:gradle gradlew ./
COPY --chown=gradle:gradle settings.gradle.kts build.gradle.kts ./
RUN ./gradlew --no-daemon dependencies
COPY --chown=gradle:gradle ./ ./
RUN ./gradlew --no-daemon clean build -x test

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app/
RUN addgroup -S app_group && adduser -S app_user -G app_group
USER app_user
COPY --from=build_image /gradle/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
