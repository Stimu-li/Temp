# College Management System Setup

## Steps to Set Up the Application

1. **Start the Apache and MySQL services.**

2. **Open phpMyAdmin** by navigating to `http://localhost/phpmyadmin` in your browser.

3. **Run the following SQL commands** to create the database and the students table:

   ```sql
   CREATE DATABASE college_management;

   USE college_management;

   CREATE TABLE students (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       email VARCHAR(100) NOT NULL UNIQUE
   );
   ```

4. **Create a new directory for your project**:

   - In the `htdocs` folder (for XAMPP) or the `Applications/MAMP/htdocs` folder (for MAMP), create a directory named `college_management`.

5. **Create the following PHP files in that directory**:

   - **db.php**
   - **add_student.php**
   - **view_students.php**

6. **Open your web browser** and go to `http://localhost/college_management/add_student.php` to add a new student.

7. **After adding students**, navigate to `http://localhost/college_management/view_students.php` to view the list of students.
