To test messaging use command like that

curl -i \
-H "Accept: application/json" \
-H "Content-Type:application/json" \
-X POST --data '{"message": "test-message"}' "http://localhost:8080/send"
