FROM anapsix/alpine-java:8
COPY quote/target/quote-1.0.jar /opt/app/quote/quote.jar
CMD java -jar /opt/app/quote/quote.jar