# Spring-Cloud-Project

Port Encryption Steps

To secure port configurations in my projects, I follow these three essential steps:

1. Define Encryption Key: In application.properties, I set encrypt.key=MY_SECRET_KEY, ensuring my encryption base is secure.

2. Encrypt Port Number: I use curl -X POST --data-urlencode "value=MY_PORT" http://localhost:8888/encrypt to encrypt the desired port number, replacing MY_PORT with the actual port.

3. Apply Encrypted Port: The encrypted port is then specified in the environment-specific properties file using server.port={cipher}ENCRYPTED_VALUE, where ENCRYPTED_VALUE is the outcome from the encryption step.
