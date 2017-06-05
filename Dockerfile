FROM java:8

ARG version=latest

ENV log_level=WARN

ADD target/mobile-device-uberJar.jar mobile-device-uberJar.jar

CMD java -jar -Dlog.level=${log_level} \
              mobile-device-uberJar.jar
EXPOSE 9000
