FROM anapsix/alpine-java:8
COPY quote/target/quote-1.0.jar /opt/app/quote/quote.jar
COPY eureka/target/eureka-1.0.jar /opt/app/eureka/eureka.jar
CMD java -jar /opt/app/quote/quote.jar \
    & java -jar /opt/app/eureka/eureka.jar