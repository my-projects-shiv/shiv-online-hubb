# 🚀 Shiv-Online-Hub Project Deployment Guide

---

# 🚀 Project Architecture

| Component | Server Type  | Technology     |
| --------- | ------------ | -------------- |
| Frontend  | NGINX Server | HTML, CSS, JS  |
| Backend   | Spring Boot  | Java 17, Maven |
| Database  | MySQL Server | MySQL 8+       |

---

# 📅 Step-by-Step Deployment Plan

---

## 🚪 Server 1: Frontend Server (NGINX)

### Install and Configure NGINX

```bash
sudo yum update -y
sudo yum install nginx -y
sudo systemctl start nginx
sudo systemctl enable nginx
```

### Deploy Frontend Code

```bash
cd /usr/share/nginx/html/
sudo rm -rf *
# Upload your frontend static files (index.html, cart.html, css/, js/, images/)
```

### Configure NGINX Proxy to Backend

Edit `/etc/nginx/nginx.conf`:

```nginx
server {
    listen 80;
    server_name <frontend-server-ip>;

    root /usr/share/nginx/html/;
    index index.html;

    location / {
        try_files $uri $uri/ =404;
    }

    location /api/ {
        proxy_pass http://<backend-server-ip>:8080/api/;
    }
}
```

```bash
# Restart NGINX\sudo systemctl restart nginx
```

---

## 🚪 Server 2: Backend Server (Spring Boot)

### Install Java and Maven

```bash
sudo yum install java-17-openjdk -y
sudo yum install maven -y
```

### Deploy Backend Code

```bash
cd /opt/
# Clone your backend project
# git clone <your-github-repo-url>
cd shiv-online-hub-backend

# Build the project
mvn clean install

# Run the application
java -jar target/*.war
# or if jar
java -jar target/*.jar
```

### Configure Backend

In `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://<db-server-ip>:3306/shiv_online_hub
spring.datasource.username=root
spring.datasource.password=yourpassword
```

---

## 🚪 Server 3: Database Server (MySQL)

### Install MySQL

```bash
sudo yum install mysql-server -y
sudo systemctl start mysqld
sudo systemctl enable mysqld
```

### Setup Database and Tables

```bash
mysql -u root -p

CREATE DATABASE shiv_online_hub;
USE shiv_online_hub;

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    image_url VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'Pending',
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```

---

# 🚀 Final Checklist

-

---

# 🌐 Application Access

| Component   | URL                |
| ----------- | ------------------ |
| Frontend    | http\:///          |
| Backend API | http\://:8080/api/ |
| Database    | Internal/private   |

---

# 📗 Notes

- Make sure security groups (firewall) allow necessary ports.
- Use screen or nohup to keep backend running.
- Backup MySQL regularly.

---

# 🛠️ Good Luck with Deployment!

---

Make sure security groups (firewall) allow necessary ports.



Use screen or nohup to keep backend running.



Backup MySQL regularly. \