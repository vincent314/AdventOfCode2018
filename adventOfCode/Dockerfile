FROM oracle/graalvm-ce:1.0.0-rc11 as builder
ADD ./build/libs/AdventOfCode2018-all.jar app.jar
RUN native-image -jar app.jar

FROM oracle/graalvm-ce:1.0.0-rc11
COPY --from=builder  app .
ENTRYPOINT ./app
