# learn-foreign-languages
App for learning foreign languages

App is developing...


Clear

            docker rm -f $(docker ps -a -q) 	


Start

            ./gradlew clean build

            docker-compose build

            docker-compose up

You will get mistake in time of execution folowing command, but it's okey. It related with line in  application.properties "spring.data.mongodb.host=mongo", it related with port of mongodb, docker; 

            ./gradlew clean build
