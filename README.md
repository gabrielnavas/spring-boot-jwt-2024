- Register

```shell
curl --location 'http://localhost:8080/auth/register' \
--header 'Content-Type: application/json'
--data-raw '{
"name": "navas",
"email": "navas@email.com",
"password": "123456"
}'
```

- Login

```shell
curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "navas@email.com",
    "password": "123456"
}'
```

- User

```shell
curl --location --request GET 'http://localhost:8080/user' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <token>'
```