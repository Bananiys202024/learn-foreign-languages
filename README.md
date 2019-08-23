# learn-foreign-languages

<h2>Describe</h2>
 App for learning foreign languages

 App is developing...



<h2>Start app</h2>


0. Get project

           $ git clone https://github.com/Bananiys202024/learn-foreign-languages
           cd learn-foreign-languages
 
1. Start back-end

           ./gradlew bootJar           
            docker-compose build
            docker-compose up
            
2. Start front-end 

            cd client
            npm install
            ng serve --port 4203
            
3. Go to <a href="http://localhost:4203/">http://localhost:4203/</a>


<h2>Docker</h2>

Clear all started containers

            docker rm -f $(docker ps -a -q) 	


Start

            ./gradlew bootJar

            docker-compose build

            docker-compose up

