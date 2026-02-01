

# 🚀 Azure DevOps Practice – Day 2: Nginx Web Server & App Deployment

## 📌 Overview

This day focuses on:

* Using **Azure CLI from Windows CMD**
* Creating and managing Azure VM resources
* Connecting to Ubuntu VM using **SSH (Windows & Mobile)**
* Updating and upgrading Ubuntu server packages
* Installing and configuring **Nginx web server**
* Opening **Port 80** using Azure CLI (NSG rule)
* Verifying web server from browser
* Deploying a **Simple Calculator Web App (HTML, CSS, JS)** on Nginx
* Editing live server files and testing from browser

---

## 🛠️ What I Did (Step-by-Step)

### 1️⃣ Logged into Azure from Windows CMD

```bash
az login
```

Successfully authenticated and selected the subscription.

---

### 2️⃣ Used Existing Resource Group & VM

Checked resource groups:

```bash
az group list -o table
```

Created VM (if not already created):

```bash
az vm create \
  --resource-group devops-practice \
  --name UbuntuVm \
  --image Ubuntu2204 \
  --size Standard_B1s \
  --admin-username pranav \
  --generate-ssh-key
```

---

### 3️⃣ Connected to VM from Windows

```bash
ssh pranav@<PUBLIC_IP>
```

Login successful to **Ubuntu 22.04** server.

---

### 4️⃣ Updated & Upgraded Server Packages

```bash
sudo apt update
sudo apt upgrade -y
```

Ensured the system is fully up to date.

---

### 5️⃣ Installed Nginx Web Server

```bash
sudo apt install -y nginx
```

Started and checked status:

```bash
sudo systemctl start nginx
sudo systemctl status nginx
```

---

### 6️⃣ Opened Port 80 Using Azure CLI

```bash
az vm open-port --resource-group devops-practice --name UbuntuVm --port 80
```

This updated the **Network Security Group (NSG)** to allow HTTP traffic.

---

### 7️⃣ Verified Nginx from Browser

Opened in browser:

```
http://<PUBLIC_IP>
```

Result:

> ✅ “Welcome to nginx!” page displayed successfully

This confirmed:

* VM is reachable from internet
* Port 80 is open
* Nginx is running correctly

---

### 8️⃣ Deployed Simple Calculator Web App on Nginx

Navigated to web root:

```bash
cd /var/www/html
mkdir calculator
```

Created files in /var/www/html/calculator:

* `index.html`
* `styles.css`
* `script.js`

#### 📄 index.html

```html
<!DOCTYPE html>
<html>
    <head>
        <title>Simple Calculator</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <div class="calculator">
            <h2>Simple Calculator</h2>
            <input type="text" id="result" readonly>

            <div class="buttons">
                <button onclick="clearResult()">C</button>
                <button onclick="appendValue('7')">7</button>
                <button onclick="appendValue('8')">8</button>
                <button onclick="appendValue('9')">9</button>
                <button onclick="appendValue('/')">/</button>

                <button onclick="appendValue('4')">4</button>
                <button onclick="appendValue('5')">5</button>
                <button onclick="appendValue('6')">6</button>
                <button onclick="appendValue('*')">*</button>

                <button onclick="appendValue('1')">1</button>
                <button onclick="appendValue('2')">2</button>
                <button onclick="appendValue('3')">3</button>
                <button onclick="appendValue('-')">-</button>

                <button onclick="appendValue('0')">0</button>
                <button onclick="calculate()">=</button>
                <button onclick="appendValue('+')">+</button>
            </div>
        </div>
        <script src="script.js"></script>
    </body>
</html>
```

---

#### 📄 script.js

```javascript
function appendValue(value){
    document.getElementById("result").value += value;
}

function clearResult(){
    document.getElementById("result").value = "";
}

function calculate() {
    try {
        let res = eval(document.getElementById("result").value);
        document.getElementById("result").value = res;
    } catch (e) {
        alert("Invalid Expression");
    }
}
```

---

#### 📄 styles.css

```css
body {
    font-family: Arial, Helvetica, sans-serif;
    background: #f2f2f2;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.calculator {
    background: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px gray;
    text-align: center;
    width: 260px;
}

#result {
    width: 100%;
    height: 50px;
    margin-bottom: 10px;
    font-size: 20px;
    text-align: right;
    padding-right: 10px;
    box-sizing: border-box;
}

.buttons {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 10px;
}

button {
    height: 45px;
    font-size: 18px;
    cursor: pointer;
}
```

---

### 9️⃣ Tested from Browser

Opened:

```
http://<PUBLIC_IP>/calculator
```

Result:

> ✅ Simple Calculator loaded and working on Azure VM via Nginx

This confirmed:

* Static site hosting works
* Nginx is serving real application files
* Cloud VM → Web Server → Browser pipeline is working

---

### 🔟 Accessed VM Using Mobile SSH Client

* Used mobile SSH app
* Connected using:

  * Public IP
  * Username: `pranav`
  * ssh key
* Successfully logged in and verified files and Nginx status

> ✅ VM accessible securely from anywhere

---

## ✅ Day 2 Achievements

* ✔️ Used Azure CLI from Windows CMD
* ✔️ Managed real Azure VM
* ✔️ Installed and configured Nginx
* ✔️ Opened HTTP port using Azure NSG
* ✔️ Deployed a real HTML/CSS/JS web app
* ✔️ Served app via public IP
* ✔️ Accessed VM from laptop and mobile via SSH
* ✔️ Practiced real cloud + Linux + web hosting
* ✔️ Cleaned up resources to save cost

---

## 🎯 What I Learned

* How to host a static website on a cloud VM
* How Nginx serves web content from `/var/www/html`
* How cloud networking (ports, NSG, public IP) works
* How to deploy and test real apps on a server
* How DevOps connects **code + server + cloud**

---
