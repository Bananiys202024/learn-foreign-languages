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


<h2>Tutorials "How use" with "Arch Linux"</h2>

<h3>Docker</h3>

Clear all started containers

            docker rm -f $(docker ps -a -q) 	


Start

            ./gradlew bootJar

            docker-compose build

            docker-compose up
            
<h3>Redis</h3>

Start

            redis-cli
 
Revise all keys

            redis-cli --scan --pattern '*'
            
Revise type of key "Anton"

            type Anton
            
Check TTL of key "mykey"

            TTL mykey  

Set TTL to key "mykey" in seconds

            EXPIRE mykey 10

Delete key "mykey"
          
            del mykey
            
 Read hash key "mykey"
 
            HGETALL mykey

<h3>Mongodb</h3>

Show all databases

            show dbs; 	


Use database "tongues"

           use tongues
           
Show tables

           show tabes
           
Revise content of table "users"

           db.users.find();
           
Drop database
       
           db.dropDatabase()
            
            
           
<h2>Access</h2>

Back-end:  <a href="http://localhost:8083/">http://localhost:8083/</a> </br>
Front-end: <a href="http://localhost:8083/">http://localhost:4203/</a>
 
