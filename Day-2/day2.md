# 🚀 Azure DevOps Practice – VM Setup & Linux Server Access

## 📌 Overview

This project documents my hands-on practice with:

- Azure CLI (Web CLI / Cloud Shell)  
- Azure Resource Group & Virtual Machine creation  
- SSH key-based authentication  
- Connecting to Ubuntu Server from Windows  
- Basic Linux server setup  
- Installing DevOps tools (Git, Wget, Curl, Docker)  

---

## 🛠️ What I Did (Step-by-Step)

### 1️⃣ Created Resource Group

```bash
az group create --name devops-practice --location centralindia
````

---

### 2️⃣ Created Ubuntu VM

```bash
az vm create \
  --resource-group devops-practice \
  --name UbuntuVm \
  --image Ubuntu2204 \
  --size Standard_B1s \
  --admin-username pranav \
  --generate-ssh-key
```

This created:

* Ubuntu 22.04 VM
* SSH keys
* Public IP
* Network resources

---

### 3️⃣ Connected to VM using SSH from Windows

```bash
ssh -i C:\Users\Pranav\.ssh\id_rsa pranav@<PUBLIC_IP>
```

After resetting the SSH key from Azure portal, the connection worked successfully.

---

### 4️⃣ Verified Server Access

On successful login, Ubuntu server showed:

* OS: Ubuntu 22.04 LTS
* Running on Azure
* Access via SSH
* Normal user: `pranav`

---

### 5️⃣ Updated System Packages

```bash
sudo apt update
```

---

### 6️⃣ Installed Basic Tools

```bash
sudo apt install -y git curl wget unzip
```

---

### 7️⃣ Installed Docker

```bash
sudo apt install -y docker.io
sudo systemctl start docker
sudo systemctl status docker
```
---
## 🧹 Cleanup (Cost Saving Step)
Deleted all resources using:

```bash
az group delete --name devops-practice --yes --no-wait
```
This avoids unnecessary Azure charges.

---
* ✔️ Real Azure VM created
* ✔️ Real SSH access fixed
* ✔️ Real DevOps environment used
* ✔️ Real cloud cleanup done