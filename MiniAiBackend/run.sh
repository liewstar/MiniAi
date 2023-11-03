mvn clean;

export JAVA_HOME=/usr/local/java/jdk17

export DATASOURCE_URL="jdbc:mysql://172.23.0.2:3306/miniai?useUnicode=true&useSSL=false&allowPublicKeyRetrievals=true&characterEncoding=utf8"
export DATASOURCE_USERNAME="root"
export DATASOURCE_PASSWORD="12345678"

export JWT_EXPIRE=604800
export JWT_SECRET="12345678"
export JWT_HEADER="HEADER"

mvn spring-boot:run -f pom.xml