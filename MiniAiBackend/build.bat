set DATASOURCE_URL="jdbc:mysql://localhost:3306/miniai?useUnicode=true&characterEncoding=utf8"
set DATASOURCE_USERNAME="root"
set DATASOURCE_PASSWORD="liaoxin0328"

set JWT_EXPIRE=604800
set JWT_SECRET="12345678"
set JWT_HEADER="HEADER"

mvn install -f pom.xml