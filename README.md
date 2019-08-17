# learn-foreign-languages

<h2>Describe</h2>
 App for learning foreign languages

 App is developing...



<h2>Start app</h2>
1. Start back-end

           ./gradlew clean build           
            docker-compose build
            docker-compose up
            
2, Start front-end from path "learn-foreign-languages/client/"

            npm install
            ng serve --port 4203
            
3. Go to <a href="http://localhost:4203/">http://localhost:4203/</a>


<h2>Docker</h2>

Clear

            docker rm -f $(docker ps -a -q) 	


Start

            ./gradlew clean build

            docker-compose build

            docker-compose up

You will get mistake in time of execution folowing command, but it's okey, this command mandatory. It related with line in  application.properties "spring.data.mongodb.host=mongo", it related with port of mongodb, docker; 

            ./gradlew clean build

