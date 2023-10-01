# MultiuserChat

### Task

Сервер представляет собой казино. Сервер объявляет начало тура. После этого в течении 10 секунд пользователи могут сделать ставку на число (@bet number). После этого сервер разыгрывает число и объявляет пользователя победителя.

### Compile project:

```bash
javac -d ./out ./src/*.java
```

### Run project:

Start Server:

```bash
java -cp ./out Server 4000
```

Start Client:

```bash
java -cp ./out Client localhost 4000
```
