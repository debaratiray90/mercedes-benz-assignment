# userservice-service1
Userservice-service1 has three end points :
#/readUser/{userId} - GET
takes the userId as a path variable and communicates with userdata-service2 to fetch user data using http protocol (restTemplate). Data decrypted using AES algorithm. Keys are given in application.yml
#/saveUser - POST
saves user and saves it in CSV/XML file. Kafka Producer produces the message and post it in KafKa Topic - user-topic. Kafla locally set up to run at port 9092. Zookeeper port : 2181
#/updateUser - PUT
updates the existing user and saves it in CSV/XML file. Kafka Producer produces the message and post it in KafKa Topic - user-topic. Kafla locally set up to run at port 9092. Zookeeper port : 2181


#Json for PUT/POST :
{
    "userId": 3005,
    "name": "Debarati Ray",
    "dateOfBirth": "2020-10-12",
    "salary": 60000,
    "age": 29
}
