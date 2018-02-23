FROM anapsix/alpine-java:8
COPY quote/target/quote-1.0.jar /opt/app/quote/quote.jar
COPY eureka/target/eureka-1.0.jar /opt/app/eureka/eureka.jar
COPY ribbon/target/ribbon-1.0.jar /opt/app/ribbon/ribbon.jar
COPY admin/target/admin-1.0.jar /opt/app/admin/admin.jar
CMD java -jar /opt/app/eureka/eureka.jar \
    & java -Dserver.port=9000 -jar /opt/app/quote/quote.jar \
    & java -Dserver.port=9001 -jar /opt/app/quote/quote.jar \
    & java -Dserver.port=9002 -jar /opt/app/quote/quote.jar \
    & java -Dserver.port=9003 -jar /opt/app/quote/quote.jar \
    & java -jar /opt/app/ribbon/ribbon.jar \
    & java -jar /opt/app/admin/admin.jar \