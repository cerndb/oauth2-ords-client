FROM jboss/wildfly

ADD target/oaut2-ords-client.war /opt/jboss/wildfly/standalone/deployments/

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0","--debug"]

