# tomcat donwload krta hai 
FROM tomcat:11   
# docket container ki working directory bnata hai 
RUN rm -rf /usr/local/tomcat/webapps/*  
#  project ke ander war copy krdeta hai 
COPY ORSProject04.war /usr/local/tomcat/webapps/ORSProject04.war

EXPOSE 8080

CMD ["catalina.sh", "run"]