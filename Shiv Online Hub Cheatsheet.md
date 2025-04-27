# Shiv Online Hub - Full Deployment Cheat Sheet

---

# 📂 Project Structure

```
shiv-online-hub/
|
|├— backend/                  # Spring Boot Backend
|   ├— src/main/java/com/shivonlinehub/dbcomponent/ (controllers, models, services, repositories)
|   ├— src/main/resources/application.properties
|   └— pom.xml
|
|├— frontend/                 # Frontend (Static website)
|   ├— src/main/resources/static/
|       ├— css/
|       ├— js/
|       ├— images/
|       ├— index.html
|       └— cart.html
|
|├— db-configuration/       # Database config files
|   └— DbConfig.java
|
└— SQL Scripts to create DB and Tables
```

---

# 🔨 Step 1: Prepare Red Hat Linux Server

```bash
sudo yum update -y
sudo yum install -y git maven java-17-openjdk nginx mysql-server
```

Start services:

```bash
sudo systemctl start nginx
sudo systemctl enable nginx
sudo systemctl start mysqld
sudo systemctl enable mysqld
```

# 🔨 Step 2: Setup Database (MySQL)

Login to MySQL:

```bash
mysql -u root -p
```

Run this SQL:

```sql
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

Exit MySQL:

```bash
exit
```

# 🔨 Step 3: Backend Setup

```bash
cd backend
mvn clean install
```

Run the Spring Boot app:

```bash
mvn spring-boot:run
```

or

```bash
java -jar target/shiv-online-hub-backend.war
```

App will run at: `http://localhost:8080`

# 🔨 Step 4: Frontend Setup

- Copy your **frontend/src/main/resources/static/** folder contents
- Move them to:

```bash
sudo cp -r * /usr/share/nginx/html/
```

# 🔨 Step 5: Configure NGINX

Edit Nginx config:

```bash
sudo vi /etc/nginx/conf.d/default.conf
```

Paste this:

```nginx
server {
    listen 80;
    server_name your-server-ip;

    root /usr/share/nginx/html;
    index index.html;

    location / {
        try_files $uri $uri/ =404;
    }

    location /api/ {
        proxy_pass http://localhost:8080/api/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

Test config:

```bash
sudo nginx -t
```

Restart Nginx:

```bash
sudo systemctl reload nginx
```

# 🔨 Step 6: Open Firewall (If needed)

```bash
sudo firewall-cmd --permanent --add-service=http
sudo firewall-cmd --permanent --add-service=https
sudo firewall-cmd --reload
```

# 🔨 Step 7: Verify Everything

- Visit `http://your-server-ip`
- You should see Shiv Online Hub Home Page 🚀
- Add products to cart → place order ✔️

---

# 🚀 PRO TIP

If you want to keep backend running forever:

```bash
nohup mvn spring-boot:run &
```

Or better, create a Systemd Service for Spring Boot app later.

---

# 📌 Important Notes

- Your Backend runs on **port 8080**.
- Your Frontend runs through **NGINX on port 80**.
- Frontend API calls automatically forwarded to Backend via `/api/` proxy.

---

# Done ✔️

Now your project is production-ready! 🚀

---

# 📅 Maintenance Commands

| Task                      | Command                                            |
| ------------------------- | -------------------------------------------------- |
| Start Spring Boot Backend | `cd backend && mvn spring-boot:run`                |
| Restart Nginx             | `sudo systemctl restart nginx`                     |
| View logs                 | `tail -f /var/log/nginx/access.log` or `nohup.out` |
| Stop Backend              | `kill -9 <PID>`                                    |

---

# Shiv Online Hub - Deployment Finished 🚀😎🎉

