package com.example.serviceapp.model;

    public class SuperAdmin extends Employee {

        public SuperAdmin(String phone, String email, String name, String company, String password) {
            super();
            this.setPhone(phone);
            this.setEmail(email);
            this.setName(name);
            this.setCompanyName(company);
            this.setPassword(password);

            this.setStatus("super_admin");
        }

        public SuperAdmin() {
            this.setStatus("super_admin");
        }

        public User createUser() {
            return null;
        }

        public int removeUser() {
            return -1;
        }

        public User editUser() {
            return null;
        }

        public Service createService() {
            return null;
        }

        public int removeService() {
            return -1;
        }

        public Service editService() {
            return null;
        }

        public Admin createAdmin() {
            return null;
        }

        public int removeAdmin() {
            return -1;
        }

        public Admin editAdmin() {
            return null;
        }

    }