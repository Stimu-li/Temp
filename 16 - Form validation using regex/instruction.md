- Start the **Apache** and **MySQL** services.

1. **Open phpMyAdmin** by navigating to `http://localhost/phpmyadmin` in your web browser.

   ```sql
   CREATE DATABASE user_data;
   USE user_data;

   CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       email VARCHAR(100) NOT NULL UNIQUE,
       phone VARCHAR(15)
   );
   ```

2. **Create a new directory** for your project in the `htdocs` folder of XAMPP (e.g., `C:\xampp\htdocs\user_form`).

3. **Create a file named `form.html`** and paste the following code into it:

4. **Create another file named `submit_form.php`** in the same directory and paste the following code:

5. **Open your web browser** and navigate to `http://localhost/user_form/form.html`.