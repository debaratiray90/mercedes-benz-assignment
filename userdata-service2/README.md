# userdata-service2
Controller Endpoint :
#getUserDetails - GET
Accepts user ID as Path Variable and fetches the data from the existing userId -> CSV/XML file which gets generated in the project path. Encrypts the data before sending it back to userservice-service1 using AES algorithm

#KafkaConsumer
Listens to the messages sent into Kafka topic -> user-topic and depending upon the event type - Update or Add, updates the existing records if present or creates a new record respectively.


Note : tried setting up Docker for schema registry for kafka-protobuf, but was getting error while connecting to the registry ( port : 8081) 