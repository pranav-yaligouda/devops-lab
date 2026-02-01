
# 🚀 Azure DevOps Practice – Day 2: Nginx Web Server Deployment & Remote Access

## 📌 Overview

This day focuses on:

* Using **Azure CLI from Windows CMD**
* Creating and managing Azure VM resources
* Updating and upgrading Ubuntu server packages
* Installing and configuring **Nginx web server**
* Opening **Port 80** using Azure CLI (NSG rule)
* Verifying web server from browser
* Editing the default Nginx web page
* Accessing the VM using **mobile SSH client**

---

## 🛠️ What I Did (Step-by-Step)

### 1️⃣ Logged into Azure from Windows CMD

```bash
az login
```

Successfully authenticated and selected the subscription.

---

### 2️⃣ Created / Used VM in Existing Resource Group

Used the existing resource group:

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

Login successful to Ubuntu 22.04 server.

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

This updated the Network Security Group (NSG) to allow HTTP traffic.

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

### 8️⃣ Updated Nginx Default Web Page

Edited the default file:

```bash
sudo nano /var/www/html/index.html
```

Replaced content with custom message, for example:

```html
<h1>Deployed by Pranav 🚀</h1>
<p>Day 2: Azure VM + Nginx सफल!</p>
```

Saved and refreshed browser → Custom page displayed ✅

---

### 9️⃣ Accessed VM Using Mobile SSH Client

* Used a mobile SSH client app
* Connected to the same VM using:

  * Public IP
  * Username: `pranav`
  * SSH key / password
* Successfully logged in and verified server access

This proved:

> ✅ VM is accessible from anywhere securely via SSH

---

## 🧹 Cleanup (Cost Saving Step)

After practice, deleted resources:

```bash
az group delete --name devops-practice --yes --no-wait
```

This avoids unnecessary Azure charges.

---

## ✅ Day 2 Achievements

* ✔️ Used Azure CLI from Windows CMD
* ✔️ Deployed and configured Nginx on Ubuntu VM
* ✔️ Opened HTTP port using Azure CLI
* ✔️ Accessed web server from browser
* ✔️ Modified live server web page
* ✔️ Connected to VM from mobile via SSH
* ✔️ Practiced real cloud + real server + real networking
* ✔️ Cleaned up resources to save cost

