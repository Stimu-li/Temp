# Web Service for Collecting Opinions on a Consumer Product

## Setup Instructions

1. **Start XAMPP**:
   - Open the XAMPP Control Panel and start **Apache** and **MySQL**.

2. **Create Database**:
   - Open phpMyAdmin by navigating to `http://localhost/phpmyadmin`.
   - Create a new database named `product_opinions`:
     ```sql
     CREATE DATABASE product_opinions;
     ```
   - Run the following SQL query to create the `opinions` table:
     ```sql
     USE product_opinions;

     CREATE TABLE opinions (
         id INT AUTO_INCREMENT PRIMARY KEY,
         product_name VARCHAR(100),
         opinion TEXT,
         timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
     );
     ```

3. **Set Up Project Files**:
   - Navigate to the `htdocs` directory in your XAMPP installation (usually at `C:\xampp\htdocs`).
   - Create a new folder named `product_opinions`.
   - Create the following files in the `product_opinions` folder:
     - **`index.html`**
     - **`submit_opinion.php`**
     - **`get_opinions.php`**
     - **`db.php`**

4. **Access the Application**:
   - Open your web browser and navigate to `http://localhost/product_opinions/index.html`.
