# ServletSimpleBook

**Requirements**
- gradle v8.3+
- java v19+
- tomcat v9.X.X+

### Task

### Configure/Compile project:
Download project:
```bash
git clone https://github.com/Danzo0l/ssanie-labi
```
and go to `servletSimpleBook` directory:
```bash
cd javaWeb/servletSimpleBook
```

#### Config
- Open `/src/main/config/config.ini.exp`
- Paste path for saving book into file
- Rename file from `config.ini.exp` to `config.ini`

#### Gradle build
- For build project in `.war` run

    ```bash
    gradle war
    ```
    or 
    ```bash
    gradle build
    ```

### Start tomcat:
1. In `apache-tomcat directory`:  
   - Windows
   ```bash
    .\bin\startup.bat
    ```
   - Linux
   ```bash
   ./bin/startup.sh
    ```

2. Add to  `"apache-tomcat\conf\tomcat-users.xml"`
    ```xml  
    <role rolename="admin-gui"/>
    <role rolename="manager-gui"/>
    <user username="admin" password="admin" roles="admin-gui,manager-gui"/>
    ```

3. Open link:  
   - [Apache-Tomcat](http://localhost:8080/manager/html/) and authorize on page.  
   - Upload `.war` file from `servletSimpleBook/build/libs`  

4. Project will be access by [http://localhost:8080/servlet-simple-book/](http://localhost:8080/servlet-simple-book/)