FROM maven:3.3.9-jdk-8

ADD . /opt/app
RUN cd /opt/app && mvn compile package -DskipTestes=true
RUN chmod +x /opt/app/start.sh

CMD touch /opt/shared/sum.txt && touch /opt/shared/diff.txt && ./opt/app/start.sh