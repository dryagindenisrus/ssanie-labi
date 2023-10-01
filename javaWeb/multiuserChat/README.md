# MultiuserChat

### Task (dop)

Сделать функцию бана черех команду `@ban <username>`.  
Каждый пользователь может добавлять других пользователей в свой чёрный список (хранится на сервере). Сообщения от пользователей из чс не приходят.

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
