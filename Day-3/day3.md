
* Architecture
* Tools used
* Step-by-step Azure VM setup
* Nginx + Java reverse proxy
* How to run the project
* And your learning outcomes

---

# 🚀 Day 3 – Java + Nginx Deployment on Azure VM (Simple Task Manager)

## 📌 Project Overview

Built and deployed a **Simple Task Manager** application using:

* **Frontend:** HTML + CSS (served by Nginx)
* **Backend:** Java (OpenJDK 17) custom HTTP server
* **Infrastructure:** Azure Virtual Machine (Ubuntu)
* **Web Server:** Nginx (Reverse Proxy)
* **Ports:**

  * `80` → Public (Nginx)
  * `8080` → Internal (Java backend)

Nginx serves the static frontend files and forwards API requests to the Java backend running on port `8080`.

---

## 🏗️ Architecture

```
User Browser
     |
     |  HTTP :80
     v
   Nginx  ─────────────►  Static Files (HTML, CSS)
     |
     |  Reverse Proxy (/api)
     v
 Java Server (Port 8080)
```

---

## 

* Azure VM (Ubuntu)
* Nginx
* OpenJDK 17
* Java (Socket Programming)
* HTML, CSS, JavaScript
* Git & GitHub

---

## 📂 Project Structure

```
taskapp/
 ├── TaskServer.java
 ├── TaskServer.class
 ├── index.html
 ├── styles.css
```

---

## 🧑‍💻 Backend: Java Server (TaskServer.java)

Simple Java HTTP server using `ServerSocket` that listens on port `8080` and handles POST requests.

```java
import java.io.*;
import java.net.*;

public class TaskServer {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Java server running on port " + port);

            while (true) {
                Socket client = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                OutputStream out = client.getOutputStream();

                String requestLine = in.readLine();
                System.out.println("Request: " + requestLine);

                int contentLength = 0;
                String line;
                while (!(line = in.readLine()).isEmpty()) {
                    if (line.startsWith("Content-Length:")) {
                        contentLength = Integer.parseInt(line.split(":")[1].trim());
                    }
                }

                char[] body = new char[contentLength];
                if (contentLength > 0) {
                    in.read(body, 0, contentLength);
                }

                String requestBody = new String(body);
                System.out.println("Body: " + requestBody);

                String responseBody = "Task received: " + requestBody;

                String httpResponse =
                        "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/plain\r\n" +
                        "Content-Length: " + responseBody.getBytes().length + "\r\n" +
                        "Connection: close\r\n" +
                        "\r\n" +
                        responseBody;

                out.write(httpResponse.getBytes());
                out.flush();
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

---

## 🌐 Frontend: index.html

```html
<!DOCTYPE html>
<html>
<head>
    <title>Simple Task Manager</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>My Task Manager</h1>

    <input type="text" id="taskInput" placeholder="Enter a task">
    <button onclick="addTask()">Add Task</button>

    <p id="result"></p>

    <script>
        function addTask() {
            let task = document.getElementById("taskInput").value;

            fetch("/api", {
                method: "POST",
                body: "ADD " + task
            })
            .then(res => res.text())
            .then(data => {
                document.getElementById("result").innerText = data;
            });
        }
    </script>
</body>
</html>
```

---

## 🎨 styles.css

```css
body {
    font-family: Arial;
    background: #f2f2f2;
    text-align: center;
    margin-top: 50px;
}

input {
    padding: 10px;
    width: 200px;
}

button {
    padding: 10px 15px;
    margin-left: 5px;
    cursor: pointer;
}

#result {
    margin-top: 20px;
    font-weight: bold;
}
```

---

## ☁️ Azure VM Setup (Using Laptop CLI)

1. Login to Azure:

```bash
az login
```

2. Create Resource Group:

```bash
az group create --name devops-practice --location centralindia
```

3. Create VM:

```bash
az vm create \
  --resource-group devops-practice \
  --name DevOpsVM \
  --image Ubuntu2204 \
  --admin-username pranav \
  --generate-ssh-keys
```

4. Open Port 80:

```bash
az vm open-port --resource-group devops-practice --name DevOpsVM --port 80
```

---

## 🔧 Install Required Software on VM

```bash
sudo apt update
sudo apt install nginx openjdk-17-jdk -y
```

Check:

```bash
nginx -v
java -version
```

---

## 📁 Deploy Frontend to Nginx

```bash
sudo mkdir -p /var/www/html/taskapp
sudo cp index.html styles.css /var/www/html/taskapp/
```

---

## ⚙️ Compile & Run Java Server

```bash
javac TaskServer.java
java TaskServer
```

This runs on:

```
http://localhost:8080
```

---

## 🔁 Configure Nginx Reverse Proxy

Edit Nginx config:

```bash
sudo nano /etc/nginx/sites-available/default
```

Add inside `server {}`:

```nginx
location / {
    root /var/www/html/taskapp;
    index index.html;
}

location /api {
    proxy_pass http://127.0.0.1:8080;
}
```

Test and restart:

```bash
sudo nginx -t
sudo systemctl restart nginx
```

---

## 🌍 Access the App

Open in browser:

```
http://<VM_PUBLIC_IP>
```

Now:

* Nginx serves HTML/CSS
* `/api` requests go to Java backend
* Java responds with: `Task received: ...`

---

## ✅ 

* Creating Azure VM using CLI
* Opening ports and networking basics
* Installing and configuring Nginx
* Running Java backend on Linux
* Setting up Nginx reverse proxy
* Serving frontend + backend together