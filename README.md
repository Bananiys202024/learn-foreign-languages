# learn-foreign-languages

<h2>Describe</h2>
 App for learning foreign languages

 App is developing...

<h2>Docker</h2>

Clear

            docker rm -f $(docker ps -a -q) 	


Start

            ./gradlew clean build

            docker-compose build

            docker-compose up

You will get mistake in time of execution folowing command, but it's okey, this command mandatory. It related with line in  application.properties "spring.data.mongodb.host=mongo", it related with port of mongodb, docker; 

            ./gradlew clean build

