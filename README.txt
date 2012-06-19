"build once, run everywhere"

Unterschiedliche Zielumgebungen müssen unterschiedliche Konfigurationen haben.
Das betrifft insbosondere das Logging und die Anbindung an externe Systeme wie Datenbank und LDAP.

Systemvoraussetzungen für Deployment des war-files
- Java 6
- Tomcat 7 
- MySQL 5.x mit InnoDB
- LDAP getestet bisher mit ApacheDS

Tomcat7
   
   Mysql-connector-java-5.1.13.jar ins tomcat/lib Verzeichnis kopieren 
   jstl-1.2.jar                    ins tomcat/lib Verzeichnis kopieren
  Eintrag in <Tomcat>/conf/context.xml:
  
    <Resource
   		name="jdbc/umgr" 
   		auth="Container" 
   		description="Usermanager Database" 
   		driverClassName="com.mysql.jdbc.Driver" 
		initialSize="2" 
		maxActive="10" 
		username="tomcat" 
		password="tomcat" 
		type="javax.sql.DataSource" 
		factory="org.apache.tomcat.jdbc.pool.DataSourceFactory"
		url="jdbc:mysql://localhost:3306/umgr" 
		validationQuery="select 1" />	        
   (Daraus ergibt sich jndi-name="java:comp/env/jdbc/umgr")
   
   Für production müssen die Werte genauer angepasst werden! 
   Siehe: http://tomcat.apache.org/tomcat-7.0-doc/jdbc-pool.html
   
   
   Optional Umgebungsvariable 'spring.profiles.active' = [dev|prod|test], dev ist default
   z.B. production tomcat Startparamter = -Dspring.profiles.active=prod
   
MySQL
   1) Installieren
   2) Benutzer anlegen: name='tomcat', password='tomcat'
   3) Database umgr anlegen und dem tomcat user Schreib/Lese Rechte geben:
      Als Benutzer mit allen DB-Rechten (default: name='root', pwd='') eine mysql-konsole öffnen und folgende Statements ausführen:
        create database umgr CHARACTER SET utf8 COLLATE utf8_general_ci;
        GRANT ALL PRIVILEGES ON *.* TO 'tomcat'@'localhost' IDENTIFIED BY 'tomcat' WITH GRANT OPTION;
   
  
Tabellen anlegen
	mysql -u tomcat -p -h localhost umgr < generated-schema.sql 
	mysql -u tomcat -p -h localhost umgr < spring-security.dll

Tomcat starten
	
Login mit Testuser
	http://localhost:8080/umgr-vaadin
	User: jmey
	Passwd: dev;2011
	

   
ERLÄUTERUNG

Entweder lässt man Maven die Ersetzungen durch Profile und Filter machen. Dann muss beim Bauen des Projektes bereits ein Profil
angegeben werden (mvn clean package -Plive|-Ptest|-Pdev). Es wird also ein war-file für eine konkrete Zielumgebung gebaut, in der die
Platzhalter entsprechend ersetzt sind (DataSource und Logging-Level zum Beispiel)

Oder es wird ein war-file für alle beliebigen Umgebungen gebaut. Wenn die Application über Spring initialisiert wird, sucht Spring
nach einem System-Property (hier: spring.profiles.active).
Anhand dieses System-Properties werden die entsprechenden EnMT-Properties aus (src/main/resources/config/dev|prod|test) gezogen.

Beim EnMT wird letzteres benutzt. Werden in Dateien Ersetzungen notwendig, die außerhalb des Scopes von Spring liegen, muss noch
einmal über die Alternative nachgedacht werden.

SECURITY

in der web.xml ist der Filter (ein hook in das Spring Security Framework) eingestellt.
in der applicationContext-security.xml ist somit die Zugriffsrechte konfiguriert.

LOGIN
  * login-page. Diese URL wird aufgerufen, wenn noch keine gültige Session existiert.
    Hier ist die 'login.jsp' definiert. Diese JSP benutzt die Spring-Parameter
  * Authenticatin Manager. Hierüber wird der Login-Prozess gesteuert. Ihm ist der umgr-eigene
    UserService mittels <user-service-ref> zugeordnet, der den Benutzer anhand seines Loginnamens aus der DB holt. 
    (Alternative:   <jdbc-user-service> benutzen und konfigurieren; einfacher, aber beschränkter)
ZUGRIFFSKONTROLLE 
  * URL -> Zugriffsberechtigungen nach Rolle (in Vaadin nur eine URL für die App)
  

ECLIPSE
  Um mit Eclipse-WTP / m2e zu arbeiten, muss m2e ab version 0.12.0 verwendet werden, sonst funktioniert
  das Filtering nicht.
  TIP: Nachsehen, ob bei dem Webprojekt unter "Properties -> Deployment Assemblies" Fehler angezeit werden.
